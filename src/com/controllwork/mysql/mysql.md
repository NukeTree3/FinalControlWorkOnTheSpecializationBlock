### sql

7.1. После создания диаграммы классов в 6 пункте, в 7 пункте база данных "Human Friends" должна быть структурирована в соответствии с этой диаграммой. Например, можно создать таблицы, которые будут соответствовать классам "Pets" и "Pack animals", и в этих таблицах будут поля, которые характеризуют каждый тип животных (например, имена, даты рождения, выполняемые команды и т.д.).

7.2 - В ранее подключенном MySQL создать базу данных с названием "Human Friends".

- Создать таблицы, соответствующие иерархии из вашей диаграммы классов.

```roomsql
USE human_friends;

CREATE TABLE cats (
id INT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(255),
birthdate DATE,
commands VARCHAR(255),
type VARCHAR(255)
);

CREATE TABLE dogs (
id INT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(255),
birthdate DATE,
commands VARCHAR(255),
type VARCHAR(255)
);

CREATE TABLE hamsters (
id INT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(255),
birthdate DATE,
commands VARCHAR(255),
type VARCHAR(255)
);

CREATE TABLE horses (
id INT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(255),
birthdate DATE,
commands VARCHAR(255),
type VARCHAR(255)
);

CREATE TABLE camels (
id INT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(255),
birthdate DATE,
commands VARCHAR(255),
type VARCHAR(255)
);

CREATE TABLE donkey (
id INT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(255),
birthdate DATE,
commands VARCHAR(255),
type VARCHAR(255)
);
```

- Заполнить таблицы данными о животных, их командах и датами рождения.

```roomsql
INSERT INTO cats (name, birthdate, commands, type) VALUES
('Дымок', '2024-11-17', 'Кушать', 'cat'),
('Шайни', '2024-10-10', 'Кушать', 'cat'),
('Стив', '2006-08-09', 'Кушать', 'cat'),
('Бибер', '2000-03-03', 'Кушать', 'cat');

INSERT INTO dogs (name, birthdate, commands, type) VALUES
('Кит', '2000-03-18', 'Сидеть, Кушать, Нельзя, Гулять', 'dog'),
('Белый Бим', '1971-10-31', 'Сидеть, Кушать, Нельзя, Гулять', 'dog'),
('Неля', '2018-09-01', 'Сидеть, Кушать, Нельзя, Гулять', 'dog'),
('Стич', '2020-11-03', 'Сидеть, Кушать, Нельзя', 'dog');

INSERT INTO hamsters (name, birthdate, commands, type) VALUES
('Семен', '2017-03-05', '', 'hamster'),
('Федор', '2014-05-19', '', 'hamster');

INSERT INTO horses (name, birthdate, commands, type) VALUES
('Звезда', '2016-05-09', 'Рысь, Галоп, Галопом, Прыжок', 'horse'),
('Искорка', '2003-12-25', 'Рысь, Галоп, Галопом, Прыжок', 'horse'),
('Спирит', '2000-09-21', '', 'horse'),
('Ветерок', '2000-10-13','Рысь, Галоп, Галопом, Прыжок', 'horse');

INSERT INTO camels (name, birthdate, commands, type) VALUES
('Дюна', '2024-10-07', 'Ходить, Нести груз', 'camel'),
('Песок', '2017-06-25', 'Ходить, Нести груз', 'camel');

INSERT INTO donkey (name, birthdate, commands, type) VALUES
('Иа', '1972-09-09', '', 'donkey'),
('Пух', '1999-06-29', 'Ходить, Нести груз', 'donkey'),
('Боря', '2005-08-13', 'Ходить, Нести груз', 'donkey');

SELECT * FROM cats;
SELECT * FROM dogs;
SELECT * FROM hamsters;
SELECT * FROM horses;
SELECT * FROM camels;
SELECT * FROM donkey;
```

- Удалить записи о верблюдах и объединить таблицы лошадей и ослов.

```roomsql
DELETE FROM camels WHERE type = 'camel';

CREATE TABLE horses_donkeys (
 id INT AUTO_INCREMENT PRIMARY KEY,
 name VARCHAR(255),
 birthdate DATE,
 commands VARCHAR(255),
 type VARCHAR(255)
);

INSERT INTO horses_donkeys (name, birthdate, commands, type)
SELECT name, birthdate, commands, type FROM donkey 
UNION ALL 
SELECT name, birthdate, commands, type FROM donkey;
```

- Создать новую таблицу для животных в возрасте от 1 до 3 лет и вычислить их возраст с точностью до месяца.

```roomsql
CREATE TABLE young_animals (
 id INT AUTO_INCREMENT PRIMARY KEY,
 name VARCHAR(255),
 birthdate DATE,
 commands VARCHAR(255),
 type VARCHAR(255)
);

INSERT INTO young_animals (name, birthdate, commands, type)
SELECT name, birthdate, commands, type FROM cats
WHERE TIMESTAMPDIFF(YEAR, birthdate, CURDATE()) BETWEEN 1 AND 3
UNION ALL
SELECT name, birthdate, commands, type FROM dogs
WHERE TIMESTAMPDIFF(YEAR, birthdate, CURDATE()) BETWEEN 1 AND 3
UNION ALL
SELECT name, birthdate, commands, type FROM hamsters
WHERE TIMESTAMPDIFF(YEAR, birthdate, CURDATE()) BETWEEN 1 AND 3
UNION ALL
SELECT name, birthdate, commands, type FROM horses
WHERE TIMESTAMPDIFF(YEAR, birthdate, CURDATE()) BETWEEN 1 AND 3
UNION ALL
SELECT name, birthdate, commands, type FROM donkey
WHERE TIMESTAMPDIFF(YEAR, birthdate, CURDATE()) BETWEEN 1 AND 3
UNION ALL
SELECT name, birthdate, commands, type FROM camels
WHERE TIMESTAMPDIFF(YEAR, birthdate, CURDATE()) BETWEEN 1 AND 3;

ALTER TABLE young_animals
ADD COLUMN age_in_months INT;

UPDATE young_animals
SET age_in_months = TIMESTAMPDIFF(MONTH, birthdate, CURDATE());

SELECT * FROM young_animals;
```

- Объединить все созданные таблицы в одну, сохраняя информацию о принадлежности к исходным таблицам.

```roomsql
CREATE TABLE all_animals (
 id INT AUTO_INCREMENT PRIMARY KEY,
 name VARCHAR(255),
 birthdate DATE,
 commands VARCHAR(255),
 type VARCHAR(255)
);

INSERT INTO all_animals (name, birthdate, commands, type)
SELECT name, birthdate, commands, type
FROM cats
UNION ALL
SELECT name, birthdate, commands, type
FROM dogs
UNION ALL
SELECT name, birthdate, commands, type
FROM hamsters
UNION ALL
SELECT name, birthdate, commands, type
FROM horses
UNION ALL
SELECT name, birthdate, commands, type
FROM donkey
UNION ALL
SELECT name, birthdate, commands, type
FROM camels;

select * from all_animals;
```