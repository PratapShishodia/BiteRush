import { Link } from "react-router-dom";
import { useState } from "react";
import { useCart } from "../context/CartContext";

function Cart() {
  const { cart, updateQuantity, removeFromCart, cartTotal } = useCart();
  const subtotal = cartTotal;

  const deliveryFee = subtotal > 399 ? 0 : 40;
  const taxes = Math.round(subtotal * 0.05);
  const total = subtotal + deliveryFee + taxes;

  if (cart.length === 0) {
    return (
      <div className="flex min-h-[70vh] items-center justify-center bg-white px-4 dark:bg-gray-950">
        <div className="text-center">
          <div className="text-6xl">🛒</div>

          <h1 className="mt-5 text-2xl font-bold">Your cart is empty</h1>

          <p className="mt-2 text-sm text-gray-500 dark:text-gray-400">
            Add something delicious to get started.
          </p>

          <Link
            to="/restaurants"
            className="mt-6 inline-block rounded-lg bg-red-500 px-6 py-3 text-sm font-semibold text-white hover:bg-red-600"
          >
            Browse restaurants
          </Link>
        </div>
      </div>
    );
  }

  return (
    <div className="min-h-screen bg-gray-50 py-8 dark:bg-gray-950">
      <div className="mx-auto max-w-6xl px-4 sm:px-6 lg:px-8">
        {/* Header */}
        <div className="mb-8">
          <p className="text-sm text-gray-500 dark:text-gray-400">
            Home / Cart
          </p>

          <h1 className="mt-2 text-3xl font-bold">Your Cart</h1>
        </div>

        <div className="grid gap-6 lg:grid-cols-[1fr_380px]">
          {/* Cart Items */}
          <div className="rounded-xl border border-gray-200 bg-white dark:border-gray-800 dark:bg-gray-900">
            <div className="border-b border-gray-200 px-5 py-4 dark:border-gray-800">
              <h2 className="font-semibold">Burger Singh</h2>

              <p className="mt-1 text-xs text-gray-500 dark:text-gray-400">
                Delivering to your selected address
              </p>
            </div>

            <div className="divide-y divide-gray-200 dark:divide-gray-800">
              {cart.map((item) => (
                <div key={item.id} className="flex gap-4 p-5">
                  {/* Image */}
                  <div className="h-20 w-20 shrink-0 overflow-hidden rounded-lg">
                    <img
                      src={item.image}
                      alt={item.name}
                      className="h-full w-full object-cover"
                    />
                  </div>

                  {/* Details */}
                  <div className="min-w-0 flex-1">
                    <div className="flex items-start justify-between gap-3">
                      <div>
                        <h3 className="font-medium">{item.name}</h3>

                        <p className="mt-1 text-sm text-gray-500 dark:text-gray-400">
                          ₹{item.price}
                        </p>
                      </div>

                      <button
                        type="button"
                        onClick={() => removeFromCart(item.id)}
                        className="text-sm text-gray-400 hover:text-red-500"
                      >
                        Remove
                      </button>
                    </div>

                    <div className="mt-3 flex items-center justify-between">
                      {/* Quantity */}
                      <div className="flex items-center rounded-lg border border-gray-300 dark:border-gray-700">
                        <button
                          type="button"
                          onClick={() => updateQuantity(item.id, -1)}
                          className="px-3 py-1.5 text-lg hover:bg-gray-100 dark:hover:bg-gray-800"
                        >
                          −
                        </button>

                        <span className="min-w-8 text-center text-sm">
                          {item.quantity}
                        </span>

                        <button
                          type="button"
                          onClick={() => updateQuantity(item.id, 1)}
                          className="px-3 py-1.5 text-lg hover:bg-gray-100 dark:hover:bg-gray-800"
                        >
                          +
                        </button>
                      </div>

                      <span className="font-medium">
                        ₹{item.price * item.quantity}
                      </span>
                    </div>
                  </div>
                </div>
              ))}
            </div>
          </div>

          {/* Bill */}
          <div className="h-fit rounded-xl border border-gray-200 bg-white p-5 dark:border-gray-800 dark:bg-gray-900">
            <h2 className="font-semibold">Bill details</h2>

            <div className="mt-5 space-y-4 text-sm">
              <div className="flex justify-between">
                <span className="text-gray-500 dark:text-gray-400">
                  Item total
                </span>

                <span>₹{subtotal}</span>
              </div>

              <div className="flex justify-between">
                <span className="text-gray-500 dark:text-gray-400">
                  Delivery fee
                </span>

                <span>{deliveryFee === 0 ? "FREE" : `₹${deliveryFee}`}</span>
              </div>

              <div className="flex justify-between">
                <span className="text-gray-500 dark:text-gray-400">Taxes</span>

                <span>₹{taxes}</span>
              </div>

              <div className="border-t border-gray-200 pt-4 dark:border-gray-800">
                <div className="flex justify-between font-semibold">
                  <span>Total</span>
                  <span>₹{total}</span>
                </div>
              </div>
            </div>

            {subtotal < 399 && (
              <p className="mt-5 rounded-lg bg-orange-50 p-3 text-xs text-orange-700 dark:bg-orange-950 dark:text-orange-300">
                Add ₹{399 - subtotal} more to get free delivery.
              </p>
            )}

            <Link
              to="/checkout"
              className="mt-5 block rounded-lg bg-red-500 px-5 py-3 text-center text-sm font-semibold text-white hover:bg-red-600"
            >
              Proceed to Checkout
            </Link>

            <Link
              to="/restaurants"
              className="mt-3 block text-center text-sm text-gray-500 hover:text-red-500 dark:text-gray-400"
            >
              Add more items
            </Link>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Cart;
