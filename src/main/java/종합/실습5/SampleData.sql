drop database if exists assignment5;
create database assignment5;
use assignment5;

create table waiting(
    wno int auto_increment , 
    wphone varchar(20) not null,
    wcount int not null ,
    constraint primary key(wno)
);

select * from waiting;

INSERT INTO waiting (wphone, wcount) VALUES
    ('010-1111-2222', 10),
    ('010-2222-3333', 5),
    ('010-3333-4444', 8),
    ('010-4444-5555', 3),
    ('010-5555-6666', 12),
    ('010-6666-7777', 7),
    ('010-7777-8888', 15),
    ('010-8888-9999', 2),
    ('010-9999-0000', 9),
    ('010-1234-5678', 4),
    ('010-8765-4321', 6),
    ('010-1357-2468', 11),
    ('010-2468-1357', 13),
    ('010-1122-3344', 14),
    ('010-2233-4455', 1),
    ('010-3344-5566', 16),
    ('010-4455-6677', 18),
    ('010-5566-7788', 17),
    ('010-6677-8899', 19),
    ('010-7788-9900', 20);