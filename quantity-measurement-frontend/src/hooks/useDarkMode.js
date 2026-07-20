import { useEffect, useState } from "react";

const STORAGE_KEY = "theme";

/**
 * hooks/useDarkMode.js
 * -----------------------------------------------------------------------
 * Dark mode state + persistence. Applies/removes a "dark" class on
 * <body>, which the CSS below reacts to.
 */
export function useDarkMode() {
  const [isDark, setIsDark] = useState(() => localStorage.getItem(STORAGE_KEY) === "dark");

  useEffect(() => {
    document.body.classList.toggle("dark", isDark);
    localStorage.setItem(STORAGE_KEY, isDark ? "dark" : "light");
  }, [isDark]);

  const toggleDarkMode = () => setIsDark((prev) => !prev);

  return { isDark, toggleDarkMode };
}

export default useDarkMode;