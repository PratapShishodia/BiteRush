import { useState } from "react";
import { Link, useParams } from "react-router-dom";
import FoodCard from "../components/FoodCard";
import { useCart } from "../context/CartContext";

const restaurant = {
  id: 1,
  name: "Burger Singh",
  cuisine: "Burger, Fast Food",
  rating: 4.4,
  reviews: "2.5K+",
  price: "₹250 for two",
  deliveryTime: "25-30 min",
  distance: "2.4 km",
  offer: "30% OFF up to ₹100",
  image: "https://images.unsplash.com/photo-1568901346375-23c9450c58cd?w=1200",
};

const menu = [
  {
    category: "Burgers",
    items: [
      {
        id: 1,
        name: "Classic Chicken Burger",
        price: 199,
        veg: false,
        description:
          "Crispy chicken patty with lettuce, cheese and our special sauce.",
        image:
          "https://images.unsplash.com/photo-1568901346375-23c9450c58cd?w=500",
      },
      {
        id: 2,
        name: "Classic Veg Burger",
        price: 159,
        veg: true,
        description:
          "Crispy vegetable patty with fresh lettuce and creamy sauce.",
        image:
          "https://images.unsplash.com/photo-1520072959219-c595dc870360?w=500",
      },
      {
        id: 3,
        name: "Spicy Chicken Burger",
        price: 229,
        veg: false,
        description: "Spicy crispy chicken, jalapeños, onions and fiery sauce.",
        image:
          "https://images.unsplash.com/photo-1572802419224-296b0aeee0d9?w=500",
      },
    ],
  },
  {
    category: "Sides",
    items: [
      {
        id: 4,
        name: "Peri Peri Fries",
        price: 129,
        veg: true,
        description: "Crispy fries tossed in spicy peri peri seasoning.",
        image:
          "https://images.unsplash.com/photo-1573080496219-bb080dd4f877?w=500",
      },
      {
        id: 5,
        name: "Cheese Fries",
        price: 149,
        veg: true,
        description: "Golden fries topped with creamy melted cheese.",
        image:
          "https://images.unsplash.com/photo-1585109649139-366815a0d713?w=500",
      },
    ],
  },
  {
    category: "Drinks",
    items: [
      {
        id: 6,
        name: "Cold Coffee",
        price: 119,
        veg: true,
        description: "Chilled creamy coffee served with ice.",
        image:
          "https://images.unsplash.com/photo-1461023058943-07fcbe16d735?w=500",
      },
      {
        id: 7,
        name: "Fresh Lime Soda",
        price: 89,
        veg: true,
        description: "Refreshing lime soda with a hint of mint.",
        image:
          "https://images.unsplash.com/photo-1513558161293-cdaf765ed2fd?w=500",
      },
    ],
  },
];

function RestaurantDetails() {
  const { id } = useParams();
  const { addToCart, cartCount, cartTotal } = useCart();

  return (
    <div className="bg-white text-gray-900 dark:bg-gray-950 dark:text-white">
      {/* Restaurant Header */}
      <section className="mx-auto max-w-7xl px-4 py-6 sm:px-6 lg:px-8">
        <p className="text-sm text-gray-500 dark:text-gray-400">
          Home / Restaurants / {restaurant.name}
        </p>

        <div className="mt-6 overflow-hidden rounded-2xl border border-gray-200 dark:border-gray-800">
          <div className="h-52 sm:h-72">
            <img
              src={restaurant.image}
              alt={restaurant.name}
              className="h-full w-full object-cover"
            />
          </div>

          <div className="p-5 sm:p-7">
            <div className="flex flex-col justify-between gap-5 sm:flex-row">
              <div>
                <h1 className="text-3xl font-bold">{restaurant.name}</h1>

                <p className="mt-2 text-gray-500 dark:text-gray-400">
                  {restaurant.cuisine}
                </p>

                <p className="mt-2 text-sm text-gray-500 dark:text-gray-400">
                  {restaurant.price} • {restaurant.distance}
                </p>
              </div>

              <div className="flex items-start gap-3">
                <div className="rounded-lg bg-green-600 px-3 py-2 text-sm font-semibold text-white">
                  ★ {restaurant.rating}
                </div>

                <div className="text-sm text-gray-500 dark:text-gray-400">
                  <p>{restaurant.reviews} reviews</p>
                  <p className="mt-1">{restaurant.deliveryTime}</p>
                </div>
              </div>
            </div>

            {/* Offer */}
            <div className="mt-6 flex flex-wrap gap-3">
              <div className="rounded-lg border border-orange-200 bg-orange-50 px-4 py-3 text-sm text-orange-700 dark:border-orange-900 dark:bg-orange-950 dark:text-orange-300">
                🏷️ {restaurant.offer}
              </div>

              <div className="rounded-lg border border-gray-200 px-4 py-3 text-sm text-gray-600 dark:border-gray-700 dark:text-gray-300">
                🛵 Free delivery above ₹399
              </div>
            </div>
          </div>
        </div>
      </section>

      {/* Menu */}
      <section className="mx-auto max-w-4xl px-4 pb-32 sm:px-6 lg:px-8">
        <div className="mb-6 border-b border-gray-200 pb-5 dark:border-gray-800">
          <h2 className="text-2xl font-bold">Menu</h2>

          <p className="mt-1 text-sm text-gray-500 dark:text-gray-400">
            Order your favorites
          </p>
        </div>

        <div className="space-y-8">
          {menu.map((section) => (
            <div key={section.category}>
              <h3 className="mb-2 text-xl font-semibold">{section.category}</h3>

              <div>
                {section.items.map((food) => (
                  <FoodCard key={food.id} food={food} onAdd={addToCart} />
                ))}
              </div>
            </div>
          ))}
        </div>
      </section>

      {/* Floating Cart */}
      {cartCount > 0 && (
        <div className="fixed bottom-5 left-1/2 z-40 w-[calc(100%-2rem)] max-w-lg -translate-x-1/2">
          <Link
            to="/cart"
            className="flex items-center justify-between rounded-xl bg-red-500 px-5 py-4 text-white shadow-xl transition hover:bg-red-600"
          >
            <div>
              <p className="text-sm font-medium">
                {cartCount} item{cartCount > 1 ? "s" : ""}
              </p>

              <p className="text-xs text-red-100">₹{cartTotal}</p>
            </div>

            <span className="font-semibold">View Cart →</span>
          </Link>
        </div>
      )}
    </div>
  );
}

export default RestaurantDetails;
