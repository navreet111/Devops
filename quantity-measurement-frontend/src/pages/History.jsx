import { useEffect, useState } from "react";
import Navbar from "../components/Navbar";
import HistoryTable from "../components/HistoryTable";
import {
  getHistory,
  getHistoryByOperation,
  getHistoryByType,
  getRecordCount,
  deleteHistory,
  OPERATION_TYPES,
  UNITS_BY_TYPE,
} from "../services/quantityService";

export default function History() {
  const [entries, setEntries] = useState([]);
  const [count, setCount] = useState(null);
  const [searchTerm, setSearchTerm] = useState("");
  const [operationFilter, setOperationFilter] = useState("");
  const [typeFilter, setTypeFilter] = useState("");
  const [loading, setLoading] = useState(false);
  const [deleting, setDeleting] = useState(false);
  const [message, setMessage] = useState("");
  const [error, setError] = useState("");

  useEffect(() => {
    fetchData();
  }, [operationFilter, typeFilter]);

  const fetchData = async () => {
    setLoading(true);
    setError("");
    try {
      let data;
      if (operationFilter) {
        data = await getHistoryByOperation(operationFilter);
      } else if (typeFilter) {
        data = await getHistoryByType(typeFilter);
      } else {
        data = await getHistory();
      }
      setEntries(data);

      const countData = await getRecordCount();
      setCount(countData);
    } catch (err) {
      console.error(err);
      setError("Failed to load history.");
    } finally {
      setLoading(false);
    }
  };

  const handleDeleteAll = async () => {
    if (!window.confirm("Delete all history? This cannot be undone.")) return;
    setDeleting(true);
    setMessage("");
    setError("");
    try {
      await deleteHistory();
      setMessage("History deleted successfully.");
      fetchData();
    } catch (err) {
      console.error(err);
      setError("Failed to delete history.");
    } finally {
      setDeleting(false);
    }
  };

  return (
    <div className="container">
      <Navbar />

      <section className="history">
        <div className="history-header">
          <h2>Operation History</h2>
        </div>

        <div className="history-stats">
          <div className="history-stat-card">
            <div className="stat-label">Total Records</div>
            <div className="stat-value">{count ?? "—"}</div>
          </div>
          <div className="history-stat-card">
            <button className="danger" onClick={handleDeleteAll} disabled={deleting}>
              {deleting ? "Deleting..." : "🗑️ Delete All History"}
            </button>
          </div>
        </div>

        {message && <div className="alert alert-success">{message}</div>}
        {error && <div className="alert alert-error">{error}</div>}

        <div className="history-actions">
          <input
            type="text"
            placeholder="Search history..."
            value={searchTerm}
            onChange={(e) => setSearchTerm(e.target.value)}
          />

          <select
            value={operationFilter}
            onChange={(e) => {
              setOperationFilter(e.target.value);
              setTypeFilter("");
            }}
          >
            <option value="">All Operations</option>
            {OPERATION_TYPES.map((op) => (
              <option key={op} value={op}>
                {op}
              </option>
            ))}
          </select>

          <select
            value={typeFilter}
            onChange={(e) => {
              setTypeFilter(e.target.value);
              setOperationFilter("");
            }}
          >
            <option value="">All Types</option>
            {Object.keys(UNITS_BY_TYPE).map((type) => (
              <option key={type} value={type}>
                {type}
              </option>
            ))}
          </select>

          <button onClick={fetchData}>Refresh</button>
        </div>

        {loading ? <p>Loading...</p> : <HistoryTable entries={entries} searchTerm={searchTerm} />}
      </section>
    </div>
  );
}