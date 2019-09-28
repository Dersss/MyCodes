--
-- File generated with SQLiteStudio v3.2.1 on Sun Apr 14 16:49:18 2019
--
-- Text encoding used: UTF-8
--
PRAGMA foreign_keys = off;
BEGIN TRANSACTION;

-- Table: Bill Of Materials
CREATE TABLE "Bill Of Materials" (Order_ID INTEGER NOT NULL UNIQUE REFERENCES "Order" (Order_ID) MATCH SIMPLE, Product_ID NOT NULL, Part_ID NOT NULL);

-- Table: Branch
CREATE TABLE Branch (Branch_ID int NOT NULL PRIMARY KEY, BranchName varchar (255), PhoneNumber int CHECK (PhoneNumber >= 10), Address varchar (255), BranchType int REFERENCES "Branch Type" (BranchType_ID));
INSERT INTO Branch (Branch_ID, BranchName, PhoneNumber, Address, BranchType) VALUES (1, 'HomeOffice', 6141111114, '1234 Lane Ave Columbus, Ohio 43021', 1);

-- Table: Branch Type
CREATE TABLE "Branch Type" (BranchType_ID INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL, BranchTypeName TEXT NOT NULL UNIQUE);
INSERT INTO "Branch Type" (BranchType_ID, BranchTypeName) VALUES (1, 'MainOffice');
INSERT INTO "Branch Type" (BranchType_ID, BranchTypeName) VALUES (2, 'MainFactory');
INSERT INTO "Branch Type" (BranchType_ID, BranchTypeName) VALUES (3, 'Factory');
INSERT INTO "Branch Type" (BranchType_ID, BranchTypeName) VALUES (4, 'Warehouse');
INSERT INTO "Branch Type" (BranchType_ID, BranchTypeName) VALUES (5, 'Office');

-- Table: Customer
CREATE TABLE Customer (Customer_ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, CustomerName varchar (255) NOT NULL, PhoneNumber int CHECK (PhoneNumber >= 10), PhysicalAddress varchar (255), ShippingAddress varchar (255), PrimaryEmail varchar (255), Email varchar (255), Fax varchar (255), Discount_ID int, ContactPerson TEXT NOT NULL);
INSERT INTO Customer (Customer_ID, CustomerName, PhoneNumber, PhysicalAddress, ShippingAddress, PrimaryEmail, Email, Fax, Discount_ID, ContactPerson) VALUES (1, 'Sparrow', 6141111118, 'The Sea', 'The Ocean', 'sparrow@sea.com', NULL, NULL, NULL, 'Parrot Blue');

-- Table: Dependent
CREATE TABLE Dependent (Name TEXT NOT NULL, Address TEXT NOT NULL, Employee_ID INTEGER REFERENCES Employee (Employee_ID) NOT NULL);

-- Table: Employee
CREATE TABLE Employee (Employee_ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, FirstName VARCHAR (255), LastName VARCHAR (255) NOT NULL, MiddleName VARCHAR (255), MaidenName VARCHAR (255), HomePhoneNumber INTEGER CHECK (HomePhoneNumber >= 10), CellPhoneNumber INTEGER CHECK (CellPhoneNumber >= 10), Gender VARCHAR (255), DateOfBirth INTEGER, Address VARCHAR (255), Spouse TEXT UNIQUE, No_Dependants INTEGER, JobTitle VARCHAR (255), Department VARCHAR (255), Branch VARCHAR (255) REFERENCES Branch (Branch_ID) MATCH SIMPLE NOT NULL, OfficeNumber INTEGER, Email VARCHAR (255), NetworkLogin VARCHAR (255) UNIQUE, Benefits VARCHAR (255), Pay TEXT REFERENCES "Pay Type" (PayType) MATCH SIMPLE NOT NULL);

-- Table: Invoice
CREATE TABLE Invoice(
Invoice_Number int NOT NULL PRIMARY KEY,
Order_Number int NOT NULL REFERENCES Order_ID,
Invoice_Status int
);

-- Table: Manufacturer
CREATE TABLE Manufacturer (Manufacturer_ID INTEGER NOT NULL UNIQUE PRIMARY KEY AUTOINCREMENT, ManufacturerName TEXT UNIQUE NOT NULL, Address TEXT NOT NULL, Phone_No INTEGER NOT NULL, PrimaryEmail VARCHAR (25) NOT NULL, Fax INTEGER);

-- Table: Order
CREATE TABLE "Order" (Order_ID INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL, Invoice_No INTEGER NOT NULL UNIQUE, Branch_ID INTEGER REFERENCES Branch (Branch_ID) NOT NULL, Customer_ID INTEGER REFERENCES Customer (Customer_ID) NOT NULL, Employee_ID INTEGER REFERENCES Employee (Employee_ID) NOT NULL, Status TEXT NOT NULL, Discount_ID INTEGER);

-- Table: Part
CREATE TABLE Part (Part_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE, Cost DECIMAL NOT NULL, PartName VARCHAR (255) NOT NULL UNIQUE, PartDescription TEXT NOT NULL, Purchasable VARCHAR (1) NOT NULL REFERENCES "Part Type" (Purchasable) MATCH SIMPLE, Manufacturer_ID INTEGER);
INSERT INTO Part (Part_ID, Cost, PartName, PartDescription, Purchasable, Manufacturer_ID) VALUES (1, 133.99, 'Buckeye Helmet', 'Buckeye Standard Helmet', '1', 1);
INSERT INTO Part (Part_ID, Cost, PartName, PartDescription, Purchasable, Manufacturer_ID) VALUES (2, 12.99, 'Buckeye Hat', 'Buckeye Grey Hat', '0', NULL);

-- Table: Part Type
CREATE TABLE "Part Type" (Purchasable INTEGER (1) UNIQUE NOT NULL, Description TEXT NOT NULL);
INSERT INTO "Part Type" (Purchasable, Description) VALUES (0, 'Non-Purchasable Part');
INSERT INTO "Part Type" (Purchasable, Description) VALUES (1, 'Purchaseable Part');

-- Table: Pay
CREATE TABLE Pay (Employee_ID int NOT NULL REFERENCES Employee (Employee_ID) MATCH SIMPLE, Salary int, Hours_Worked int, Hourly_Pay int, Commision int, PayType INTEGER NOT NULL REFERENCES "Pay Type" (Pay_ID) MATCH SIMPLE);

-- Table: Pay Type
CREATE TABLE "Pay Type" (Pay_ID INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL, PayType TEXT NOT NULL UNIQUE, Salary DOUBLE, HoursWorked DECIMAL, HourlyPay DOUBLE, Commission DOUBLE);
INSERT INTO "Pay Type" (Pay_ID, PayType, Salary, HoursWorked, HourlyPay, Commission) VALUES (1, 'OfficeBase', 50000.0, NULL, NULL, NULL);
INSERT INTO "Pay Type" (Pay_ID, PayType, Salary, HoursWorked, HourlyPay, Commission) VALUES (2, 'Factory/Warehouse', NULL, 40, 16.0, NULL);
INSERT INTO "Pay Type" (Pay_ID, PayType, Salary, HoursWorked, HourlyPay, Commission) VALUES (3, 'Sales', 65000.0, NULL, NULL, 15.0);

-- Table: Product
CREATE TABLE Product (Product_ID int NOT NULL PRIMARY KEY, Branch_ID int NOT NULL REFERENCES Branch (Branch_ID) MATCH SIMPLE, ProductName varchar (255), ProductDescription TEXT);

-- Trigger: ContactPerson
CREATE TRIGGER ContactPerson AFTER INSERT ON Customer FOR EACH ROW BEGIN INSERT INTO ContactPerson (Name, Customer_ID, PhoneNo) 
VALUES (Customer(ContactPerson), Customer(Customer_ID), Customer(PhoneNo));
 END;

-- View: Branches
CREATE VIEW Branches AS SELECT * FROM Branch;

-- View: Customers
CREATE VIEW Customers AS SELECT * FROM Customer;

-- View: Employees
CREATE VIEW Employees AS SELECT * FROM Employee;

COMMIT TRANSACTION;
PRAGMA foreign_keys = on;
