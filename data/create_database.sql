CREATE TABLE "directores" (
	"id"	INTEGER,
	"nombre"	TEXT,
	"url_foto"	TEXT,
	"url_web"	TEXT,
	PRIMARY KEY("id" AUTOINCREMENT)
);
CREATE TABLE "generos" (
	"id"	INTEGER,
	"descripcion"	TEXT,
	PRIMARY KEY("id" AUTOINCREMENT)
);
CREATE TABLE "peliculas" (
	"id"	INTEGER,
	"titulo"	TEXT,
	"id_director"	INTEGER,
	"año"	INTEGER,
	"url_caratula"	TEXT,
	"id_genero"	INTEGER,
	"es_animacion"	INTEGER,
	PRIMARY KEY("id" AUTOINCREMENT),
	FOREIGN KEY("id_genero") REFERENCES "generos"("id"),
	FOREIGN KEY("id_director") REFERENCES "directores"("id")
);
CREATE TABLE "artistas" (
	"id"	INTEGER,
	"nombre"	TEXT,
	"url_foto"	TEXT,
	"url_web"	TEXT,
	PRIMARY KEY("id")
);
CREATE TABLE "repartos" (
	"id_pelicula"	INTEGER,
	"id_artista"	INTEGER,
	FOREIGN KEY("id_pelicula") REFERENCES "peliculas"("id"),
	FOREIGN KEY("id_artista") REFERENCES "artistas"("id"),
	PRIMARY KEY("id_pelicula","id_artista")
);
--Ingreso tres directores para tests
INSERT INTO directores (id, nombre,url_foto,url_web) VALUES (1, "Quentin Tarantino","www.qtimg.com","www.qtarantino.com");
INSERT INTO directores (id, nombre,url_foto,url_web) VALUES (2, "Mel Gibson","www.mgimg.com","www.mgibson.com");
INSERT INTO directores (id, nombre,url_foto,url_web) VALUES (3, "Ben Affleck","www.baimg.com","www.baffleck.com");
--Ingreso de los diferentes géneros
INSERT INTO generos (id, descripcion) VALUES (1, "Accion");
INSERT INTO generos (id, descripcion) VALUES (2, "Aventura");
INSERT INTO generos (id, descripcion) VALUES (3, "Comedia");
INSERT INTO generos (id, descripcion) VALUES (4, "Drama");
INSERT INTO generos (id, descripcion) VALUES (5, "Fantasia");
INSERT INTO generos (id, descripcion) VALUES (6, "Terror");
INSERT INTO generos (id, descripcion) VALUES (7, "Sci-Fi");
INSERT INTO generos (id, descripcion) VALUES (8, "Musical");
INSERT INTO generos (id, descripcion) VALUES (9, "Suspense");
INSERT INTO generos (id, descripcion) VALUES (10, "Western");
INSERT INTO generos (id, descripcion) VALUES (11, "Documental");
INSERT INTO generos (id, descripcion) VALUES (12, "Biografico");
INSERT INTO generos (id, descripcion) VALUES (13, "Romance");

--Ingreso 2 peliculas para el test
INSERT INTO peliculas (id, titulo, id_director, año, url_caratula, id_genero, es_animacion) VALUES (1, "Kill Bill", 1, 2005, "www.kb.com", 1, 1);
INSERT INTO peliculas (id, titulo, id_director, año, url_caratula, id_genero, es_animacion) VALUES (2, "El Gran Dictador", 2, 1940, "www.gd.com", 3, 2);
