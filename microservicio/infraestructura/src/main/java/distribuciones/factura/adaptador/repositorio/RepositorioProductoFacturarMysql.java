package distribuciones.factura.adaptador.repositorio;

import distribuciones.factura.modelo.entidad.Factura;
import distribuciones.factura.modelo.entidad.ProductoFacturar;
import distribuciones.factura.puerto.repositorio.RepositorioProductoFacturar;
import distribuciones.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import distribuciones.infraestructura.jdbc.sqlstatement.SqlStatement;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RepositorioProductoFacturarMysql implements RepositorioProductoFacturar {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;
    private final MapeoProductoFacturar mapeoProductoFacturar;

    @SqlStatement(namespace = "productofacturar", value = "crear")
    private static String sqlCrear;

    @SqlStatement(namespace = "productofacturar", value = "obtenerporfactura")
    private static String sqlObtenerPorFactura;

    public RepositorioProductoFacturarMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate, MapeoProductoFacturar mapeoProductoFacturar) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
        this.mapeoProductoFacturar = mapeoProductoFacturar;
    }

    @Override
    public void guardarPorFactura(Factura factura, Long idFactura) {
        factura.getProductos().stream().forEach(productoFacturar -> this.guardar(productoFacturar, idFactura));
    }

    @Override
    public List<ProductoFacturar> obtenerPorFactura(Long idFactura) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id_factura", idFactura);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .query(sqlObtenerPorFactura, paramSource, mapeoProductoFacturar );
    }

    private void guardar(ProductoFacturar productoFacturar, Long idFactura) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id_factura", idFactura);
        paramSource.addValue("id_producto", productoFacturar.getProducto().getId());
        paramSource.addValue("cantidad", productoFacturar.getCantidad());
        this.customNamedParameterJdbcTemplate.crear(paramSource, sqlCrear);
    }
}
