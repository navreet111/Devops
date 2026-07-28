# Quantity-Measurement — Monolith → Microservices

Original monolithic Spring Boot app ko **2 microservices** mein split kiya gaya hai:

1. **auth-service** — sirf Google OAuth2 login + JWT issue karna
2. **quantity-service** — saara business logic (unit conversion, add/subtract/divide/compare, history, DB)

Frontend (`quantity-measurement-frontend`) **bilkul unchanged** rakha gaya hai — ek line bhi edit nahi hui.

---

## Naya folder structure

```
Quantity-Measurement-Microservices/
├── auth-service/                     <- NEW microservice
│   ├── pom.xml
│   └── src/main/java/com/apps/quantitymeasurement/
│       ├── app/AuthServiceApp.java           (NEW - main class)
│       ├── config/SecurityConfig.java        (NEW - trimmed, only OAuth2 flow)
│       ├── controller/AuthController.java    (COPIED unchanged)
│       └── security/
│           ├── JwtService.java                (COPIED unchanged)
│           ├── CustomOAuth2UserService.java    (COPIED unchanged)
│           ├── OAuth2LoginSuccessHandler.java  (COPIED unchanged)
│           └── JwtAuthenticationEntryPoint.java(COPIED unchanged)
│   └── src/main/resources/application.properties   (NEW - port 8080, oauth+jwt config only)
│
├── quantity-service/                 <- NEW microservice
│   ├── pom.xml
│   └── src/main/java/com/apps/quantitymeasurement/
│       ├── app/QuantityMeasurementApp.java     (COPIED, unrelated OAuth wiring removed)
│       ├── Quantity.java, Length*.java, Weight*.java,
│       │   VolumeUnit.java, TemperatureUnit.java,
│       │   IMeasurable.java, SupportsArithmetic.java   (COPIED unchanged - pure domain logic)
│       ├── config/SwaggerConfig.java           (COPIED unchanged)
│       ├── config/SecurityConfig.java          (NEW - trimmed, only JWT validation, no OAuth2)
│       ├── controller/QuantityMeasurementController.java (COPIED unchanged)
│       ├── dto/* (COPIED unchanged)
│       ├── entity/* (COPIED unchanged)
│       ├── repository/* (COPIED unchanged)
│       ├── service/* (COPIED unchanged)
│       ├── exception/* (COPIED unchanged)
│       ├── util/* (COPIED unchanged)
│       └── security/
│           ├── JwtService.java              (COPIED unchanged - validates tokens)
│           └── JwtAuthenticationFilter.java (COPIED unchanged)
│           └── JwtAuthenticationEntryPoint.java (COPIED unchanged)
│   └── src/main/resources/application.properties  (NEW - port 8081, DB+swagger config, same JWT secret)
│   └── src/main/resources/db/schema.sql      (COPIED unchanged)
│
└── quantity-measurement-frontend/    <- UNCHANGED, copied as-is (node_modules excluded, run `npm install`)
```

> Note: `.idea/`, `target/`, `.class` build artifacts, `.git`, and the DB `.mv.db`/`.trace.db` files
> from the original zip were not carried over (build/runtime junk, regenerate automatically).
> Test files (`src/test/...`) bhi migrate nahi kiye — inhe dono services mein manually split karna hoga
> (auth tests → auth-service, baaki sab → quantity-service) kyunki wo abhi bhi original monolith package
> structure assume karte hain.

---

## Kis file mein kya change hua

| File | Service | Kya hua |
|---|---|---|
| `AuthServiceApp.java` | auth-service | **NEW** main class — sirf `@SpringBootApplication`, no JPA (auth-service mein DB nahi hai) |
| `QuantityMeasurementApp.java` | quantity-service | Copied — same class/package rakha, kewal OAuth2-specific annotations kabhi thi hi nahi isliye kuch hataya nahi |
| `config/SecurityConfig.java` (auth-service) | auth-service | **NEW/trimmed version** — sirf `oauth2Login()` + permitAll paths (`/api/auth/**`, `/oauth2/**`). `JwtAuthenticationFilter` yahan se hataya (auth-service khud koi protected resource serve nahi karta) |
| `config/SecurityConfig.java` (quantity-service) | quantity-service | **NEW/trimmed version** — sirf `JwtAuthenticationFilter` + `anyRequest().authenticated()`. `oauth2Login()`, `CustomOAuth2UserService`, `successHandler` sab hata diya (ye ab auth-service ki responsibility hai) |
| `AuthController.java` | auth-service | Copied **unchanged** (same code, same endpoints: `/`, `/api/auth/login`, `/login-success`, `/logout`) |
| `QuantityMeasurementController.java` | quantity-service | Copied **unchanged** (saare `/api/v1/quantities/**` endpoints same) |
| `JwtService.java` | dono services mein | Copied **unchanged** as-is in both — same secret key (`app.jwt.secret`) use hota hai dono jagah, isliye auth-service se issue hua token quantity-service directly (bina kisi extra network call ke) verify kar leta hai — standard stateless-JWT microservice pattern |
| `JwtAuthenticationFilter.java` | quantity-service | Copied unchanged — ab sirf yahi service isse use karti hai |
| `CustomOAuth2UserService.java`, `OAuth2LoginSuccessHandler.java` | auth-service | Copied unchanged — sirf auth-service mein rakhe (quantity-service ko inki zaroorat nahi) |
| `Quantity.java`, `Length*.java`, `Weight*.java`, `VolumeUnit.java`, `TemperatureUnit.java`, `IMeasurable.java`, `SupportsArithmetic.java` | quantity-service | Copied **byte-for-byte unchanged** — koi logic touch nahi hua |
| `dto/*`, `entity/*`, `repository/*`, `service/*`, `exception/*`, `util/*` | quantity-service | Copied **unchanged** |
| `pom.xml` (auth-service) | auth-service | **NEW** — sirf `spring-boot-starter-web`, `spring-boot-starter-oauth2-client`, `jjwt-*`, `spring-dotenv`. JPA/H2/Postgres/Swagger/Lombok/Mockito **hata diye** (auth-service ko inki zaroorat nahi) |
| `pom.xml` (quantity-service) | quantity-service | **NEW** — original pom se `spring-boot-starter-oauth2-client` aur `spring-dotenv` hataye, `spring-boot-starter-security` explicitly add kiya (pehle ye oauth2-client ke through transitively aata tha) |
| `application.properties` (auth-service) | auth-service | **NEW** — port `8080`, sirf `google.client-id/secret`, `app.jwt.secret`, `app.jwt.expiration`. DB/Swagger config hataya |
| `application.properties` (quantity-service) | quantity-service | **NEW** — port `8081`, DB (H2 file), Swagger paths, same `app.jwt.secret`. Google OAuth2 config hataya |
| `quantity-measurement-frontend/**` | — | **Koi change nahi** — as-is copy |

---

## ⚠️ Ek zaroori infra note (logic change nahi, sirf config)

Frontend ki `quantityService.js` mein ek hi `BASE_URL = "http://localhost:8080"` hai jo **dono** kaam ke liye use hota hai:
- `loginWithGoogle()` → `${BASE_URL}/api/auth/login`
- Saari quantity APIs → `${BASE_URL}/api/v1/quantities/...`

Ab jab do alag services alag ports par chalengi (auth-service `:8080`, quantity-service `:8081`),
to ek hi `BASE_URL` dono ko nahi cover kar sakta — ye sirf port ki wajah se hai, kisi bhi service ke logic ki wajah se nahi.

Aapne bola frontend mat badlo, isliye maine **koi frontend file touch nahi ki**. Real deployment ke liye
aapko in mein se koi ek karna hoga (dono chhoti, non-logic cheezein hain):

1. **Simplest**: `quantityService.js` mein ek dusra constant `QUANTITY_BASE_URL="http://localhost:8081"` add karke sirf quantity-wale API calls usse point karo (login wala `BASE_URL` `:8080` hi rahega — Google OAuth redirect URI console mein registered hai, wo change nahi karna padega).
2. **Production-style**: Ek API Gateway / Nginx reverse proxy `:8080` par lagao jo `/api/auth/**` ko auth-service aur `/api/v1/quantities/**` ko quantity-service par forward kare — tab frontend ka ek `BASE_URL` bina kisi change ke chalega.

---

## Run karne ka tareeka

```bash
# Terminal 1 - auth-service
cd auth-service
./mvnw spring-boot:run     # runs on :8080

# Terminal 2 - quantity-service
cd quantity-service
./mvnw spring-boot:run     # runs on :8081

# Terminal 3 - frontend
cd quantity-measurement-frontend
npm install
npm run dev                # runs on :5173
```

`.env` (auth-service ke root mein) mein `GOOGLE_CLIENT_ID` aur `GOOGLE_CLIENT_SECRET` set karna na bhoolein.
