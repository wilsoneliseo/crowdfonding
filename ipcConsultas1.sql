--monto recaudado por iniciativa
select p.idpatrocinio, r.p_minimo, i.idiniciativa from patrocinio p, recompensa r, iniciativa i
where p.idrecompensa=r.idrecompensa and r.iniciativa=i.idiniciativa;

select sum(r.p_minimo) from patrocinio p, recompensa r, iniciativa i
where p.idrecompensa=r.idrecompensa and r.iniciativa=i.idiniciativa  and i.idiniciativa=13;

--backers por iniciativa
select count(p.idpatrocinio) from patrocinio p, recompensa r, iniciativa i
where p.idrecompensa=r.idrecompensa and r.iniciativa=i.idiniciativa and i.idiniciativa=13;



select * from iniciativa;
select * from patrocinio;
select * from recompensa;
select * from usuario;
select * from subcategoria;
select * from categoria;


-- por subcategoria
select p.idpatrocinio, r.p_minimo, r.iniciativa, i.idsubcategoria, s."nombreSub" from patrocinio p, recompensa r, iniciativa i, subcategoria s, categoria c
where p.idrecompensa=r.idrecompensa and r.iniciativa=i.idiniciativa and i.idsubcategoria=s.idsubcategoria and s.idcategoria=c.idcategoria;

--por categoria
select c."nombreCat", avg(r.p_minimo) from patrocinio p, recompensa r, iniciativa i, subcategoria s, categoria c
where p.idrecompensa=r.idrecompensa and r.iniciativa=i.idiniciativa and i.idsubcategoria=s.idsubcategoria and s.idcategoria=c.idcategoria
group by c."nombreCat";

--historial por clientes de recompensas
SELECT  u.idusuario, u.nombre, p.idpatrocinio, r.descripcion, i."nombreIni"
FROM patrocinio p, usuario u, recompensa r, iniciativa i
WHERE p.idusuario=u.idusuario AND p.idrecompensa=r.idrecompensa AND r.iniciativa=i.idiniciativa
ORDER BY u.idusuario;

--historial por iniciativas de recompensas
SELECT i.idiniciativa, i."nombreIni", p.idpatrocinio, r.descripcion, u.nombre
FROM patrocinio p, usuario u, recompensa r, iniciativa i
WHERE p.idusuario=u.idusuario AND p.idrecompensa=r.idrecompensa AND r.iniciativa=i.idiniciativa
ORDER BY i.idiniciativa;

select nombre, count(nombre) as total 
from tabla 
group by nombre 
order by 2 desc

--recompensas mas compradas (rmc)
select r.descripcion, count(p.idrecompensa) as total
from patrocinio p, recompensa r
where p.idrecompensa=r.idrecompensa
group by r.descripcion
order by 2 desc

--usuarios con mas inciativas (ucmi)
select u.nombre, count(i.idusuario) as total 
from iniciativa i, usuario u
where i.idusuario=u.idusuario
group by u.nombre 
order by 2 desc

--usuarios que mas dinero han donado (uqmdhd)
select u.nombre, sum(r.p_minimo) as total
from patrocinio p, recompensa r, usuario u
where p.idrecompensa=r.idrecompensa and p.idusuario=u.idusuario
group by u.nombre
order by 2 desc

--usuarios que mas recompensas han comprado (uqmrhc)
select u.nombre, count(p.idusuario) as total 
from patrocinio p, usuario u
where p.idusuario=u.idusuario
group by u.nombre 
order by 2 desc

select * from patrocinio