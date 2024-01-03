drop table Accounts if exists cascade;
drop table Clients if exists cascade;
drop table CreditCards if exists cascade;
drop table Transactions if exists cascade;
drop table Users if exists cascade;
drop table Roles if exists cascade;
create table Accounts (
    id identity primary key,
    accountNumber varchar(50) not null,
    moneyAmount varchar(50) not null,
    currency varchar(50) not null,
    creditCard bigint,
    client bigint,
    status varchar(50) not null,
    block varchar(50) not null
);
create table Clients (
    id identity primary key,
    fullName varchar(50) not null,
    age varchar(50) not null,
    address varchar(100) not null,
    phoneNumber varchar(50) not null,
    user_id bigint
);
create table CreditCards (
    id identity primary key,
    cardNumber varchar(50) not null,
    cardExpiration varchar(10) not null,
    cardCVV varchar(3) not null,
    client bigint
);
create table Transactions (
    id identity primary key,
    description varchar(50) not null,
    creationDate date not null,
    amount varchar(50) not null,
    account bigint,
    type varchar(50) not null
);
create table Users (
    id identity primary key,
    username varchar(50) unique not null,
    password varchar(100) not null,
    role bigint not null
);
create table Roles (
    id identity primary key,
    roleName varchar(50) not null
);
alter table Users add foreign key (role) references Roles(id);
alter table Transactions add foreign key (account) references Accounts(id);
alter table Accounts add foreign key (creditCard) references CreditCards(id);
alter table CreditCards add foreign key (client) references Clients(id);
