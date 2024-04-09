CREATE
    DATABASE expense_tracking;

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE users
(
    user_id       UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    email         VARCHAR(255) NOT NULL,
    password      VARCHAR(255) NOT NULL,
    profile_image VARCHAR(255)
);

CREATE TABLE otps
(
    otp_id     UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    otp_code   VARCHAR(10) NOT NULL,
    issued_at  TIMESTAMP   NOT NULL,
    expiration TIMESTAMP   NOT NULL,
    verify     BOOLEAN     NOT NULL,
    user_id    UUID        NOT NULL,
    CONSTRAINT users_fk FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE categories
(
    category_id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    user_id     UUID         NOT NULL,
    CONSTRAINT users_fk FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE expenses
(
    expense_id  UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    amount      FLOAT     NOT NULL,
    description VARCHAR(255),
    date        TIMESTAMP NOT NULL,
    user_id     UUID      NOT NULL,
    category_id UUID      NOT NULL,
    CONSTRAINT users_fk FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT categories_fk FOREIGN KEY (category_id) REFERENCES categories (category_id) ON DELETE CASCADE ON UPDATE CASCADE
);