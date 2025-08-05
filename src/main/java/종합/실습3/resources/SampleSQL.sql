drop database if exists assignment3;
create database assignment3;
use assignment3;

create table waiting(
	wno int auto_increment,
    wphone varchar(20) not null,
    wcount int not null,
    constraint primary key ( wno )
);

INSERT INTO waiting(wphone, wcount) VALUES ("010-1111-1111", 5);
INSERT INTO waiting(wphone, wcount) VALUES ("010-2222-2222", 2);
INSERT INTO waiting(wphone, wcount) VALUES ("010-3333-3333", 4);
INSERT INTO waiting(wphone, wcount) VALUES ("010-4444-4444", 1);
INSERT INTO waiting(wphone, wcount) VALUES ("010-5555-5555", 6);
INSERT INTO waiting(wphone, wcount) VALUES ("010-6666-6666", 3);
INSERT INTO waiting(wphone, wcount) VALUES ("010-7777-7777", 7);
INSERT INTO waiting(wphone, wcount) VALUES ("010-8888-8888", 8);
INSERT INTO waiting(wphone, wcount) VALUES ("010-9999-9999", 2);
INSERT INTO waiting(wphone, wcount) VALUES ("010-1234-5678", 5);
INSERT INTO waiting(wphone, wcount) VALUES ("010-2345-6789", 6);
INSERT INTO waiting(wphone, wcount) VALUES ("010-3456-7890", 1);
INSERT INTO waiting(wphone, wcount) VALUES ("010-4567-8901", 3);
INSERT INTO waiting(wphone, wcount) VALUES ("010-5678-9012", 9);
INSERT INTO waiting(wphone, wcount) VALUES ("010-6789-0123", 4);
INSERT INTO waiting(wphone, wcount) VALUES ("010-7890-1234", 7);
INSERT INTO waiting(wphone, wcount) VALUES ("010-8901-2345", 2);
INSERT INTO waiting(wphone, wcount) VALUES ("010-9012-3456", 6);
INSERT INTO waiting(wphone, wcount) VALUES ("010-1020-3040", 10);
INSERT INTO waiting(wphone, wcount) VALUES ("010-1122-3344", 5);
INSERT INTO waiting(wphone, wcount) VALUES ("010-2233-4455", 3);
INSERT INTO waiting(wphone, wcount) VALUES ("010-3344-5566", 8);
INSERT INTO waiting(wphone, wcount) VALUES ("010-4455-6677", 2);
INSERT INTO waiting(wphone, wcount) VALUES ("010-5566-7788", 4);
INSERT INTO waiting(wphone, wcount) VALUES ("010-6677-8899", 6);
INSERT INTO waiting(wphone, wcount) VALUES ("010-7788-9900", 1);
INSERT INTO waiting(wphone, wcount) VALUES ("010-8899-0011", 7);
INSERT INTO waiting(wphone, wcount) VALUES ("010-9900-1122", 2);
INSERT INTO waiting(wphone, wcount) VALUES ("010-1010-2020", 3);
INSERT INTO waiting(wphone, wcount) VALUES ("010-2020-3030", 5);
INSERT INTO waiting(wphone, wcount) VALUES ("010-3030-4040", 9);
INSERT INTO waiting(wphone, wcount) VALUES ("010-4040-5050", 6);
INSERT INTO waiting(wphone, wcount) VALUES ("010-5050-6060", 1);
INSERT INTO waiting(wphone, wcount) VALUES ("010-6060-7070", 4);
INSERT INTO waiting(wphone, wcount) VALUES ("010-7070-8080", 8);
INSERT INTO waiting(wphone, wcount) VALUES ("010-8080-9090", 3);
INSERT INTO waiting(wphone, wcount) VALUES ("010-9090-0000", 7);
INSERT INTO waiting(wphone, wcount) VALUES ("010-0000-1111", 2);
INSERT INTO waiting(wphone, wcount) VALUES ("010-1111-2222", 6);
INSERT INTO waiting(wphone, wcount) VALUES ("010-2222-3333", 4);

select * from waiting;