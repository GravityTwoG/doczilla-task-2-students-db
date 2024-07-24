# Задание #2

## Запуск

Использовалась 20 версия Node.js и 22 версия JDK.

### Сервер

1. Создать файл application.properties в директории backend/src/main/resources и скопировать в него содержимое файла application.properties.example.
2. Запуск БД в докере `docker-compose up`
3. В InteliJ запустить класс Main из пакета org.gravitytwog.backend.Main или target [spring-boot:run]
4. Приложение само создаст нужную таблицу students
5. Приложение будет запущено на порту 8080
6. Open API спецификация доступна по адресу http://localhost:8080/api-docs
7. Swagger ui доступен по адресу http://localhost:8080/swagger-ui/index.html

### Клиент

Необходимо создать файл `.env` в директории client и указать в нем переменную `VITE_API_BASE_URL` с адресом http://localhost:8080.

```bash
npm ci
npm run dev
```
