CREATE TABLE loginTBL
(
	ID varchar(50)  NOT NULL,
	Name varchar(50),
	LoginTime varchar(24)
);
ALTER TABLE loginTBL ADD CONSTRAINT pk_loginTBL PRIMARY KEY (ID);
