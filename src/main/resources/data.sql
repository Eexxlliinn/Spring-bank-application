delete from Clients cascade;
delete from CreditCards cascade;
delete from Accounts cascade;
delete from Transactions cascade;
delete from Users cascade;
delete from Roles cascade;

insert into Roles(id, roleName) values ('1', 'USER');
insert into Roles(id, roleName) values ('2', 'ADMIN');

drop sequence USERS_SEQ if exists;
CREATE SEQUENCE USERS_SEQ START WITH 1 INCREMENT BY 50;
drop sequence CREDIT_CARDS_SEQ if exists;
CREATE SEQUENCE CREDIT_CARDS_SEQ START WITH 1 INCREMENT BY 50;
drop sequence ACCOUNTS_SEQ if exists;
CREATE SEQUENCE ACCOUNTS_SEQ START WITH 1 INCREMENT BY 50;
drop sequence TRANSACTIONS_SEQ if exists;
CREATE SEQUENCE TRANSACTIONS_SEQ START WITH 1 INCREMENT BY 50;