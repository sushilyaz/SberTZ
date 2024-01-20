### CRUD for mobile
Я далек от мысли, что с вашей нагрузкой вы будете заглядывать в каждый файл и читать комментарии, поэтому я в кратце напишу все здесь

- java 17
- Gradle
- Документирование
- Логирование с помощью sout
- Принципы DRY, YAGNI соблюдены, также написаны интеграционные тесты на каждый из обработчиков(mockito)

О коде:
- CRUD по мобильным телефонам (из вашего примера).
- Все реализовал согласно требованиям ТЗ, ничего лишнего не добавлял. Мог делать и без dto и работать с обычной сущностью (знаю, что так нельзя по многим причинам),
но я привык все делать с dto и мне уже так быстрее
- Данный проект можно развернуть и прогнать по тестам
- Все как бы "нетривиальные" моменты подробнее расписал в комментариях

В main помимо dto, service, repository, dto, controller использовал:
- Мапперы для автоматической генерации кода преобразования из dto в сущность и наоборот, а также для частичного update
- обработку исключения ResourceNotFoundException (база)
- конфиг для работы с JsonNullable

В test использовал:
- mockito
- Генерацию модели с помощью Instancio(чтобы не настраивать каждый раз в ручную объект тестирования) и Faker(генерация данных)
- Все тесты друг от друга само собой изолированы и написаны на каждый обработчик (в том числе и на частичное обновление)

