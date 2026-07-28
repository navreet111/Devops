import { useEffect } from "react";
import { useNavigate, useSearchParams } from "react-router-dom";
import { loginWithGoogle } from "../services/quantityService";
import useDarkMode from "../hooks/useDarkMode";

const FEATURES = [
  "Compare quantities across units instantly",
  "Convert length, weight, temperature & volume",
  "Add, subtract, and divide with unit precision",
  "Full operation history, searchable and filterable",
];

/**
 * Combined landing + login screen, split into two panels:
 *  - Left: branding / product pitch (static, no auth needed)
 *  - Right: the actual sign-in action
 * Also handles the ?token=... redirect from the backend after a
 * successful Google login (same as before).
 */
export default function Login() {
  const [searchParams] = useSearchParams();
  const navigate = useNavigate();
  const { isDark, toggleDarkMode } = useDarkMode();

useEffect(() => {
    const token = searchParams.get("token");
    const name = searchParams.get("name");
    const picture = searchParams.get("picture");
const email = searchParams.get("email");

if (email) {
    localStorage.setItem("email", decodeURIComponent(email));
}
    if (token) {
        localStorage.setItem("jwt", token);

        if (name) {
            localStorage.setItem("username", decodeURIComponent(name));
        }

        if (picture) {
            localStorage.setItem("picture", decodeURIComponent(picture));
        }

        navigate("/home", { replace: true });
    }
}, [searchParams, navigate]);

  const token = searchParams.get("token");

  return (
    <div className="split-page">
      {/* ============ LEFT PANEL — branding ============ */}
      <div className="split-left">
        <div className="split-left-top">
          <div className="split-brand">
            <span className="split-brand-mark">QM</span>
            <span>QuantityMeasurement</span>
          </div>

        </div>

        <div className="split-left-body">
          <h1>
            Measure, convert &amp; compare
            <br />
            with precision.
          </h1>
          <p>
            A full-stack quantity measurement system built for accuracy —
            track every calculation, filter by type, and never lose a
            record.
          </p>

          <ul className="split-feature-list">
            {FEATURES.map((f) => (
              <li key={f}>
                <span className="check-mark">✓</span>
                {f}
              </li>
            ))}
          </ul>
        </div>


      </div>

      {/* ============ RIGHT PANEL — sign in ============ */}
      <div className="split-right">
        <button
          type="button"
          className="split-theme-toggle"
          onClick={toggleDarkMode}
          title="Toggle dark mode"
        >
          {isDark ? "☀️" : "🌙"}
        </button>

        <div className="split-right-inner">
          {token ? (
            <>
              <h2>Signing you in…</h2>
              <div className="spinner" />
            </>
          ) : (
            <>
              <h2>Sign in</h2>
              <p className="split-right-subtitle">
                Use your Google account to access the dashboard.
              </p>

              <button className="google-btn" onClick={loginWithGoogle}>
                <svg className="google-icon" viewBox="0 0 48 48" aria-hidden="true">
                  <path
                    fill="#FFC107"
                    d="M43.6 20.5H42V20H24v8h11.3c-1.6 4.7-6.1 8-11.3 8-6.6 0-12-5.4-12-12s5.4-12 12-12c3.1 0 5.9 1.2 8 3.1l5.7-5.7C34.6 6.1 29.6 4 24 4 12.9 4 4 12.9 4 24s8.9 20 20 20 20-8.9 20-20c0-1.3-.1-2.7-.4-3.5z"
                  />
                  <path
                    fill="#FF3D00"
                    d="M6.3 14.7l6.6 4.8C14.5 16 18.9 13 24 13c3.1 0 5.9 1.2 8 3.1l5.7-5.7C34.6 6.1 29.6 4 24 4c-7.6 0-14.1 4.3-17.7 10.7z"
                  />
                  <path
                    fill="#4CAF50"
                    d="M24 44c5.5 0 10.4-1.9 14.3-5.1l-6.6-5.6C29.6 34.9 26.9 36 24 36c-5.2 0-9.7-3.3-11.3-8l-6.5 5C9.8 39.6 16.3 44 24 44z"
                  />
                  <path
                    fill="#1976D2"
                    d="M43.6 20.5H42V20H24v8h11.3c-.8 2.3-2.2 4.2-4.1 5.6l6.6 5.6C41.5 35.9 44 30.4 44 24c0-1.3-.1-2.7-.4-3.5z"
                  />
                </svg>
                Continue with Google
              </button>

              <div className="split-divider">
                <span>New here?</span>
              </div>
              <p className="split-footnote">
                Signing in with Google creates your account automatically —
                no separate sign-up needed.
              </p>
            </>
          )}
        </div>
      </div>
    </div>
  );
}