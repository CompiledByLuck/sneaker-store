# Звіт про виконання gz11

**Тема:** backend інтернет-магазину спортивного взуття (Sneaker Store).

## Виконані вимоги

1. **Створено окремий Maven-проєкт** `sneaker-store` на Java 21 та Spring Boot 3.4.13 із залежністю Spring Web.
2. **Визначено власну предметну область.** Доменна сутність `Sneaker` містить поля `brand`, `model`, `size`, `price`, `color`, `gender`, `category`, `material`, `season`, `soleMaterial`, `waterproof`, `coverUrl`, `images`, `description`.
3. **Реалізовано багатошарову архітектуру:**
   - `domain` — доменна модель `Sneaker`;
   - `repository` — інтерфейс `SneakerRepository` та реалізація `InMemorySneakerRepository`;
   - `service` — інтерфейс `SneakerService`, реалізація `SneakerServiceImpl`;
   - `service.mapper` — перетворення доменної моделі на DTO;
   - `web` — `SneakerController`;
   - `web.dto` — DTO для картки та детальної сторінки;
   - `exception` — обробка ситуації, коли ID не знайдений;
   - `config` — початкове наповнення каталогу.
4. **Застосовано DI через конструктори.** Контролер залежить від інтерфейсу сервісу, сервіс — від інтерфейсу репозиторію та маппера.
5. **Створено два GET-ендпоінти:**
   - `GET /api/sneakers` — повертає масив `SneakerCardDto`;
   - `GET /api/sneakers/{id}` — повертає `SneakerDetailDto`.
6. **Реалізовано 404.** Якщо кросівок із заданим ID немає, виникає `SneakerNotFoundException` зі статусом `404 Not Found`.
7. **Додано п'ять початкових записів** у `SneakerSeeder`. Сховище працює в пам'яті, без бази даних.
8. **Додано статичні SVG-зображення** у `src/main/resources/static/images/`.
9. **Підготовлено `requests.md`** з командами `curl` для переліку, деталей, 404 та статичного зображення.
10. **Додано інтеграційні тести** для основних HTTP-сценаріїв.

## Як перевіряти

Інструкція зі створення GitHub-репозиторію, запуску застосунку, тестів і перевірки `curl` наведена в [`README.md`](README.md). Команди запитів також винесені у [`requests.md`](requests.md).

## Примітка щодо виконання збірки

Проєкт підготовлено з Maven Wrapper launcher. Для першого запуску потрібен доступ до Інтернету, щоб завантажити Maven і залежності. У середовищі створення архіву Maven не був встановлений, а мережевий доступ до Maven Central / Apache Archive був недоступний, тому виконати `mvn test` тут не вдалося. Запустіть перевірку локально командою `./mvnw test` або `mvnw.cmd test` після розпакування.
