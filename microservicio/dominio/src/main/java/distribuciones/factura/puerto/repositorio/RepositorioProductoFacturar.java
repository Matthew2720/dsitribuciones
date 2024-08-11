package distribuciones.factura.puerto.repositorio;

import distribuciones.factura.modelo.entidad.Factura;
import distribuciones.factura.modelo.entidad.ProductoFacturar;

import java.util.List;

public interface RepositorioProductoFacturar {

    void guardarPorFactura(Factura factura, Long idFactura);

    List<ProductoFacturar> obtenerPorFactura(Long idFactura);
}
