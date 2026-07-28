import axios from "axios";

// Auth-service (Google login) — port 8080
export const BASE_URL = "http://localhost:8080";

// Quantity-service (business APIs) — port 8081
export const QUANTITY_BASE_URL = "http://localhost:8081";
const API_URL = `${QUANTITY_BASE_URL}/api/v1/quantities`;   // <-- yahi line badli hai

const apiClient = axios.create({
  baseURL: API_URL,
  headers: { "Content-Type": "application/json" },
});

apiClient.interceptors.request.use((config) => {
  const token = localStorage.getItem("jwt");
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

// If the token is missing/expired, backend returns 401 -> send user back to login.
apiClient.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response && error.response.status === 401) {
      localStorage.removeItem("jwt");
      window.location.href = "/login";
    }
    return Promise.reject(error);
  }
);

// Builds the QuantityInputDTO shape the backend expects.
function buildInputDTO(thisQuantity, thatQuantity = null, targetQuantity = null) {
  const dto = { thisQuantityDTO: thisQuantity };
  if (thatQuantity) dto.thatQuantityDTO = thatQuantity;
  if (targetQuantity) dto.targetQuantityDTO = targetQuantity;
  return dto;
}

// ============================================================
// Compare
// ============================================================
export async function compareQuantities(thisQuantity, thatQuantity) {
  const res = await apiClient.post("/compare", buildInputDTO(thisQuantity, thatQuantity));
  return res.data;
}

// ============================================================
// Convert
// ============================================================
export async function convertQuantity(thisQuantity, targetQuantity) {
  const res = await apiClient.post("/convert", buildInputDTO(thisQuantity, null, targetQuantity));
  return res.data;
}

// ============================================================
// Add
// ============================================================
export async function addQuantities(thisQuantity, thatQuantity) {
  const res = await apiClient.post("/add", buildInputDTO(thisQuantity, thatQuantity));
  return res.data;
}

// ============================================================
// Subtract (with target unit)
// ============================================================
export async function subtractQuantities(thisQuantity, thatQuantity, targetQuantity) {
  const res = await apiClient.post(
    "/subtract/target",
    buildInputDTO(thisQuantity, thatQuantity, targetQuantity)
  );
  return res.data;
}

// ============================================================
// Divide (with target unit)
// ============================================================
export async function divideQuantities(thisQuantity, thatQuantity) {
  const res = await apiClient.post("/divide/target", buildInputDTO(thisQuantity, thatQuantity));
  return res.data;
}

// ============================================================
// History
// ============================================================
export async function getHistory() {
  const res = await apiClient.get("/history");
  return res.data;
}

export async function getHistoryByOperation(operation) {
  const res = await apiClient.get(`/history/operation/${operation}`);
  return res.data;
}

export async function getHistoryByType(measurementType) {
  const res = await apiClient.get(`/history/type/${measurementType}`);
  return res.data;
}

// ============================================================
// Count
// ============================================================
export async function getRecordCount() {
  const res = await apiClient.get("/count");
  return res.data;
}

// ============================================================
// Delete all history
// ============================================================
export async function deleteHistory() {
  const res = await apiClient.delete("/history");
  return res.data;
}

// ============================================================
// Auth helpers
// ============================================================
export function loginWithGoogle() {
  window.location.href = `${BASE_URL}/api/auth/login`;
}

export function logout() {
    localStorage.removeItem("jwt");
    window.location.href = "/api/auth/login";
}

export function isLoggedIn() {
  return Boolean(localStorage.getItem("jwt"));
}

// Units available per measurement type (matches backend enums).
export const UNITS_BY_TYPE = {
  LENGTH: ["FEET", "INCHES", "YARDS", "CENTIMETERS"],
  WEIGHT: ["GRAM", "KILOGRAM", "TONNE"],
  TEMPERATURE: ["CELSIUS", "FAHRENHEIT"],
  VOLUME: ["LITRE", "MILLILITRE", "GALLON"],
};
//

// Operation types (matches backend OperationType enum) — used for history filter.
export const OPERATION_TYPES = ["COMPARE", "CONVERT", "ADD", "SUBTRACT", "MULTIPLY", "DIVIDE"];