/*BASE DE DATOS*/

DROP DATABASE IF EXISTS BD_OPENFLY;
CREATE DATABASE  BD_OPENFLY;
USE BD_OPENFLY;

-- TABLA PAIS
DROP TABLE IF EXISTS PAIS;
CREATE TABLE PAIS(
	codPais  CHAR(5)     NOT NULL,
	descPais VARCHAR(20) NOT NULL);
ALTER TABLE PAIS
ADD CONSTRAINT PKPais PRIMARY KEY (codPais);

-- TABLA CLIENTE
DROP TABLE IF EXISTS CLIENTE;
CREATE TABLE CLIENTE(
	codClie	   CHAR(6)     NOT NULL ,
	nomTipDocClie  VARCHAR(20) NOT NULL,
	numDocClie  VARCHAR(8) NOT NULL, 
	nomClie    VARCHAR(30) NOT NULL, 
	apeClie    VARCHAR(30) NOT NULL, 
	fecNacClie DATE        NOT NULL, 
	sexoClie    CHAR(1)    NOT NULL,   
	telfClie   CHAR(15)	   NOT NULL, 
	correoClie   VARCHAR(50) NOT NULL, 
	codPais    CHAR(5)    NOT NULL,
	nomUsu    VARCHAR(20)    NOT NULL UNIQUE,
    contUsu   VARCHAR(20)    NOT NULL);
ALTER TABLE CLIENTE
ADD CONSTRAINT PKCliente PRIMARY KEY (codClie),
ADD CONSTRAINT FKPaisCli FOREIGN KEY (`codPais`) REFERENCES PAIS(`codPais`),
ADD CONSTRAINT UQNumDocCli UNIQUE (numDocClie),
ADD CONSTRAINT CKSexo CHECK (sexoClie IN('F' AND 'M')),
ADD CONSTRAINT CKTipoDoc CHECK (nomTipDocClie IN('DNI' AND 'Pasaporte')) ;

/*-----------------------------------------------------------------PASO 1--------------------------------------------------------------*/
-- TABLA CIUDAD
DROP TABLE IF EXISTS CIUDAD;
CREATE TABLE CIUDAD(
	codCiu CHAR(4) NOT NULL,
    descCiu VARCHAR(30) NOT NULL
);
ALTER TABLE CIUDAD
ADD CONSTRAINT PKCiudad PRIMARY KEY (codCiu);

-- TABLA AEROPUERTO
DROP TABLE IF EXISTS AEROPUERTO;
CREATE TABLE AEROPUERTO(
	codAero  CHAR(3)    NOT NULL,
	descAero VARCHAR(50) NOT NULL,
	codCiu  CHAR(4) NOT NULL
);
ALTER TABLE AEROPUERTO
ADD CONSTRAINT PKAeropuerto PRIMARY KEY (codAero),
ADD CONSTRAINT FKCiudadAero FOREIGN KEY (`codCiu`) REFERENCES CIUDAD(`codCiu`);

                                
-- TABLA ORIGEN
DROP TABLE IF EXISTS ORIGEN;
CREATE TABLE ORIGEN(
	codOrig  CHAR(3)  NOT NULL,
	codAero  CHAR(3)  NOT NULL
);
ALTER TABLE ORIGEN
ADD CONSTRAINT PKOrigen PRIMARY KEY (codOrig),
ADD CONSTRAINT FKAeroOri FOREIGN KEY (`codAero`) REFERENCES AEROPUERTO(`codAero`);

-- TABLA DESTINO
DROP TABLE IF EXISTS DESTINO;
CREATE TABLE DESTINO(
	codDest  CHAR(3)   NOT NULL,
	codAero  CHAR(3)   NOT NULL         
);
ALTER TABLE DESTINO
ADD CONSTRAINT PKDestino PRIMARY KEY (codDest),
ADD CONSTRAINT FKAeroDest FOREIGN KEY (`codAero`) REFERENCES AEROPUERTO(`codAero`);

-- TABLA VUELO
DROP TABLE IF EXISTS VUELO;
CREATE TABLE VUELO(
	codVue  CHAR(6) NOT NULL,
	codOrig CHAR(3) NOT NULL , 
    codDest CHAR(3) NOT NULL,
    tipVuelo VARCHAR(12) NOT NULL,
    cantiAsieVue  INT NOT NULL,
	fechSalVue  DATE NOT NULL,
    horaSalVue TIME NULL,
	horaLlegVue TIME NULL,
    precioVuel DECIMAL(6,2) NULL
);
ALTER TABLE VUELO 
ADD CONSTRAINT PKVuelo PRIMARY KEY (codVue),
ADD CONSTRAINT FKOrigVue FOREIGN KEY (codOrig) REFERENCES ORIGEN(codOrig),
ADD CONSTRAINT FKDestVue FOREIGN KEY (codDest) REFERENCES DESTINO(codDest),
ADD CONSTRAINT CKVuelo CHECK (tipVuelo IN('Ida' AND 'Vuelta')),
ADD CONSTRAINT CKCantAsiento CHECK (cantiAsieVue>0),
ADD CONSTRAINT CKPrecioVue CHECK (precioVuel>0);

                        
/*-----------------------------------------------------------------PASO 2--------------------------------------------------------------*/
-- 	TABLA ACOMPAÑANTE
DROP TABLE IF EXISTS ACOMPAÑANTE;
CREATE TABLE ACOMPAÑANTE(
	numDocAcomp VARCHAR(8)  NOT NULL,
	tipDocAcomp   VARCHAR(20) NOT NULL,
	nomAcomp    VARCHAR(50)  NOT NULL,
	apeAcomp    VARCHAR(50)  NOT NULL,  
	fecNacAcomp DATE  NOT NULL,
	sexoAcomp    CHAR(1)   NOT NULL
);
ALTER TABLE ACOMPAÑANTE
ADD CONSTRAINT PKAcompañante PRIMARY KEY(numDocAcomp),
ADD CONSTRAINT UQNumDocAcomp UNIQUE (numDocAcomp),
ADD CONSTRAINT CKSexoAcom CHECK (sexoAcomp  IN('F' AND 'M')),
ADD CONSTRAINT CKTipoDocAcom CHECK (tipDocAcomp IN('DNI' AND 'Pasaporte')) ;

/*-----------------------------------------------------------------PASO 3--------------------------------------------------------------*/
/*MOSTRAR EN EL PDF BOLETO,DETALLE_BOLETO,FACTURA*/
-- TABLA BOLETA
DROP TABLE IF EXISTS BOLETO;
CREATE TABLE BOLETO(
	codBoleto    CHAR(7)  NOT NULL,
    codClie 	 CHAR(6) NOT NULL,
    codVue CHAR(6) NOT NULL,
    codVV  CHAR(6) NULL,
    cantiAsienReser INT NOT NULL
);
ALTER TABLE BOLETO
ADD CONSTRAINT PKBoleto PRIMARY KEY (codBoleto),
ADD CONSTRAINT FKClienBol FOREIGN KEY (codClie) REFERENCES CLIENTE (codClie),
ADD CONSTRAINT FKVuelBol FOREIGN KEY (codVue) REFERENCES VUELO(codVue),
ADD CONSTRAINT FKVuelBole FOREIGN KEY (codVV) REFERENCES VUELO(codVue),
ADD CONSTRAINT CKCantAsientoBole CHECK (cantiAsienReser>0);

-- TABLA DETALLE DE BOLETA
DROP TABLE IF EXISTS DETALLE_BOLETO;
CREATE TABLE DETALLE_BOLETO(
	codBoleto    CHAR(7)  NOT NULL,
    numDocAcomp VARCHAR(8)  NOT NULL
);
ALTER TABLE DETALLE_BOLETO
ADD CONSTRAINT FKBoleto FOREIGN KEY (codBoleto) REFERENCES BOLETO(codBoleto),
ADD CONSTRAINT FKAcompañante FOREIGN KEY (numDocAcomp) REFERENCES ACOMPAÑANTE(numDocAcomp);

-- TABLA PROMOCIONES
DROP TABLE IF EXISTS PROMOCIONES;
CREATE TABLE PROMOCIONES (
	cod_Promo char(10) NOT NULL,
    descrip_Promo varchar(70) NOT NULL,
    porcentaje_desc DECIMAL(3,2) NOT NULL,
    fechaIni_Promo DATE NOT NULL,
    fechaFin_Promo DATE NOT NULL
);
ALTER TABLE PROMOCIONES
ADD CONSTRAINT PKPromocion PRIMARY KEY (cod_Promo);

-- TABLA FACTURA
DROP TABLE IF EXISTS FACTURA;
CREATE TABLE FACTURA (
	numFac CHAR(5) NOT NULL,
    codBoleto  CHAR(7)  NOT NULL,
    fechEmiFac DATE NOT NULL,
    numTarjetFac VARCHAR(16) NOT NULL,
    imporTotalFact DECIMAL(6,2) NOT NULL,
    igvFact DECIMAL(6,2) NOT NULL,
    totalPagarFact DECIMAL(6,2)NOT NULL,
    cod_Promo char(10)  NULL
);
ALTER TABLE FACTURA 
ADD CONSTRAINT PKFactura PRIMARY KEY (numFac),
ADD CONSTRAINT FKBoletoFac FOREIGN KEY (codBoleto) REFERENCES BOLETO(codBoleto),/*YA QUE PUEDE HABER DOS BOLETAS UNO DE IDA Y OTRO DE VUELTA*/
ADD CONSTRAINT FKFactuPromo FOREIGN KEY (cod_Promo) REFERENCES PROMOCIONES (cod_Promo),
ADD CONSTRAINT CKImporte CHECK (imporTotalFact>0),
ADD CONSTRAINT CKIGV CHECK (igvFact>0),
ADD CONSTRAINT CKTotal CHECK(totalPagarFact>0);

-- TABLA ASIENTO
DROP TABLE IF EXISTS ASIENTO;
CREATE TABLE ASIENTO (
	codBoleto    CHAR(7)  NOT NULL,
    codVue      char(6)  NOT NULL,
    nomAsiento   VARCHAR(4) NOT NULL
);
ALTER TABLE ASIENTO
ADD CONSTRAINT FKVueloAs foreign key (codVue) REFERENCES VUELO(codVue),
ADD CONSTRAINT FKBoletaAs foreign key (codBoleto) REFERENCES BOLETO(codBoleto);

/*----------------------------------------------INSERTANDO  REGISTROS--------------------------------------------------*/
INSERT PAIS VALUES  ('+54','Argentina'),
		            ('+591','Bolivia'),
                    ('+55','Brasil'),
                    ('+56','Chile'),
                    ('+57','Colombia'),
                    ('+506','Costa Rica'),
                    ('+53','Cuba'),
                    ('+593','Ecuador'),
                    ('+34','España'),
                    ('+502','Guatemala'),
                    ('+504','Honduras'),
                    ('+876','Jamaica'),
                    ('+52','México'),
                    ('+505','Nicaragua'),
                    ('+595','Paraguay'),
                    ('+507','Panamá'),
                    ('+51','Perú'),
                    ('+1787','Puerto Rico'),
                    ('+809','República Dominicana'),
                    ('+598','Uruguay'),
                    ('+58','Venezuela');
                    
INSERT INTO CLIENTE VALUES 	('C10001','DNI','70915520','Madeliy','Ricra Gutierrez','2002-04-23','F','986180040','madeleyricra23@gmail.com','+51','Made','2020@Made'),
							('C10002','DNI','70856230','Luis','Perez Burga','2001-08-21','M','978569522','i201910562@cibertec.edu.pe','+51','Luis','2020@Luis'),
                            ('C10003','DNI','72563246','Willy','Melendez Gamarra','2001-09-17','M','912365895','i201914476@cibertec.edu.pe','+51','Willy','2020@Willy'),
                            ('C10004','DNI','74256335','Carlos','Machaca Huaman','2001-08-25','M','923658952','i201911241@cibertec.edu.pe','+51','Carlos','2020@Carlos'),
                            ('C10005','DNI','74256325','Maverick','Champi Romero','2001-09-21','M','923658956','i201911922@cibertec.edu.pe','+51','Maverick','2020@Maverick');

INSERT INTO CIUDAD VALUES 	('PC01','Arequipa'),
							('PC02','Ayacucho'),
                            ('PC03','Cajamarca'),
                            ('PC04','Cuzco'),
                            ('PC05','Iquitos'),
                            ('PC06','Lima'),
                            ('PC07','Piura'),
                            ('PC08','Puerto Maldonado'),
                            ('PC09','Tarapoto'),
                            ('PC10','Trujillo'),
                            ('PC11','Tumbes');
                            
INSERT INTO AEROPUERTO VALUES 	('AQP','Rodríguez Ballón','PC01'),
								('AYP','Coronel FAP Alfredo Mendívil','PC02'),
                                ('CJA','Mayor Gral. FAP Armando Revoredo','PC03'),
                                ('CUZ','Alejandro Velazco Astete','PC04'),
                                ('IQT','Coronel FAP Francisco Secada V.','PC05'),
                                ('LIM','Internacional Jorge Chávez','PC06'),
                                ('PIU','Capitán FAP Carlos Concha','PC07'),
                                ('PEM','Padre Aldamiz','PC08'),
                                ('TPP','Tarapoto','PC09'),
                                ('TRU','Capitán FAP Carlos Martínez Pinillos','PC10'),
                                ('TBP','Capitán FAP Pedro Canga','PC11');			

INSERT INTO ORIGEN VALUES ('O01','AQP'),
						  ('O02','AYP'),
						  ('O03','CJA'),
						  ('O04','CUZ'),
						  ('O05','IQT'),
						  ('O06','LIM'),
						  ('O07','PIU'),
						  ('O08','PEM'),
						  ('O09','TPP'),
						  ('O10','TRU'),
						  ('O11','TBP');     
                          
INSERT INTO DESTINO VALUES ('D01','AQP'),
						   ('D02','AYP'),
						   ('D03','CJA'),
						   ('D04','CUZ'),
						   ('D05','IQT'),
						   ('D06','LIM'),
						   ('D07','PIU'),
						   ('D08','PEM'),
						   ('D09','TPP'),
						   ('D10','TRU'),
						   ('D11','TBP');             
 /*VUELOS DE IDA*/                          
 INSERT INTO VUELO VALUES('VA1001','O01','D10','Ida',42,'2020-08-01','12:00:00','13:30:00',120.0),
						 ('VA1002','O02','D09','Ida',42,'2020-08-08','21:00:00','22:30:00',120.0),
                         ('VA1003','O03','D08','Ida',42,'2020-08-01','10:20:00','11:30:00',120.0),
                         ('VA1004','O04','D07','Ida',42,'2020-08-08','18:00:00','19:30:00',120.0),
                         ('VA1005','O05','D06','Ida',42,'2020-08-01','12:00:00','13:30:00',120.0),
                         ('VA1006','O06','D05','Ida',42,'2020-08-08','12:00:00','13:30:00',120.0),
                         ('VA1007','O07','D04','Ida',42,'2020-08-01','12:00:00','13:30:00',120.0),
                         ('VA1008','O08','D03','Ida',42,'2020-08-08','12:00:00','13:30:00',120.0),
                         ('VA1009','O09','D02','Ida',42,'2020-08-01','12:00:00','13:30:00',120.0),
                         ('VA1010','O10','D01','Ida',42,'2020-08-08','12:00:00','13:30:00',120.0),
                        
                        ('VA1021','O06','D11','Ida',42,'2020-08-02','07:30:00','09:30:00',120.0),
                        ('VA1022','O06','D10','Ida',42,'2020-08-03','08:00:00','09:30:00',120.0),
                        ('VA1023','O06','D09','Ida',42,'2020-08-04','05:20:00','07:30:00',120.0),
                        ('VA1024','O06','D08','Ida',42,'2020-08-02','11:45:00','14:00:00',120.0),
                        ('VA1025','O06','D07','Ida',42,'2020-08-05','14:00:00','15:30:00',120.0),
                        ('VA1026','O06','D05','Ida',42,'2020-08-01','09:00:00','10:30:00',120.0),
                        ('VA1027','O06','D04','Ida',42,'2020-08-04','18:35:00','20:05:00',120.0),
                        ('VA1028','O06','D03','Ida',42,'2020-08-02','20:00:00','21:40:00',120.0),
                        ('VA1029','O06','D02','Ida',42,'2020-08-06','22:30:00','23:00:00',120.0),
                        ('VA1030','O06','D01','Ida',42,'2020-08-07','15:00:00','16:30:00',120.0),
                        
                        ('VA1041','O06','D04','Ida',42,'2020-08-01','15:30:00','17:00:00',120.0),
                        ('VA1042','O06','D04','Ida',42,'2020-08-01','18:00:00','19:30:00',120.0),
                        ('VA1043','O06','D04','Ida',42,'2020-08-03','12:00:00','13:30:00',120.0),
                        ('VA1044','O06','D04','Ida',42,'2020-08-04','07:30:00','09:00:00',120.0),
                        ('VA1045','O06','D04','Ida',42,'2020-08-05','22:00:00','23:30:00',120.0),
                        ('VA1046','O06','D04','Ida',42,'2020-08-06','06:00:00','07:30:00',120.0),
                        ('VA1047','O06','D04','Ida',42,'2020-08-06','07:00:00','08:30:00',120.0),
                        ('VA1048','O06','D04','Ida',42,'2020-08-07','05:45:00','07:15:00',120.0),
                        ('VA1049','O06','D04','Ida',42,'2020-08-07','08:30:00','10:00:00',120.0),
                        ('VA1050','O06','D04','Ida',42,'2020-08-08','07:45:00','09:15:00',120.0),
                        
                        ('VA1061','O06','D01','Ida',42,'2020-08-02','13:00:00','15:30:00',120.0),
                        ('VA1062','O01','D06','Ida',42,'2020-08-03','07:15:00','09:45:00',120.0),
                        ('VA1063','O01','D06','Ida',42,'2020-08-03','05:00:00','07:30:00',120.0),
                        ('VA1064','O06','D03','Ida',42,'2020-08-04','14:30:00','15:00:00',120.0),
                        ('VA1065','O03','D06','Ida',42,'2020-08-02','16:45:00','19:15:00',120.0),
                        ('VA1066','O03','D06','Ida',42,'2020-08-02','22:15:00','23:30:00',120.0),
                        ('VA1067','O01','D02','Ida',42,'2020-08-03','07:00:00','08:30:00',120.0),
                        ('VA1068','O02','D01','Ida',42,'2020-08-05','06:05:00','07:45:00',120.0),
                        ('VA1069','O03','D04','Ida',42,'2020-08-05','10:20:00','11:50:00',120.0),
                        ('VA1070','O04','D03','Ida',42,'2020-08-06','11:00:00','13:00:00',120.0),
                        
                        ('VA1081','O05','D06','Ida',42,'2020-08-07','14:05:00','15:45:00',120.0),
                        ('VA1082','O06','D05','Ida',42,'2020-08-08','20:30:00','22:30:00',120.0),
                        ('VA1083','O07','D08','Ida',42,'2020-08-05','07:45:00','09:15:00',120.0),
                        ('VA1084','O08','D07','Ida',42,'2020-08-06','15:05:00','16:35:00',120.0),
                        ('VA1085','O09','D10','Ida',42,'2020-08-09','20:15:00','21:00:00',120.0),
                        ('VA1086','O10','D09','Ida',42,'2020-08-10','22:00:00','23:30:00',120.0),
                        ('VA1087','O11','D01','Ida',42,'2020-08-05','10:15:00','11:30:00',120.0),
                        ('VA1088','O01','D02','Ida',42,'2020-08-06','05:20:00','06:50:00',120.0),
                        ('VA1089','O02','D05','Ida',42,'2020-08-04','09:00:00','11:00:00',120.0),
                        ('VA1090','O05','D01','Ida',42,'2020-08-02','11:30:00','13:00:00',120.0);
/*VUELOS VUELTA*/
INSERT INTO VUELO VALUES('VA1011','O10','D01','Vuelta',42,'2020-08-08','12:00:00','13:30:00',120.0),
						('VA1012','O09','D02','Vuelta',42,'2020-08-15','21:00:00','22:30:00',120.0),
                        ('VA1013','O08','D03','Vuelta',42,'2020-08-08','18:00:00','19:15:00',120.0),
                        ('VA1014','O07','D04','Vuelta',42,'2020-08-15','12:00:00','13:30:00',120.0),
                        ('VA1015','O06','D05','Vuelta',42,'2020-08-08','12:00:00','13:30:00',120.0),
                        ('VA1016','O05','D06','Vuelta',42,'2020-08-15','12:00:00','13:30:00',120.0),
                        ('VA1017','O04','D07','Vuelta',42,'2020-08-08','12:00:00','13:30:00',120.0),
                        ('VA1018','O03','D08','Vuelta',42,'2020-08-15','12:00:00','13:30:00',120.0),
                        ('VA1019','O02','D09','Vuelta',42,'2020-08-08','12:00:00','13:30:00',120.0),
                        ('VA1020','O01','D10','Vuelta',42,'2020-08-15','12:00:00','13:30:00',120.0),
                        
                        ('VA1031','O11','D06','Vuelta',42,'2020-08-03','10:00:00','12:30:00',120.0),
                        ('VA1032','O10','D06','Vuelta',42,'2020-08-10','09:20:00','11:00:00',120.0),
                        ('VA1033','O09','D06','Vuelta',42,'2020-08-06','06:30:00','08:10:00',120.0),
                        ('VA1034','O08','D06','Vuelta',42,'2020-08-03','12:00:00','13:30:00',120.0),
                        ('VA1035','O07','D06','Vuelta',42,'2020-08-15','13:00:00','14:45:00',120.0),
                        ('VA1036','O05','D06','Vuelta',42,'2020-08-21','10:00:00','11:45:00',120.0),
                        ('VA1037','O04','D06','Vuelta',42,'2020-08-07','11:00:00','12:30:00',120.0),
                        ('VA1038','O03','D06','Vuelta',42,'2020-08-05','20:00:00','21:30:00',120.0),
                        ('VA1039','O02','D06','Vuelta',42,'2020-08-10','22:00:00','23:30:00',120.0),
                        ('VA1040','O01','D06','Vuelta',42,'2020-08-12','05:20:00','07:00:00',120.0),
                        
                        ('VA1051','O04','D06','Vuelta',42,'2020-08-04','10:20:00','11:50:00',120.0),
                        ('VA1052','O04','D06','Vuelta',42,'2020-08-08','12:45:00','14:15:00',120.0),
                        ('VA1053','O04','D06','Vuelta',42,'2020-08-07','07:15:00','08:45:00',120.0),
                        ('VA1054','O04','D06','Vuelta',42,'2020-08-08','12:00:00','13:30:00',120.0),
                        ('VA1055','O04','D06','Vuelta',42,'2020-08-06','13:15:00','14:45:00',120.0),
                        ('VA1056','O04','D06','Vuelta',42,'2020-08-08','20:45:00','22:15:00',120.0),
                        ('VA1057','O04','D06','Vuelta',42,'2020-08-13','06:05:00','07:35:00',120.0),
                        ('VA1058','O04','D06','Vuelta',42,'2020-08-14','21:00:00','22:30:00',120.0),
                        ('VA1059','O04','D06','Vuelta',42,'2020-08-14','19:45:00','21:15:00',120.0),
                        ('VA1060','O04','D06','Vuelta',42,'2020-08-13','10:20:00','11:50:00',120.0),
                        
                        ('VA1071','O01','D06','Vuelta',42,'2020-08-04','18:00:00','19:30:00',120.0),
                        ('VA1072','O06','D01','Vuelta',42,'2020-08-07','19:15:00','20:45:00',120.0),
                        ('VA1073','O06','D01','Vuelta',42,'2020-08-13','06:00:00','07:45:00',120.0),
                        ('VA1074','O03','D06','Vuelta',42,'2020-08-08','08:30:00','22:05:00',120.0),
                        ('VA1075','O06','D03','Vuelta',42,'2020-08-07','09:20:00','11:15:00',120.0),
                        ('VA1076','O06','D03','Vuelta',42,'2020-08-12','12:15:00','14:30:00',120.0),
                        ('VA1077','O02','D01','Vuelta',42,'2020-08-09','13:45:00','15:05:00',120.0),
                        ('VA1078','O01','D02','Vuelta',42,'2020-08-11','08:30:00','10:30:00',120.0),
                        ('VA1079','O04','D03','Vuelta',42,'2020-08-16','09:00:00','11:30:00',120.0),
                        ('VA1080','O03','D04','Vuelta',42,'2020-08-07','07:05:00','09:35:00',120.0),
                        
                        ('VA1091','O06','D05','Vuelta',42,'2020-08-08','17:00:00','18:30:00',120.0),
                        ('VA1092','O05','D06','Vuelta',42,'2020-08-11','10:15:00','12:00:00',120.0),
                        ('VA1093','O08','D07','Vuelta',42,'2020-08-09','09:00:00','10:30:00',120.0),
                        ('VA1094','O07','D08','Vuelta',42,'2020-08-08','15:30:00','16:45:00',120.0),
                        ('VA1095','O10','D09','Vuelta',42,'2020-08-13','13:00:00','14:30:00',120.0),
                        ('VA1096','O09','D10','Vuelta',42,'2020-08-12','10:20:00','11:40:00',120.0),
                        ('VA1097','O01','D11','Vuelta',42,'2020-08-07','08:15:00','09:45:00',120.0),
                        ('VA1098','O02','D01','Vuelta',42,'2020-08-07','07:20:00','09:05:00',120.0),
                        ('VA1099','O05','D02','Vuelta',42,'2020-08-06','22:00:00','23:45:00',120.0),
                        ('VA1100','O01','D05','Vuelta',42,'2020-08-04','20:05:00','21:30:00',120.0);
                                    
                  
INSERT INTO PROMOCIONES VALUES('cbllmykuyv','10% de descuento para destinos a Iquitos - Arequipa',0.10,'2020-07-11','2020-10-11'),
							  ('corctcoufx','15% de descuento para destinos a Tumbes - Piura',0.15,'2020-07-11','2020-08-17'),
							  ('gfdenbblto','20% de descuento para destinos a Cajamarca - Lima',0.20,'2020-07-11','2020-09-11'),
							  ('mbdtlqasar','25% de descuento para destinos a Ayacucho - Cuzco',0.25,'2020-07-11','2020-08-09'),
							  ('vfvdcvdfvr','28% de descuento para destinos a Puerto Maldonado',0.35,'2020-07-11','2020-11-19'),
							  ('bfngnghnbg','30% de descuento para destinos a Tarapoto - Trujillo',0.30,'2020-07-11','2020-09-29');                      
/*-----------------------------------------------------------------------------------------------------------------------------------------*/
/*--------------------------------------------------PROCEDIMIENTOS ALMACENADOS-------------------------------------------------------------*/
/*-----------------------------------------------------------------------------------------------------------------------------------------*/

/*----------BUSCAR CLIENTE------------*/
-- INSERTAR CLIENTE
DELIMITER //
CREATE PROCEDURE AgregarCliente(codClie char(6), nomTipDocClie varchar(20), numDocClie varchar(8), 
					nomClie varchar(30), apeClie varchar(30) , fecNacClie date, sexoClie char(1),
					telfClie char(15), correoClie varchar(50), codPais char(4), nomUsu varchar(20),contUsu varchar(20))
BEGIN
	INSERT INTO CLIENTE VALUES(codClie, nomTipDocClie, numDocClie, nomClie, apeClie, fecNacClie, sexoClie,
					telfClie, correoClie, codPais,nomUsu, contUsu);
END //
DELIMITER ;
-- BUSCAR CLIENTE
DELIMITER //
CREATE PROCEDURE BuscarClienteXusu(usu VARCHAR(20))
BEGIN
	SELECT C.*, P.descPais FROM CLIENTE C INNER JOIN
    PAIS P ON C.codPais=P.codPais
    WHERE nomUsu=usu;
END //
DELIMITER ;

-- EDITAR INFORMACIÓN DEL CLIENTE
DELIMITER //
 CREATE PROCEDURE EditarInfo(nroD VARCHAR(8),telf CHAR(15), correo VARCHAR(50), usu VARCHAR(20), cont VARCHAR(20))
 begin
	UPDATE CLIENTE
    SET telfClie=telf, correoClie=correo, nomUsu=usu, contUsu=cont
    where numDocClie=nroD;
 END//
DELIMITER  ;

DELIMITER //
CREATE PROCEDURE UltimoCliente()
BEGIN
	SELECT codClie FROM CLIENTE
    ORDER BY codClie DESC LIMIT 1;
END //
DELIMITER ;

-- CAMBIAR CONTRASEÑA
DELIMITER //
CREATE PROCEDURE ContraseñaCliente(nom  VARCHAR(20), cont VARCHAR(20))
BEGIN
	UPDATE CLIENTE
    SET contUsu= cont
    WHERE nomUsu=nom;
END //
DELIMITER ;
-- BUSCAR CLIENTE
DELIMITER //
CREATE PROCEDURE BuscarCliente(codigo CHAR(6))
BEGIN
	SELECT C.*, P.descPais FROM CLIENTE C INNER JOIN
    PAIS P ON C.codPais=P.codPais
    WHERE codClie=codigo;
END //
DELIMITER ;
/*---------BUSCAR ACOMPAÑANTE------------*/
DELIMITER //
CREATE PROCEDURE BuscarAcompañante(CodACcomp VARCHAR(8))
BEGIN
	SELECT*FROM ACOMPAÑANTE 
    WHERE numDocAcomp=CodACcomp;
END //
DELIMITER;


/*-----------------------------------VUELO-----------------------------------------------*/
-- MODIFICAR VUELO
DELIMITER //
CREATE PROCEDURE ModificarVuelo(cod char(6),fechSal date,horaSal time,horaLleg time,precio decimal(6,2))
BEGIN
	UPDATE VUELO SET fechSalVue=fechSal, horaSalVue=horaSal, horaLlegVue=horaLleg,precioVuel=precio
    WHERE codVue=cod;
END //
DELIMITER ;
-- BUSCAR VUELO
DELIMITER //
CREATE PROCEDURE BuscarVuelo(codVuelo CHAR(6))
BEGIN
	SELECT codVue,tipVuelo,cantiAsieVue,fechSalVue,horaSalVue,horaLlegVue,precioVuel,
			 O.codOrig, A.descAero,C.descCiu, D.codDest,AE.descAero,CI.descCiu	
						FROM VUELO V INNER JOIN ORIGEN O ON V.codOrig=O.codOrig
						INNER JOIN DESTINO D ON V.codDest=D.codDest
						INNER JOIN AEROPUERTO A ON A.codAero=O.codAero
                        INNER JOIN AEROPUERTO AE ON  AE.codAero=D.codAero
                        INNER JOIN CIUDAD C ON A.codCiu=C.codCiu 
                        INNER JOIN CIUDAD CI ON AE.codCiu=CI.codCiu 
						WHERE codVue=codVuelo;
END //
DELIMITER ;

-- LISTAR ORIGEN
DELIMITER //
CREATE PROCEDURE ListarOrigen()
BEGIN
	SELECT codOrig,descAero,descCiu FROM ORIGEN O INNER JOIN aeropuerto A
    ON O.codAero=A.codAero 
    INNER JOIN ciudad C
    ON C.codCiu=A.codCiu;
END //
DELIMITER ;

-- LISTAR DESTINO
DELIMITER //
CREATE PROCEDURE ListarDestino()
BEGIN
	SELECT codDest,descAero,descCiu FROM destino D INNER JOIN aeropuerto A
    ON D.codAero=A.codAero 
    INNER JOIN ciudad C
    ON C.codCiu=A.codCiu;
END //
DELIMITER ;

-- LISTAR VUELO (IDA O VUELTA)
DELIMITER //
CREATE PROCEDURE ListarVuelo(vuelo VARCHAR(12))
BEGIN
	SELECT codVue,tipVuelo,cantiAsieVue,fechSalVue,horaSalVue,horaLlegVue,precioVuel,
			 O.codOrig, AE.descAero,C.descCiu, D.codDest,A.descAero,CI.descCiu	
						FROM VUELO V INNER JOIN ORIGEN O ON V.codOrig=O.codOrig
						INNER JOIN DESTINO D ON V.codDest=D.codDest
						INNER JOIN AEROPUERTO A ON O.codAero=A.codAero
						INNER JOIN AEROPUERTO AE ON  AE.codAero=D.codAero
						INNER JOIN CIUDAD C ON A.codCiu=C.codCiu 
                        INNER JOIN CIUDAD CI ON AE.codCiu=CI.codCiu 
                        WHERE tipVuelo=vuelo;
END //
DELIMITER ;

-- BUSCA VUELO DE IDA 
DELIMITER //
CREATE PROCEDURE BuscarTipVuelo(Origen CHAR(3), Destino CHAR(3),tipVue VARCHAR(12),canti INT,fecha DATE)
BEGIN
	SELECT codVue,tipVuelo,cantiAsieVue,fechSalVue,horaSalVue,horaLlegVue,precioVuel,
			 O.codOrig, AE.descAero,C.descCiu, D.codDest,A.descAero,CI.descCiu	
						FROM VUELO V INNER JOIN ORIGEN O ON V.codOrig=O.codOrig
						INNER JOIN DESTINO D ON V.codDest=D.codDest
						INNER JOIN AEROPUERTO A ON O.codAero=A.codAero
						INNER JOIN AEROPUERTO AE ON  AE.codAero=D.codAero
						INNER JOIN CIUDAD C ON A.codCiu=C.codCiu 
                        INNER JOIN CIUDAD CI ON AE.codCiu=CI.codCiu 
                        WHERE tipVuelo=tipVue AND O.codOrig=Origen AND D.codDest=Destino 
						AND canti<cantiAsieVue AND fechSalVue=fecha;
END //
DELIMITER ;

/*----------------FACTURA-----------------*/
-- INSERTAR ACOMPAÑANTE
DELIMITER //
CREATE PROCEDURE insertarAcompañante(numdoc varchar(8) , tipdoc varchar(20), nom varchar(50), ape varchar(50), fech date, sex char(1))
BEGIN 
	INSERT INTO acompañante VALUES (numdoc, tipdoc, nom,ape,fech,sex);
END //
DELIMITER ;

-- INSERTAR BOLETO
DELIMITER //
CREATE PROCEDURE insertarBoleto(codB char(7), codC char(6), codV char(6), codVV char(6),asient int)
BEGIN
	INSERT INTO BOLETO VALUES (codB,codC,codV,codVV ,asient);
    IF(codVV is null)
    THEN
    BEGIN
		UPDATE VUELO 
		SET cantiAsieVue=cantiAsieVue-asient
		WHERE codV=codVue;
    END;
    ELSE 
    BEGIN
		UPDATE VUELO 
		SET cantiAsieVue=cantiAsieVue-(asient/2)
		WHERE codV=codVue;
		UPDATE VUELO 
		SET cantiAsieVue=cantiAsieVue-(asient/2)
		WHERE codVV=codVue;
    END;
    END IF;
END //
DELIMITER ;

-- INSERTAR DETALLE BOLETA
DELIMITER //
CREATE PROCEDURE insertarDetalleBoleto(codB char(7), numdoc varchar(8))
BEGIN
	INSERT INTO detalle_boleto VALUES (codB,numdoc);
END //
DELIMITER ;

-- INSERTAR FACTURA
DELIMITER //
CREATE PROCEDURE insertarFactura(num char(5) , codB char(7), fech date, numTarj varchar(16), importe DECIMAL(6,2), igv DECIMAL(6,2) , total DECIMAL(6,2),promo CHAR(10))
BEGIN
	INSERT INTO factura VALUES (num, codB,fech, numTarj, importe, igv,total,promo);
END//
DELIMITER ;

-- RECUPERAR CODIGO BOLETA
DELIMITER //
CREATE PROCEDURE buscarUltimaBoleto()
BEGIN
	SELECT codBoleto FROM BOLETO 
    ORDER BY codBoleto DESC LIMIT 1;
END //
DELIMITER ;

-- RECUPERAR NUM FAC
DELIMITER //
CREATE PROCEDURE buscarUltimaFactura()
BEGIN
	SELECT numFac FROM factura 
    ORDER BY codBoleto DESC LIMIT 1;
END //
DELIMITER ;

-- INSERTAR ASIENTO
DELIMITER //
CREATE PROCEDURE insertarAsiento(codB char(7), codV char(6) ,nomA varchar(4))
BEGIN 
	INSERT INTO asiento VALUES (codB, codV, nomA);
END //
DELIMITER ;

-- LISTAR ASIENTOS OCUPADOS
DELIMITER //
CREATE PROCEDURE listarAsiento(codV char(6))
BEGIN
	SELECT * FROM asiento 
    WHERE codVue = codV ;
END //
DELIMITER ;

-- LISTAR PROMOCIONES
delimiter //
CREATE PROCEDURE ListarPromociones()
begin
	select * from PROMOCIONES;
end //
delimiter ;

-- BUSCAR PROMOCIONES
delimiter //
CREATE PROCEDURE BuscarPromociones(COD char(10))
begin
	select * from PROMOCIONES where cod_Promo=COD;
end //
delimiter  ;

-- INSERTAR PROMOCIONES 
delimiter //
CREATE PROCEDURE InsertarPromociones(COD CHAR(10),DESCRIP VARCHAR(70),DESCUENTO DECIMAL(3,2),FECHAINICIO DATE,FECHAFINAL DATE)
begin
	insert into PROMOCIONES values (COD,DESCRIP,DESCUENTO,FECHAINICIO,FECHAFINAL);
 end //
 delimiter  ;
 
 -- MODIFICAR PROMOCIONES
 delimiter //
 CREATE PROCEDURE ModificarPromociones(COD CHAR(10),DESCRIP VARCHAR(70),DESCUENTO DECIMAL(3,2),FECHAINICIO DATE , FECHAFINAL DATE)
 begin
	UPDATE PROMOCIONES SET cod_Promo=COD,descrip_Promo=DESCRIP,porcentaje_desc=DESCUENTO,fechaIni_Promo=FECHAINICIO,fechaFin_Promo=FECHAFINAL
    where cod_Promo=COD;
  end //
  delimiter  ;
 
 -- ELIMINAR PROMOCIONES
DELIMITER //
 CREATE PROCEDURE EliminarPromociones(COD CHAR(10))
 begin
	DELETE FROM PROMOCIONES
    where cod_Promo=COD;
 END//
DELIMITER  ;
-- LISTAR BOLETOS
delimiter //
CREATE PROCEDURE ListarBoletos()
begin
	select * from BOLETO;
end //
delimiter ;
/*-----------------------------REGISTRO VUELO----------------------*/
-- INSERTAR VUELO

DELIMITER //
CREATE PROCEDURE InsertarVuelo(CODIGOVUELO CHAR(6),CODIGOORIGEN CHAR(3),CODIGODESTINO CHAR(3),TIPO VARCHAR(12),
				CANTIDADASIENTO INT,FECHA DATE,HORASALIDA TIME,HORALLEGADA TIME, PRECIO DECIMAL(6,2))
BEGIN
	INSERT INTO VUELO VALUES (CODIGOVUELO,CODIGOORIGEN,CODIGODESTINO,TIPO,
							CANTIDADASIENTO,FECHA,HORASALIDA,HORALLEGADA,PRECIO);
END//
DELIMITER  ;
-- RECUPERAR ULTIMO CODIGO
DELIMITER //
CREATE PROCEDURE BuscarUltimoVuelo()
BEGIN
	SELECT codVue From VUELO
    ORDER BY codVue DESC LIMIT 1;
END//
DELIMITER  ;

/*--------------------------------------------------REGISTROS-------------------------------------------------------------*/
CALL insertarBoleto("BOL1001", "C10001", "VA1001", null, 1);
CALL insertarFactura("F1001", "BOL1001", "2020-07-02", "5647920004312640", 93, 20 , 113,null);
CALL insertarAsiento("BOL1001","VA1001", "a1");

CALL insertarBoleto("BOL1002", "C10002", "VA1001", null, 1);
CALL insertarFactura("F1002", "BOL1002", "2020-07-03", "4652005012399007", 93, 20 , 113,null);
CALL insertarAsiento("BOL1002","VA1001", "a4");

CALL insertarBoleto("BOL1003", "C10003", "VA1001", null, 1);
CALL insertarFactura("F1003", "BOL1003", "2020-07-04", "4200581269364201", 93, 20 , 113,null);
CALL insertarAsiento("BOL1003","VA1001", "a5");

CALL insertarBoleto("BOL1004", "C10004", "VA1001", null, 1);
CALL insertarFactura("F1004", "BOL1004", "2020-07-04", "5628930074771244", 93, 20 , 113,null);
CALL insertarAsiento("BOL1004","VA1001", "b3");

CALL insertarBoleto("BOL1005", "C10005", "VA1001", null, 1);
CALL insertarFactura("F1005", "BOL1005", "2020-07-11", "5200461389664221", 93, 20 , 113,null);
CALL insertarAsiento("BOL1005","VA1001", "d7");

CALL insertarBoleto("BOL1006", "C10001", "VA1002", null, 1);
CALL insertarFactura("F1006", "BOL1006", "2020-07-04", "4450231166007722", 93, 20 , 113,null);
CALL insertarAsiento("BOL1006","VA1002", "e1");

CALL insertarBoleto("BOL1007", "C10002", "VA1002", null, 1);
CALL insertarFactura("F1007", "BOL1007", "2020-07-10", "5422369641112020", 93, 20 , 113,null);
CALL insertarAsiento("BOL1007","VA1002", "e2");

CALL insertarBoleto("BOL1008", "C10003", "VA1002", null, 1);
CALL insertarFactura("F1008", "BOL1008", "2020-07-06", "4200580009364201", 93, 20 , 113,null);
CALL insertarAsiento("BOL1008","VA1002", "a4");

CALL insertarBoleto("BOL1009", "C10004", "VA1002", null, 1);
CALL insertarFactura("F1009", "BOL1009", "2020-07-01", "4255830074771244", 93, 20 , 113,null);
CALL insertarAsiento("BOL1009","VA1002", "b1");

CALL insertarBoleto("BOL1010", "C10005", "VA1001", null, 1);
CALL insertarFactura("F1010", "BOL1010", "2020-07-02", "4652061389664221", 93, 20 , 113,null);
CALL insertarAsiento("BOL1010","VA1002", "c6");

CALL insertarBoleto("BOL1011", "C10001", "VA1003", null, 1);
CALL insertarFactura("F1011", "BOL1011", "2020-07-02", "5647920004377740", 93, 20 , 113,null);
CALL insertarAsiento("BOL1011","VA1003", "b1");

CALL insertarBoleto("BOL1012", "C10002", "VA1003", null, 1);
CALL insertarFactura("F1012", "BOL1012", "2020-07-03", "4652009912399007", 93, 20 , 113,null);
CALL insertarAsiento("BOL1012","VA1003", "a5");

CALL insertarBoleto("BOL1013", "C10003", "VA1003", null, 1);
CALL insertarFactura("F1013", "BOL1013", "2020-07-08", "4200581278964201", 93, 20 , 113,null);
CALL insertarAsiento("BOL1013","VA1003", "a2");

CALL insertarBoleto("BOL1014", "C10004", "VA1001", null, 1);
CALL insertarFactura("F1014", "BOL1014", "2020-07-13", "4808930074771244", 93, 20 , 113,null);
CALL insertarAsiento("BOL1014","VA1003", "d7");

CALL insertarBoleto("BOL1015", "C10005", "VA1003", null, 1);
CALL insertarFactura("F1015", "BOL1015", "2020-07-12", "5200001389664221", 93, 20 , 113,null);
CALL insertarAsiento("BOL1015","VA1003", "e1");


/*SELECT*FROM BOLETO;
SELECT*FROM PROMOCIONES;
SELECT*FROM VUELO;
SELECT*FROM CLIENTE;
SELECT*FROM DETALLE_BOLETO;
SELECT*FROM FACTURA;
SELECT * FROM ASIENTO;
SELECT*FROM CIUDAD;
SELECT*FROM ACOMPAÑANTE;*/
-- :)
