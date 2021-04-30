CREATE TABLE "cargos_nexos" (
	"id" SERIAL,
	"nombre" VARCHAR(20) NOT NULL,
	PRIMARY KEY ("id")
);
COMMENT ON TABLE "cargos_nexos" IS 'cargos del sistema'
;
COMMENT ON COLUMN "cargos_nexos"."id" IS '';
COMMENT ON COLUMN "cargos_nexos"."nombre" IS '';

INSERT INTO cargos_nexos (nombre) VALUES ('Asesor Ventas');
INSERT INTO cargos_nexos (nombre) VALUES ('Administrador');
INSERT INTO cargos_nexos (nombre) VALUES ('Soporte');

CREATE TABLE "usuarios_nexos" (
	"id" SERIAL,
	"nombre" VARCHAR(50) NOT NULL,
	"edad" NUMERIC NOT NULL DEFAULT 0,
	"cargo" SERIAL,
	"fecha_ingreso" DATE NOT NULL DEFAULT current_date,
	PRIMARY KEY ("id")
);

COMMENT ON TABLE "usuarios_nexos" IS 'usuarios del sistema';
COMMENT ON COLUMN "usuarios_nexos"."id" IS E'';
COMMENT ON COLUMN "usuarios_nexos"."nombre" IS E'';
COMMENT ON COLUMN "usuarios_nexos"."edad" IS E'';
COMMENT ON COLUMN "usuarios_nexos"."cargo" IS E'';
COMMENT ON COLUMN "usuarios_nexos"."fecha_ingreso" IS E'';

INSERT INTO usuarios_nexos (nombre, edad, cargo, fecha_ingreso) VALUES ('Andres Camargo', 25, 1, '02/25/2020');
INSERT INTO usuarios_nexos (nombre, edad, cargo, fecha_ingreso) VALUES ('Felipe Marulanda', 35, 2, '02/25/2020');
INSERT INTO usuarios_nexos (nombre, edad, cargo, fecha_ingreso) VALUES ('Horacio Angulo', 45, 3, '02/25/2020');

CREATE TABLE "mercancia_nexos" (
	"id" SERIAL NOT NULL,
	"nombre" VARCHAR(50) NOT NULL DEFAULT E'',
	"cantidad" INTEGER NOT NULL DEFAULT 0,
	"fecha_ingreso" DATE NOT NULL DEFAULT CURRENT_DATE CHECK(fecha_ingreso <= CURRENT_DATE),
	"usuario" SERIAL NOT NULL,
	PRIMARY KEY ("id"),
	UNIQUE ("nombre")
);

INSERT INTO mercancia_nexos(nombre,cantidad,fecha_ingreso,usuario) VALUES ('jabon', 5, '12/05/2020', 2);
INSERT INTO mercancia_nexos(nombre,cantidad,usuario) VALUES ('cereal', 5, 2);

--auditoria tabla mercancia:
CREATE TABLE "auditoria_mercancia_nexos" (
	"id" SERIAL NOT NULL,
	"producto" SERIAL NOT NULL,
	"usuario" SERIAL NOT NULL,
	"fecha_modificacion" DATE NOT NULL DEFAULT CURRENT_DATE,
	PRIMARY KEY ("id")
);