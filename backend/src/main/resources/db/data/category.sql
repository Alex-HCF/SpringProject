INSERT INTO category (name)
VALUES ('Personal items'),
       ('Realty'),
       ('Transport');

INSERT INTO category (name, parent_id)
VALUES ('Personal items', null),
       ('Realty', null),
       ('Transport', null),
       ('Electronics', null),
       ('Telephones', 4),
       ('Clothes', 1),
       ('Man clothes', 4),
       ('Shoes', 5),
       ('Shirts', 5),
       ('Female clothes', 4),
       ('Houses', 2),
       ('Apartments', 2),
       ('Cars', 3),
       ('Motorcycles', 3);