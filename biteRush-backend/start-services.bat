@echo off
setlocal

rem Set JVM timezone for all BiteRush services
set "JAVA_TOOL_OPTIONS=-Duser.timezone=Asia/Kolkata"

rem BiteRush Backend service launcher
if /i "%~1"=="eureka" goto eureka
if /i "%~1"=="gateway" goto gateway
if /i "%~1"=="auth" goto auth
if /i "%~1"=="user" goto user
if /i "%~1"=="order" goto order
if /i "%~1"=="payment" goto payment
if /i "%~1"=="restaurant" goto restaurant
if /i "%~1"=="menu" goto menu
if /i "%~1"=="notification" goto notification
if /i "%~1"=="rating" goto rating
if /i "%~1"=="favourite" goto favourite
if /i "%~1"=="cart" goto cart

start "eureka-server" /max cmd /k call "%~f0" eureka
timeout /t 10 /nobreak >nul
start "api-gateway" /max cmd /k call "%~f0" gateway
start "auth-service" /max cmd /k call "%~f0" auth
start "user-service" /max cmd /k call "%~f0" user
start "order-service" /max cmd /k call "%~f0" order
start "payment-service" /max cmd /k call "%~f0" payment
start "restaurant-service" /max cmd /k call "%~f0" restaurant
start "menu-service" /max cmd /k call "%~f0" menu
start "notification-service" /max cmd /k call "%~f0" notification
start "rating-service" /max cmd /k call "%~f0" rating
start "favourite-service" /max cmd /k call "%~f0" favourite
start "cart-service" /max cmd /k call "%~f0" cart
exit /b

:eureka
cd /d "%~dp0eureka-server"
set "APPLICATION_PORT=8082"
mvnw.cmd spring-boot:run
exit /b

:gateway
cd /d "%~dp0api-gateway"
set "APPLICATION_PORT=8081"
set "JWT_SECRET=4b9b4a7d9e6a7a1c3b1d2e8f5c6a7b8d9e0f1a2b3c4d5e6f7a8b9c0d1e2f3p1"
set "EUREKA_URL=http://localhost:8082/eureka/"
mvnw.cmd spring-boot:run
exit /b

:auth
cd /d "%~dp0auth-service"
set "DB_URL=jdbc:postgresql://localhost:5432/auth_service_db"
set "DB_USERNAME=postgres"
set "DB_PASSWORD=2122"
set "DB_DIALECT=org.hibernate.dialect.PostgreSQLDialect"
set "JWT_SECRET=4b9b4a7d9e6a7a1c3b1d2e8f5c6a7b8d9e0f1a2b3c4d5e6f7a8b9c0d1e2f3p1"
set "APPLICATION_PORT=8090"
set "EUREKA_URL=http://localhost:8082/eureka/"
mvnw.cmd spring-boot:run
exit /b

:user
cd /d "%~dp0user-service"
set "DB_URL=jdbc:postgresql://localhost:5432/user_service_db"
set "DB_USERNAME=postgres"
set "DB_PASSWORD=2122"
set "DB_DIALECT=org.hibernate.dialect.PostgreSQLDialect"
set "APPLICATION_PORT=8100"
set "EUREKA_URL=http://localhost:8082/eureka/"
mvnw.cmd spring-boot:run
exit /b

:order
cd /d "%~dp0order-service"
set "DB_URL=jdbc:postgresql://localhost:5432/order_service_db"
set "DB_USERNAME=postgres"
set "DB_PASSWORD=2122"
set "DB_DIALECT=org.hibernate.dialect.PostgreSQLDialect"
set "APPLICATION_PORT=8110"
set "EUREKA_URL=http://localhost:8082/eureka/"
set "MONGO_URI=mongodb://username:password@localhost:27017/order_status_db"
mvnw.cmd spring-boot:run
exit /b

:payment
cd /d "%~dp0payment-service"
set "DB_URL=jdbc:postgresql://localhost:5432/payment_service_db"
set "DB_USERNAME=postgres"
set "DB_PASSWORD=2122"
set "DB_DIALECT=org.hibernate.dialect.PostgreSQLDialect"
set "APPLICATION_PORT=8120"
set "EUREKA_URL=http://localhost:8082/eureka/"
mvnw.cmd spring-boot:run
exit /b

:restaurant
cd /d "%~dp0restaurant-service"
set "DB_URL=jdbc:postgresql://localhost:5432/restaurant_service_db"
set "DB_USERNAME=postgres"
set "DB_PASSWORD=2122"
set "DB_DIALECT=org.hibernate.dialect.PostgreSQLDialect"
set "APPLICATION_PORT=8130"
set "EUREKA_URL=http://localhost:8082/eureka/"
mvnw.cmd spring-boot:run
exit /b

:menu
cd /d "%~dp0menu-service"
set "DB_URL=jdbc:postgresql://localhost:5432/menu_service_db"
set "DB_USERNAME=postgres"
set "DB_PASSWORD=2122"
set "DB_DIALECT=org.hibernate.dialect.PostgreSQLDialect"
set "APPLICATION_PORT=8140"
set "EUREKA_URL=http://localhost:8082/eureka/"
mvnw.cmd spring-boot:run
exit /b

:notification
cd /d "%~dp0notification-service"
set "DB_URL=mongodb://username:password@localhost:27017/notification_db"
set "APPLICATION_PORT=8150"
set "EUREKA_URL=http://localhost:8082/eureka/"
mvnw.cmd spring-boot:run
exit /b

:rating
cd /d "%~dp0rating-service"
set "DB_URL=jdbc:postgresql://localhost:5432/review_db"
set "DB_USERNAME=postgres"
set "DB_PASSWORD=2122"
set "DB_DIALECT=org.hibernate.dialect.PostgreSQLDialect"
set "APPLICATION_PORT=8160"
set "EUREKA_URL=http://localhost:8082/eureka/"
mvnw.cmd spring-boot:run
exit /b

:favourite
cd /d "%~dp0favourite-service"
set "DB_URL=mongodb://username:password@localhost:27017/favourite_db"
set "APPLICATION_PORT=8170"
set "EUREKA_URL=http://localhost:8082/eureka/"
mvnw.cmd spring-boot:run
exit /b

:cart
cd /d "%~dp0cart-service"
set "DB_URL=jdbc:postgresql://localhost:5432/cart_service_db"
set "DB_USERNAME=postgres"
set "DB_PASSWORD=2122"
set "DB_DIALECT=org.hibernate.dialect.PostgreSQLDialect"
set "APPLICATION_PORT=8180"
set "EUREKA_URL=http://localhost:8082/eureka/"
mvnw.cmd spring-boot:run
exit /b
