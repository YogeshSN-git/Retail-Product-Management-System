insert into vendor (delivery_charge, rating, vendor_name, vendor_id) values (50.0,5 ,'ABC',1);
insert into vendor (delivery_charge, rating, vendor_name, vendor_id) values (60.0,3 ,'Nairobi',2);
insert into vendor (delivery_charge, rating, vendor_name, vendor_id) values (100.0,4 ,'GuJun',3);

insert into vendorstock (expected_date, product_stock, vendor_id, product_id) values ('2021-01-01', 100,1 , 1);
insert into vendorstock (expected_date, product_stock, vendor_id, product_id) values ('2020-12-12', 20,1 , 2);

insert into vendorstock (expected_date, product_stock, vendor_id, product_id) values ('2020-12-31', 0,2, 3);
insert into vendorstock (expected_date, product_stock, vendor_id, product_id) values ('2021-01-01', 80,2 , 4);

insert into vendorstock (expected_date, product_stock, vendor_id, product_id) values ('2021-12-24', 50,3 , 5);
insert into vendorstock (expected_date, product_stock, vendor_id, product_id) values ('2021-01-10', 120,3, 6);