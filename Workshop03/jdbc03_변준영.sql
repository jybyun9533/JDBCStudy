use encore;
CREATE TABLE user (
	ssn int primary key,
    username varchar(50),
    address varchar(100));
    
    CREATE TABLE stock (
	stocknum int primary key,
    stockname varchar(50),
    stockprice double,
    stockquantity double
    );

CREATE TABLE Trade (
	tradenum int primary key,
    tradeprice double,
    tradequantity double,
    ssn int,
    stocknum int
    );
    
    alter table trade add foreign key(ssn) references user(ssn);
    alter table trade add foreign key(stocknum) references stock(stocknum);