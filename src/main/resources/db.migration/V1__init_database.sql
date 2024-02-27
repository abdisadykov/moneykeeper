CREATE SCHEMA IF NOT EXISTS moneykeeper;

CREATE TABLE moneykeeper.users
(
    user_id    SERIAL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name  VARCHAR(255) NOT NULL,
    email      VARCHAR(255) NOT NULL UNIQUE,
    password   VARCHAR(255) NOT NULL,
    roles      VARCHAR(255) NOT NULL
);

CREATE TABLE moneykeeper.accounts
(
    account_id   SERIAL PRIMARY KEY,
    account_name VARCHAR(255) NOT NULL,
    balance      DECIMAL      NOT NULL,
    user_id      INT,
    FOREIGN KEY (user_id) REFERENCES user (user_id)
);

CREATE TABLE moneykeeper.categories
(
    category_id SERIAL PRIMARY KEY,
    title       VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE moneykeeper.operations
(
    operation_id SERIAL PRIMARY KEY,
    type         VARCHAR(255) NOT NULL,
    amount       DECIMAL,
    created_time TIMESTAMP,
    account_id   INT,
    category_id  INT,
    FOREIGN KEY (account_id) REFERENCES account (account_id),
    FOREIGN KEY (category_id) REFERENCES category (category_id)
);
