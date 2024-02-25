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

INSERT INTO Login_credintals (User_Id, User_Name, Password) VALUES
('201000', 'Jordan Ufot', '123321'),
('215000', 'Subash Neupane', '123321'),
('176420', 'Bimal Itani', '123321');

select * from Login_credintals;

-- Create a table for user accounts
CREATE TABLE IF NOT EXISTS User_Accounts (
    Account_Id INT AUTO_INCREMENT PRIMARY KEY,
    User_Id VARCHAR(20) NOT NULL,
    Account_Number VARCHAR(20) NOT NULL,
    Account_Holder_Name VARCHAR(50) NOT NULL,
    Current_Balance DECIMAL(10, 2) DEFAULT 0.0,
    Previous_Transactions TEXT,
    FOREIGN KEY (User_Id) REFERENCES Login_credintals(User_Id)
);

-- Insert some sample data into User_Accounts

INSERT INTO User_Accounts (User_Id, Account_Number, Account_Holder_Name, Current_Balance, Previous_Transactions) VALUES
('176000', '123456', 'John Doe', 1000.00, 'Initial deposit: $1000.00'),
('189000', '789012', 'Jane Smith', 1500.50, 'Initial deposit: $1500.50'),
('190000', '345678', 'Alice Jones', 500.25, 'Initial deposit: $500.25'),
('201000', '457383', 'Jordan Ufot',  200,  'Initial deposit: $200'),
('215000', '457344', 'Subash Neupane',  200.99,  'Initial deposit: $200.99'),
('176420', '457678', 'Bimal Itani',  200.99,  'Initial deposit: $200.99');

select * from User_Accounts;

update User_Accounts
set Current_Balance=200
where User_Id=215000;

CREATE TABLE IF NOT EXISTS User_Info (
    Account_Number VARCHAR(20) primary key,
    Account_Holder_Name VARCHAR(50) NOT NULL,
    Age INT NOT NULL,
    Mailing_Address VARCHAR(70) NOT NULL,
    Phone_number VARCHAR(30) NOT NULL
);


INSERT INTO User_Info ( Account_Number, Account_Holder_Name, Age, Mailing_Address, Phone_number) VALUES
( '123456', 'John Doe', 40, '858 xyzsb street','98036363763'),
( '789012', 'Jane Smith', 50, '173 efbbfd street','9803736783'),
( '345678', 'Alice Jones', 25, '987 uihfud street','980373773'),
( '457383', 'Jordan Ufot',  20,  '400 Magnolia street','8649156281'),
( '457344', 'Subash Neupane',  21,  '400 Magnolia street','8033873929'),
( '457678', 'Bimal Itani',  20,  '400 Magnolia street','9803014299');


select* from User_Info;


SET SQL_SAFE_UPDATES=0;



