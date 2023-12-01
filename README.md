# Weather-analyzer

### Настройка
Для того чтобы запустить приложение должна быть установлена java 17 и в mysql создана бд
для внесения своих параметров измените url, username, password в файле [application.yaml](src%2Fmain%2Fresources%2Fapplication.yaml)

### Запуск
При первом запуске приложение само создаст таблицы и наполнит их данными.
При последующем перезапуске приложения нужно раскомментировать закомментированные строки в [application.yaml](src%2Fmain%2Fresources%2Fapplication.yaml) 
и закомментировать следующее:
* jpa:
  * hibernate:
    * ddl-auto: none
  * defer-datasource-initialization: true
* sql:
  * init:
    * mode: always 


### Тестирование
Для тестирования создан файл [test-requests.http](test-requests.http).
<br>
В первом запросе возвращается последняя добавленная запись в бд
(GET http://localhost:8080/weather)
Пример: 
```json
{
  "temperature": -8.9,
  "wind": 14802.8,
  "pressure": 1006.0,
  "humidity": 95,
  "condition": "Partly cloudy",
  "location": {
    "name": "Minsk",
    "region": "Minsk",
    "country": "Belarus",
    "lat": 53.9,
    "lon": 27.57,
    "tzId": "Europe/Minsk",
    "localTimeEpoch": 1701457335,
    "localTime": "2023-12-01T22:02:00"
  }
}
```
Второй, третий и четвертый запрос будут возвращать данные в формате
```json
{
  "avg_tempC": -7.0,
  "avg_wind_mph": 15085.0,
  "avg_pressure_mb": 1001.0,
  "avg_humidity": 95.0
}
```
Данные запросы различаются лишь количеством дней в выборке.
<br>
Во втором средняя погода за сегодня (GET http://localhost:8080/avg-weather).
<br>
В третьем с 2023-11-27 по сегодня (GET http://localhost:8080/avg-weather?from=2023-11-27).
<br>
В четвертом с 2023-11-27 по 2023-11-30 (GET http://localhost:8080/avg-weather?from=2023-11-27&to?2023-11-30).

Для тестирования запросов изменяйте параметры `from` и `to`, формат даты: год-месяц-день. Предусмотрена ошибка ввода неверных дат.
