# Приклади curl-запитів — Sneaker Store

Перед виконанням запитів запустіть backend на `http://localhost:8080`.

## 1. Перелік кросівок (картки каталогу)

```bash
curl http://localhost:8080/api/sneakers
```

У відповіді буде масив із п'яти об'єктів. Кожна картка містить `id`, `brand`, `model`, `size`, `price`, `color`, `category` і `coverUrl`.

Форматований JSON (за наявності `jq`):

```bash
curl -s http://localhost:8080/api/sneakers | jq
```

## 2. Детальна інформація про одну пару

```bash
curl http://localhost:8080/api/sneakers/1
```

Детальна відповідь містить також `gender`, `material`, `season`, `soleMaterial`, `waterproof`, масив `images` та `description`.

## 3. Кросівки з невідомим ID — очікується 404

```bash
curl -i http://localhost:8080/api/sneakers/999
```

У першому рядку відповіді має бути HTTP-статус `404`.

## 4. Отримати SVG-зображення як статичний ресурс

```bash
curl -i http://localhost:8080/images/adidas-ultraboost.svg
```

Очікується `200 OK` і тип вмісту `image/svg+xml`.

Інші наявні зображення: `nike-pegasus.svg`, `puma-suede.svg`, `newbalance-574.svg`, `asics-gel-venture.svg`. Для кожної моделі також є файл `-detail.svg`.

## 5. Windows PowerShell

У PowerShell використовуйте `curl.exe`, щоб уникнути конфлікту з псевдонімом `curl`:

```powershell
curl.exe http://localhost:8080/api/sneakers
curl.exe http://localhost:8080/api/sneakers/1
curl.exe -i http://localhost:8080/api/sneakers/999
curl.exe -i http://localhost:8080/images/adidas-ultraboost.svg
```

> На занятті 1.1 реалізовано лише читання (`GET`). Запитів `POST`, `PUT`, `PATCH` або `DELETE` у цьому проєкті немає.
