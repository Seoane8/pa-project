DROP TABLE Inscription;
DROP TABLE SportTest;
DROP TABLE Province;
DROP TABLE SportTestType;
DROP TABLE User;

CREATE TABLE User (
    id BIGINT NOT NULL AUTO_INCREMENT,
    userName VARCHAR(60) COLLATE latin1_bin NOT NULL,
    password VARCHAR(60) NOT NULL, 
    firstName VARCHAR(60) NOT NULL,
    lastName VARCHAR(60) NOT NULL, 
    email VARCHAR(60) NOT NULL,
    role TINYINT NOT NULL,
    CONSTRAINT UserPK PRIMARY KEY (id),
    CONSTRAINT UserNameUniqueKey UNIQUE (userName)
) ENGINE = InnoDB;

CREATE INDEX UserIndexByUserName ON User (userName);

CREATE TABLE Province (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(60) NOT NULL,
    CONSTRAINT ProvincePK PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE SportTestType (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(60) NOT NULL,
    CONSTRAINT ProvincePK PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE SportTest (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(60) NOT NULL,
    description VARCHAR(2000) NOT NULL,
    date DATETIME NOT NULL,
    price DECIMAL(11, 2) NOT NULL,
    maxParticipants INT NOT NULL,
    numParticipants INT NOT NULL,
    sportTestTypeId BIGINT NOT NULL,
    location VARCHAR(60) NOT NULL,
    provinceId BIGINT NOT NULL,
    rating DECIMAL(3,2),
    numRatings INT,
    version INT NOT NULL,
    CONSTRAINT SportTestPK PRIMARY KEY (id),
    CONSTRAINT SportTestSportTestTypeIdFK FOREIGN KEY (sportTestTypeId)
        REFERENCES SportTestType (id),
    CONSTRAINT SportTestProvinceId FOREIGN KEY (provinceId)
        REFERENCES Province (id)
) ENGINE = InnoDB;

CREATE TABLE Inscription (
    id BIGINT NOT NULL AUTO_INCREMENT,
    userId BIGINT NOT NULL,
    sportTestId BIGINT NOT NULL,
    cardNumber VARCHAR(20) NOT NULL,
    reservationDate DATETIME NOT NULL,
    price DECIMAL(11, 2) NOT NULL,
    dorsal INT NOT NULL,
    dorsalCollected BOOLEAN NOT NULL,
    score INT,
    CONSTRAINT InscriptionPK PRIMARY KEY (id),
    CONSTRAINT InscriptionUserIdFK FOREIGN KEY (userId)
        REFERENCES User (id),
    CONSTRAINT InscriptionSportTestIdFK FOREIGN KEY (sportTestId)
        REFERENCES SportTest (id)
) ENGINE = InnoDB;
