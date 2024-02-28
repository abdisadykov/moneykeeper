-- Вставка тестовых данных в таблицу user
INSERT INTO basqaru.users (first_name, last_name, email, password, roles)
VALUES ('John', 'Doe', 'john@google.com', 'password123', 'ROLE_USER'),
       ('Jane', 'Smith', 'jane@outlook.com', 'password456', 'ROLE_USER');

-- Вставка тестовых данных в таблицу account
INSERT INTO basqaru.accounts (account_name, balance, user_id)
VALUES ('Savings', 1000.00, 1),
       ('Checking', 500.00, 2);

-- Вставка тестовых данных в таблицу category
INSERT INTO basqaru.categories (title)
VALUES ('Food'),
       ('Transport'),
       ('Housing'),
       ('Entertainment');

-- Вставка тестовых данных в таблицу operation
INSERT INTO basqaru.operations (type, amount, account_id, category_id, created_time)
VALUES (0, 2000.00, 1, 1, NOW()),
       (1, 50.00, 2, 2, NOW()),
       (1, 100.00, 1, 3, NOW());
