import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { useCart } from "../context/CartContext";

const addresses = [
  {
    id: 1,
    type: "Home",
    address: "123, Sector 15, Ghaziabad, Uttar Pradesh",
  },
  {
    id: 2,
    type: "Work",
    address: "45, RDC, Raj Nagar, Ghaziabad, Uttar Pradesh",
  },
];

function Checkout() {
  const navigate = useNavigate();

  const { cart, cartTotal, clearCart } = useCart();

  const [selectedAddress, setSelectedAddress] = useState(1);
  const [paymentMethod, setPaymentMethod] = useState("upi");
  const [placingOrder, setPlacingOrder] = useState(false);

  const deliveryFee = cartTotal > 399 ? 0 : 40;
  const taxes = Math.round(cartTotal * 0.05);
  const total = cartTotal + deliveryFee + taxes;

  const placeOrder = () => {
    setPlacingOrder(true);

    setTimeout(() => {
      clearCart();
      navigate("/orders/12345");
    }, 800);
  };

  if (cart.length === 0) {
    return (
      <div className="flex min-h-[70vh] items-center justify-center bg-gray-50 px-4 dark:bg-gray-950">
        <div className="text-center">
          <div className="text-6xl">🛒</div>

          <h1 className="mt-5 text-2xl font-bold">Your cart is empty</h1>

          <p className="mt-2 text-sm text-gray-500 dark:text-gray-400">
            Add something delicious before checking out.
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
            Home / Cart / Checkout
          </p>

          <h1 className="mt-2 text-3xl font-bold">Checkout</h1>
        </div>

        <div className="grid gap-6 lg:grid-cols-[1fr_380px]">
          {/* Left */}
          <div className="space-y-6">
            {/* Address */}
            <section className="rounded-xl border border-gray-200 bg-white p-5 dark:border-gray-800 dark:bg-gray-900">
              <div className="flex items-center justify-between">
                <div>
                  <h2 className="font-semibold">Delivery address</h2>

                  <p className="mt-1 text-sm text-gray-500 dark:text-gray-400">
                    Where should we deliver your order?
                  </p>
                </div>

                <button
                  type="button"
                  onClick={() => alert("Add address functionality coming soon")}
                  className="text-sm font-medium text-red-500"
                >
                  + Add new
                </button>
              </div>

              <div className="mt-5 space-y-3">
                {addresses.map((address) => (
                  <button
                    key={address.id}
                    type="button"
                    onClick={() => setSelectedAddress(address.id)}
                    className={`w-full rounded-lg border p-4 text-left transition ${
                      selectedAddress === address.id
                        ? "border-red-500 bg-red-50 dark:bg-red-950/30"
                        : "border-gray-200 hover:border-gray-400 dark:border-gray-700"
                    }`}
                  >
                    <div className="flex items-center justify-between">
                      <span className="font-medium">{address.type}</span>

                      <span
                        className={`h-4 w-4 rounded-full border-2 ${
                          selectedAddress === address.id
                            ? "border-red-500 bg-red-500"
                            : "border-gray-400"
                        }`}
                      />
                    </div>

                    <p className="mt-2 text-sm text-gray-500 dark:text-gray-400">
                      {address.address}
                    </p>
                  </button>
                ))}
              </div>
            </section>

            {/* Payment */}
            <section className="rounded-xl border border-gray-200 bg-white p-5 dark:border-gray-800 dark:bg-gray-900">
              <h2 className="font-semibold">Payment method</h2>

              <div className="mt-5 space-y-3">
                <button
                  type="button"
                  onClick={() => setPaymentMethod("upi")}
                  className={`flex w-full items-center justify-between rounded-lg border p-4 text-left ${
                    paymentMethod === "upi"
                      ? "border-red-500 bg-red-50 dark:bg-red-950/30"
                      : "border-gray-200 dark:border-gray-700"
                  }`}
                >
                  <div>
                    <p className="font-medium">UPI</p>

                    <p className="mt-1 text-xs text-gray-500 dark:text-gray-400">
                      Google Pay, PhonePe, Paytm
                    </p>
                  </div>

                  <span>📱</span>
                </button>

                <button
                  type="button"
                  onClick={() => setPaymentMethod("card")}
                  className={`flex w-full items-center justify-between rounded-lg border p-4 text-left ${
                    paymentMethod === "card"
                      ? "border-red-500 bg-red-50 dark:bg-red-950/30"
                      : "border-gray-200 dark:border-gray-700"
                  }`}
                >
                  <div>
                    <p className="font-medium">Card</p>

                    <p className="mt-1 text-xs text-gray-500 dark:text-gray-400">
                      Credit or debit card
                    </p>
                  </div>

                  <span>💳</span>
                </button>

                <button
                  type="button"
                  onClick={() => setPaymentMethod("cod")}
                  className={`flex w-full items-center justify-between rounded-lg border p-4 text-left ${
                    paymentMethod === "cod"
                      ? "border-red-500 bg-red-50 dark:bg-red-950/30"
                      : "border-gray-200 dark:border-gray-700"
                  }`}
                >
                  <div>
                    <p className="font-medium">Cash on Delivery</p>

                    <p className="mt-1 text-xs text-gray-500 dark:text-gray-400">
                      Pay when your order arrives
                    </p>
                  </div>

                  <span>💵</span>
                </button>
              </div>
            </section>
          </div>

          {/* Order Summary */}
          <aside className="h-fit rounded-xl border border-gray-200 bg-white p-5 dark:border-gray-800 dark:bg-gray-900">
            <h2 className="font-semibold">Order summary</h2>

            <div className="mt-5 space-y-4">
              {cart.map((item) => (
                <div
                  key={item.id}
                  className="flex justify-between gap-4 text-sm"
                >
                  <div>
                    <p>{item.name}</p>

                    <p className="mt-1 text-xs text-gray-500 dark:text-gray-400">
                      {item.quantity} × ₹{item.price}
                    </p>
                  </div>

                  <span>₹{item.price * item.quantity}</span>
                </div>
              ))}
            </div>

            <div className="mt-5 space-y-3 border-t border-gray-200 pt-5 text-sm dark:border-gray-800">
              <div className="flex justify-between">
                <span className="text-gray-500 dark:text-gray-400">
                  Item total
                </span>

                <span>₹{cartTotal}</span>
              </div>

              <div className="flex justify-between">
                <span className="text-gray-500 dark:text-gray-400">
                  Delivery
                </span>

                <span>{deliveryFee === 0 ? "FREE" : `₹${deliveryFee}`}</span>
              </div>

              <div className="flex justify-between">
                <span className="text-gray-500 dark:text-gray-400">Taxes</span>

                <span>₹{taxes}</span>
              </div>

              <div className="flex justify-between border-t border-gray-200 pt-4 font-semibold dark:border-gray-800">
                <span>Total</span>

                <span>₹{total}</span>
              </div>
            </div>

            <button
              type="button"
              onClick={placeOrder}
              disabled={placingOrder}
              className="mt-6 w-full rounded-lg bg-red-500 px-5 py-3 text-sm font-semibold text-white hover:bg-red-600 disabled:cursor-not-allowed disabled:opacity-60"
            >
              {placingOrder ? "Placing order..." : `Place Order • ₹${total}`}
            </button>

            <Link
              to="/cart"
              className="mt-3 block text-center text-sm text-gray-500 hover:text-red-500 dark:text-gray-400"
            >
              ← Back to cart
            </Link>
          </aside>
        </div>
      </div>
    </div>
  );
}

export default Checkout;
