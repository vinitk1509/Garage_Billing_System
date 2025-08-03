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


CREATE TABLE bills (
    id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT,
    vehicle_id INT,
    date TIMESTAMP,
    total_cost DOUBLE,
    FOREIGN KEY (customer_id) REFERENCES customers(id),
    FOREIGN KEY (vehicle_id) REFERENCES vehicles(id)
);

CREATE TABLE bill_items (
    id INT PRIMARY KEY AUTO_INCREMENT,
    bill_id INT,
    service_id INT,
    cost DOUBLE,
    FOREIGN KEY (bill_id) REFERENCES bills(id),
    FOREIGN KEY (service_id) REFERENCES services(id)
);


create table bill_services(
bill_id int,
service_id int,
foreign key (bill_id) references bill(id),
foreign key (service_id) references services(id)
);
