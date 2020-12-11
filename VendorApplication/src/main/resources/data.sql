insert into vendor (delivery_charge, rating, vendor_name, vendor_id) values (50.0,5 ,'ABC',1);
insert into vendor (delivery_charge, rating, vendor_name, vendor_id) values (60.0,3 ,'Nairobi',2);
insert into vendor (delivery_charge, rating, vendor_name, vendor_id) values (100.0,4 ,'GuJun',3);

insert into vendorstock (expected_date, product_stock, vendor_id, product_id,vendor_stock_id) values ('2021-01-01', 100,1,1,1);
insert into vendorstock (expected_date, product_stock, vendor_id, product_id,vendor_stock_id) values ('2020-12-12', 20,1,2,2);
insert into vendorstock (expected_date, product_stock, vendor_id, product_id,vendor_stock_id) values ('2020-12-31', 0,1,3,3);
insert into vendorstock (expected_date, product_stock, vendor_id, product_id,vendor_stock_id) values ('2021-12-24', 110,1,5,4);

insert into vendorstock (expected_date, product_stock, vendor_id, product_id,vendor_stock_id) values ('2020-12-12', 180,2,2,5);
insert into vendorstock (expected_date, product_stock, vendor_id, product_id,vendor_stock_id) values ('2020-12-31', 0,2,3,6);
insert into vendorstock (expected_date, product_stock, vendor_id, product_id,vendor_stock_id) values ('2021-01-01', 80,2,4,7);
insert into vendorstock (expected_date, product_stock, vendor_id, product_id,vendor_stock_id) values ('2021-01-10', 150,2,6,8);

insert into vendorstock (expected_date, product_stock, vendor_id, product_id,vendor_stock_id) values ('2021-01-01', 120,3,1,9);
insert into vendorstock (expected_date, product_stock, vendor_id, product_id,vendor_stock_id) values ('2021-01-01', 200,3,4,10);
insert into vendorstock (expected_date, product_stock, vendor_id, product_id,vendor_stock_id) values ('2021-12-24', 30,3,5,11);
insert into vendorstock (expected_date, product_stock, vendor_id, product_id,vendor_stock_id) values ('2021-01-10', 120,3,6,12);