#  Hotel App

REST API приложение на Spring Boot для управления данными отелей: поиск, фильтрация, добавление удобств и построение статистических (histogram) запросов.

---

##  Технологии

- Java 21
- Spring Boot 3.3.x
- Spring Web
- Spring Data JPA (Hibernate)
- Bean Validation (Jakarta Validation)
- MapStruct
- H2 Database (in-memory)
- SpringDoc OpenAPI (Swagger)
- Maven

---

##  Возможности приложения

###  Управление отелями
- Создание отеля
- Получение списка всех отелей
- Получение отеля по ID
- Поиск отелей с фильтрацией
- Пагинация и сортировка

---

###  Поиск и фильтрация
Поддерживаются фильтры:
- название (name, поиск по LIKE)
- бренд (brand)
- город (city)
- страна (country)
- удобства (amenities, мультифильтр)

---

###  Удобства (amenities)
- Добавление удобств к отелю
- Проверка на дубликаты
- Защита от повторного добавления

---

###  Histogram API (агрегации)
Поддерживаемые группировки:
- по бренду (BRAND)
- по городу (CITY)
- по стране (COUNTRY)
- по удобствам (AMENITIES)

---

##  API эндпоинты

### Базовый путь:
```md
GET /property-view/hotels/property-view
```

---

###  Отели

#### Получить все отели
```md
GET /property-view/hotels
```
---

#### Получить отель по ID
```md
GET /property-view/hotels/{id}
```
---

#### Создать отель
```md
POST /property-view/hotels
```
---

#### Поиск отелей
```md
GET /property-view/search
```
Параметры: name, brand, city, country, amenities, page, size, sortBy, direction

---

###  Удобства

#### Добавить удобства
```md
POST /property-view/hotels/{id}/amenities
```
Тело запроса:
```json
["WiFi", "Pool", "Parking"]
```
### Histogram
```md
GET /property-view/histogram?type=BRAND
```
#### Поддерживаемые значения: 
- BRAND
- CITY 
- COUNTRY 
- AMENITIES

## Swagger UI:
```md
http://localhost:8092/swagger-ui.html
```
