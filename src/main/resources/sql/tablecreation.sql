CREATE DATABASE hotel CHARACTER SET utf8;

USE hotel;

CREATE TABLE Users (
    user_id         BIGINT AUTO_INCREMENT     PRIMARY KEY,
    email           VARCHAR(35)               NOT NULL UNIQUE,
    password        VARCHAR(256)              NOT NULL,
    first_name      VARCHAR(60),
    last_name       VARCHAR(60),
    role            VARCHAR(60),
);

CREATE TABLE Orders (
    order_id        BIGINT AUTO_INCREMENT     PRIMARY KEY,
    user_id         BIGINT                    NOT NULL,
    capacity        INT,
    type            VARCHAR(15),
    start_date      TIMESTAMP                 NOT NULL,
    end_date        TIMESTAMP                 NOT NULL,
    FOREIGN KEY (user_id) REFERENCES Users (user_id)
);

CREATE TABLE Rooms (
    room_id         BIGINT AUTO_INCREMENT     PRIMARY KEY,
    capacity        INT,
    type            VARCHAR(15),
    price           INT                       NOT NULL
);

CREATE TABLE Bills (
    bill_id         BIGINT AUTO_INCREMENT     PRIMARY KEY,
    user_id         BIGINT                    NOT NULL,
    room_id         BIGINT                    NOT NULL,
    admin_id        BIGINT                    NOT NULL,
    order_id        BIGINT                    NOT NULL,
    price           INT                       NOT NULL,
    status          BIT,
    FOREIGN KEY (user_id)   REFERENCES Users (user_id),
    FOREIGN KEY (room_id)   REFERENCES Rooms (room_id),
    FOREIGN KEY (admin_id)  REFERENCES Users (user_id),
    FOREIGN KEY (order_id)  REFERENCES Orders(order_id)
);

CREATE TABLE BookedRooms (
    id              BIGINT AUTO_INCREMENT     PRIMARY KEY,
    room_id         BIGINT                    NOT NULL,
    order_id        BIGINT                    NOT NULL,
    FOREIGN KEY (room_id)       REFERENCES Rooms (room_id),
    FOREIGN KEY (order_id)      REFERENCES Orders(order_id)
);

