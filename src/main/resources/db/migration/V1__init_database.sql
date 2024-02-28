CREATE SCHEMA IF NOT EXISTS basqaru;

CREATE TABLE basqaru.users
(
    user_id    SERIAL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name  VARCHAR(255) NOT NULL,
    email      VARCHAR(255) NOT NULL UNIQUE,
    password   VARCHAR(255) NOT NULL,
    roles      VARCHAR(255) NOT NULL
);

CREATE TABLE basqaru.accounts
(
    account_id   SERIAL PRIMARY KEY,
    account_name VARCHAR(255) NOT NULL,
    balance      DECIMAL      NOT NULL,
    user_id      INT,
    FOREIGN KEY (user_id) REFERENCES users (user_id)
);

CREATE TABLE basqaru.categories
(
    category_id SERIAL PRIMARY KEY,
    title       VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE basqaru.operations
(
    operation_id SERIAL PRIMARY KEY,
    type         SMALLINT NOT NULL,
    amount       DECIMAL,
    created_time TIMESTAMP,
    account_id   INT,
    category_id  INT,
    FOREIGN KEY (account_id) REFERENCES accounts (account_id),
    FOREIGN KEY (category_id) REFERENCES categories (category_id)
);
