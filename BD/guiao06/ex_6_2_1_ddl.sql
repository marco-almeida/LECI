CREATE SCHEMA Projects;
GO
CREATE TABLE Employee(
    Fname VARCHAR(10) NOT NULL,
	Minit VARCHAR(10) NOT NULL,
	Lname VARCHAR(10) NOT NULL,
	Ssn INT NOT NULL CHECK (Ssn > 0),
	Bdate DATE NOT NULL,
	Addresss VARCHAR(100) NOT NULL,
	Sex CHAR(1) NOT NULL CHECK (Sex = 'F' OR Sex = 'M'),
	Salary INT NOT NULL,
	Super_ssn INT CHECK (Super_ssn > 0),
	Dno INT NOT NULL,
	PRIMARY KEY(Ssn),
	FOREIGN KEY (Super_ssn) REFERENCES Employee(Ssn)
);
CREATE TABLE Department(
    Dname VARCHAR(50) NOT NULL,
	Dnumber INT NOT NULL,
	Mgr_ssn INT CHECK (Mgr_ssn > 0),
	Mgr_start_date DATE,
	PRIMARY KEY(Dnumber),
	FOREIGN KEY (Mgr_ssn) REFERENCES Employee(Ssn)
);

CREATE TABLE Dept_Locations(
	Dnumber INT NOT NULL,
	Dlocation VARCHAR(50) NOT NULL,
	PRIMARY KEY(Dnumber,Dlocation),
	FOREIGN KEY(Dnumber) REFERENCES Department(Dnumber)
);

CREATE TABLE Project(
    Pname VARCHAR(100) NOT NULL,
	Pnumber INT NOT NULL,
	Plocation VARCHAR(50) NOT NULL,
	Dnum INT NOT NULL,
	PRIMARY KEY(Pnumber),
	FOREIGN KEY(Dnum) REFERENCES Department(Dnumber)
);

CREATE TABLE Works_On(
    Essn INT NOT NULL CHECK (Essn > 0),
	Pno INT NOT NULL,
	Hourss INT,
	PRIMARY KEY(Essn, Pno),
	FOREIGN KEY(Essn) REFERENCES Employee(Ssn),
	FOREIGN KEY(Pno) REFERENCES Project(Pnumber)
);

CREATE TABLE Dependents(
    Essn INT NOT NULL CHECK (Essn > 0),
	Dependent_name VARCHAR(100) NOT NULL,
	Sex CHAR(1) NOT NULL CHECK (Sex = 'F' OR Sex = 'M'),
	Bdate DATE NOT NULL,
	Relationship VARCHAR(100) NOT NULL,
	PRIMARY KEY(Essn, Dependent_name),
	FOREIGN KEY(Essn) REFERENCES Employee(Ssn)
);