import { Link } from "react-router-dom";
import { useAuth } from "../context/AuthContext";

function Profile() {
  const { user, logout } = useAuth();
  return (
    <div className="min-h-screen bg-gray-50 py-8 dark:bg-gray-950">
      <div className="mx-auto max-w-5xl px-4 sm:px-6 lg:px-8">
        <div className="mb-8">
          <p className="text-sm text-gray-500 dark:text-gray-400">
            Home / Profile
          </p>

          <h1 className="mt-2 text-3xl font-bold">My Profile</h1>
        </div>

        <div className="grid gap-6 lg:grid-cols-[280px_1fr]">
          {/* Profile Card */}
          <div className="h-fit rounded-xl border border-gray-200 bg-white p-6 dark:border-gray-800 dark:bg-gray-900">
            <div className="flex flex-col items-center text-center">
              <div className="flex h-20 w-20 items-center justify-center rounded-full bg-red-100 text-3xl dark:bg-red-950">
                👤
              </div>

              <h2 className="mt-4 text-lg font-semibold">
                {user?.name || "User"}
              </h2>

              <p className="mt-1 text-sm text-gray-500 dark:text-gray-400">
                {user?.email || "user@example.com"}
              </p>

              <button
                type="button"
                onClick={() => alert("Edit profile coming soon")}
                className="mt-5 rounded-lg border border-gray-300 px-4 py-2 text-sm font-medium hover:border-red-500 hover:text-red-500 dark:border-gray-700"
              >
                Edit Profile
              </button>
            </div>
          </div>

          {/* Profile Options */}
          <div className="space-y-4">
            <Link
              to="/orders"
              className="flex items-center justify-between rounded-xl border border-gray-200 bg-white p-5 transition hover:border-red-300 dark:border-gray-800 dark:bg-gray-900"
            >
              <div>
                <h3 className="font-semibold">My Orders</h3>

                <p className="mt-1 text-sm text-gray-500 dark:text-gray-400">
                  View your order history and track active orders.
                </p>
              </div>

              <span className="text-gray-400">→</span>
            </Link>

            <Link
              to="/favorites"
              className="flex items-center justify-between rounded-xl border border-gray-200 bg-white p-5 transition hover:border-red-300 dark:border-gray-800 dark:bg-gray-900"
            >
              <div>
                <h3 className="font-semibold">Favorites</h3>

                <p className="mt-1 text-sm text-gray-500 dark:text-gray-400">
                  Restaurants you've saved.
                </p>
              </div>

              <span className="text-gray-400">→</span>
            </Link>

            <button
              type="button"
              onClick={() => alert("Address management coming soon")}
              className="flex w-full items-center justify-between rounded-xl border border-gray-200 bg-white p-5 text-left transition hover:border-red-300 dark:border-gray-800 dark:bg-gray-900"
            >
              <div>
                <h3 className="font-semibold">Saved Addresses</h3>

                <p className="mt-1 text-sm text-gray-500 dark:text-gray-400">
                  Manage your delivery addresses.
                </p>
              </div>

              <span className="text-gray-400">→</span>
            </button>

            <button
              type="button"
              onClick={logout}
              className="w-full rounded-xl border border-red-200 bg-white p-5 text-left font-medium text-red-500 hover:bg-red-50 dark:border-red-950 dark:bg-gray-900 dark:hover:bg-red-950/30"
            >
              Logout
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Profile;
