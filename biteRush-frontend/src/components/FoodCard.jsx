function FoodCard({ food, onAdd }) {
  return (
    <div className="flex gap-4 border-b border-gray-200 py-5 dark:border-gray-800">
      {/* Food Image */}
      <div className="order-2 h-28 w-28 shrink-0 overflow-hidden rounded-xl sm:h-32 sm:w-32">
        <img
          src={food.image}
          alt={food.name}
          className="h-full w-full object-cover"
        />
      </div>

      {/* Food Details */}
      <div className="order-1 flex min-w-0 flex-1 flex-col">
        <div className="flex items-start justify-between gap-3">
          <div>
            <h3 className="font-semibold text-gray-900 dark:text-white">
              {food.name}
            </h3>

            <p className="mt-1 font-medium text-gray-800 dark:text-gray-200">
              ₹{food.price}
            </p>
          </div>

          {food.veg && (
            <span className="rounded border border-green-600 px-1.5 py-0.5 text-xs text-green-600">
              VEG
            </span>
          )}
        </div>

        <p className="mt-2 line-clamp-2 text-sm leading-5 text-gray-500 dark:text-gray-400">
          {food.description}
        </p>

        <button
          type="button"
          onClick={() => onAdd(food)}
          className="mt-auto w-fit rounded-lg border border-red-500 px-4 py-1.5 text-sm font-medium text-red-500 transition hover:bg-red-500 hover:text-white"
        >
          Add
        </button>
      </div>
    </div>
  );
}

export default FoodCard;
