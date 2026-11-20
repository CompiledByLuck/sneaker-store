# Sneaker Store — REST API магазину спортивного взуття

Навчальний backend-проєкт для самостійної роботи до заняття 1.1. Предметна область — магазин кросівок. Проєкт реалізує два GET-ендпоінти: перелік кросівок та детальну інформацію про одну позицію.

## Технології

- Java 21
- Spring Boot 3.4.13
- Spring Web (Spring MVC / REST)
- Maven
- Вбудований Tomcat
- Зберігання початкових даних у пам'яті (`ConcurrentHashMap`), без бази даних

## Що реалізовано

| Вимога | Реалізація |
|---|---|
| Власна предметна область | `Sneaker` — модель кросівок із брендом, моделлю, розміром, кольором, категорією, матеріалом, сезоном тощо |
| Два GET-ендпоінти | `GET /api/sneakers` і `GET /api/sneakers/{id}` |
| DTO замість доменної моделі | `SneakerCardDto` для переліку та `SneakerDetailDto` для деталей |
| Багатошарова архітектура | `domain`, `repository`, `service`, `web`, `exception`, `config` |
| Інтерфейси на межах | `SneakerRepository` і `SneakerService` |
| DI через конструктор | Сервіс, контролер і сидер отримують залежності через конструктори |
| Початкове наповнення | `SneakerSeeder` додає п'ять записів під час запуску |
| 404 для відсутнього ID | `SneakerNotFoundException` із `@ResponseStatus(HttpStatus.NOT_FOUND)` |
| Статичні зображення | SVG-файли в `src/main/resources/static/images/` |
| Приклади запитів | Файл [`requests.md`](requests.md) |
| Автоматичні перевірки | `SneakerControllerTest` перевіряє список, деталі, 404 та SVG-ресурс |

## Структура проєкту

```text
sneaker-store/
├── .mvn/wrapper/              налаштування Maven launcher
├── src/
│   ├── main/
│   │   ├── java/com/ua/sneakerstore/
│   │   │   ├── SneakerStoreApplication.java
│   │   │   ├── config/SneakerSeeder.java
│   │   │   ├── domain/Sneaker.java
│   │   │   ├── exception/SneakerNotFoundException.java
│   │   │   ├── repository/
│   │   │   │   ├── SneakerRepository.java
│   │   │   │   └── InMemorySneakerRepository.java
│   │   │   ├── service/
│   │   │   │   ├── SneakerService.java
│   │   │   │   ├── SneakerServiceImpl.java
│   │   │   │   └── mapper/SneakerMapper.java
│   │   │   └── web/
│   │   │       ├── SneakerController.java
│   │   │       └── dto/
│   │   │           ├── SneakerCardDto.java
│   │   │           └── SneakerDetailDto.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── static/images/     SVG-зображення кросівок
│   └── test/java/.../SneakerControllerTest.java
├── requests.md
├── pom.xml
├── mvnw
└── mvnw.cmd
```

## 1. Вимоги до комп'ютера

Потрібна **Java 21**. Перевірте її командою:

```bash
java -version
```

У виводі має бути версія `21`. У проєкті є `mvnw` (Linux/macOS) та `mvnw.cmd` (Windows). Під час першого запуску launcher завантажує Maven 3.9.9 і кешує його в домашньому каталозі користувача. Для цього потрібен доступ до Інтернету. Якщо Maven 3.9.9 уже встановлений і доступний у `PATH`, можна запускати його напряму командою `mvn`.

## 2. Як відкрити проєкт

1. Розпакуйте `sneaker-store.zip` у зручну папку.
2. В IntelliJ IDEA виберіть **File → Open** і вкажіть папку `sneaker-store` (саме ту, де лежить `pom.xml`).
3. Підтвердіть імпорт Maven-проєкту та дочекайтеся завантаження залежностей.
4. Переконайтеся, що Project SDK встановлено на Java 21.

Також можна працювати з проєктом через термінал, не відкриваючи IntelliJ IDEA.

## 3. Як створити GitHub-репозиторій і додати до нього проєкт

### Крок 1. Створіть порожній репозиторій на GitHub

1. Увійдіть на [github.com](https://github.com/).
2. Натисніть **New repository**.
3. Вкажіть назву, наприклад `sneaker-store`.
4. Оберіть Public або Private.
5. Для цього способу залиште **Add a README**, `.gitignore` та License вимкненими — ці файли вже є у проєкті.
6. Натисніть **Create repository**. Скопіюйте HTTPS-адресу репозиторію, наприклад `https://github.com/USERNAME/sneaker-store.git`.

### Крок 2. Ініціалізуйте Git у папці проєкту

Відкрийте термінал у корені `sneaker-store` (там, де знаходиться `pom.xml`) і виконайте:

```bash
git init
git add .
git commit -m "feat: create sneaker store REST API"
git branch -M main
git remote add origin https://github.com/USERNAME/sneaker-store.git
git push -u origin main
```

Замініть `USERNAME` на свій GitHub-логін. Якщо Git запитає авторизацію, увійдіть через браузер або використайте налаштований Git Credential Manager / SSH.

Перевірити прив'язаний віддалений репозиторій можна так:

```bash
git remote -v
```

**Важливо:** команди `git init` і `git remote add origin` виконуються для цієї папки один раз. Коли згодом зміните код, достатньо:

```bash
git add .
git commit -m "опис змін"
git push
```

Якщо Git ще не встановлений, установіть Git for Windows або Git для своєї ОС і повторіть команди в новому терміналі.

## 4. Як запустити backend

Запускати команди потрібно з кореня проєкту.

### Windows (PowerShell або cmd)

```powershell
.\mvnw.cmd spring-boot:run
```

### Linux / macOS

```bash
./mvnw spring-boot:run
```

Під час першого запуску Maven завантажить залежності Spring Boot. Після успішного старту в консолі з'явиться повідомлення на кшталт `Started SneakerStoreApplication`. Сервер слухає порт `8080`.

Відкрийте другий термінал для `curl`-запитів. Перший термінал із backend має залишатися відкритим. Щоб зупинити застосунок, натисніть `Ctrl+C`.

## 5. Як перевірити API через curl

### 5.1. Отримати перелік усіх кросівок

```bash
curl http://localhost:8080/api/sneakers
```

Очікується JSON-масив із п'яти карток. У картці навмисно немає повного опису й усіх технічних характеристик.

### 5.2. Отримати одну пару з усіма деталями

```bash
curl http://localhost:8080/api/sneakers/1
```

Для `id=1` повертається Adidas Ultraboost Light із розміром, матеріалом, сезоном, типом підошви, ознакою водонепроникності, двома шляхами до зображень і описом.

### 5.3. Перевірити помилку 404

```bash
curl -i http://localhost:8080/api/sneakers/999
```

Очікуваний HTTP-статус — `404 Not Found`, оскільки запису з таким ID немає.

### 5.4. Перевірити, що зображення доступне як статика

```bash
curl -i http://localhost:8080/images/adidas-ultraboost.svg
```

Очікується `200 OK` і `Content-Type: image/svg+xml`.

### Windows PowerShell

У PowerShell використовуйте `curl.exe`:

```powershell
curl.exe http://localhost:8080/api/sneakers
curl.exe http://localhost:8080/api/sneakers/1
curl.exe -i http://localhost:8080/api/sneakers/999
curl.exe -i http://localhost:8080/images/adidas-ultraboost.svg
```

Усі ці приклади збережені також у [`requests.md`](requests.md).

## 6. Запуск тестів

Windows:

```powershell
.\mvnw.cmd test
```

Linux/macOS:

```bash
./mvnw test
```

Тести перевіряють, що API повертає п'ять карток, деталі першої пари, статус 404 для невідомого ID та SVG-зображення.

## 7. Якщо порт 8080 зайнятий

Можна запустити застосунок на іншому порту.

Windows PowerShell:

```powershell
$env:SERVER_PORT=8081
.\mvnw.cmd spring-boot:run
```

Linux/macOS:

```bash
SERVER_PORT=8081 ./mvnw spring-boot:run
```

Після цього у всіх `curl`-командах замініть порт `8080` на `8081`.

## 8. Важливі особливості цієї роботи

- Це **лише backend**. React або інший UI до gz11 не входить.
- На цьому етапі є тільки два GET-ендпоінти. Створення, редагування й видалення кросівок не реалізовані.
- Дані зберігаються в оперативній пам'яті. Після перезапуску сервер знову завантажує п'ять початкових записів із `SneakerSeeder`.
- Один запис каталогу описує конкретний розмір моделі. Наприклад, різні розміри тієї самої моделі в реальному магазині могли б бути окремими позиціями.
