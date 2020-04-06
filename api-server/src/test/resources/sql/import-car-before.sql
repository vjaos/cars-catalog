DELETE
FROM cars;

INSERT INTO cars(car_number, color, release_year, brand, created, updated)
VALUES ('abc123', 'Black', 2005, 'Lexus', now(), now())