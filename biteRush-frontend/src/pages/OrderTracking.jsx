import { Link, useParams } from "react-router-dom";

const order = {
  id: "12345",
  restaurant: "Burger Singh",
  estimatedTime: "25-30 min",
  deliveryAddress: "123, Sector 15, Ghaziabad, Uttar Pradesh",
  total: 457,
  deliveryPartner: {
    name: "Rahul",
    phone: "9876543210",
  },
};

const statuses = [
  {
    title: "Order placed",
    description: "Your order has been received.",
    time: "12:30 PM",
  },
  {
    title: "Restaurant accepted",
    description: "The restaurant has accepted your order.",
    time: "12:32 PM",
  },
  {
    title: "Preparing your food",
    description: "Your food is being prepared.",
    time: "12:35 PM",
  },
  {
    title: "Out for delivery",
    description: "Your delivery partner is on the way.",
    time: "12:50 PM",
  },
  {
    title: "Delivered",
    description: "Enjoy your meal!",
    time: "1:05 PM",
  },
];

function OrderTracking() {
  const { id } = useParams();

  // Temporary status for frontend development
  const currentStatus = 3;

  return (
    <div className="min-h-screen bg-gray-50 py-8 dark:bg-gray-950">
      <div className="mx-auto max-w-4xl px-4 sm:px-6 lg:px-8">
        {/* Header */}
        <div className="mb-8">
          <p className="text-sm text-gray-500 dark:text-gray-400">
            Home / Orders / {id}
          </p>

          <div className="mt-3 flex flex-col justify-between gap-3 sm:flex-row sm:items-center">
            <div>
              <h1 className="text-3xl font-bold">Track your order</h1>

              <p className="mt-1 text-sm text-gray-500 dark:text-gray-400">
                Order #{order.id}
              </p>
            </div>

            <span className="w-fit rounded-full bg-orange-100 px-4 py-2 text-sm font-medium text-orange-700 dark:bg-orange-950 dark:text-orange-300">
              On the way
            </span>
          </div>
        </div>

        <div className="space-y-6">
          {/* Delivery ETA */}
          <section className="rounded-xl bg-red-500 p-6 text-white">
            <p className="text-sm text-red-100">Estimated delivery</p>

            <h2 className="mt-2 text-3xl font-bold">{order.estimatedTime}</h2>

            <p className="mt-2 text-sm text-red-100">
              Your order from {order.restaurant} is on its way.
            </p>
          </section>

          {/* Status Timeline */}
          <section className="rounded-xl border border-gray-200 bg-white p-6 dark:border-gray-800 dark:bg-gray-900">
            <h2 className="font-semibold">Order status</h2>

            <div className="mt-6">
              {statuses.map((status, index) => {
                const completed = index <= currentStatus;
                const current = index === currentStatus;

                return (
                  <div
                    key={status.title}
                    className="relative flex gap-4 pb-7 last:pb-0"
                  >
                    {/* Line */}
                    {index < statuses.length - 1 && (
                      <div
                        className={`absolute left-[11px] top-6 h-full w-0.5 ${
                          index < currentStatus
                            ? "bg-green-500"
                            : "bg-gray-200 dark:bg-gray-700"
                        }`}
                      />
                    )}

                    {/* Circle */}
                    <div
                      className={`relative z-10 flex h-6 w-6 shrink-0 items-center justify-center rounded-full border-2 ${
                        completed
                          ? "border-green-500 bg-green-500 text-white"
                          : "border-gray-300 bg-white dark:border-gray-600 dark:bg-gray-900"
                      }`}
                    >
                      {completed && <span className="text-xs">✓</span>}
                    </div>

                    {/* Content */}
                    <div className="flex flex-1 justify-between gap-4">
                      <div>
                        <h3
                          className={`text-sm font-medium ${
                            current
                              ? "text-green-600"
                              : completed
                                ? "text-gray-900 dark:text-white"
                                : "text-gray-400"
                          }`}
                        >
                          {status.title}
                        </h3>

                        <p className="mt-1 text-sm text-gray-500 dark:text-gray-400">
                          {status.description}
                        </p>
                      </div>

                      <span className="shrink-0 text-xs text-gray-400">
                        {status.time}
                      </span>
                    </div>
                  </div>
                );
              })}
            </div>
          </section>

          {/* Delivery Partner */}
          <section className="rounded-xl border border-gray-200 bg-white p-6 dark:border-gray-800 dark:bg-gray-900">
            <h2 className="font-semibold">Delivery partner</h2>

            <div className="mt-5 flex items-center justify-between">
              <div className="flex items-center gap-4">
                <div className="flex h-12 w-12 items-center justify-center rounded-full bg-gray-100 text-xl dark:bg-gray-800">
                  👨
                </div>

                <div>
                  <p className="font-medium">{order.deliveryPartner.name}</p>

                  <p className="mt-1 text-sm text-gray-500 dark:text-gray-400">
                    Delivery partner
                  </p>
                </div>
              </div>

              <a
                href={`tel:${order.deliveryPartner.phone}`}
                className="rounded-lg border border-gray-300 px-4 py-2 text-sm font-medium hover:border-red-500 hover:text-red-500 dark:border-gray-700"
              >
                Call
              </a>
            </div>
          </section>

          {/* Delivery Address */}
          <section className="rounded-xl border border-gray-200 bg-white p-6 dark:border-gray-800 dark:bg-gray-900">
            <h2 className="font-semibold">Delivery address</h2>

            <p className="mt-3 text-sm text-gray-500 dark:text-gray-400">
              {order.deliveryAddress}
            </p>
          </section>

          {/* Actions */}
          <div className="flex flex-col gap-3 sm:flex-row">
            <Link
              to="/orders"
              className="flex-1 rounded-lg border border-gray-300 px-5 py-3 text-center text-sm font-medium hover:border-red-500 hover:text-red-500 dark:border-gray-700"
            >
              View all orders
            </Link>

            <Link
              to="/"
              className="flex-1 rounded-lg bg-red-500 px-5 py-3 text-center text-sm font-semibold text-white hover:bg-red-600"
            >
              Order something else
            </Link>
          </div>
        </div>
      </div>
    </div>
  );
}

export default OrderTracking;
