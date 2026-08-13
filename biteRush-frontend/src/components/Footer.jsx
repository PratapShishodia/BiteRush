import { Link } from "react-router-dom";

function Footer() {
  return (
    <footer className="border-t border-gray-200 bg-gray-50 dark:border-gray-800 dark:bg-gray-900">
      <div className="mx-auto max-w-7xl px-4 py-10 sm:px-6 lg:px-8">
        <div className="grid gap-8 sm:grid-cols-2 lg:grid-cols-4">
          {/* Brand */}
          <div>
            <Link to="/" className="text-2xl font-bold text-red-500">
              BiteRush
            </Link>

            <p className="mt-3 max-w-xs text-sm leading-6 text-gray-500 dark:text-gray-400">
              Discover the best food and restaurants around you.
            </p>
          </div>

          {/* Company */}
          <div>
            <h3 className="font-semibold text-gray-900 dark:text-white">
              Company
            </h3>

            <div className="mt-4 flex flex-col gap-3 text-sm text-gray-500 dark:text-gray-400">
              <Link to="/" className="hover:text-red-500">
                Home
              </Link>

              <Link to="/restaurants" className="hover:text-red-500">
                Restaurants
              </Link>

              <Link to="/orders" className="hover:text-red-500">
                Orders
              </Link>
            </div>
          </div>

          {/* Explore */}
          <div>
            <h3 className="font-semibold text-gray-900 dark:text-white">
              Explore
            </h3>

            <div className="mt-4 flex flex-col gap-3 text-sm text-gray-500 dark:text-gray-400">
              <Link to="/search" className="hover:text-red-500">
                Search
              </Link>

              <Link to="/favorites" className="hover:text-red-500">
                Favorites
              </Link>

              <Link to="/profile" className="hover:text-red-500">
                Profile
              </Link>
            </div>
          </div>

          {/* Social */}
          <div>
            <h3 className="font-semibold text-gray-900 dark:text-white">
              Follow us
            </h3>

            <div className="mt-4 flex gap-3">
              <a
                href="https://instagram.com"
                target="_blank"
                rel="noreferrer"
                className="flex h-9 w-9 items-center justify-center rounded-full bg-gray-200 text-sm hover:bg-red-500 hover:text-white dark:bg-gray-800"
              >
                IG
              </a>

              <a
                href="https://facebook.com"
                target="_blank"
                rel="noreferrer"
                className="flex h-9 w-9 items-center justify-center rounded-full bg-gray-200 text-sm hover:bg-red-500 hover:text-white dark:bg-gray-800"
              >
                FB
              </a>

              <a
                href="https://x.com"
                target="_blank"
                rel="noreferrer"
                className="flex h-9 w-9 items-center justify-center rounded-full bg-gray-200 text-sm hover:bg-red-500 hover:text-white dark:bg-gray-800"
              >
                X
              </a>
            </div>
          </div>
        </div>

        <div className="mt-10 border-t border-gray-200 pt-6 text-sm text-gray-500 dark:border-gray-800 dark:text-gray-400">
          © {new Date().getFullYear()} BiteRush. All rights reserved.
        </div>
      </div>
    </footer>
  );
}

export default Footer;
