import { Link } from "react-router-dom";

const orders = [
  {
    id: "12345",
    restaurant: "Burger Singh",
    date: "Today, 12:30 PM",
    status: "On the way",
    statusColor: "orange",
    items: ["Classic Chicken Burger", "Peri Peri Fries × 2"],
    total: 457,
  },
  {
    id: "12344",
    restaurant: "Biryani Blues",
    date: "Yesterday, 8:15 PM",
    status: "Delivered",
    statusColor: "green",
    items: ["Chicken Biryani", "Cold Drink"],
    total: 399,
  },
  {
    id: "12343",
    restaurant: "Pizza Heaven",
    date: "10 Aug, 7:45 PM",
    status: "Delivered",
    statusColor: "green",
    items: ["Farmhouse Pizza", "Garlic Bread"],
    total: 549,
  },
];

function Orders() {
  return (
    <div className="min-h-screen bg-gray-50 py-8 dark:bg-gray-950">
      <div className="mx-auto max-w-4xl px-4 sm:px-6 lg:px-8">
        {/* Header */}
        <div className="mb-8">
          <p className="text-sm text-gray-500 dark:text-gray-400">
            Home / Orders
          </p>

          <h1 className="mt-2 text-3xl font-bold">My Orders</h1>

          <p className="mt-2 text-sm text-gray-500 dark:text-gray-400">
            View and track your recent orders.
          </p>
        </div>

        {/* Orders */}
        {orders.length > 0 ? (
          <div className="space-y-5">
            {orders.map((order) => (
              <div
                key={order.id}
                className="rounded-xl border border-gray-200 bg-white dark:border-gray-800 dark:bg-gray-900"
              >
                {/* Header */}
                <div className="flex flex-col justify-between gap-3 border-b border-gray-200 p-5 sm:flex-row sm:items-center dark:border-gray-800">
                  <div>
                    <h2 className="font-semibold">{order.restaurant}</h2>

                    <p className="mt-1 text-xs text-gray-500 dark:text-gray-400">
                      Order #{order.id} • {order.date}
                    </p>
                  </div>

                  <span
                    className={`w-fit rounded-full px-3 py-1 text-xs font-medium ${
                      order.statusColor === "orange"
                        ? "bg-orange-100 text-orange-700 dark:bg-orange-950 dark:text-orange-300"
                        : "bg-green-100 text-green-700 dark:bg-green-950 dark:text-green-300"
                    }`}
                  >
                    {order.status}
                  </span>
                </div>

                {/* Details */}
                <div className="p-5">
                  <div className="flex flex-col justify-between gap-5 sm:flex-row">
                    <div>
                      <p className="text-sm font-medium">
                        {order.items.join(", ")}
                      </p>

                      <p className="mt-2 text-sm text-gray-500 dark:text-gray-400">
                        Total paid: ₹{order.total}
                      </p>
                    </div>

                    <div className="flex gap-3">
                      {order.status === "On the way" && (
                        <Link
                          to={`/orders/${order.id}`}
                          className="rounded-lg bg-red-500 px-4 py-2 text-center text-sm font-medium text-white hover:bg-red-600"
                        >
                          Track order
                        </Link>
                      )}

                      <button
                        type="button"
                        onClick={() =>
                          alert("Reorder functionality coming soon")
                        }
                        className="rounded-lg border border-gray-300 px-4 py-2 text-sm font-medium hover:border-red-500 hover:text-red-500 dark:border-gray-700"
                      >
                        Reorder
                      </button>
                    </div>
                  </div>
                </div>
              </div>
            ))}
          </div>
        ) : (
          <div className="rounded-xl border border-dashed border-gray-300 bg-white py-20 text-center dark:border-gray-700 dark:bg-gray-900">
            <div className="text-5xl">🧾</div>

            <h2 className="mt-5 text-xl font-semibold">No orders yet</h2>

            <p className="mt-2 text-sm text-gray-500 dark:text-gray-400">
              Your completed and active orders will appear here.
            </p>

            <Link
              to="/restaurants"
              className="mt-6 inline-block rounded-lg bg-red-500 px-5 py-3 text-sm font-semibold text-white hover:bg-red-600"
            >
              Start ordering
            </Link>
          </div>
        )}

        {/* Empty state isn't needed yet because we're using dummy data */}

        {/* Browse restaurants */}
        <div className="mt-8 text-center">
          <Link
            to="/restaurants"
            className="text-sm font-medium text-red-500 hover:text-red-600"
          >
            Browse restaurants →
          </Link>
        </div>
      </div>
    </div>
  );
}

export default Orders;
