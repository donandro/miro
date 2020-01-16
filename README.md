# Решение для тестового задания в Miro.
Разрабатывал в Eclipse IDE. 

### Методы сервиса:

#### Создание виджета:
POST /api/widgets

Пример запроса
```json
{
	"x": 50,
	"y": 50,
	"zIndex": 2,
	"width": 100,
	"height": 500
}
```

Пример ответа
```json
{
    "id": 1,
    "x": 50,
    "y": 50,
    "zIndex": 2,
    "width": 100,
    "height": 50,
    "modifyDate": null
}
```

#### Получение виджета
POST /api/widgets/1

Пример ответа
```json
{
    "id": 1,
    "x": 50,
    "y": 50,
    "zIndex": 2,
    "width": 100,
    "height": 50,
    "modifyDate": null
}
```

#### Изменение виджета:
PATCH /api/widgets/1

Пример запроса:
```json
{
	"x": 200,
	"y": 200,
	"zIndex": 2,
	"width": 1,
	"height": 1
}
```

Пример ответа:
```json
{
    "id": 1,
    "x": 200,
    "y": 200,
    "zIndex": 2,
    "width": 1,
    "height": 1,
    "modifyDate": "2020-01-16T18:28:40.047+0000"
}
```

#### Список виджетов:
GET /api/widgets

Для пагинации в запросе на список виджетов нужно использовать GET-параметры "pageNumber" и "pageSize". (прим. http://localhost:8080/api/widgets?pageNumber=3&pageSize=5)
Формат ответа для данного запроса:
```json
{
    "items": [
        {
            "id": 15,
            "x": 50,
            "y": 50,
            "zIndex": 11,
            "width": 888,
            "height": 999,
            "modifyDate": "2020-01-16T17:39:12.416+0000"
        }
		...
    ],
    "pageNumber": 3,
    "pageSize": 5,
    "itemsCount": 24
}
```
Сделаны доп задания 1 и 4.
Для настройки выбора хранилища используется файл настроек application.properties в параметре app.store.current, значения : memory/DB. Для In-memory db используется H2.
Сервис запускается на порту 8080
