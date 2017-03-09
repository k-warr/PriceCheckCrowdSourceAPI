CREATE TABLE brand
(
    brandId INT(11) PRIMARY KEY NOT NULL COMMENT 'This uniquely identifies a brand.' AUTO_INCREMENT,
    brandName INT(11) COMMENT 'This is the name of the brand.'
);
CREATE UNIQUE INDEX brand_brandId_uindex ON brand (brandId);
CREATE TABLE grocery
(
    groceryId INT(11) PRIMARY KEY NOT NULL COMMENT 'This uniquely identifies a grocery in the price check web service.' AUTO_INCREMENT,
    groceryName VARCHAR(128) NOT NULL COMMENT 'This is the name of the grocery.',
    longtitude DECIMAL(12,9) NOT NULL COMMENT 'This is the longtitude coordinates of the grocery.',
    latitude DECIMAL(12,9) NOT NULL COMMENT 'This is the latitude coordinate of the grocery.',
    groceryAddress VARCHAR(128) NOT NULL COMMENT 'This is the address of the grocery.'
);
CREATE UNIQUE INDEX grocery_groceryId_uindex ON grocery (groceryId);
CREATE TABLE item
(
    itemId INT(11) PRIMARY KEY NOT NULL COMMENT 'This uniquely identifies an item in the price check web service.' AUTO_INCREMENT,
    itemName VARCHAR(128) NOT NULL COMMENT 'This is the common name of the item used in inquiry.',
    unit VARCHAR(64) NOT NULL,
    unitValue INT(11) NOT NULL
);
CREATE UNIQUE INDEX item_itemId_uindex ON item (itemId);
CREATE TABLE priceFact
(
    factId BIGINT(20) PRIMARY KEY NOT NULL COMMENT 'This uniquely identifies a price fact.' AUTO_INCREMENT,
    userId INT(11) NOT NULL COMMENT 'This is the user that submitted the price of the item in the grocery at a date and time.',
    priceAmount DECIMAL(9,2) NOT NULL COMMENT 'This is the price amount the user submitted for an item in a grocery on a date and time.',
    groceryId INT(11) NOT NULL COMMENT 'This is the grocery the price is found for the item as submitted by the user on a date and time.',
    itemId INT(11) NOT NULL COMMENT 'This is the item priced by the user in a grocery on a date and time.',
    factDateTime DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT 'This is the date and time price is submitted by the user in a grocery for an item.',
    branId INT(11),
    CONSTRAINT priceFact_user_userId_fk FOREIGN KEY (userId) REFERENCES user (userId) ON UPDATE CASCADE,
    CONSTRAINT priceFact_grocery_groceryId_fk FOREIGN KEY (groceryId) REFERENCES grocery (groceryId) ON UPDATE CASCADE,
    CONSTRAINT priceFact_item_itemId_fk FOREIGN KEY (itemId) REFERENCES item (itemId) ON UPDATE CASCADE,
    CONSTRAINT priceFact_brand_brandId_fk FOREIGN KEY (branId) REFERENCES brand (brandId) ON UPDATE CASCADE
);
CREATE UNIQUE INDEX priceFact_factId_uindex ON priceFact (factId);
CREATE INDEX priceFact_grocery_groceryId_fk ON priceFact (groceryId);
CREATE INDEX priceFact_user_userId_fk ON priceFact (userId);
CREATE INDEX priceFact_item_itemId_fk ON priceFact (itemId);
CREATE INDEX priceFact_branId_index ON priceFact (branId);
CREATE INDEX priceFact_factDateTime_index ON priceFact (factDateTime);
CREATE TABLE user
(
    userId INT(11) PRIMARY KEY NOT NULL COMMENT 'This uniquely identifies a user of the web service price check.' AUTO_INCREMENT,
    apiKey CHAR(32) NOT NULL COMMENT 'This is a unique identifier of the user of the web service price check'
);
CREATE UNIQUE INDEX user_userId_uindex ON user (userId);