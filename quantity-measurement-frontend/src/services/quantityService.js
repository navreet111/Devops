import axios from "axios";

// ============================================================
// Backend URLs
// ============================================================

// Auth Service (Google OAuth)
export const BASE_URL =
  "http://ec2-13-49-123-197.eu-north-1.compute.amazonaws.com:8081";

// Quantity Service
export const QUANTITY_BASE_URL =
  "http://ec2-13-49-123-197.eu-north-1.compute.amazonaws.com:8082";

const API_URL = `${QUANTITY_BASE_URL}/api/v1/quantities`;

const apiClient = axios.create({
  baseURL: API_URL,
  headers: {
    "Content-Type": "application/json",
  },
});

// ============================================================
// JWT Interceptor
// ============================================================

apiClient.interceptors.request.use((config) => {
  const token = localStorage.getItem("jwt");

  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }

  return config;
});

// ============================================================
// Handle Unauthorized
// ============================================================

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

// ============================================================
// DTO Builder
// ============================================================

function buildInputDTO(thisQuantity, thatQuantity = null, targetQuantity = null) {
  const dto = {
    thisQuantityDTO: thisQuantity,
  };

  if (thatQuantity) dto.thatQuantityDTO = thatQuantity;
  if (targetQuantity) dto.targetQuantityDTO = targetQuantity;

  return dto;
}

// ============================================================
// Compare
// ============================================================

export async function compareQuantities(thisQuantity, thatQuantity) {
  const res = await apiClient.post(
    "/compare",
    buildInputDTO(thisQuantity, thatQuantity)
  );

  return res.data;
}

// ============================================================
// Convert
// ============================================================

export async function convertQuantity(thisQuantity, targetQuantity) {
  const res = await apiClient.post(
    "/convert",
    buildInputDTO(thisQuantity, null, targetQuantity)
  );

  return res.data;
}

// ============================================================
// Add
// ============================================================

export async function addQuantities(thisQuantity, thatQuantity) {
  const res = await apiClient.post(
    "/add",
    buildInputDTO(thisQuantity, thatQuantity)
  );

  return res.data;
}

// ============================================================
// Subtract
// ============================================================

export async function subtractQuantities(
  thisQuantity,
  thatQuantity,
  targetQuantity
) {
  const res = await apiClient.post(
    "/subtract/target",
    buildInputDTO(thisQuantity, thatQuantity, targetQuantity)
  );

  return res.data;
}

// ============================================================
// Divide
// ============================================================

export async function divideQuantities(thisQuantity, thatQuantity) {
  const res = await apiClient.post(
    "/divide/target",
    buildInputDTO(thisQuantity, thatQuantity)
  );

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
// Delete History
// ============================================================

export async function deleteHistory() {
  const res = await apiClient.delete("/history");
  return res.data;
}

// ============================================================
// Authentication
// ============================================================

export function loginWithGoogle() {
  window.location.href = `${BASE_URL}/api/auth/login`;
}

export function logout() {
  localStorage.removeItem("jwt");
  window.location.href = `${BASE_URL}/api/auth/login`;
}

export function isLoggedIn() {
  return Boolean(localStorage.getItem("jwt"));
}

// ============================================================
// Units
// ============================================================

export const UNITS_BY_TYPE = {
  LENGTH: ["FEET", "INCHES", "YARDS", "CENTIMETERS"],
  WEIGHT: ["GRAM", "KILOGRAM", "TONNE"],
  TEMPERATURE: ["CELSIUS", "FAHRENHEIT"],
  VOLUME: ["LITRE", "MILLILITRE", "GALLON"],
};

// ============================================================
// Operations
// ============================================================

export const OPERATION_TYPES = [
  "COMPARE",
  "CONVERT",
  "ADD",
  "SUBTRACT",
  "MULTIPLY",
  "DIVIDE",
];