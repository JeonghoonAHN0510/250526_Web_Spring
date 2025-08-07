DROP DATABASE IF EXISTS exam04;
CREATE DATABASE exam04;
USE exam04;

CREATE TABLE waiting (
    wno   INT AUTO_INCREMENT,
    wphone VARCHAR(50) NOT NULL,
    wcount  INT,
    wdate DATETIME DEFAULT NOW(),
    CONSTRAINT PRIMARY KEY ( wno )
);

INSERT INTO waiting (wphone, wcount) VALUES ('010-1111-2222', 5);
INSERT INTO waiting (wphone, wcount) VALUES ('010-2222-3333', 2);
INSERT INTO waiting (wphone, wcount) VALUES ('010-3333-4444', 4);
INSERT INTO waiting (wphone, wcount) VALUES ('010-4444-5555', 3);
INSERT INTO waiting (wphone, wcount) VALUES ('010-5555-6666', 6);
INSERT INTO waiting (wphone, wcount) VALUES ('010-6666-7777', 1);
INSERT INTO waiting (wphone, wcount) VALUES ('010-7777-8888', 7);
INSERT INTO waiting (wphone, wcount) VALUES ('010-8888-9999', 2);
INSERT INTO waiting (wphone, wcount) VALUES ('010-9999-0000', 5);
INSERT INTO waiting (wphone, wcount) VALUES ('010-0000-1111', 3);

select * from waiting;