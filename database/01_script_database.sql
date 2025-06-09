docker run --name mysql-container -e MYSQL_ROOT_PASSWORD=root -p 3306:3306 -v C:\Users\zurit\OneDrive\Escritorio\Gustavo\Database\MySQL -d mysql:latest

CREATE USER 'admin'@'%' IDENTIFIED BY 'admin';
GRANT ALL PRIVILEGES ON teatro.* TO 'admin'@'%';
FLUSH PRIVILEGES;

