insert into user values('bansari@gmail.com', 'Bansari', 'Patel');
insert into user values('mike@gmail.com', 'Mike', 'Bexter');
insert into user values('kelso@gmail.com', 'Kelso', 'Ken');
insert into user values('Kenny@gmail.com', 'Kenny', 'Sage');
insert into user values('Ben@gmail.com', 'Ben', 'Paul');
insert into user values('Percy@gmail.com', 'Percy', 'Burgess');
insert into user values('Adriana@gmail.com', 'Adriana', 'Downes');
insert into user values('Amani@gmail.com', 'Amani', 'Hogg');
insert into user values('Isra@gmail.com', 'Isra', 'Pittman');
insert into user values('Meredith@gmail.com', 'Meredith', 'Bell');
insert into user values('Violet@gmail.com', 'Violet', 'Shelton');

insert into Transaction(transaction_type) values('Invoice');
insert into Transaction(transaction_type) values('Billing');
insert into Transaction(transaction_type) values('Subscription');

insert into user_transaction(user_id, transaction_id, transaction_date_time) values('bansari@gmail.com', 1,'2020-05-12 10:02:22');
insert into user_transaction(user_id, transaction_id, transaction_date_time) values('bansari@gmail.com', 1,'2020-05-12 05:11:56');
insert into user_transaction(user_id, transaction_id, transaction_date_time) values('bansari@gmail.com', 3,'2020-05-12 11:32:43');
insert into user_transaction(user_id, transaction_id, transaction_date_time) values('mike@gmail.com', 1,'2020-02-22 05:11:56');
insert into user_transaction(user_id, transaction_id, transaction_date_time) values('mike@gmail.com', 1,'2020-01-16 03:17:11');
insert into user_transaction(user_id, transaction_id, transaction_date_time) values('mike@gmail.com', 2,'2019-05-13 12:32:22');
insert into user_transaction(user_id, transaction_id, transaction_date_time) values('mike@gmail.com', 2,'2020-05-12 11:16:34');
insert into user_transaction(user_id, transaction_id, transaction_date_time) values('kelso@gmail.com', 2,'2020-05-12 09:45:34');
insert into user_transaction(user_id, transaction_id, transaction_date_time) values('kelso@gmail.com', 3,'2018-10-12 04:43:54');