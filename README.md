# aikam-test-task

Подготовка:
1. Создать новую базу данных
2. Экспортировать данные, выполнив `pg_restore -U <имя пользователя> -d <название базы данных> aikamtestdb.tar`
3. Добавить информацию для доступа к базе данных в файл src/main/resources/db.properties

Сборка:
~~~
mvn package
~~~

Запуск:
~~~
cd target
java -jar aikam-test-task-1.0.jar search search_input.json search_output.json - запуск операции search
java -jar aikam-test-task-1.0.jar stat stat_input.json stat_output.json - запуск операции stat
~~~
