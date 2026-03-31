-- #################################################

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
--Populate the parking table with data
INSERT INTO parking.parking(spot_id, type, status, zone, floor, number, current_reservation_id)
VALUES (uuid_generate_v4(), 'STANDARD',  'AVAILABLE', 'Zone-A', '1' , '01', null);

INSERT INTO parking.parking(spot_id, type, status, zone, floor, number, current_reservation_id)
VALUES (uuid_generate_v4(),  'STANDARD',  'AVAILABLE', 'Zone-A', '1' , '01', null);

INSERT INTO parking.parking(spot_id, type, status, zone, floor, number, current_reservation_id)
VALUES (uuid_generate_v4(), 'STANDARD',  'AVAILABLE', 'Zone-A', '1' , '01', null);

INSERT INTO parking.parking(spot_id, type, status, zone, floor, number, current_reservation_id)
VALUES (uuid_generate_v4(), 'STANDARD',  'AVAILABLE', 'Zone-A', '1' , '01', null);

INSERT INTO parking.parking(spot_id, type, status, zone, floor, number, current_reservation_id)
VALUES (uuid_generate_v4(), 'STANDARD',  'AVAILABLE', 'Zone-A', '1' , '02', null);

INSERT INTO parking.parking(spot_id, type, status, zone, floor, number, current_reservation_id)
VALUES (uuid_generate_v4(), 'STANDARD',  'AVAILABLE', 'Zone-A', '1' , '03', null);

INSERT INTO parking.parking(spot_id, type, status, zone, floor, number, current_reservation_id)
VALUES (uuid_generate_v4(), 'STANDARD',  'AVAILABLE', 'Zone-A', '1' , '04', null);

INSERT INTO parking.parking(spot_id, type, status, zone, floor, number, current_reservation_id)
VALUES (uuid_generate_v4(), 'STANDARD',  'AVAILABLE', 'Zone-A', '2' , '01', null);

INSERT INTO parking.parking(spot_id, type, status, zone, floor, number, current_reservation_id)
VALUES (uuid_generate_v4() , 'STANDARD',  'AVAILABLE', 'Zone-A', '2' , '03', null);

INSERT INTO parking.parking(spot_id, type, status, zone, floor, number, current_reservation_id)
VALUES (uuid_generate_v4(), 'STANDARD',  'AVAILABLE', 'Zone-B', '1' , '01', null);

INSERT INTO parking.parking(spot_id, type, status, zone, floor, number, current_reservation_id)
VALUES (uuid_generate_v4(), 'STANDARD',  'AVAILABLE', 'Zone-B', '1' , '02', null);

INSERT INTO parking.parking(spot_id, type, status, zone, floor, number, current_reservation_id)
VALUES (uuid_generate_v4(), 'STANDARD',  'AVAILABLE', 'Zone-B', '2' , '01', null);

INSERT INTO parking.parking(spot_id, type, status, zone, floor, number, current_reservation_id)
VALUES (uuid_generate_v4(), 'STANDARD',  'AVAILABLE', 'Zone-B', '2' , '02', null);

INSERT INTO parking.parking(spot_id, type, status, zone, floor, number, current_reservation_id)
VALUES (uuid_generate_v4(), 'STANDARD',  'AVAILABLE', 'Zone-B', '3' , '01', null);

INSERT INTO parking.parking(spot_id, type, status, zone, floor, number, current_reservation_id)
VALUES  (uuid_generate_v4() ,'STANDARD',  'AVAILABLE', 'Zone-B', '3' , '02', null);