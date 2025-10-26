/*CREATE TABLE users1 (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    USER_NAME VARCHAR(50) NOT NULL UNIQUE,
    FIRST_NAME VARCHAR(50) NOT NULL,
    LAST_NAME VARCHAR(50) NOT NULL,
    EMAIL_ADDRESS VARCHAR(50) NOT NULL,
    ROLE VARCHAR(50) NOT NULL,
    SSN VARCHAR(50) NOT NULL UNIQUE
);*/
-- =====================================
-- Table: users1
-- =====================================
CREATE TABLE users1 (
    userid BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_name VARCHAR(50) NOT NULL UNIQUE,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email_address VARCHAR(50) NOT NULL,
    role VARCHAR(50) NOT NULL,
    ssn VARCHAR(50) NOT NULL UNIQUE
);

-- =====================================
-- Table: orders
-- =====================================
CREATE TABLE orders (
    orderid INT AUTO_INCREMENT PRIMARY KEY,
    order_description VARCHAR(255),
    users1_id BIGINT,
    CONSTRAINT fk_user_order FOREIGN KEY (users1_id) REFERENCES users1(userid)
);

