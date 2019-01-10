insert  into grupos(create_at,estado, hora_toma_fin, hora_toma_inicio, nombre, turnos_max) values (now(),"1","23:30","23:00","Empaques","10");
 insert  into grupos(create_at,estado, hora_toma_fin, hora_toma_inicio, nombre, turnos_max) values (now(),"1","23:30","23:00","Coordinador","12");
 insert  into grupos(create_at,estado, hora_toma_fin, hora_toma_inicio, nombre, turnos_max) values (now(),"1","23:30","23:00","Encargado","16");


insert into empaques (rut,nombre,apellido,correo,create_at,estado,id_grupo,foto,certificado,grupo_id) values ('193185495','Bastian','Gonzalez','Bastian.gonzalezz.v@gmail.com','2018-01-20','1','2',null,null,1);
insert into empaques (rut,nombre,apellido,correo,create_at,estado,id_grupo,foto,certificado,grupo_id) values ('12671781','Rut','Villarroel','rut.villarroel.v@gmail.com','2018-01-23','0','1',null,null,2);