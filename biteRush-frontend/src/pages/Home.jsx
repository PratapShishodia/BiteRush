import { useState } from "react";
import { Link } from "react-router-dom";
import RestaurantCard from "../components/RestaurantCard";

const categories = [
  {
    name: "Pizza",
    image: "https://images.unsplash.com/photo-1579751626657-72bc17010498?w=400",
  },
  {
    name: "Biryani",
    image: "https://images.unsplash.com/photo-1563379091339-03246963d96c?w=400",
  },
  {
    name: "Burger",
    image: "https://images.unsplash.com/photo-1568901346375-23c9450c58cd?w=400",
  },
  {
    name: "Chinese",
    image: "https://images.unsplash.com/photo-1563245372-f21724e3856d?w=400",
  },
  {
    name: "Thali",
    image: "https://images.unsplash.com/photo-1546833999-b9f581a1996d?w=400",
  },
  {
    name: "Desserts",
    image: "https://images.unsplash.com/photo-1551024506-0bccd828d307?w=400",
  },
  {
    name: "South Indian",
    image: "https://images.unsplash.com/photo-1589301760014-d929f3979dbc?w=400",
  },
  {
    name: "Rolls",
    image: "https://images.unsplash.com/photo-1626700051175-6818013e1d4f?w=400",
  },
];

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
];

const diningRestaurants = [
  {
    id: 5,
    name: "The Terrace",
    cuisine: "North Indian, Continental",
    rating: 4.6,
    time: "15 min",
    price: "₹1,200 for two",
    offer: "20% OFF",
    image: "https://images.unsplash.com/photo-1517248135467-4c7edcad34c4?w=800",
  },
  {
    id: 6,
    name: "Urban Fork",
    cuisine: "Italian, Continental",
    rating: 4.4,
    time: "20 min",
    price: "₹1,000 for two",
    offer: "15% OFF",
    image: "https://images.unsplash.com/photo-1552566626-52f8b828add9?w=800",
  },
  {
    id: 7,
    name: "Spice Route",
    cuisine: "Asian, Indian",
    rating: 4.5,
    time: "18 min",
    price: "₹900 for two",
    offer: "25% OFF",
    image: "https://images.unsplash.com/photo-1559339352-11d035aa65de?w=800",
  },
  {
    id: 8,
    name: "The Food Yard",
    cuisine: "Multi Cuisine",
    rating: 4.3,
    time: "22 min",
    price: "₹800 for two",
    offer: "10% OFF",
    image: "https://images.unsplash.com/photo-1515003197210-e0cd71810b5f?w=800",
  },
];

const nightlifeRestaurants = [
  {
    id: 9,
    name: "Sky Lounge",
    cuisine: "Bar, Continental",
    rating: 4.5,
    time: "10 min",
    price: "₹1,500 for two",
    offer: "Happy Hours",
    image: "https://images.unsplash.com/photo-1514933651103-005eec06c04b?w=800",
  },
  {
    id: 10,
    name: "The Social House",
    cuisine: "Bar Food, Asian",
    rating: 4.4,
    time: "15 min",
    price: "₹1,300 for two",
    offer: "20% OFF",
    image: "https://images.unsplash.com/photo-1519671482749-fd09be7ccebf?w=800",
  },
  {
    id: 11,
    name: "High Street",
    cuisine: "Pub, Fast Food",
    rating: 4.2,
    time: "18 min",
    price: "₹1,400 for two",
    offer: "Live Music",
    image: "https://images.unsplash.com/photo-1572116469696-31de0f17cc34?w=800",
  },
  {
    id: 12,
    name: "Moonlight",
    cuisine: "Lounge, Asian",
    rating: 4.6,
    time: "20 min",
    price: "₹1,600 for two",
    offer: "DJ Night",
    image: "https://images.unsplash.com/photo-1566737236500-c8ac43014a8e?w=800",
  },
];

const tabs = ["Delivery", "Dining Out", "Nightlife"];

function Home() {
  const [activeTab, setActiveTab] = useState("Delivery");

  const getRestaurants = () => {
    if (activeTab === "Dining Out") {
      return diningRestaurants;
    }

    if (activeTab === "Nightlife") {
      return nightlifeRestaurants;
    }

    return restaurants;
  };

  const activeRestaurants = getRestaurants();

  return (
    <div className="bg-white text-gray-900 dark:bg-gray-950 dark:text-white">
      {/* Hero */}
      <section className="bg-gray-50 dark:bg-gray-900">
        <div className="mx-auto max-w-7xl px-4 py-16 sm:px-6 lg:px-8 lg:py-24">
          <div className="mx-auto max-w-3xl text-center">
            <p className="mb-3 text-sm font-medium text-red-500">
              {activeTab === "Delivery"
                ? "Food delivered to your doorstep"
                : activeTab === "Dining Out"
                  ? "Discover great places to eat"
                  : "Find the best places for your night out"}
            </p>

            <h1 className="text-4xl font-bold tracking-tight sm:text-5xl lg:text-6xl">
              Discover the best food
              <span className="text-red-500"> near you</span>
            </h1>

            <p className="mx-auto mt-5 max-w-xl text-base text-gray-500 sm:text-lg dark:text-gray-400">
              Find restaurants, discover new dishes and enjoy great experiences
              around you.
            </p>

            <Link
              to="/search"
              className="mx-auto mt-8 flex max-w-2xl items-center gap-3 rounded-xl border border-gray-200 bg-white px-5 py-4 text-left shadow-sm transition hover:shadow-md dark:border-gray-700 dark:bg-gray-800"
            >
              <span className="text-lg">🔍</span>

              <span className="flex-1 text-sm text-gray-400 sm:text-base">
                Search for restaurant, cuisine or dish
              </span>

              <span className="rounded-lg bg-red-500 px-4 py-2 text-sm font-medium text-white">
                Search
              </span>
            </Link>
          </div>
        </div>
      </section>

      {/* Tabs */}
      <section className="border-b border-gray-100 dark:border-gray-800">
        <div className="mx-auto flex max-w-7xl gap-8 overflow-x-auto px-4 sm:px-6 lg:px-8">
          {tabs.map((tab) => (
            <button
              key={tab}
              type="button"
              onClick={() => setActiveTab(tab)}
              className={`whitespace-nowrap border-b-2 py-5 text-sm font-medium transition ${
                activeTab === tab
                  ? "border-red-500 text-red-500"
                  : "border-transparent text-gray-500 hover:text-red-500 dark:text-gray-400"
              }`}
            >
              {tab}
            </button>
          ))}
        </div>
      </section>

      {/* Cuisine */}
      <section className="mx-auto max-w-7xl px-4 py-10 sm:px-6 lg:px-8">
        <div className="mb-6">
          <h2 className="text-2xl font-semibold">Explore cuisines</h2>

          <p className="mt-1 text-sm text-gray-500 dark:text-gray-400">
            What are you craving today?
          </p>
        </div>

        <div className="grid grid-cols-4 gap-4 sm:grid-cols-6 lg:grid-cols-8">
          {categories.map((category) => (
            <Link
              key={category.name}
              to={`/restaurants?cuisine=${category.name}`}
              className="group text-center"
            >
              <div className="mx-auto aspect-square max-w-28 overflow-hidden rounded-full bg-gray-100">
                <img
                  src={category.image}
                  alt={category.name}
                  className="h-full w-full object-cover transition duration-300 group-hover:scale-110"
                />
              </div>

              <p className="mt-3 text-sm font-medium text-gray-700 dark:text-gray-300">
                {category.name}
              </p>
            </Link>
          ))}
        </div>
      </section>

      {/* Restaurants */}
      <section className="bg-gray-50 dark:bg-gray-900">
        <div className="mx-auto max-w-7xl px-4 py-12 sm:px-6 lg:px-8">
          <div className="mb-6 flex items-end justify-between">
            <div>
              <h2 className="text-2xl font-semibold">
                {activeTab === "Delivery"
                  ? "Popular restaurants"
                  : activeTab === "Dining Out"
                    ? "Popular dining places"
                    : "Popular nightlife spots"}
              </h2>

              <p className="mt-1 text-sm text-gray-500 dark:text-gray-400">
                {activeTab === "Delivery"
                  ? "Top picks around you"
                  : "Places worth checking out"}
              </p>
            </div>

            <Link
              to="/restaurants"
              className="text-sm font-medium text-red-500"
            >
              View all →
            </Link>
          </div>

          <div className="grid gap-6 sm:grid-cols-2 lg:grid-cols-4">
            {activeRestaurants.map((restaurant) => (
              <RestaurantCard key={restaurant.id} restaurant={restaurant} />
            ))}
          </div>
        </div>
      </section>

      {/* CTA */}
      <section className="mx-auto max-w-7xl px-4 py-16 text-center sm:px-6 lg:px-8">
        <h2 className="text-3xl font-bold">Hungry already?</h2>

        <p className="mt-3 text-gray-500 dark:text-gray-400">
          Find something delicious and order in just a few clicks.
        </p>

        <Link
          to="/restaurants"
          className="mt-6 inline-flex rounded-lg bg-red-500 px-6 py-3 text-sm font-semibold text-white transition hover:bg-red-600"
        >
          Explore restaurants
        </Link>
      </section>
    </div>
  );
}

export default Home;
