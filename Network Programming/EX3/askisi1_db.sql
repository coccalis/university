/* 1. Connect to the db server (as root)*/


/*root creates a new database */
drop database if exists askisi1_db;
CREATE DATABASE askisi1_db; 
use askisi1_db;

/* 2. root creates a new user and grants privileges to the new database*/

CREATE USER kokkalis IDENTIFIED BY '1234';
GRANT ALL PRIVILEGES ON askisi1_db.* TO kokkalis;
SHOW GRANTS FOR kokkalis;


/* 3. use the askisi1_db database and create a new table inside askisi1_db*/
DROP TABLE IF EXISTS students;
CREATE TABLE students (
    ID INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(30),
    lastName VARCHAR(30),
    university VARCHAR(30),
    semester INT(2),
    Modules INT(3),
    
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

select * from students;


INSERT INTO students (firstName, lastName, university, semester, Modules) VALUES ('Aggelos', 'Georgoulas', 'PADA', '5', '25');
INSERT INTO students (firstName, lastName, university, semester, Modules) VALUES ('Xristos', 'Kokkalis', 'PADA', '6', '30');
INSERT INTO students (firstName, lastName, university, semester, Modules) VALUES ('Nikos', 'Papadopoulos', 'OPA', '3', '10');