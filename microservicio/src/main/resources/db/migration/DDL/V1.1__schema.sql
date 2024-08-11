CREATE TABLE usuarios (
  id SERIAL PRIMARY KEY,
  username VARCHAR(50) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL,
  correo_electronico VARCHAR(100) NOT NULL UNIQUE,
  nombre_completo VARCHAR(100),
  fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  ultima_fecha_inicio_sesion TIMESTAMP,
  estado BOOLEAN DEFAULT TRUE,
  rol VARCHAR(50) DEFAULT 'usuario'
);