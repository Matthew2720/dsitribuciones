package distribuciones.factura.comando.fabrica;

import distribuciones.cliente.puerto.RepositorioCliente;
import distribuciones.factura.comando.ComandoProductoFacturar;
import distribuciones.factura.comando.ComandoSolicitudFacturar;
import distribuciones.factura.modelo.entidad.ProductoFacturar;
import distribuciones.factura.modelo.entidad.SolicitudFacturar;
import distribuciones.producto.puerto.RepositorioProducto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FabricaSolicitudFacturar {

    private final RepositorioCliente repositorioCliente;
    private final RepositorioProducto repositorioProducto;

    public FabricaSolicitudFacturar(RepositorioCliente repositorioCliente, RepositorioProducto repositorioProducto) {
        this.repositorioCliente = repositorioCliente;
        this.repositorioProducto = repositorioProducto;
    }

    public SolicitudFacturar crear(ComandoSolicitudFacturar comandoSolicitudFacturar) {
        return new SolicitudFacturar(repositorioCliente.obtener(comandoSolicitudFacturar.idCliente()),
                obtenerProductos(comandoSolicitudFacturar.comandoProductosFacturar())
        );
    }

    private List<ProductoFacturar> obtenerProductos(List<ComandoProductoFacturar> comandoProductosFacturar) {
        return comandoProductosFacturar.stream().map(comandoProducto ->
                        ProductoFacturar.crear(
                                comandoProducto.cantidad(),
                                repositorioProducto.obtener(comandoProducto.idProducto())))
                                .toList();
    }
}
