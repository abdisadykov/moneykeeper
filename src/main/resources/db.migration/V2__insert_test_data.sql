-- Вставка тестовых данных в таблицу user
INSERT INTO moneykeeper.users (first_name, last_name, email, password, roles)
VALUES ('John', 'Doe', 'john@google.com', 'password123', 'ROLE_USER'),
       ('Jane', 'Smith', 'jane@outlook.com', 'password456', 'ROLE_USER');

-- Вставка тестовых данных в таблицу account
INSERT INTO moneykeeper.accounts (account_name, balance, user_id)
VALUES ('Savings', 1000.00, 1),
       ('Checking', 500.00, 2);

-- Вставка тестовых данных в таблицу category
INSERT INTO moneykeeper.categories (title)
VALUES ('Food'),
       ('Transport'),
       ('Housing'),
       ('Entertainment');

-- Вставка тестовых данных в таблицу operation
INSERT INTO moneykeeper.operations (type, amount, account_id, category_id, created_time)
VALUES ('INCOME', 2000.00, 1, 1, NOW()),
       ('EXPENSE', 50.00, 2, 2, NOW()),
       ('EXPENSE', 100.00, 1, 3, NOW());
