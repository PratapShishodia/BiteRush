function ErrorState({
  title = "Something went wrong",
  message = "We couldn't load this page. Please try again.",
  onRetry,
}) {
  return (
    <div className="flex min-h-[60vh] items-center justify-center px-4 text-center">
      <div className="max-w-md">
        <div className="text-5xl">⚠️</div>

        <h2 className="mt-5 text-xl font-semibold text-gray-900 dark:text-white">
          {title}
        </h2>

        <p className="mt-2 text-sm text-gray-500 dark:text-gray-400">
          {message}
        </p>

        {onRetry && (
          <button
            type="button"
            onClick={onRetry}
            className="mt-5 rounded-lg bg-red-500 px-5 py-3 text-sm font-semibold text-white transition hover:bg-red-600"
          >
            Try again
          </button>
        )}
      </div>
    </div>
  );
}

export default ErrorState;
