## Операционные системы и виртуализация (Linux)

1. Использование команды cat в Linux

- Создать два текстовых файла: "Pets"(Домашние животные) и "Pack animals"(вьючные животные), используя команду `cat` в терминале Linux. В первом файле перечислить собак, кошек и хомяков. Во втором — лошадей, верблюдов и ослов.

- Объединить содержимое этих двух файлов в один и просмотреть его содержимое.

- Переименовать получившийся файл в "Human Friends"(.

Пример конечного вывода после команды “” :

``` 
ls
cat > pets
cat > pack_animals
ls
cat pets pack_animals > file3
ls
mv file3 HumanFriends 
```

2. Работа с директориями в Linux

- Создать новую директорию и переместить туда файл "Human Friends".

```
ls
mkdir newdir
sudo mv HumanFriends /home/matvei/newdir
ls
cd newdir
ls
cd ..
```


3. Работа с MySQL в Linux. “Установить MySQL на вашу вычислительную машину ”

- Подключить дополнительный репозиторий MySQL и установить один из пакетов из этого репозитория.

```
sudo apt update 
sudo apt install
sudo apt install mysql-server
wget https://dev.mysql.com/get/mysql-apt-config_0.8.12-1_all.deb
sudo apt install ./mysql-apt-config_0.8.12-1_all.deb
```

4. Управление deb-пакетами

- Установить и затем удалить deb-пакет, используя команду `dpkg`.
```
sudo apt update 
sudo apt install
sudo apt search nginx
apt list nginx
sudo apt install -y nginx
cd /etc/nginx
ll
sudo apt purge nginx
sudo dpkg -P nginx
```

5. История команд в терминале Ubuntu

- Сохранить и выложить историю ваших терминальных команд в Ubuntu.
```
history
```