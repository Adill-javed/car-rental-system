CREATE DATABASE IF NOT EXISTS car_rental;
USE car_rental;

CREATE TABLE IF NOT EXISTS cars (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100),
  base_price_per_day DOUBLE,
  available BOOLEAN DEFAULT TRUE
);


INSERT INTO cars (name, base_price_per_day, available) VALUES
('Honda City', 30.0, TRUE),
('Swift', 25.0, TRUE),
('Toyota Innova', 50.0, TRUE),
('Kia Seltos', 45.0, TRUE),
('Ford Mustang',65.0,TRUE);