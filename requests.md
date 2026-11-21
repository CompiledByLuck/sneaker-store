# Приклади curl-запитів — Sneaker Store (gz11 + gz12)

Усі запити спрямовані до `http://localhost:8080`. Для `POST` і `PUT` використовується окремий вхідний DTO `SneakerRequest`: поле `id` у тілі запиту не передається, його призначає сервер.

## 1. Отримати перелік усіх кросівок — GET

```bash
curl -i http://localhost:8080/api/sneakers
```

Очікується `200 OK` та масив карток із п'яти початкових позицій. Картка містить `id`, `brand`, `model`, `size`, `price`, `color`, `category` і `coverUrl`.

Форматований JSON (за наявності `jq`):

```bash
curl -s http://localhost:8080/api/sneakers | jq
```

## 2. Отримати одну пару з повною інформацією — GET

```bash
curl -i http://localhost:8080/api/sneakers/1
```

Очікується `200 OK` та об'єкт із повним набором полів, включно з `gender`, `material`, `season`, `soleMaterial`, `waterproof`, `images` і `description`.

## 3. Фільтрація за категорією — GET з query-параметром

```bash
curl -G --data-urlencode "category=Бігові" http://localhost:8080/api/sneakers
```

Очікується `200 OK` та масив позицій категорії `Бігові`. Пошук категорії не залежить від регістру. Інші приклади значень: `Повсякденні`, `Трейлові`.

Без параметра `category` або з порожнім значенням повертається повний каталог.

## 4. Створити нову пару — POST

```bash
curl -i -X POST http://localhost:8080/api/sneakers \
  -H "Content-Type: application/json" \
  -d '{
    "brand": "Reebok",
    "model": "Classic Leather",
    "size": 42,
    "price": 3499.00,
    "color": "Білий",
    "gender": "Унісекс",
    "category": "Повсякденні",
    "material": "Натуральна шкіра",
    "season": "Демісезон",
    "soleMaterial": "Гума",
    "waterproof": false,
    "coverUrl": "/images/reebok-classic.svg",
    "images": ["/images/reebok-classic.svg"],
    "description": "Класичні повсякденні кросівки зі шкіряним верхом."
  }'
```

Очікується `201 Created` та створений об'єкт у відповіді. Сервер сам призначає `id`; не додавайте його до JSON. У чистому сховищі після п'яти початкових записів нова позиція отримає `id=6`.

## 5. Повністю оновити пару — PUT

У прикладі оновлюється запис із `id=6`, створений попереднім запитом. Передайте всі поля, які потрібно зберегти в оновленому записі: `PUT` замінює значення ресурсу, а не змінює лише одне поле.

```bash
curl -i -X PUT http://localhost:8080/api/sneakers/6 \
  -H "Content-Type: application/json" \
  -d '{
    "brand": "Reebok",
    "model": "Classic Leather Updated",
    "size": 43,
    "price": 3699.00,
    "color": "Чорний",
    "gender": "Унісекс",
    "category": "Повсякденні",
    "material": "Натуральна шкіра",
    "season": "Демісезон",
    "soleMaterial": "Гума",
    "waterproof": false,
    "coverUrl": "/images/reebok-classic.svg",
    "images": ["/images/reebok-classic.svg"],
    "description": "Оновлений опис класичної повсякденної моделі."
  }'
```

Очікується `200 OK` та оновлений об'єкт. Ідентифікатор у URL залишається незмінним. Для неіснуючого ID повертається `404 Not Found`.

Перевірити результат оновлення:

```bash
curl http://localhost:8080/api/sneakers/6
```

## 6. Видалити пару — DELETE

```bash
curl -i -X DELETE http://localhost:8080/api/sneakers/6
```

Очікується `204 No Content` без тіла відповіді. Якщо ID не існує, повертається `404 Not Found`.

Перевірити, що запис видалено:

```bash
curl -i http://localhost:8080/api/sneakers/6
```

Очікується `404 Not Found`.

## 7. Перевірка 404 для інших операцій

Неіснуючий запис для читання:

```bash
curl -i http://localhost:8080/api/sneakers/999
```

Оновлення неіснуючого запису:

```bash
curl -i -X PUT http://localhost:8080/api/sneakers/999 \
  -H "Content-Type: application/json" \
  -d '{"brand":"Test","model":"Missing","size":42,"price":1000.00,"color":"Чорний","gender":"Унісекс","category":"Тестові","material":"Текстиль","season":"Літо","soleMaterial":"Гума","waterproof":false,"coverUrl":"/images/test.svg","images":[],"description":"Перевірка 404"}'
```

Видалення неіснуючого запису:

```bash
curl -i -X DELETE http://localhost:8080/api/sneakers/999
```

Для всіх трьох запитів очікується `404 Not Found`.

## 8. Перевірка статичного зображення

```bash
curl -i http://localhost:8080/images/adidas-ultraboost.svg
```

Очікується `200 OK` і `Content-Type: image/svg+xml`.

## Примітки

- Команди з `id=6` передбачають чистий запуск сховища: п'ять початкових записів мають ID від 1 до 5, тому перший створений запис отримує ID 6. Якщо до цього створювали інші записи, використовуйте `id`, повернений у відповіді `POST`.
- У Windows PowerShell використовуйте `curl.exe` замість `curl` (який може бути псевдонімом PowerShell). Для запитів із JSON зручно передавати ті самі аргументи через `curl.exe`.
- Дані зберігаються в оперативній пам'яті. Після перезапуску застосунку знову з'являються п'ять початкових позицій, а створені під час роботи записи зникають.
