import { useState } from "react";
import { Link, useSearchParams } from "react-router-dom";

const searchData = [
  {
    id: 1,
    name: "Burger Singh",
    type: "Restaurant",
    cuisine: "Burger, Fast Food",
    rating: 4.4,
    image: "https://images.unsplash.com/photo-1568901346375-23c9450c58cd?w=500",
  },
  {
    id: 2,
    name: "Classic Chicken Burger",
    type: "Dish",
    cuisine: "Burger",
    rating: 4.5,
    price: 199,
    image: "https://images.unsplash.com/photo-1568901346375-23c9450c58cd?w=500",
  },
  {
    id: 3,
    name: "Biryani Blues",
    type: "Restaurant",
    cuisine: "Biryani, Mughlai",
    rating: 4.5,
    image: "https://images.unsplash.com/photo-1563379091339-03246963d96c?w=500",
  },
  {
    id: 4,
    name: "Chicken Biryani",
    type: "Dish",
    cuisine: "Biryani",
    rating: 4.6,
    price: 249,
    image: "https://images.unsplash.com/photo-1563379091339-03246963d96c?w=500",
  },
  {
    id: 5,
    name: "Pizza Heaven",
    type: "Restaurant",
    cuisine: "Pizza, Italian",
    rating: 4.2,
    image: "https://images.unsplash.com/photo-1579751626657-72bc17010498?w=500",
  },
  {
    id: 6,
    name: "Farmhouse Pizza",
    type: "Dish",
    cuisine: "Pizza",
    rating: 4.3,
    price: 299,
    image: "https://images.unsplash.com/photo-1579751626657-72bc17010498?w=500",
  },
  {
    id: 7,
    name: "The Chinese Bowl",
    type: "Restaurant",
    cuisine: "Chinese, Asian",
    rating: 4.3,
    image: "https://images.unsplash.com/photo-1563245372-f21724e3856d?w=500",
  },
];

function Search() {
  const [searchParams, setSearchParams] = useSearchParams();
  const [loading, setLoading] = useState(false);
  const initialQuery = searchParams.get("q") || "";

  const [query, setQuery] = useState(initialQuery);
  const [activeType, setActiveType] = useState("All");

  const handleSearch = (event) => {
    event.preventDefault();

    setLoading(true);

    setTimeout(() => {
      const trimmedQuery = query.trim();

      if (trimmedQuery) {
        setSearchParams({ q: trimmedQuery });
      } else {
        setSearchParams({});
      }

      setLoading(false);
    }, 300);
  };

  const results = searchData.filter((item) => {
    const matchesQuery =
      !query.trim() ||
      item.name.toLowerCase().includes(query.toLowerCase()) ||
      item.cuisine.toLowerCase().includes(query.toLowerCase());

    const matchesType = activeType === "All" || item.type === activeType;

    return matchesQuery && matchesType;
  });

  return (
    <div className="min-h-screen bg-white py-8 text-gray-900 dark:bg-gray-950 dark:text-white">
      <div className="mx-auto max-w-6xl px-4 sm:px-6 lg:px-8">
        {/* Header */}
        <div className="mb-8">
          <p className="text-sm text-gray-500 dark:text-gray-400">
            Home / Search
          </p>

          <h1 className="mt-2 text-3xl font-bold">Search</h1>
        </div>

        {/* Search */}
        <form onSubmit={handleSearch} className="flex max-w-3xl gap-3">
          <div className="flex flex-1 items-center rounded-xl border border-gray-300 bg-white px-4 dark:border-gray-700 dark:bg-gray-900">
            <span className="mr-3">🔍</span>

            <input
              type="text"
              value={query}
              onChange={(event) => setQuery(event.target.value)}
              placeholder="Search restaurant, cuisine or dish"
              className="w-full bg-transparent py-3 text-sm outline-none placeholder:text-gray-400"
            />
          </div>

          <button
            type="submit"
            disabled={loading}
            className="rounded-xl bg-red-500 px-5 text-sm font-semibold text-white hover:bg-red-600 disabled:cursor-not-allowed disabled:opacity-60"
          >
            {loading ? "Searching..." : "Search"}
          </button>
        </form>

        {/* Filters */}
        <div className="mt-6 flex gap-3">
          {["All", "Restaurant", "Dish"].map((type) => (
            <button
              key={type}
              type="button"
              onClick={() => setActiveType(type)}
              className={`rounded-full border px-4 py-2 text-sm transition ${
                activeType === type
                  ? "border-red-500 bg-red-500 text-white"
                  : "border-gray-300 text-gray-600 hover:border-red-500 hover:text-red-500 dark:border-gray-700 dark:text-gray-300"
              }`}
            >
              {type}
            </button>
          ))}
        </div>

        {/* Results */}
        <div className="mt-10">
          <div className="mb-5">
            <h2 className="text-xl font-semibold">
              {query ? `Results for "${query}"` : "Popular searches"}
            </h2>

            <p className="mt-1 text-sm text-gray-500 dark:text-gray-400">
              {results.length} result
              {results.length !== 1 ? "s" : ""} found
            </p>
          </div>

          {results.length > 0 ? (
            <div className="grid gap-5 sm:grid-cols-2 lg:grid-cols-3">
              {results.map((item) => (
                <Link
                  key={`${item.type}-${item.id}`}
                  to={
                    item.type === "Restaurant"
                      ? `/restaurant/${item.id}`
                      : `/restaurant/1`
                  }
                  className="group flex gap-4 rounded-xl border border-gray-200 bg-white p-4 transition hover:-translate-y-0.5 hover:shadow-md dark:border-gray-800 dark:bg-gray-900"
                >
                  <div className="h-20 w-20 shrink-0 overflow-hidden rounded-lg">
                    <img
                      src={item.image}
                      alt={item.name}
                      className="h-full w-full object-cover transition group-hover:scale-105"
                    />
                  </div>

                  <div className="min-w-0">
                    <div className="flex items-center gap-2">
                      <h3 className="truncate font-semibold">{item.name}</h3>

                      <span className="shrink-0 rounded bg-gray-100 px-2 py-0.5 text-[10px] text-gray-500 dark:bg-gray-800 dark:text-gray-400">
                        {item.type}
                      </span>
                    </div>

                    <p className="mt-2 text-sm text-gray-500 dark:text-gray-400">
                      {item.cuisine}
                    </p>

                    <div className="mt-2 flex items-center gap-3 text-xs">
                      <span className="rounded bg-green-600 px-1.5 py-0.5 text-white">
                        ★ {item.rating}
                      </span>

                      {item.price && (
                        <span className="text-gray-500 dark:text-gray-400">
                          ₹{item.price}
                        </span>
                      )}
                    </div>
                  </div>
                </Link>
              ))}
            </div>
          ) : (
            <div className="rounded-xl border border-dashed border-gray-300 py-16 text-center dark:border-gray-700">
              <div className="text-4xl">🔎</div>

              <h2 className="mt-4 font-semibold">No results found</h2>

              <p className="mt-2 text-sm text-gray-500 dark:text-gray-400">
                Try searching for another restaurant or dish.
              </p>

              <button
                type="button"
                onClick={() => {
                  setQuery("");
                  setSearchParams({});
                }}
                className="mt-5 text-sm font-medium text-red-500"
              >
                Clear search
              </button>
            </div>
          )}
        </div>
      </div>
    </div>
  );
}

export default Search;
