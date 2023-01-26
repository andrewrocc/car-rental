INSERT INTO USER VALUES
(1, 'Eugene', 'Onegin', 'EugeneOnegin@mail.com', '1111222233334444'),
(2, 'Mark', 'Baum', 'markbaum@info.com', '9999888877776666'),
(3, 'John', 'Doe', 'anonymous@dark.org', '2281488555487130'),
(4, 'Egor', 'Nikitin', 'borland1997@tut.by', '2469105876642001'),
(5, 'Tsvor', 'Andrew', 'prostandrey1998@gmail.com', '7410258741002563'),
(6, 'Soroka', 'Andrey', 'lolipophiphopkrasivo@mail.ru', '9654120873221457'),
(7, 'Konstantin', 'Bolotko', 'pro100kosti@mail.com', '0123456789125478');

/*INSERT INTO PAYMENT VALUES
(1, '2022-01-01 10:10'),
(2, '2022-10-01 12:10'),
(3, '2020-03-12 11:11'),
(4, '2020-01-01 10:10'),
(5, '2019-12-01 09:01'),
(6, '2022-04-23 14:34'),
(7, '2022-12-19 00:30');*/

INSERT INTO CAR_BRAND VALUES
(1, 'BMW'),
(2, 'Mercedes-Benz'),
(3, 'Toyta'),
(4, 'Audi'),
(5, 'Honda'),
(6, 'Volkswagen'),
(7, 'Ford'),
(8, 'KIA'),
(9, 'Lexus'),
(10, 'Lincoln'),
(11, 'Nissan'),
(12, 'Volvo');

INSERT INTO CAR_MODEL VALUES
(1, 1, 'M3 competition'),
(2, 2, 'AMG G63'),
(3, 3, 'Land Cruiser 300'),
(4, 4, 'RS 5'),
(5, 5, 'CR-V 2020'),
(6, 6, 'Passat Business 2022'),
(7, 7, 'GT 2017'),
(8, 8, 'K5'),
(9, 9, 'ES'),
(10, 10, 'Town Car'),
(11, 11, 'GT-R'),
(12, 12, 'S90'),
(13, 1, '420i'),
(14, 1, '520i'),
(15, 2, 'AMG GT'),
(16, 2, 'E 450'),
(17, 3, 'Prius LE'),
(18, 4, 'RS 4'),
(19, 5, 'Accord Sport 2.0T'),
(20, 6, 'Golf R'),
(21, 7, '2022 Expedition');

-- INSERT INTO CAR_type VALUES
-- (1, 'sedan'),
-- (2, 'hatchback'),
-- (3, 'crossover'),
-- (4, 'sport car'),
-- (5, 'hypercar'),
-- (6, 'business');

INSERT INTO CAR VALUES
(1, '9874', 250.45, 1, 1),
(2, '1234', 140.1, 2, 2),
(3, '3410', 505.19, 3, 3),
(4, '1246', 785.4, 4, 4),
(5, '7806', 111.0, 5, 5),
(6, '0689', 65.98, 6, 6),
(7, '6748', 506.8, 7, 7),
(8, '1111', 450.1, 8, 8),
(9, '0121', 150.99, 9, 9),
(10, '1188', 751.0, 10, 10),
(11, '0167', 920.50, 11, 11),
(12, '2121', 156.19, 12, 12),
(13, '0965e', 345.31, 1, 13),
(14, '2121', 126.89, 1, 14),
(15, '6790s', 355.12, 2, 15),
(16, '130te', 263.52, 2, 16),
(17, '647t1', 865.16, 3, 17),
(18, '60j42', 345.78, 4, 18),
(19, '45fh4', 946.01, 5, 19),
(20, '1058f', 563.71, 6, 20),
(21, '9567r', 836.14, 7, 21);

INSERT INTO BOOKING VALUES
(1, 324.7, '2022-01-01', 3),
(2, 56.8, '2022-10-01', 2),
(3, 234.6, '2020-03-12', 1),
(4, 45.4, '2020-01-01', 4),
(5, 78.8, '2019-12-01', 4),
(6, 796.6, '2022-04-23', 4),
(7, 47.9, '2022-12-19', 7),
(8, 124.1, '2023-01-29', 10),
(9, 456.10, '2023-02-10', 3),
(10, 841.77, '2023-02-10', 20),
(11, 456.10, '2023-02-10', 1),
(12, 592.96, '2023-02-22', 1),
(13, 957.10, '2023-04-19', 8),
(14, 56.20, '2023-03-22', 15),
(15, 78.02, '2023-01-10', 1);

INSERT INTO ROLE VALUES
(1, "admin", "Administrators have full, unrestricted access rights to the service or domain."),
(2, "user", "Users do not have rights to change the parameters of the service and can use most of the functions."),
(3, "guest", "By default, guests can only view cars on the service, but they can not order them.");

INSERT INTO PERMISSION VALUES
(1, "create_car", 1),
(2, "view_cars", 1),
(3, "update_car", 1),
(4, "delete_car", 1),
(5, "create_order", 1),
(6, "create_payment", 1),
(7, "view_payments", 1),
(8, "view_orders", 1),
(9, "create_user", 1),
(10, "update_user", 1),
(11, "delete_user", 1),
(12, "view_users", 1),
(13, "view_cars", 2),
(14, "create_order", 2),
(15, "create_payment", 2),
(16, "view_cars", 3);

/*INSERT INTO BOOKING_PAYMENT VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 3),
(5, 4),
(6, 5),
(7, 6),
(7, 7);*/

INSERT INTO USER_BOOKING VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 3),
(5, 4),
(6, 5),
(7, 6),
(7, 7),
(1, 8),
(2, 9),
(2, 10),
(3, 11),
(4, 12),
(5, 13),
(6, 14),
(7, 15);

INSERT INTO USER_ROLE VALUES
(1, 2),
(2, 2),
(3, 2),
(4, 2),
(5, 2),
(6, 2),
(7, 1);

INSERT INTO BOOKING_CAR VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 13),
(9, 12),
(10, 15),
(11, 19),
(12, 11),
(13, 10),
(14, 9),
(15, 20);