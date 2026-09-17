@echo off
setlocal EnableExtensions

echo Stopping BiteRush services...

for %%S in (eureka-server api-gateway auth-service user-service order-service payment-service restaurant-service menu-service notification-service rating-service favourite-service cart-service) do (
    echo Closing %%S...

    rem First try to close any matching console window.
    taskkill /F /FI "WINDOWTITLE eq %%S" /T >nul 2>&1
    taskkill /F /FI "WINDOWTITLE eq %%S*" /T >nul 2>&1
    taskkill /F /FI "WINDOWTITLE eq *%%S*" /T >nul 2>&1
)

rem Force-stop the actual Java and cmd processes spawned by the project.
rem This is the reliable fallback when title-based matching or wmic fails.
taskkill /F /IM java.exe /T >nul 2>&1
taskkill /F /IM cmd.exe /T >nul 2>&1

echo.
echo All BiteRush services have been stopped.
endlocal
exit /b
