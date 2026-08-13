import RestaurantCard from "../components/RestaurantCard";
import { Link } from "react-router-dom";
import { useFavorites } from "../context/FavoritesContext";

function Favorites() {
  const { favorites } = useFavorites();

  return (
    <div className="min-h-screen bg-gray-50 py-8 dark:bg-gray-950">
      <div className="mx-auto max-w-7xl px-4 sm:px-6 lg:px-8">
        <div className="mb-8">
          <p className="text-sm text-gray-500 dark:text-gray-400">
            Home / Favorites
          </p>

          <h1 className="mt-2 text-3xl font-bold">Your Favorites</h1>

          <p className="mt-2 text-sm text-gray-500 dark:text-gray-400">
            Restaurants you've saved for later.
          </p>
        </div>

        {favorites.length > 0 ? (
          <div className="grid gap-6 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4">
            {favorites.map((restaurant) => (
              <RestaurantCard key={restaurant.id} restaurant={restaurant} />
            ))}
          </div>
        ) : (
          <div className="rounded-xl border border-dashed border-gray-300 bg-white py-20 text-center dark:border-gray-700 dark:bg-gray-900">
            <div className="text-5xl">❤️</div>

            <h2 className="mt-5 text-xl font-semibold">No favorites yet</h2>

            <p className="mt-2 text-sm text-gray-500 dark:text-gray-400">
              Save restaurants you love and find them here.
            </p>

            <Link
              to="/restaurants"
              className="mt-6 inline-block rounded-lg bg-red-500 px-5 py-3 text-sm font-semibold text-white hover:bg-red-600"
            >
              Explore restaurants
            </Link>
          </div>
        )}
      </div>
    </div>
  );
}

export default Favorites;
