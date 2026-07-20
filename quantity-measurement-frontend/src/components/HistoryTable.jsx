import formatDate from "../utils/formatDate";

/**
 * Reusable table used by both Home (recent entries) and History
 * (full list + client-side search).
 */
export default function HistoryTable({ entries = [], searchTerm = "" }) {
  const term = searchTerm.trim().toLowerCase();

  const filtered = term
    ? entries.filter((item) => {
        const haystack = [
          item.operation,
          item.thisQuantity?.measurementType,
          item.result,
          item.errorMessage,
        ]
          .filter(Boolean)
          .join(" ")
          .toLowerCase();
        return haystack.includes(term);
      })
    : entries;

  return (
    <table id="historyTable">
      <thead>
        <tr>
          <th>Date &amp; Time</th>
          <th>Type</th>
          <th>Operation</th>
          <th>Result</th>
        </tr>
      </thead>
      <tbody>
        {filtered.length === 0 ? (
          <tr>
            <td colSpan={4} style={{ textAlign: "center", padding: "20px" }}>
              No history found.
            </td>
          </tr>
        ) : (
          filtered.map((item) => (
            <tr key={item.id}>
              <td className="date-cell">{formatDate(item.createdAt)}</td>
              <td>{item.thisQuantity?.measurementType || "—"}</td>
              <td>
                <span className={`op-badge op-${item.operation?.toLowerCase()}`}>
                  {item.operation}
                </span>
              </td>
              <td>{item.error ? item.errorMessage : item.result}</td>
            </tr>
          ))
        )}
      </tbody>
    </table>
  );
}