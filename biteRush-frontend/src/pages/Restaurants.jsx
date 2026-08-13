import { useMemo, useState } from "react";
import { Link, useSearchParams } from "react-router-dom";
import RestaurantCard from "../components/RestaurantCard";
import ErrorState from "../components/ErrorState";

const restaurants = [
  {
    id: 1,
    name: "Burger Singh",
    cuisine: "Burger, Fast Food",
    rating: 4.4,
    time: "25-30 min",
    price: "₹250 for two",
    offer: "30% OFF",
    image: "https://images.unsplash.com/photo-1568901346375-23c9450c58cd?w=800",
  },
  {
    id: 2,
    name: "Biryani Blues",
    cuisine: "Biryani, Mughlai",
    rating: 4.5,
    time: "30-35 min",
    price: "₹400 for two",
    offer: "20% OFF",
    image: "https://images.unsplash.com/photo-1563379091339-03246963d96c?w=800",
  },
  {
    id: 3,
    name: "Pizza Heaven",
    cuisine: "Pizza, Italian",
    rating: 4.2,
    time: "25-30 min",
    price: "₹500 for two",
    offer: "25% OFF",
    image: "https://images.unsplash.com/photo-1579751626657-72bc17010498?w=800",
  },
  {
    id: 4,
    name: "The Chinese Bowl",
    cuisine: "Chinese, Asian",
    rating: 4.3,
    time: "35-40 min",
    price: "₹350 for two",
    offer: "15% OFF",
    image: "https://images.unsplash.com/photo-1563245372-f21724e3856d?w=800",
  },
  {
    id: 5,
    name: "South Spice",
    cuisine: "South Indian",
    rating: 4.4,
    time: "20-25 min",
    price: "₹250 for two",
    offer: "20% OFF",
    image: "https://images.unsplash.com/photo-1589301760014-d929f3979dbc?w=800",
  },
  {
    id: 6,
    name: "Sweet Truth",
    cuisine: "Desserts, Bakery",
    rating: 4.3,
    time: "20-25 min",
    price: "₹300 for two",
    offer: "25% OFF",
    image: "https://images.unsplash.com/photo-1551024506-0bccd828d307?w=800",
  },
  {
    id: 7,
    name: "Rolls Mania",
    cuisine: "Rolls, Fast Food",
    rating: 4.1,
    time: "25-30 min",
    price: "₹250 for two",
    offer: "15% OFF",
    image: "https://images.unsplash.com/photo-1626700051175-6818013e1d4f?w=800",
  },
  {
    id: 8,
    name: "Royal Thali",
    cuisine: "Thali, North Indian",
    rating: 4.5,
    time: "30-35 min",
    price: "₹400 for two",
    offer: "10% OFF",
    image: "https://images.unsplash.com/photo-1546833999-b9f581a1996d?w=800",
  },
];

const cuisines = [
  "All",
  "Pizza",
  "Biryani",
  "Burger",
  "Chinese",
  "Thali",
  "Desserts",
  "South Indian",
  "Rolls",
];

function Restaurants() {
  const [searchParams, setSearchParams] = useSearchParams();

  const cuisineFromUrl = searchParams.get("cuisine") || "All";

  const [activeCuisine, setActiveCuisine] = useState(cuisineFromUrl);

  const [sortBy, setSortBy] = useState("relevance");
  const [error, setError] = useState(false);

  const filteredRestaurants = useMemo(() => {
    let result = restaurants;

    if (activeCuisine !== "All") {
      result = result.filter((restaurant) =>
        restaurant.cuisine.toLowerCase().includes(activeCuisine.toLowerCase()),
      );
    }

    if (sortBy === "rating") {
      result = [...result].sort((a, b) => b.rating - a.rating);
    }

    if (sortBy === "delivery") {
      result = [...result].sort((a, b) => parseInt(a.time) - parseInt(b.time));
    }

    return result;
  }, [activeCuisine, sortBy]);

  const handleCuisine = (cuisine) => {
    setActiveCuisine(cuisine);

    if (cuisine === "All") {
      setSearchParams({});
    } else {
      setSearchParams({ cuisine });
    }
  };

  if (error) {
    return (
      <ErrorState
        title="Restaurants unavailable"
        message="We couldn't load restaurants right now."
        onRetry={() => setError(false)}
      />
    );
  }

  return (
    <div className="min-h-screen bg-gray-50 py-8 dark:bg-gray-950">
      <div className="mx-auto max-w-7xl px-4 sm:px-6 lg:px-8">
        {/* Header */}
        <div>
          <p className="text-sm text-gray-500 dark:text-gray-400">
            Home / Restaurants
          </p>

          <h1 className="mt-2 text-3xl font-bold">Restaurants</h1>

          <p className="mt-2 text-sm text-gray-500 dark:text-gray-400">
            Discover the best restaurants near you.
          </p>
        </div>

        {/* Filters */}
        <div className="mt-8 flex flex-col gap-4 lg:flex-row lg:items-center lg:justify-between">
          {/* Cuisine */}
          <div className="flex gap-2 overflow-x-auto pb-1">
            {cuisines.map((cuisine) => (
              <button
                key={cuisine}
                type="button"
                onClick={() => handleCuisine(cuisine)}
                className={`whitespace-nowrap rounded-full border px-4 py-2 text-sm transition ${
                  activeCuisine === cuisine
                    ? "border-red-500 bg-red-500 text-white"
                    : "border-gray-300 bg-white text-gray-600 hover:border-red-500 hover:text-red-500 dark:border-gray-700 dark:bg-gray-900 dark:text-gray-300"
                }`}
              >
                {cuisine}
              </button>
            ))}
          </div>

          {/* Sort */}
          <select
            value={sortBy}
            onChange={(event) => setSortBy(event.target.value)}
            className="rounded-lg border border-gray-300 bg-white px-4 py-2 text-sm outline-none dark:border-gray-700 dark:bg-gray-900"
          >
            <option value="relevance">Sort: Relevance</option>

            <option value="rating">Sort: Rating</option>

            <option value="delivery">Sort: Delivery time</option>
          </select>
        </div>

        {/* Result count */}
        <div className="mt-8">
          <p className="text-sm text-gray-500 dark:text-gray-400">
            {filteredRestaurants.length} restaurant
            {filteredRestaurants.length !== 1 ? "s" : ""} found
          </p>
        </div>

        {/* Restaurant Grid */}
        {filteredRestaurants.length > 0 ? (
          <div className="mt-5 grid gap-6 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4">
            {filteredRestaurants.map((restaurant) => (
              <RestaurantCard key={restaurant.id} restaurant={restaurant} />
            ))}
          </div>
        ) : (
          <div className="mt-8 rounded-xl border border-dashed border-gray-300 bg-white py-20 text-center dark:border-gray-700 dark:bg-gray-900">
            <div className="text-5xl">🍽️</div>

            <h2 className="mt-5 text-xl font-semibold">No restaurants found</h2>

            <p className="mt-2 text-sm text-gray-500 dark:text-gray-400">
              Try selecting another cuisine.
            </p>

            <button
              type="button"
              onClick={() => handleCuisine("All")}
              className="mt-5 text-sm font-medium text-red-500"
            >
              Show all restaurants
            </button>
          </div>
        )}

        {/* Bottom CTA */}
        <div className="mt-12 text-center">
          <Link
            to="/search"
            className="text-sm font-medium text-red-500 hover:text-red-600"
          >
            Looking for something specific? Search →
          </Link>
        </div>
      </div>
    </div>
  );
}

export default Restaurants;
