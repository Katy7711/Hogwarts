CREATE TABLE car (
                     id INTEGER PRIMARY KEY,
                     brand TEXT,
                     model TEXT,
                     price INTEGER
);

CREATE TABLE car_owners (
                            id INTEGER PRIMARY KEY,
                            name TEXT,
                            age INTEGER,
                            driver_license BOOLEAN,
                            car_id INTEGER REFERENCES car (id)
);