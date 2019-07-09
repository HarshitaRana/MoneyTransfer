--This script is used for unit test cases, DO NOT CHANGE!
--Dropping any existing table named User
DROP TABLE IF EXISTS User;

--Create new sample table named User
CREATE TABLE User (UserId LONG PRIMARY KEY AUTO_INCREMENT NOT NULL,
 UserName VARCHAR(20) NOT NULL,
 EmailAddress VARCHAR(30) NOT NULL,
 Contact VARCHAR(13) NOT NULL);

 
CREATE UNIQUE INDEX index_u on User(UserName,EmailAddress);

INSERT INTO User (UserName, EmailAddress, Contact) VALUES ('Harshita','harsh@gmail.com','+919783546464');
INSERT INTO User (UserName, EmailAddress, Contact) VALUES ('Joe','joe@gmail.com','+919754546464');
INSERT INTO User (UserName, EmailAddress, Contact) VALUES ('James','james@gmail.com','+919988667755');
INSERT INTO User (UserName, EmailAddress, Contact) VALUES ('San','san@gmail.com','+919900112233');
INSERT INTO User (UserName, EmailAddress, Contact) VALUES ('Scot','scot@gmail.com','+918767564534');

--Dropping any existing table named Account
DROP TABLE IF EXISTS Account;

--Create new sample table named Account
CREATE TABLE Account (AccountId LONG PRIMARY KEY AUTO_INCREMENT NOT NULL,
UserName VARCHAR(30),
Balance DECIMAL(19,4),
Currency VARCHAR(30)
);

CREATE UNIQUE INDEX index_acc on Account(UserName,Currency);


INSERT INTO Account (UserName,Balance,Currency) VALUES ('Harshita',700.0000,'USD');
INSERT INTO Account (UserName,Balance,Currency) VALUES ('Joe',200.0000,'USD');
INSERT INTO Account (UserName,Balance,Currency) VALUES ('James',500.0000,'EUR');
INSERT INTO Account (UserName,Balance,Currency) VALUES ('San',500.0000,'EUR');
INSERT INTO Account (UserName,Balance,Currency) VALUES ('Scot',500.0000,'GBP');
