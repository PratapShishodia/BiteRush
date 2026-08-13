import { Link } from "react-router-dom";
import { useState } from "react";
import { useCart } from "../context/CartContext";
import { useAuth } from "../context/AuthContext";

const locations = ["Ghaziabad", "Noida", "Delhi", "Gurugram", "Greater Noida"];

function Navbar() {
  const [darkMode, setDarkMode] = useState(
    document.documentElement.classList.contains("dark"),
  );
  const { user, isLoggedIn, logout } = useAuth();
  const [menuOpen, setMenuOpen] = useState(false);
  const [locationOpen, setLocationOpen] = useState(false);
  const [location, setLocation] = useState("Ghaziabad");

  const { cartCount } = useCart();

  const toggleTheme = () => {
    setDarkMode((current) => !current);
    document.documentElement.classList.toggle("dark");
  };

  const selectLocation = (selectedLocation) => {
    setLocation(selectedLocation);
    setLocationOpen(false);
  };

  const closeMenu = () => {
    setMenuOpen(false);
  };

  return (
    <>
      <nav className="sticky top-0 z-50 border-b border-gray-200 bg-white dark:border-gray-800 dark:bg-gray-950">
        <div className="mx-auto flex h-16 max-w-7xl items-center gap-4 px-4 sm:px-6 lg:px-8">
          {/* Logo */}
          <Link
            to="/"
            onClick={closeMenu}
            className="shrink-0 text-2xl font-bold text-red-500"
          >
            BiteRush
          </Link>

          {/* Desktop Location */}
          <button
            type="button"
            onClick={() => setLocationOpen(true)}
            className="hidden items-center gap-2 text-sm text-gray-600 hover:text-red-500 dark:text-gray-300 md:flex"
          >
            <span>📍</span>
            <span>{location}</span>
            <span>⌄</span>
          </button>

          {/* Search */}
          <Link
            to="/search"
            onClick={closeMenu}
            className="flex min-w-0 flex-1 items-center gap-3 rounded-lg bg-gray-100 px-4 py-2.5 text-sm text-gray-500 transition hover:bg-gray-200 dark:bg-gray-900 dark:text-gray-400 dark:hover:bg-gray-800"
          >
            <span>🔍</span>

            <span className="truncate">Search for restaurant or dish</span>
          </Link>

          {/* Desktop actions */}
          <div className="hidden items-center gap-5 md:flex">
            <Link
              to="/orders"
              className="text-sm text-gray-700 hover:text-red-500 dark:text-gray-300"
            >
              Orders
            </Link>

            <Link
              to="/favorites"
              className="text-sm text-gray-700 hover:text-red-500 dark:text-gray-300"
            >
              Favorites
            </Link>

            {isLoggedIn ? (
              <Link
                to="/profile"
                className="text-sm text-gray-700 hover:text-red-500 dark:text-gray-300"
              >
                {user.name}
              </Link>
            ) : (
              <Link
                to="/login"
                className="text-sm text-gray-700 hover:text-red-500 dark:text-gray-300"
              >
                Login
              </Link>
            )}

            <Link to="/cart" className="relative text-xl" aria-label="Cart">
              🛒
              {cartCount > 0 && (
                <span className="absolute -right-2 -top-2 flex h-5 min-w-5 items-center justify-center rounded-full bg-red-500 px-1 text-[10px] font-bold text-white">
                  {cartCount}
                </span>
              )}
            </Link>

            <button
              type="button"
              onClick={toggleTheme}
              className="text-xl"
              aria-label="Toggle theme"
            >
              {darkMode ? "☀️" : "🌙"}
            </button>
          </div>

          {/* Mobile actions */}
          <div className="flex items-center gap-3 md:hidden">
            <Link to="/cart" className="relative text-xl" aria-label="Cart">
              🛒
              {cartCount > 0 && (
                <span className="absolute -right-2 -top-2 flex h-5 min-w-5 items-center justify-center rounded-full bg-red-500 px-1 text-[10px] font-bold text-white">
                  {cartCount}
                </span>
              )}
            </Link>

            <button
              type="button"
              onClick={toggleTheme}
              className="text-xl"
              aria-label="Toggle theme"
            >
              {darkMode ? "☀️" : "🌙"}
            </button>

            <button
              type="button"
              onClick={() => setMenuOpen((current) => !current)}
              className="flex h-9 w-9 items-center justify-center rounded-lg border border-gray-300 text-lg dark:border-gray-700"
              aria-label="Toggle menu"
            >
              {menuOpen ? "✕" : "☰"}
            </button>
          </div>
        </div>

        {/* Mobile menu */}
        {menuOpen && (
          <div className="border-t border-gray-200 bg-white dark:border-gray-800 dark:bg-gray-950 md:hidden">
            <div className="mx-auto max-w-7xl px-4 py-4 sm:px-6">
              <button
                type="button"
                onClick={() => setLocationOpen(true)}
                className="mb-3 flex w-full items-center gap-3 rounded-lg bg-gray-100 px-4 py-3 text-left text-sm dark:bg-gray-900"
              >
                <span>📍</span>

                <div>
                  <p className="font-medium">{location}</p>

                  <p className="text-xs text-gray-500 dark:text-gray-400">
                    Select delivery location
                  </p>
                </div>
              </button>

              <div className="flex flex-col">
                <Link
                  to="/orders"
                  onClick={closeMenu}
                  className="border-b border-gray-100 py-4 text-sm dark:border-gray-800"
                >
                  My Orders
                </Link>

                <Link
                  to="/favorites"
                  onClick={closeMenu}
                  className="border-b border-gray-100 py-4 text-sm dark:border-gray-800"
                >
                  Favorites
                </Link>

                {isLoggedIn ? (
                  <>
                    <Link
                      to="/profile"
                      onClick={closeMenu}
                      className="border-b border-gray-100 py-4 text-sm dark:border-gray-800"
                    >
                      {user.name}
                    </Link>

                    <button
                      type="button"
                      onClick={() => {
                        logout();
                        closeMenu();
                      }}
                      className="border-b border-gray-100 py-4 text-left text-sm text-red-500 dark:border-gray-800"
                    >
                      Logout
                    </button>
                  </>
                ) : (
                  <Link
                    to="/login"
                    onClick={closeMenu}
                    className="border-b border-gray-100 py-4 text-sm dark:border-gray-800"
                  >
                    Login
                  </Link>
                )}

                <Link
                  to="/login"
                  onClick={closeMenu}
                  className="border-b border-gray-100 py-4 text-sm dark:border-gray-800"
                >
                  Login
                </Link>

                <Link
                  to="/restaurants"
                  onClick={closeMenu}
                  className="py-4 text-sm font-medium text-red-500"
                >
                  Explore Restaurants →
                </Link>
              </div>
            </div>
          </div>
        )}
      </nav>

      {/* Location Modal */}
      {locationOpen && (
        <div
          className="fixed inset-0 z-[60] flex items-end justify-center bg-black/50 p-4 sm:items-center"
          onClick={() => setLocationOpen(false)}
        >
          <div
            className="w-full max-w-md rounded-2xl bg-white p-5 dark:bg-gray-900"
            onClick={(event) => event.stopPropagation()}
          >
            <div className="flex items-center justify-between">
              <div>
                <h2 className="text-lg font-semibold">Select location</h2>

                <p className="mt-1 text-sm text-gray-500 dark:text-gray-400">
                  Choose where you want your food delivered.
                </p>
              </div>

              <button
                type="button"
                onClick={() => setLocationOpen(false)}
                className="text-xl text-gray-400 hover:text-gray-700 dark:hover:text-white"
                aria-label="Close"
              >
                ✕
              </button>
            </div>

            {/* Current location */}
            <button
              type="button"
              onClick={() => {
                setLocation("Current Location");
                setLocationOpen(false);
              }}
              className="mt-5 flex w-full items-center gap-3 rounded-lg border border-red-200 bg-red-50 p-4 text-left text-red-600 dark:border-red-900 dark:bg-red-950/30"
            >
              <span>📍</span>

              <div>
                <p className="font-medium">Use current location</p>

                <p className="mt-1 text-xs text-red-500/70">
                  Use your device location
                </p>
              </div>
            </button>

            {/* Locations */}
            <div className="mt-4">
              <p className="mb-2 text-xs font-medium uppercase tracking-wide text-gray-400">
                Popular locations
              </p>

              <div className="divide-y divide-gray-100 dark:divide-gray-800">
                {locations.map((item) => (
                  <button
                    key={item}
                    type="button"
                    onClick={() => selectLocation(item)}
                    className="flex w-full items-center gap-3 py-3 text-left text-sm hover:text-red-500"
                  >
                    <span>📍</span>

                    <span>{item}</span>

                    {location === item && (
                      <span className="ml-auto text-red-500">✓</span>
                    )}
                  </button>
                ))}
              </div>
            </div>
          </div>
        </div>
      )}
    </>
  );
}

export default Navbar;
