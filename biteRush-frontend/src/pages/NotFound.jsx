import { Link } from "react-router-dom";

function NotFound() {
  return (
    <div className="flex min-h-[70vh] items-center justify-center bg-white px-4 text-center dark:bg-gray-950">
      <div>
        <p className="text-7xl font-bold text-red-500">404</p>

        <h1 className="mt-5 text-2xl font-bold">Page not found</h1>

        <p className="mt-2 max-w-md text-sm text-gray-500 dark:text-gray-400">
          Sorry, we couldn't find the page you're looking for.
        </p>

        <div className="mt-6 flex justify-center gap-3">
          <Link
            to="/"
            className="rounded-lg bg-red-500 px-5 py-3 text-sm font-semibold text-white hover:bg-red-600"
          >
            Go Home
          </Link>

          <Link
            to="/restaurants"
            className="rounded-lg border border-gray-300 px-5 py-3 text-sm font-medium hover:border-red-500 hover:text-red-500 dark:border-gray-700"
          >
            Explore Restaurants
          </Link>
        </div>
      </div>
    </div>
  );
}

export default NotFound;
