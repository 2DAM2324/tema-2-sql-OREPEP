-- Jose Antonio
-- .read /home/orepep/NetBeansProjects/Practica_sqlite_ana/create.sql
--DROP TABLE lista_reproduccion;
--DROP TABLE usuarios  ; 
--DROP TABLE canciones ;

CREATE TABLE usuarios (
    nombre_usuario      VARCHAR(17) NOT NULL,
    apellidos_usuario   VARCHAR(25) NOT NULL,
    login_usuario       VARCHAR(15) PRIMARY KEY,
    password_usuario    VARCHAR(15) NOT NULL
);

CREATE TABLE canciones (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    duracion VARCHAR(10) NOT NULL
);

CREATE TABLE lista_reproduccion (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario VARCHAR(15) ,
    id_cancion INT ,
    FOREIGN KEY (id_usuario) REFERENCES usuarios(login_usuario),
    FOREIGN KEY (id_cancion) REFERENCES canciones(id)
);

INSERT INTO canciones (nombre, duracion) VALUES ('Nombre de la Canción', '3:30');
INSERT INTO canciones (nombre, duracion) VALUES ('For the Night' , '3,10'); 
INSERT INTO canciones (nombre, duracion) VALUES ('Narcotics' , '2,53'); 
INSERT INTO canciones (nombre, duracion) VALUES
('Bohemian Rhapsody', '6:07'),
('Imagine', '3:02'),
('Like a Rolling Stone', '6:13'),
('Billie Jean', '4:54'),
('Smells Like Teen Spirit', '5:01'),
('Yesterday', '2:05'),
('Hotel California', '6:30'),
('Purple Haze', '3:17'),
('Hey Jude', '7:11'),
('Stairway to Heaven', '8:02'),
('Thriller', '5:57'),
('Wonderwall', '4:18'),
('Let It Be', '4:03'),
('Imagine', '3:02'),
('Purple Rain', '8:41'),
('Brown Eyed Girl', '3:05'),
('With or Without You', '4:56'),
('Every Breath You Take', '4:13'),
('Dancing Queen', '3:51');

INSERT INTO usuarios (nombre_usuario , apellidos_usuario , login_usuario , password_usuario) VALUES ('Juan', 'Cabello Rodríguez', 'jcabello', 'jcabello');
INSERT INTO usuarios (nombre_usuario , apellidos_usuario , login_usuario , password_usuario) VALUES ('Miguel Ángel', 'Díaz Díaz', 'madiaz', 'madiaz');
INSERT INTO usuarios (nombre_usuario , apellidos_usuario , login_usuario , password_usuario) VALUES ('Jesús Francisco', 'Dugarte Vargas', 'jfdugarte', 'jfdugarte');
INSERT INTO usuarios (nombre_usuario , apellidos_usuario , login_usuario , password_usuario) VALUES ('Pablo', 'Espigares Fernández', 'pespigares', 'pespigares');
INSERT INTO usuarios (nombre_usuario , apellidos_usuario , login_usuario , password_usuario) VALUES ('José Antonio', 'González Román', 'jagonzalez', 'jagonzalez');
INSERT INTO usuarios (nombre_usuario , apellidos_usuario , login_usuario , password_usuario) VALUES ('Álvaro', 'López Ruedas', 'alopez', 'alopez');
INSERT INTO usuarios (nombre_usuario , apellidos_usuario , login_usuario , password_usuario) VALUES ('Raúl', 'Martín Ruiz', 'rmartin', 'rmartin');
INSERT INTO usuarios (nombre_usuario , apellidos_usuario , login_usuario , password_usuario) VALUES ('Adrián', 'Martín Seco', 'amartin', 'amartin');
INSERT INTO usuarios (nombre_usuario , apellidos_usuario , login_usuario , password_usuario) VALUES ('Fernando', 'Martínez Vallecillos', 'fmartinez', 'fmartinez');
INSERT INTO usuarios (nombre_usuario , apellidos_usuario , login_usuario , password_usuario) VALUES ('Álvaro', 'Moreno Manzano', 'amoreno', 'amoreno');
INSERT INTO usuarios (nombre_usuario , apellidos_usuario , login_usuario , password_usuario) VALUES ('Adrián', 'Navarro Salmerón', 'anavarro', 'anavarro');
INSERT INTO usuarios (nombre_usuario , apellidos_usuario , login_usuario , password_usuario) VALUES ('Fernando', 'Ortega Castro', 'fortega', 'fortega');
INSERT INTO usuarios (nombre_usuario , apellidos_usuario , login_usuario , password_usuario) VALUES ('Belén', 'Robustillo Carmona', 'brobustillo', 'brobustillo');
INSERT INTO usuarios (nombre_usuario , apellidos_usuario , login_usuario , password_usuario) VALUES ('Carlos Javier', 'Rodríguez Muñoz', 'cjrodriguez', 'cjrodriguez');

INSERT INTO lista_reproduccion (id_usuario , id_cancion) VALUES ('jagonzalez' , '3');
INSERT INTO lista_reproduccion (id_usuario , id_cancion) VALUES ('jagonzalez' , '11');
INSERT INTO lista_reproduccion (id_usuario , id_cancion) VALUES ('jagonzalez' , '12');
INSERT INTO lista_reproduccion (id_usuario , id_cancion) VALUES ('amartin' , '3');
INSERT INTO lista_reproduccion (id_usuario , id_cancion) VALUES ('amartin' , '1');
