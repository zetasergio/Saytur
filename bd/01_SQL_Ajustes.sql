ALTER TABLE public.rol
  ADD COLUMN rol_editable boolean NOT NULL DEFAULT true;
COMMENT ON COLUMN public.rol.rol_editable IS 'Campo que indica si el registro es editable por el usuario';


-- 2016-09-23 - William Diaz Pabón
ALTER TABLE public.rol
   ALTER COLUMN rol_nombre SET NOT NULL;
ALTER TABLE public.rol
   ALTER COLUMN rol_estado SET DEFAULT 1;
ALTER TABLE public.rol
   ALTER COLUMN rol_estado SET NOT NULL;
COMMENT ON COLUMN public.rol.rol_nombre IS 'Nombre del rol';
COMMENT ON COLUMN public.rol.rol_estado IS 'Indica el estado actual del registro, de acuerdo a los siguientes valores:
0 => Inactivo
1 => Activo
2 => Suspendido';

-- 2016-09-24 - William Diaz Pabón
ALTER TABLE public.rol
  ADD COLUMN rol_version integer NOT NULL DEFAULT 1;
COMMENT ON COLUMN public.rol.rol_version IS 'Campo para controlar la versión del registro desde el sistema java';
