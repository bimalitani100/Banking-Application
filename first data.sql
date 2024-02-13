create database bank_login;
use bank_login;
create table if not exists Login_credintals(
User_Id varchar(20) Not null primary key,
User_Name varchar(20) Not null,
Password varchar(20) not null
);
INSERT INTO Login_credintals (User_Id,User_Name,Password) VALUES
('176000','john_doe', 'hashed_password_1'),
('189000','jane_smith', 'hashed_password_2'),
('190000','alice_jones', 'hashed_password_3');

select * from Login_credintals;


