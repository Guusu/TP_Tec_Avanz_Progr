CREATE TABLE usuarios 
(
    id_usuario int,
    usuario varchar(20),
    password varchar(500),
    nombre varchar(50),
    email varchar(100),
    CONSTRAINT PK_USUARIO PRIMARY KEY (id_usuario) 
)

create table salas
(
    id_sala int,
    nombre varchar(50),
    capacidad int,
    tipo_sala varchar(20),
    CONSTRAINT PK_SALA PRIMARY KEY (id_sala) 
)

create table tipos_show
(
    id_tipo_show int,
    nombre varchar(50),
    descripcion varchar(100),
    CONSTRAINT PK_TIPO_SHOW PRIMARY KEY (id_tipo_show) 
)

CREATE TABLE artistas
(
    id_artista int,
    nombre varchar(50),
    nacionalidad varchar(50),
    genero varchar(50),
    CONSTRAINT PK_ARTISTA PRIMARY KEY (id_artista) 
)

CREATE TABLE espectaculos
(
    id_espectaculo int,
    fecha DateTime,
    duracion_minutos int,
    id_tipo_show int,
    precio_base_entrada DECIMAL(10,2),
    id_sala int,
    CONSTRAINT PK_ESPECTACULO PRIMARY KEY (id_espectaculo),
    CONSTRAINT FK_ESPECTACULO_TIPO_SHOW FOREIGN KEY (id_tipo_show) REFERENCES tipos_show(id_tipo_show),
    CONSTRAINT FK_ESPECTACULO_SALA FOREIGN KEY (id_sala) REFERENCES salas(id_sala)
)

create table espectaculo_artista 
(
    id_espectaculo int,
    id_artista int,
    constraint pk_espectaculo_artista primary key (id_espectaculo,id_artista),
    constraint fk_espectArtista_espectaculo foreign key (id_espectaculo) references espectaculos(id_espectaculo),
    constraint fk_espectARtista_artista foreign key (id_artista) references artistas(id_artista) 
)

create table entradas 
(
    id_entrada int,
    tipo varchar(50),
    precio decimal(10,2),
    id_usuario int,
    id_espectaculo int,
    constraint pk_entradas primary key (id_entrada),
    constraint fk_entrada_usuario foreign key (id_usuario) references usuarios (id_usuario),
    constraint fk_entrada_espectaculo foreign key (id_espectaculo) references espectaculos (id_espectaculo)
)