CREATE TABLE Users (
                       id              INT             NOT NULL    PRIMARY KEY     AUTO_INCREMENT,
                       username        VARCHAR(255)    NOT NULL    UNIQUE,
                       passwordhash        VARCHAR(255)    NOT NULL,
                       salt            VARCHAR(255)    NOT NULL,   -- TODO, make a char when we have made a salt.
                       email           VARCHAR(255)    NOT NULL
    -- `role` VARCHAR(255) NOT NULL,
);