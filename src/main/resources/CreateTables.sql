CREATE TABLE loginTBL
(
	ID varchar(50)  NOT NULL,
	Name varchar(50),
	LoginTime varchar(24)
);
ALTER TABLE loginTBL ADD CONSTRAINT pk_loginTBL PRIMARY KEY (ID);
CREATE TABLE APIKEYTBL
(
	APPID varchar(100)  NOT NULL,
	APIKEY varchar(100) NOT NULL,
	SECRETKEY varchar(200) NOT NULL,
	USEDTIME timestamp  NOT NULL
);
ALTER TABLE APIKEYTBL ADD CONSTRAINT pk_APIKEYTBL PRIMARY KEY (APPID,APIKEY,SECRETKEY);

