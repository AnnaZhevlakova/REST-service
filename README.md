Для запуска сервиса нужно выполнить команду docker-compose up.

Сервис работает на порту localhost:5500.

https://serp-ya.github.io/card-transfer/  фронтенд приложение развернуто по этому адресу , может отправлять запрос без
дополнительных дороботок на развернутый сервис.

Примеры запроса
curl --location 'http://localhost:5500/transfer' \
--header 'accept: application/json' \
--header 'Content-Type: application/json' \
--data '{
"cardFromNumber": "1111111111111111",
"cardFromValidTill": "12/26",
"cardFromCVV": "123",
"cardToNumber": "2222222222222222",
"amount": {
"value": 1000,
"currency": "RUR"
}
}'

curl --location 'http://localhost:5500/confirmOperation' \
--header 'accept: application/json' \
--header 'Content-Type: application/json' \
--data '{
"operationId": "3265879",
"code": "5986"
}'

Так как в сервисах отсутствует реализация в них кидается Exception, поэтому оба инпоинта сейчас возвращают 500 с
текстом "Что то пошло не так"
