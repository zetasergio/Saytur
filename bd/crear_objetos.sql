---- CREANDO TABLA USUARIO -----------

CREATE TABLE public.usuario
(
  usu_id serial NOT NULL, -- Llave primaria de la tabla
  usu_usuario character varying(50) NOT NULL, -- Valor del usuario en el sistema, que puede ser el correo electrónico
  usu_clave character varying(50) NOT NULL, -- Clave del usuario en el sistema
  usu_estado integer NOT NULL DEFAULT 1, -- Indica el estado actual del registro, de acuerdo a los siguientes valores:...
  CONSTRAINT usuario_pk PRIMARY KEY (usu_id),
  CONSTRAINT usuario_usuario_uk UNIQUE (usu_usuario)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.usuario
  OWNER TO postgres;
COMMENT ON COLUMN public.usuario.usu_id IS 'Llave primaria de la tabla';
COMMENT ON COLUMN public.usuario.usu_usuario IS 'Valor del usuario en el sistema, que puede ser el correo electrónico';
COMMENT ON COLUMN public.usuario.usu_clave IS 'Clave del usuario en el sistema';
COMMENT ON COLUMN public.usuario.usu_estado IS 'Indica el estado actual del registro, de acuerdo a los siguientes valores:
0 => Inactivo
1 => Activo
2 => Suspendido
';
------------------------------------

---- CREANDO TABLA ROL -----------

CREATE TABLE public.rol
(
  rol_id serial NOT NULL, -- Llave primaria de la tabla
  rol_nombre character varying(50), -- Nombre del rol
  rol_estado integer, -- Indica el estado actual del registro, de acuerdo a los siguientes valores:...
  CONSTRAINT rol_pk PRIMARY KEY (rol_id),
  CONSTRAINT rol_nombre_uk UNIQUE (rol_nombre)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.rol
  OWNER TO postgres;
COMMENT ON COLUMN public.rol.rol_id IS 'Llave primaria de la tabla';
COMMENT ON COLUMN public.rol.rol_nombre IS 'Nombre del rol';
COMMENT ON COLUMN public.rol.rol_estado IS 'Indica el estado actual del registro, de acuerdo a los siguientes valores:
0 => Inactivo
1 => Activo
2 => Suspendido';

---- CREANDO TABLA FUNCIONALIDAD -----------

CREATE TABLE public.funcionalidad
(
  fun_id serial NOT NULL, -- Llave primaria de la tabla
  fun_nombre character varying(50) NOT NULL, -- Nombre de la funcionalidad
  fun_estado integer NOT NULL DEFAULT 1, -- Indica el estado actual del registro, de acuerdo a los siguientes valores:...
  CONSTRAINT funcionalidad_pk PRIMARY KEY (fun_id),
  CONSTRAINT funcionalidad_nombre_uk UNIQUE (fun_nombre)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.funcionalidad
  OWNER TO postgres;
COMMENT ON COLUMN public.funcionalidad.fun_id IS 'Llave primaria de la tabla';
COMMENT ON COLUMN public.funcionalidad.fun_nombre IS 'Nombre de la funcionalidad ';
COMMENT ON COLUMN public.funcionalidad.fun_estado IS 'Indica el estado actual del registro, de acuerdo a los siguientes valores:
0 => Inactivo
1 => Activo
2 => Suspendido';

--------------------------------------------

---- CREANDO TABLA FUNCIONALIDAD_ROL -----------
CREATE TABLE public.funcionalidad_rol
(
  fur_id serial NOT NULL, -- Llave primaria de la tabla
  fur_funcionalidad integer NOT NULL, -- Llave foranea de la tabla funcionalidad
  fur_rol integer NOT NULL, -- Llave foranea de la tabla rol
  fur_estado integer NOT NULL DEFAULT 1, -- Indica el estado actual del registro, de acuerdo a los siguientes valores:...
  CONSTRAINT funcionalidad_rol_pk PRIMARY KEY (fur_id),
  CONSTRAINT funcionalidad_rol_funcionalidad_fk FOREIGN KEY (fur_funcionalidad) REFERENCES funcionalidad (fun_id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT funcionalidad_rol_rol_fk FOREIGN KEY (fur_rol) REFERENCES rol (rol_id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.funcionalidad_rol
  OWNER TO postgres;
COMMENT ON COLUMN public.funcionalidad_rol.fur_id IS 'Llave primaria de la tabla';
COMMENT ON COLUMN public.funcionalidad_rol.fur_funcionalidad IS 'Llave foranea de la tabla funcionalidad';
COMMENT ON COLUMN public.funcionalidad_rol.fur_rol IS 'Llave foranea de la tabla rol';
COMMENT ON COLUMN public.funcionalidad_rol.fur_estado IS 'Indica el estado actual del registro, de acuerdo a los siguientes valores:
0 => Inactivo
1 => Activo
2 => Suspendido';

---- CREANDO TABLA AUTORIZACIÓN -----------
CREATE TABLE public.autorizacion
(
  aut_id serial NOT NULL, -- Llave primaria de la tabla
  aut_funcionalidad_rol integer NOT NULL, -- Llave foranea de la tabla funcionalidad_rol
  aut_usuario integer NOT NULL, -- Llave foranea de la tabla usuario
  aut_estado integer NOT NULL DEFAULT 1, -- Indica el estado actual del registro, de acuerdo a los siguientes valores:...
  CONSTRAINT autorizacion_pk PRIMARY KEY (aut_id),
  CONSTRAINT autorizacion_funcionalidad_rol_fk FOREIGN KEY (aut_funcionalidad_rol) REFERENCES funcionalidad_rol (fur_id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT autorizacion_usuario_fk FOREIGN KEY (aut_usuario) REFERENCES usuario (usu_id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.autorizacion
  OWNER TO postgres;
COMMENT ON COLUMN public.autorizacion.aut_id IS 'Llave primaria de la tabla';
COMMENT ON COLUMN public.autorizacion.aut_funcionalidad_rol IS 'Llave foranea de la tabla funcionalidad_rol';
COMMENT ON COLUMN public.autorizacion.aut_usuario IS 'Llave foranea de la tabla usuario';
COMMENT ON COLUMN public.autorizacion.aut_estado IS 'Indica el estado actual del registro, de acuerdo a los siguientes valores:
0 => Inactivo
1 => Activo
2 => Suspendido';


------------------------------------
-- creando los registros iniciales para el usuario administrador
INSERT INTO public.usuario(usu_usuario, usu_clave, usu_estado) VALUES ('admin', 'admin', 1);

INSERT INTO public.rol(rol_nombre, rol_estado) VALUES ('administrador', 1);

INSERT INTO public.funcionalidad(fun_nombre, fun_estado) VALUES ('Gestionar Roles', 1);

INSERT INTO public.funcionalidad_rol(fur_funcionalidad, fur_rol, fur_estado) VALUES (1, 1, 1);

INSERT INTO public.autorizacion(aut_funcionalidad_rol, aut_usuario, aut_estado) VALUES (1, 1, 1);


----------------------------------
-- SQL para buscar al usuario
select usu_clave from usuario where usu_estado = 1 and usu_usuario = 'admin';

-- SQL para buscar todos los roles que tiene el usuario
SELECT rol_nombre, 'Roles' 
  FROM usuario 	JOIN autorizacion ON (aut_usuario = usu_id and aut_estado = 1)
		JOIN funcionalidad_rol ON (fur_id = aut_funcionalidad_rol and fur_estado = 1)
		JOIN rol ON (rol_id = fur_rol and rol_estado = 1)
 WHERE usu_usuario = 'admin';

