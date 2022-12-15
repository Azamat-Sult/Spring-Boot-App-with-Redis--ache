Для запуска Redis введите в терминале:

````
docker pull redis

docker run -p 6379:6379 --name redis-srv redis
````
Чтобы посмотреть состояние кэша Redis  введите:
````
docker exec -it redis-srv /bin/bash
````
Часто используемые команды консоли Redis:
````
redis-cli keys * (check all current keys in redis database)
redis-cli FLUSHDB (clean redis database)
redis-cli info stats | grep 'keyspace_*' (check miss and hit, which will be explained later)
````