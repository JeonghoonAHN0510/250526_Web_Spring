drop database if exists assessment;
create database assessment;
use assessment;

create table MEMBER_TBL_02 (
	custno int not null,	-- 회원코드
    custname varchar(20),	-- 회원이름
    phone varchar(13),		-- 연락처
    address varchar(60),	-- 주소
    joindate date,			-- 가입일자(YYYY-MM-DD)
    grade char(1),			-- 고객등급( A / B / C )
    city char(2),			-- 거주도시 코드
    constraint primary key(custno)
);

create table MONEY_TBL_02 (
	custno int not null,	-- 회원코드
    salenol int not null,	-- 판매코드
    pcost int,				-- 단가
    amount int,				-- 수량
    price int,				-- 가격
    pcode varchar(4),		-- 상품코드
    sdate date,				-- 판매날짜
    constraint foreign key (custno)
		references MEMBER_TBL_02(custno),
	constraint primary key (salenol)
);

insert into member_tbl_02 values
	( 100001, '김행복', '010-1111-2222', '서울 동대문구 휘경1동', '2015-12-02', 'A', '01' ),
    ( 100002, '이축복', '010-1111-3333', '서울 동대문구 휘경2동', '2015-12-06', 'B', '01' ),
    ( 100003, '장믿음', '010-1111-4444', '울릉군 울릉읍 독도1리', '2015-10-01', 'B', '30' ),
    ( 100004, '최사랑', '010-1111-5555', '울릉군 울릉읍 독도2리', '2015-11-13', 'A', '30' ),
    ( 100005, '진평화', '010-1111-6666', '제주도 제주시 외나무골', '2015-12-25', 'B', '60' ),
    ( 100006, '차공단', '010-1111-7777', '제주도 제주시 감나무골', '2015-12-11', 'C', '60' );

insert into money_tbl_02 values
	( 100001, 20160001, 500, 5, 2500, 'A001', '2016-01-01' ),
    ( 100001, 20160002, 1000, 4, 4000, 'A002', '2016-01-01' ),
    ( 100001, 20160003, 500, 3, 1500, 'A008', '2016-01-01' ),
    ( 100002, 20160005, 500, 1, 500, 'A001', '2016-01-03' ),
    ( 100003, 20160006, 1500, 2, 3000, 'A003', '2016-01-03' ),
    ( 100004, 20160007, 500, 2, 1000, 'A001', '2016-01-04' ),
    ( 100004, 20160008, 300, 1, 300, 'A005', '2016-01-04' ),
    ( 100004, 20160009, 600, 1, 600, 'A006', '2016-01-04' ),
    ( 100004, 20160010, 3000, 1, 3000, 'A007', '2016-01-06' );

select * from member_tbl_02;
select * from money_tbl_02;	