drop database if exists banking_db;
CREATE DATABASE banking_db; 
use banking_db;


CREATE USER kokkalis IDENTIFIED BY '1234';
GRANT ALL PRIVILEGES ON banking_db.* TO kokkalis;
SHOW GRANTS FOR kokkalis;


DROP TABLE IF EXISTS accounts;
CREATE TABLE accounts (
    Account_ID INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    A_FirstName VARCHAR(30),
    A_Surname VARCHAR(30),
	A_BirthDate VARCHAR(30),
    A_Email VARCHAR(300),
    A_Phone VARCHAR(30),
	A_Address VARCHAR(30),
	A_Country VARCHAR(30),
	A_Balance VARCHAR(30),
	A_Status VARCHAR(30),


    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

select * from accounts;


insert into accounts (A_FirstName, A_Surname, A_BirthDate, A_Email, A_Phone, A_Address, A_Country, A_Balance, A_Status) 
values ("Linda", "Rutledge", "08/17/1991", "semper.dui.lectus@aol.org", "(996) 457-8216", "Ap #350-2398 Nullam Rd.", "Costa Rica","7336.17", "ACTIVE" )