import { Link, useLocation } from "react-router-dom";
import { logout } from "../services/quantityService";
import useDarkMode from "../hooks/useDarkMode";
import { useState, useEffect, useRef } from "react";

export default function Navbar() {
  const location = useLocation();
  const { isDark, toggleDarkMode } = useDarkMode();
const [open, setOpen] = useState(false);

const dropdownRef = useRef(null);
const email = localStorage.getItem("email");
const username = localStorage.getItem("username");
const picture = localStorage.getItem("picture");
const [imgError, setImgError] = useState(false);
  const handleLogout = () => {
    logout();
  };
useEffect(() => {

    const handleClickOutside = (event) => {
        if (
            dropdownRef.current &&
            !dropdownRef.current.contains(event.target)
        ) {
            setOpen(false);
        }
    };

    document.addEventListener("mousedown", handleClickOutside);

    return () =>
        document.removeEventListener("mousedown", handleClickOutside);

}, []);
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

      <div className="profile-menu" ref={dropdownRef}>
        <button
          className="profile-btn"
          onClick={() => setOpen(!open)}
        >
          <div className="profile-avatar">
            {picture && !imgError ? (
              <img src={picture} alt="Profile" onError={() => setImgError(true)} />
            ) : (
              username?.charAt(0).toUpperCase()
            )}
          </div>

          <div className="profile-info">
            <span className="profile-name">{username}</span>
            <span className="profile-text">Welcome back!</span>
          </div>

          <span className={`arrow ${open ? "rotate" : ""}`}>▼</span>
        </button>

       {open && (
         <div className="profile-dropdown">

           <div className="dropdown-user">
             <div className="dropdown-avatar">
               {picture && !imgError ? (
                 <img src={picture} alt="Profile" onError={() => setImgError(true)} />
               ) : (
                 username?.charAt(0).toUpperCase()
               )}
             </div>

             <div>
               <h3>{username}</h3>
               <p>{email}</p>
             </div>
           </div>

           <div className="dropdown-divider"></div>

           <div className="profile-details-card">
             <h4>👤 My Profile</h4>

             <div className="profile-row">
               <span className="label">Name</span>
               <span className="value">{username}</span>
             </div>

             <div className="profile-row">
               <span className="label">Email</span>
               <span className="value">{email}</span>
             </div>
           </div>

           <div className="dropdown-divider"></div>

           <button
             className="dropdown-item logout"
             onClick={handleLogout}
           >
             🚪 Logout
           </button>

         </div>
       )}
      </div>
</div>
    </header>
  );
}