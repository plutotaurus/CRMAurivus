CREATE TABLE Users (
                       id              INT             NOT NULL    PRIMARY KEY     AUTO_INCREMENT,
                       username        VARCHAR(255)    NOT NULL    UNIQUE,
                       passwordhash    VARCHAR(255)    NOT NULL
);