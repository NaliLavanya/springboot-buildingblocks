INSERT INTO users1 (USER_NAME, FIRST_NAME, LAST_NAME, EMAIL_ADDRESS, ROLE, SSN)
VALUES ('anjali.kumar', 'Anjali', 'Kumar', 'anjali.kumar@example.com', 'Admin', 'SSN001');

INSERT INTO users1 (USER_NAME, FIRST_NAME, LAST_NAME, EMAIL_ADDRESS, ROLE, SSN)
VALUES ('lavanya.nali', 'Lavanya', 'Nali', 'lavanya.nali@example.com', 'User', 'SSN002');

INSERT INTO users1 (USER_NAME, FIRST_NAME, LAST_NAME, EMAIL_ADDRESS, ROLE, SSN)
VALUES ('prasanth.reddy', 'Prasanth', 'Reddy', 'prasanth.reddy@example.com', 'Admin', 'SSN003');

INSERT INTO users1 (USER_NAME, FIRST_NAME, LAST_NAME, EMAIL_ADDRESS, ROLE, SSN)
VALUES ('sanjay.verma', 'Sanjay', 'Verma', 'sanjay.verma@example.com', 'User', 'SSN004');

INSERT INTO users1 (USER_NAME, FIRST_NAME, LAST_NAME, EMAIL_ADDRESS, ROLE, SSN)
VALUES ('vikram.yadav', 'Vikram', 'Yadav', 'vikram.yadav@example.com', 'User', 'SSN005');
INSERT INTO orders (orderid, order_description, users1_id)
VALUES 
(1, 'Laptop Purchase', 1),
(2, 'Mobile Phone Purchase', 1),
(3, 'Headphones Order', 2),
(4, 'Bluetooth Speaker', 2);