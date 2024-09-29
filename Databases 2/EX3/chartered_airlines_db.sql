/*Ονοματεπώνυμο: ΧΡΗΣΤΟΣ ΚΟΚΚΑΛΗΣ
  ΑΜ: 19390090
  ΤΜΗΜΑ: 01
  ΑΣΚΗΣΗ 3
*/

drop database if exists chartered_airlines_bd;
create database chartered_airlines_bd;
use chartered_airlines_bd;


CREATE USER kokkalis IDENTIFIED BY '1234';
GRANT ALL PRIVILEGES ON chartered_airlines_bd.* TO kokkalis;
SHOW GRANTS FOR kokkalis;

drop table if exists FLIGHTS;
create table FLIGHTS(
	flight_no numeric(3),
	departure VARCHAR(255),
	arrival VARCHAR(255),
	seats INT(3),
	free_seats INT(3),
	type_of_flight varchar(255),
	depart_date varchar(255),
    

    CONSTRAINT PK_flight_no primary key(flight_no)
);

insert into FLIGHTS(flight_no, departure, arrival, seats, free_seats, type_of_flight, depart_date)
values
(1,'Eleftherios Venizelos','London Southend Airport', 50, 15,'international',  date('2021-12-30')),
(2,'Venice Marco Polo Airport', 'Macedonia',60,30,'international', date('2022-02-10')),
(3,'London Heathrow', 'Chania International Airport', 80, 40,'international', date('2022-04-21')),
(4, 'Eleftherios Venizelos', 'Chania International Airport', 40, 20, 'domestic', date('2022-03-01'));


drop table if exists customers;
create table customers(
	customer_no numeric(3),
	customer_lastname varchar(25),
	customer_firstname varchar(25),
	citizenship varchar(10),
	birth_date varchar(20),

    constraint PK_customer_no primary key(customer_no)
);
describe customers;


insert into customers(customer_no,customer_lastname,customer_firstname,citizenship,birth_date)
values
(1, 'Doe' ,'John', 'CY', date ('2000-3-17') ),
(2, 'Kokkalis', 'Xristos', 'GR', date ('2001-11-12')),
(3, 'Black', 'Donald', 'US', date ('2001-1-6'));



drop table if exists reservations;
create table reservations (
	res_no numeric(3),
	customer_no numeric(3),
	flight_no numeric(3),
	price double(10,2),
	res_date varchar(255),

    constraint PK_res_no primary key(res_no),
    constraint FK_customer_no foreign key(customer_no) references customers(customer_no),
    constraint FK_flight_no foreign key(flight_no) references flights(flight_no)
);

describe reservations;

insert into reservations(res_no,customer_no,flight_no,price, res_date)
value
(1,1,1,600, current_date()),
(2,2,2,300, current_date()),
(3,3,2,400, current_date()),
(4,1,3,550, current_date()),
(5,2,3,700, current_date()),
(6,3,4,100, current_date());



select * from FLIGHTS;
select * from customers;
select * from reservations;

/*###### TRIGGER - FUNCTION - PROCEDURE #########*/

delimiter //
create trigger FLIGHTS_FREE_SEATS
after insert on reservations
for each row
begin
update flights
set free_seats = ifnull(free_seats,0) - 1
where flight_no = new.flight_no;
end;
// 
delimiter ;


insert into reservations(res_no,customer_no,flight_no,price, res_date)
value
(9,3,4,300, current_date() );

select * from FLIGHTS;


drop function if exists res_duration
delimiter //
create function res_duration (res_no int) 
returns int
begin
	declare duration int default 0;
    select TIMESTAMPDIFF(MONTH, reservations.res_date, flights.depart_date) into duration
	  from reservations, flights
      where reservations.flight_no = flights.flight_no and res_no = reservations.res_no;
      return duration;	 
end;
// 
delimiter ;

select res_duration(1) as duration;


drop procedure if exists GetSumOfFlight;
delimiter //
create procedure GetSumOfFlight(in flightNo int, out sum double)
begin
	select sum(price) into sum
    from reservations
    where flight_no = flightNo;
end
//
delimiter ;


call GetSumOfFlight(3, @sum);

select @sum as sum;



