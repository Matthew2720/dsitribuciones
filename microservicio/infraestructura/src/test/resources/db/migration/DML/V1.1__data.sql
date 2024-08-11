INSERT INTO cliente (nombre, tipo_cliente) VALUES ('Cliente 1', 'PREFERENCIAL');
INSERT INTO cliente (nombre, tipo_cliente) VALUES ('Cliente 2', 'COMUN');

INSERT INTO producto (nombre, aplica_iva, valor) VALUES ('Producto 1', TRUE, 25000);
INSERT INTO producto (nombre, aplica_iva, valor) VALUES ('Producto 2', FALSE, 10000);

INSERT INTO factura (id_cliente, valor_total, estado) VALUES (1, 25000, 'ACTIVA');
INSERT INTO factura (id_cliente, valor_total, estado) VALUES (2, 3000, 'ANULADA');

INSERT INTO producto_facturar (id_factura, id_producto, cantidad) VALUES (1, 1, 2);
