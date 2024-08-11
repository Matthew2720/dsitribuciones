package distribuciones.producto.puerto;

import distribuciones.producto.entidad.Producto;

public interface RepositorioProducto {

    Producto obtener(Long id);
}
