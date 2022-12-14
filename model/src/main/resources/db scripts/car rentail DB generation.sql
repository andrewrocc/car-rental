SET FOREIGN_KEY_CHECKS=0;
/* Drop Tables */
DROP TABLE IF EXISTS USER CASCADE;

DROP TABLE IF EXISTS BOOKING CASCADE;

/*DROP TABLE IF EXISTS PAYMENT CASCADE;*/

/*DROP TABLE IF EXISTS BOOKING_PAYMENT CASCADE;*/

DROP TABLE IF EXISTS CAR CASCADE;

DROP TABLE IF EXISTS CAR_MODEL CASCADE;

DROP TABLE IF EXISTS CAR_BRAND CASCADE;

DROP TABLE IF EXISTS ROLE CASCADE;

DROP TABLE IF EXISTS USER_ROLE CASCADE;

DROP TABLE IF EXISTS PERMISSION CASCADE;

DROP TABLE IF EXISTS USER_BOOKING CASCADE;

DROP TABLE IF EXISTS BOOKING_CAR CASCADE;

DROP TABLE IF EXISTS CAR_PHOTO CASCADE;

/* Create Tables */
CREATE TABLE USER (
	ID BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
	FIRSTNAME VARCHAR(100) NOT NULL,
    LASTNAME VARCHAR(100) NOT NULL,
    EMAIL VARCHAR(100) NOT NULL,
    PAYMENT_CARD VARCHAR(16),
	CONSTRAINT PK_USER PRIMARY KEY (ID ASC)
);

CREATE TABLE BOOKING (
	ID BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
	PRICE FLOAT NOT NULL,
    DATE DATE NOT NULL,
    NUMBER_OF_DAY INT NOT NULL,
	CONSTRAINT PK_BOOKING PRIMARY KEY (ID ASC)
);

CREATE TABLE CAR (
	ID BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
	NUMBER VARCHAR(30) NOT NULL,
    PRICE DECIMAL(10, 2) NOT NULL,
    BRAND_ID BIGINT UNSIGNED NOT NULL,
    MODEL_ID BIGINT UNSIGNED NOT NULL,
	CONSTRAINT PK_CAR PRIMARY KEY (ID ASC)
);

CREATE TABLE CAR_BRAND (
	ID BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    NAME VARCHAR(100) NOT NULL,
    CONSTRAINT PK_CAR_BRAND PRIMARY KEY (ID ASC)
);

CREATE TABLE CAR_MODEL (
	ID BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    BRAND_ID BIGINT UNSIGNED NOT NULL,
    NAME VARCHAR(100) NOT NULL,
    CONSTRAINT PK_CAR_MODEL PRIMARY KEY (ID ASC)
);

/*CREATE TABLE PAYMENT (
	ID BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
	DATETIME DATETIME NOT NULL,
	CONSTRAINT PK_PAYMENT PRIMARY KEY (ID ASC)
);*/

CREATE TABLE ROLE (
	ID BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    NAME VARCHAR(100) NOT NULL,
    DESCRIPTION VARCHAR(255) NOT NULL,
    CONSTRAINT PK_ROLE PRIMARY KEY (ID ASC)
);

CREATE TABLE USER_ROLE (
	USER_ID BIGINT UNSIGNED NOT NULL,
    ROLE_ID BIGINT UNSIGNED NOT NULL,
    CONSTRAINT PK_USER_ROLE PRIMARY KEY (USER_ID ASC, ROLE_ID ASC)
);

CREATE TABLE PERMISSION (
	ID BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    NAME VARCHAR(100) NOT NULL,
    ROLE_ID BIGINT UNSIGNED NOT NULL,
    CONSTRAINT PK_PERMISSION PRIMARY KEY (ID ASC)
);

CREATE TABLE USER_BOOKING (
	USER_ID BIGINT UNSIGNED NOT NULL,
    BOOKING_ID BIGINT UNSIGNED NOT NULL,
    CONSTRAINT PK_USER_BOOKING PRIMARY KEY (USER_ID ASC, BOOKING_ID ASC)
);

CREATE TABLE BOOKING_CAR (
	BOOKING_ID BIGINT UNSIGNED NOT NULL,
    CAR_ID BIGINT UNSIGNED NOT NULL,
    CONSTRAINT PK_BOOKING_CAR PRIMARY KEY (BOOKING_ID ASC, CAR_ID ASC)
);

CREATE TABLE CAR_PHOTO (
	ID VARCHAR(36) PRIMARY KEY NOT NULL,
    CAR_ID BIGINT UNSIGNED NOT NULL,
    CAR_PHOTO MEDIUMBLOB NOT NULL
);

/*CREATE TABLE BOOKING_PAYMENT (
	BOOKING_ID BIGINT UNSIGNED NOT NULL,
    PAYMENT_ID BIGINT UNSIGNED NOT NULL,
    CONSTRAINT PK_BOOKING_PAYMENT PRIMARY KEY (BOOKING_ID ASC, PAYMENT_ID ASC)
);*/

/*ALTER TABLE BOOKING ADD CONSTRAINT FK_BOOKING_CAR FOREIGN KEY (CAR_ID)
REFERENCES CAR (ID) ON DELETE CASCADE ON UPDATE CASCADE;
*/

ALTER TABLE CAR ADD CONSTRAINT FK_CAR_CARBRAND FOREIGN KEY (BRAND_ID)
REFERENCES CAR_BRAND (ID) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE CAR ADD CONSTRAINT FK_CAR_CARMODEL FOREIGN KEY (MODEL_ID)
REFERENCES CAR_MODEL (ID) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE CAR_MODEL ADD CONSTRAINT FK_CARMODEL_CARBRAND FOREIGN KEY (BRAND_ID)
REFERENCES CAR_BRAND (ID) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE USER_ROLE ADD CONSTRAINT FK_USER_ROLE FOREIGN KEY (ROLE_ID)
REFERENCES ROLE (ID) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE USER_ROLE ADD CONSTRAINT FK_USER_ROLE_USER FOREIGN KEY (USER_ID)
REFERENCES USER (ID) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE PERMISSION ADD CONSTRAINT FK_PERMISSION_ROLE FOREIGN KEY (ROLE_ID)
REFERENCES ROLE (ID) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE USER_BOOKING ADD CONSTRAINT FK_M2M_USER_BOOKING_BOOKING FOREIGN KEY (BOOKING_ID)
REFERENCES BOOKING (ID) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE USER_BOOKING ADD CONSTRAINT FK_M2M_USER_BOOKING_USER FOREIGN KEY (USER_ID)
REFERENCES USER (ID) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE CAR_PHOTO ADD CONSTRAINT FK_CAR_PHOTO FOREIGN KEY (CAR_ID)
REFERENCES CAR (ID) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE BOOKING_CAR ADD CONSTRAINT FK_BOOKING_CAR FOREIGN KEY (CAR_ID)
REFERENCES CAR (ID) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE BOOKING_CAR ADD CONSTRAINT FK_CAR_BOOKING FOREIGN KEY (BOOKING_ID)
REFERENCES BOOKING (ID) ON DELETE CASCADE ON UPDATE CASCADE;

/*ALTER TABLE BOOKING_PAYMENT ADD CONSTRAINT FK_M2M_BOOKING_PAYMENT_PAYMENT FOREIGN KEY (PAYMENT_ID)
REFERENCES PAYMENT (ID) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE BOOKING_PAYMENT ADD CONSTRAINT FK_M2M_BOOKING_PAYMENT_BOOKING FOREIGN KEY (BOOKING_ID)
REFERENCES BOOKING (ID) ON DELETE CASCADE ON UPDATE CASCADE;*/

SET FOREIGN_KEY_CHECKS=1;