/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Luis
 * Created: 29 oct. 2021
 */

--Tabla provincia
CREATE TABLE PROVINCIA(

    NOMBRE VARCHAR(20) NOT NULL,
    PAIS VARCHAR(20),
    COMUNIDAD_AUTONOMA VARCHAR(20),

    CONSTRAINT NOMBRE_PK PRIMARY KEY(NOMBRE)
);
--Datos tabla provincia
INSERT INTO PROVINCIA (NOMBRE, PAIS, COMUNIDAD_AUTONOMA) VALUES ('ÁLAVA', 'ESPAÑA', 'PAÍS VASCO');
INSERT INTO PROVINCIA (NOMBRE, PAIS, COMUNIDAD_AUTONOMA) VALUES ('ALBACETE', 'ESPAÑA', 'CASTILLA-LA MANCHA');
INSERT INTO PROVINCIA (NOMBRE, PAIS, COMUNIDAD_AUTONOMA) VALUES ('ALICANTE', 'ESPAÑA', 'VALENCIA');
INSERT INTO PROVINCIA (NOMBRE, PAIS, COMUNIDAD_AUTONOMA) VALUES ('ALMERIA', 'ESPAÑA','ANDALUCÍA');
INSERT INTO PROVINCIA (NOMBRE, PAIS, COMUNIDAD_AUTONOMA) VALUES ('AVILA', 'ESPAÑA', 'CASTILLA Y LEÓN');
INSERT INTO PROVINCIA (NOMBRE, PAIS, COMUNIDAD_AUTONOMA) VALUES ('BADAJOZ', 'ESPAÑA', 'EXTREMADURA');
INSERT INTO PROVINCIA (NOMBRE, PAIS, COMUNIDAD_AUTONOMA) VALUES ('BALEARES', 'ESPAÑA', 'ISLAS BALEARES');
INSERT INTO PROVINCIA (NOMBRE, PAIS, COMUNIDAD_AUTONOMA) VALUES ('BARCELONA', 'ESPAÑA', 'CATALUÑA');
INSERT INTO PROVINCIA (NOMBRE, PAIS, COMUNIDAD_AUTONOMA) VALUES ('MÁLAGA', 'ESPAÑA', 'ANDALUCÍA');
INSERT INTO PROVINCIA (NOMBRE, PAIS, COMUNIDAD_AUTONOMA) VALUES ('SIN PROVINCIA', 'SIN PROVINCIA', 'SIN PROVINCIA');

--Tabla persona
CREATE TABLE PERSONA(

    DNI VARCHAR(9) NOT NULL,
    NOMBRE VARCHAR(20) NOT NULL,
    APELLIDOS VARCHAR(20),
    DIRECCION VARCHAR(50),
    LOCALIDAD VARCHAR(20),
    PROVINCIA VARCHAR(20),
    TELEFONO INTEGER,

    CONSTRAINT DNI_PK PRIMARY KEY (DNI),
    CONSTRAINT PROVINCIA_PERSONA_FK FOREIGN KEY(PROVINCIA) REFERENCES PROVINCIA (NOMBRE)
);

--Tabla reserva_Habitacion
CREATE TABLE RESERVA_HABITACION(
    
    COD_HABITACION INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,
    DNI_CLIENTE VARCHAR(9) NOT NULL,
    FECHA_LLEGADA DATE NOT NULL,
    FECHA_SALIDA DATE NOT NULL,
    REGIMEN VARCHAR(50) NOT NULL,
    NUMERO_HABITACIONES INTEGER NOT NULL,
    TIPO_HABITACION VARCHAR(50) NOT NULL,
    FUMADOR BOOLEAN,

    CONSTRAINT COD_HABITACION_PK PRIMARY KEY(COD_HABITACION),
    CONSTRAINT DNI_CLIENTE_HABITACION_FK FOREIGN KEY(DNI_CLIENTE) REFERENCES PERSONA (DNI),
    CONSTRAINT NOMBRE_FK_HABITACION FOREIGN KEY(TIPO_HABITACION) REFERENCES TIPO_HABITACION(NOMBRE)
);

--Tabla reserva_Salon
CREATE TABLE RESERVA_SALON(
    
    COD_SALON INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,
    DNI_CLIENTE VARCHAR(9) NOT NULL,
    TIPO_EVENTO VARCHAR(20) NOT NULL,
    NUM_PERSONAS INTEGER NOT NULL,
    TIPO_COCINA VARCHAR(50),
    FECHA_EVENTO DATE NOT NULL,
    HABITACIONES BOOLEAN,
    NUM_HABITACIONES INTEGER,
    NUM_DIAS INTEGER,
    CONSTRAINT COD_SALON_PK PRIMARY KEY(COD_SALON),
    CONSTRAINT DNI_CLIENTE_SALON_FK FOREIGN KEY(DNI_CLIENTE) REFERENCES PERSONA (DNI),
    CONSTRAINT NOMBRE_FK_SALON FOREIGN KEY(TIPO_COCINA) REFERENCES TIPO_COCINA(NOMBRE)
);

--Tabla tipo cocina
CREATE TABLE TIPO_COCINA(
 
    NOMBRE VARCHAR(50) NOT NULL,

    CONSTRAINT NOMBRE_PK_COCINA PRIMARY KEY(NOMBRE)
);

INSERT INTO TIPO_COCINA (NOMBRE) VALUES ('EUROPEA');
INSERT INTO TIPO_COCINA (NOMBRE) VALUES ('DE LOS 80');
INSERT INTO TIPO_COCINA (NOMBRE) VALUES ('TRADICIONAL');
INSERT INTO TIPO_COCINA (NOMBRE) VALUES ('BARROCA');
INSERT INTO TIPO_COCINA (NOMBRE) VALUES ('FRANCESA');
INSERT INTO TIPO_COCINA (NOMBRE) VALUES ('AMERICANA');
INSERT INTO TIPO_COCINA (NOMBRE) VALUES ('BUFÉ VEGETARIANO');
INSERT INTO TIPO_COCINA (NOMBRE) VALUES ('BUFÉ');
INSERT INTO TIPO_COCINA (NOMBRE) VALUES ('CARTA');
INSERT INTO TIPO_COCINA (NOMBRE) VALUES ('CHEF');
INSERT INTO TIPO_COCINA (NOMBRE) VALUES ('NO PRECISA');


--Tabla tipo habitaciones
CREATE TABLE TIPO_HABITACION(
    
    NOMBRE VARCHAR(50) NOT NULL,

    CONSTRAINT NOMBRE_PK_HABITACION PRIMARY KEY(NOMBRE)
);
INSERT INTO TIPO_HABITACION (NOMBRE) VALUES ('INDIVIDUAL');
INSERT INTO TIPO_HABITACION (NOMBRE) VALUES ('DOBLE');
INSERT INTO TIPO_HABITACION (NOMBRE) VALUES ('TRIPLE');
INSERT INTO TIPO_HABITACION (NOMBRE) VALUES ('DUPLÉX');
INSERT INTO TIPO_HABITACION (NOMBRE) VALUES ('ÁTICO');
