USE medical_store_db;
-- ======================================
-- USERS
-- ======================================

INSERT INTO users(full_name,username,password,role,phone,email)
VALUES
('Ashirvad Shukla','admin','ashirvad@123','ADMIN','7651889112','admin123@gmail.com'),
('Virat Kohli','kohli','kohli@123','PHARMACIST','9935119111','kohli18@gmail.com'),
('Vartika Mishra','mishra','mishra123','STAFF','8978556776','priya8726@gmail.com');


-- ======================================
-- SUPPLIERS
-- ======================================

INSERT INTO suppliers(supplier_name,phone,email,address,company_name)
VALUES
('Jagdeep Nadda','9943876545','nadda23452gmail.com','Sitapur','Raj Pharma pvt. ltd.'),
('Chandan Gupta','9584757473','chandan23@gmail.com','Lucknow','Ambar Medical Agency'),
('Anamika Pathak','8756474747','anamika54@gmail.com','Lakhimpur','Vimal Dawakhana'),
('Varun Nair','8767859487','varun34@gmail.com','Pilibhit','The Medo pharma'),
('Vandana Pandey','6564739203','vandana43@gmail.com','Maholi','Shyam Medical Agency');


-- =======================================
-- MEDICINES
-- =======================================
-- 1 - 10
INSERT INTO medicines(medicine_name,formula,category,batch_no,manufacture_date,expiry_date,purchase_price,selling_price,quantity_in_stock,rack_no,supplier_id)
VALUES
('Paracetamol 500','Paracetamol','Tablet','PCM001','2025-01-15','2028-01-14',12.50,18.00,150,'A1',1),
('Dolo 650','Paracetamol','Tablet','DOL001','2025-02-10','2028-02-02',18.00,25.00,120,'A2',1),
('Crocin Advance','Paracetamol','Tablet','CRC003','2025-01-25','2025-02-24',15.00,22.00,100,'A3',2),
('Combiflam','Ibuprofen + Paracetamol','Tablet','CBF004','2025-03-12','2028-03-11',28.00,38.00,80,'A4',2),
('Aspirin 75','Aspirin','Tablet','ASP005','2025-01-18','2028-01-17',20.00,30.00,90,'A5',3),
('Amoxicillin 500','Amoxicillin','Capsule','AMX006','2025-02-05','2027-02-04',48.00,65.00,75,'B1',3),
('Azithromycin 500','Azithromycin','Tablet','AZM007','2025-05-03','2027-05-02',75.00,95,60,'B2',3),
('Cefixime 200','Cefixime','Tablet','CFX008','2025-02-20','2027-02-19',85.00,110,70,'B2',4),
('Cetirizine 10','Cetirizine','Tablet','CTZ009','2025-03-15','2027-03-14',8.00,12.00,180,'C1',4),
('Benadryl Syrup','diphenhydramine','Syrup','BND010','2025-04-01','2028-03-31',65.00,85.00,40,'C2',5);

SELECT * FROM medicines;


-- 11 - 20
INSERT INTO medicines
(medicine_name, formula, category, batch_no, manufacture_date, expiry_date,
 purchase_price, selling_price, quantity_in_stock, rack_no, supplier_id)
VALUES
('Pantoprazole 40','Pantoprazole','Tablet','PAN011','2025-03-10','2028-03-09',38.00,55.00,120,'C3',1),
('Omeprazole 20','Omeprazole','Capsule','OME012','2025-02-18','2028-02-17',30.00,45.00,110,'C4',2),
('Rabeprazole 20','Rabeprazole','Tablet','RAB013','2025-01-22','2028-01-21',42.00,60.00,95,'C5',3),
('Metformin 500','Metformin','Tablet','MET014','2025-03-05','2028-03-04',22.00,35.00,160,'D1',4),
('Glimipride 2','Glimepiride','Tablet','GLM015','2025-04-12','2028-04-11',28.00,42.00,130,'D2',4),
('Amlodipine 5','Amlodipine','Tablet','AML016','2025-02-15','2028-02-14',18.00,28.00,140,'D3',5),
('Telmisartan 40','Telmisartan','Tablet','TEL017','2025-03-18','2028-03-17',55.00,75.00,90,'D4',5),
('Atorvastatin 10','Atorvastatin','Tablet','ATR018','2025-01-28','2028-01-27',45.00,65.00,85,'D5',1),
('Vitamin C 500','Ascorbic Acid','Tablet','VTC019','2025-04-05','2028-04-04',15.00,25.00,200,'E1',2),
('Calcium Tablets','Calcium Carbonate','Tablet','CAL020','2025-05-08','2028-05-07',32.00,48.00,170,'E2',3);



-- 21 - 30
INSERT INTO medicines
(medicine_name, formula, category, batch_no, manufacture_date, expiry_date,
 purchase_price, selling_price, quantity_in_stock, rack_no, supplier_id)
VALUES
('Insulin Injection','Human Insulin','Injection','INS021','2025-03-15','2027-03-14',320.00,380.00,35,'E3',4),
('ORS Powder','Oral Rehydration Salts','Powder','ORS022','2025-04-20','2028-04-19',12.00,20.00,180,'E4',2),
('Digene Gel','Magaldrate + Simethicone','Gel','DIG023','2025-02-25','2028-02-24',95.00,120.00,45,'F1',3),
('Volini Spray','Diclofenac Spray','Spray','VOL024','2025-01-30','2028-01-29',110.00,145.00,60,'F2',5),
('Betadine Solution','Povidone Iodine','Antiseptic','BET025','2025-05-05','2028-05-04',75.00,95.00,70,'F3',1),
('Zinc Tablets','Zinc Sulphate','Tablet','ZNC026','2025-03-12','2028-03-11',18.00,28.00,160,'F4',2),
('Liv 52','Herbal Liver Tonic','Tablet','LIV027','2025-02-18','2028-02-17',105.00,140.00,90,'F5',3),
('Dexorange Syrup','Iron + Folic Acid','Syrup','DEX028','2025-04-10','2028-04-09',88.00,115.00,55,'G1',4),
('Multivitamin Capsules','Multivitamin','Capsule','MVT029','2025-05-01','2028-04-30',65.00,90.00,125,'G2',5),
('Refresh Eye Drops','Carboxymethylcellulose','Eye Drops','EYE030','2025-03-22','2027-03-21',140.00,180.00,40,'G3',1);

SELECT COUNT(*) AS Total_medicines FROM medicines;
SELECT medicine_id, medicine_name, category, quantity_in_stock FROM medicines
ORDER BY medicine_id;

-- =====================================================
-- CUSTOMERS
-- =====================================================

INSERT INTO customers
(customer_name, phone, email,address)
VALUES
('Pankaj verma','9934876754','pankaj.verma@gmail.com','Hardoi'),
('Dheeral Pal','9987645634','dheeraj.pal@gmail.com','Maholi'),
('Vibhav Shukla','7857458932','vibhav.shukla@gmail.com','Sitapur'),
('Inzmam Khan','6754635287','inzmam.khan@gmail.com','Laharpur'),
('Harsh Raj','6473564863','harsh.raj@gmail.com','Bahraich'),
('Aman Kushwaha','9877676543','aman.kushwaha@gmail.com','Banaras'),
('Dhoni Shukla','7651990334','dhoni.shukla@gmail.com','Maholi'),
('Akshat Diwakar','7567435467','akshat.diwakar@gmail.com','Gonda'),
('Anshuman Shukla','8303288997','anshuman.shukla@gmail.com','Lucknow'),
('Kartikeya Kumar','8974256735','kartikeya.kumar@gmail.com','Lucknow'),
('Kamini Shukla','7574562343','kamini.shukla@gmail.com','Mishrikh'),
('Indra Kumar','6785345623','indra.kumar@gmail.com','Maholi'),
('Bharat Mishra','9978645682','bharat.mishra@gmail.com','Bramhawali'),
('Sumit Sukla','8764062478','sumit.shukla@gmail.com','Maholi'),
('Akanksha Shukla','7234786541','askanksha.shukla@gmail.com','Lucknow');

SELECT COUNT(*) AS Total_customers FROM customers;
SELECT * FROM customers;

