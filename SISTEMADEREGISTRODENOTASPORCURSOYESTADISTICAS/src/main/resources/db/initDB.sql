CREATE TABLE apoderado(
	rut VARCHAR(12) PRIMARY KEY,
	nombre VARCHAR(30) NOT NULL,
	correo VARCHAR(50) NOT NULL
);

CREATE TABLE profesor(
	rut VARCHAR(12) PRIMARY KEY,
	nombre VARCHAR(30) NOT NULL,
	correo VARCHAR(50) NOT NULL
);

CREATE TABLE curso(
	id int IDENTITY PRIMARY KEY,
	nombre VARCHAR(20) NOT NULL,
	grado VARCHAR(20) NOT NULL,
	rut_profesor VARCHAR(12),
	FOREIGN KEY (rut_profesor) REFERENCES profesor(rut),
	UNIQUE(nombre, grado)
);

CREATE TABLE alumno(
	rut VARCHAR(12) PRIMARY KEY,
	nombre VARCHAR(30) NOT NULL,
	correo VARCHAR(50) NOT NULL,
	rut_apoderado VARCHAR(12), 
	id_curso INT,
	FOREIGN KEY (rut_apoderado) REFERENCES apoderado(rut),
	FOREIGN KEY (id_curso) REFERENCES curso(id)
);

CREATE TABLE asignatura(
	id int IDENTITY PRIMARY KEY,
	nombre VARCHAR(20) NOT NULL,
	id_curso int,
	rut_profesor VARCHAR(12), 
	FOREIGN KEY (rut_profesor) REFERENCES profesor(rut),
	FOREIGN KEY (id_curso) REFERENCES curso(id),
	UNIQUE(nombre, id_curso)
);