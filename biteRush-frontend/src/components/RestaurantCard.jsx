import { Link } from "react-router-dom";
import { useFavorites } from "../context/FavoritesContext";

function RestaurantCard({ restaurant }) {
  const { isFavorite, toggleFavorite } = useFavorites();

  const favorite = isFavorite(restaurant.id);

  const handleFavorite = (event) => {
    event.preventDefault();
    event.stopPropagation();

    toggleFavorite(restaurant);
  };

  return (
    <Link
      to={`/restaurant/${restaurant.id}`}
      className="group overflow-hidden rounded-xl border border-gray-200 bg-white transition hover:-translate-y-1 hover:shadow-lg dark:border-gray-800 dark:bg-gray-950"
    >
      {/* Image */}
      <div className="relative aspect-[4/3] overflow-hidden">
        <img
          src={restaurant.image}
          alt={restaurant.name}
          className="h-full w-full object-cover transition duration-300 group-hover:scale-105"
        />

        {restaurant.offer && (
          <span className="absolute bottom-3 left-3 rounded-md bg-white px-2 py-1 text-xs font-medium text-gray-800 shadow">
            {restaurant.offer}
          </span>
        )}

        {/* Favorite */}
        <button
          type="button"
          onClick={handleFavorite}
          aria-label={favorite ? "Remove from favorites" : "Add to favorites"}
          className="absolute right-3 top-3 flex h-9 w-9 items-center justify-center rounded-full bg-white/95 text-lg shadow transition hover:scale-110"
        >
          {favorite ? "❤️" : "🤍"}
        </button>
      </div>

      {/* Content */}
      <div className="p-4">
        <div className="flex items-start justify-between gap-2">
          <h3 className="font-semibold text-gray-900 dark:text-white">
            {restaurant.name}
          </h3>

          <span className="shrink-0 rounded bg-green-600 px-2 py-1 text-xs font-medium text-white">
            ★ {restaurant.rating}
          </span>
        </div>

        <p className="mt-2 text-sm text-gray-500 dark:text-gray-400">
          {restaurant.cuisine}
        </p>

        <div className="mt-3 flex items-center justify-between text-xs text-gray-500 dark:text-gray-400">
          <span>{restaurant.price}</span>
          <span>{restaurant.time}</span>
        </div>
      </div>
    </Link>
  );
}

export default RestaurantCard;
