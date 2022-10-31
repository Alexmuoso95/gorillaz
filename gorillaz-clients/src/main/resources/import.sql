INSERT INTO users (id , name , password, status ) VALUES (1,'alex','$2a$10$CvY4CmEClKWTWFU0xEBj2O9jEzXwT.9INQq3uHi2vMKRtJRtsqDBG',1);
INSERT INTO users (id , name , password, status ) VALUES (2,'yare','$2a$10$gqNNNd0tVcxrKqStuzIC0OBARJoC1WI1GmLSrWrKjvEuaL7W001CO',1);

INSERT INTO roles (id , name ) VALUES (1,'ROLE_ADMIN');
INSERT INTO roles (id , name ) VALUES (2,'ROLE_USER');

INSERT INTO users_roles (user_id , role_id ) VALUES (1,1);
INSERT INTO users_roles (user_id , role_id ) VALUES (2,2);
INSERT INTO users_roles (user_id , role_id ) VALUES (1,2);

INSERT INTO clients (id,name, last_name, email, phone_number, create_at ) VALUES (100,'ALEX','GUZMAN','ALEXMUOSORIO@GMAIL.COM', '0000', '2018-01-01');
INSERT INTO clients (id,name, last_name, email, phone_number, create_at ) VALUES (101,'YARE','OROZCO','OROZCO@GMAIL.COM', '123', '2018-02-01');
INSERT INTO clients (id,name, last_name, email, phone_number, create_at ) VALUES (102,'PEPE','AGUILAR','PEPE@GMAIL.COM', '456', '2018-03-01');
INSERT INTO clients (id,name, last_name, email, phone_number, create_at ) VALUES (103,'SERGIO','PEREX','PEREX@GMAIL.COM', '789', '2018-04-01');

INSERT INTO address (id,street, colony, num_ext, num_int, postal_code,client_id ) VALUES (100,'AV.1','V.Hermosa','123', '', '28017',100);
INSERT INTO address (id,street, colony, num_ext, num_int, postal_code,client_id ) VALUES (105,'AV.2','V.Hermosa','000', '', '28017',100);
INSERT INTO address (id,street, colony, num_ext, num_int, postal_code,client_id ) VALUES (101,'AV.2','V.Carranza','345', '', '28017',101);
INSERT INTO address (id,street, colony, num_ext, num_int, postal_code,client_id ) VALUES (102,'AV.3','Villa','567', '', '28017',102);
INSERT INTO address (id,street, colony, num_ext, num_int, postal_code,client_id ) VALUES (103,'AV.4','Padros','789', '', '28017',103);

INSERT INTO products (id,name,price,description) VALUES (20,'Lavado de interiores','','Nos aseguramos que su auto quede impecable');
INSERT INTO products (id,name,price,description) VALUES (21,'Lavado de exteriores','','');
INSERT INTO products (id,name,price,description) VALUES (22,'Enserado','','');
INSERT INTO products (id,name,price,description) VALUES (23,'Pintura','','');
INSERT INTO products (id,name,price,description) VALUES (24,'Restauracion','','');
INSERT INTO products (id,name,price,description) VALUES (25,'Retoques','','');