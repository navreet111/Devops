import { Link, useLocation } from "react-router-dom";
import { logout } from "../services/quantityService";
import useDarkMode from "../hooks/useDarkMode";

export default function Navbar() {
  const location = useLocation();
  const { isDark, toggleDarkMode } = useDarkMode();

  const handleLogout = () => {
    logout();
  };

  return (
    <header className="topbar">
      <div className="topbar-brand">
        <div className="brand-icon">📐</div>
        <div>
          <h1>Quantity Measurement</h1>
          <p>Spring Boot • REST API • React</p>
        </div>
      </div>

      <nav className="nav-links">
        <Link to="/home" className={location.pathname === "/home" ? "active" : ""}>
          Dashboard
        </Link>
        <Link to="/history" className={location.pathname === "/history" ? "active" : ""}>
          History
        </Link>
      </nav>

      <div className="top-actions">
        <button id="themeBtn" onClick={toggleDarkMode} title="Toggle dark mode">
          {isDark ? "☀️ Light" : "🌙 Dark"}
        </button>

      <button id="logoutBtn" onClick={handleLogout}>
          🚪 Logout
      </button>

      </div>
    </header>
  );
}