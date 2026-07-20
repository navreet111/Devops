/**
 * utils/formatDate.js
 * -----------------------------------------------------------------------
 * Backend ka createdAt field (LocalDateTime) Jackson se aa sakta hai
 * ya to ISO string ("2026-07-16T10:15:30") ya array ([2026,7,16,10,15,30])
 * ke form mein — dono cases handle karta hai.
 */
export function formatDate(value) {
  if (!value) return "—";

  let date;
  if (Array.isArray(value)) {
    const [year, month, day, hour = 0, minute = 0, second = 0] = value;
    date = new Date(year, month - 1, day, hour, minute, second);
  } else {
    date = new Date(value);
  }

  if (isNaN(date.getTime())) return String(value);

  return date.toLocaleString("en-IN", {
    day: "2-digit",
    month: "short",
    year: "numeric",
    hour: "2-digit",
    minute: "2-digit",
  });
}

export default formatDate;