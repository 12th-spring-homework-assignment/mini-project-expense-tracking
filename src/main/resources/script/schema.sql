CREATE DATABASE expense_tracking;

CREATE TABLE users
(
    user_id       SERIAL PRIMARY KEY,
    email         VARCHAR(255) NOT NULL,
    password      VARCHAR(255) NOT NULL,
    profile_image VARCHAR(255)
);

CREATE TABLE otps
(
    otp_id     SERIAL PRIMARY KEY,
    otp_code   VARCHAR(4) NOT NULL,
    issued_at  TIMESTAMP  NOT NULL,
    expiration TIMESTAMP  NOT NULL,
    verify     BOOLEAN    NOT NULL,
    user_id    INT        NOT NULL,
    CONSTRAINT users_fk FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE categories
(
    category_id SERIAL PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    user_id     INT          NOT NULL,
    CONSTRAINT users_fk FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE expenses
(
    expense_id  SERIAL PRIMARY KEY,
    amount      FLOAT     NOT NULL,
    description VARCHAR(255),
    date        TIMESTAMP NOT NULL,
    user_id     INT       NOT NULL,
    category_id INT       NOT NULL,
    CONSTRAINT users_fk FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT categories_fk FOREIGN KEY (category_id) REFERENCES categories (category_id) ON DELETE CASCADE ON UPDATE CASCADE
);