create database garage_sys_db;
use garage_sys_db;

create table customers(
id int auto_increment primary key,
name varchar(100) NOT null,
phone varchar(15),
email varchar(100)
);

SELECT * FROM customers;
SELECT * FROM vehicles;
SELECT * FROM services;


create table vehicles(
id INT PRIMARY KEY AUTO_INCREMENT,
customer_id int,
number_plate varchar(20),
model varchar(50),
type varchar(50),
foreign key (customer_id) references customers(id)
);

create table services(
id int primary key auto_increment,
name varchar(255),
price decimal(10,2)
);

INSERT INTO services (name, price) VALUES
('Oil Change', 1500.00),
('Wheel Alignment', 800.00),
('Brake Inspection', 600.00),
('Engine Diagnostics', 2000.00),
('Battery Replacement', 3000.00),
('Car Wash', 400.00),
('AC Service', 1800.00);

