CREATE TABLE cliente (
  id SERIAL PRIMARY KEY,
  nombre VARCHAR(100) NOT NULL,
  tipo_cliente VARCHAR(20) NOT NULL
);

CREATE TABLE producto (
  id SERIAL PRIMARY KEY,
  nombre VARCHAR(100) NOT NULL,
  aplica_iva BOOLEAN NOT NULL,
  valor DECIMAL(10,2) NOT NULL
);

CREATE TABLE factura (
  id SERIAL PRIMARY KEY,
  id_cliente INT NOT NULL,
  valor_total DECIMAL(10,2) NOT NULL,
  estado VARCHAR(20) NOT NULL,
  CONSTRAINT cliente_fk FOREIGN KEY (id_cliente) REFERENCES cliente (id)
);

CREATE TABLE producto_facturar (
  id SERIAL PRIMARY KEY,
  id_factura INT NOT NULL,
  id_producto INT NOT NULL,
  cantidad INT NOT NULL,
  CONSTRAINT producto_fk FOREIGN KEY (id_producto) REFERENCES producto (id),
  CONSTRAINT factura_fk FOREIGN KEY (id_factura) REFERENCES factura (id)
);
