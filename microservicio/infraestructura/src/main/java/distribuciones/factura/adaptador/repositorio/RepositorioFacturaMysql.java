package distribuciones.factura.adaptador.repositorio;

import distribuciones.factura.modelo.entidad.Factura;
import distribuciones.factura.puerto.repositorio.RepositorioFactura;
import distribuciones.factura.puerto.repositorio.RepositorioProductoFacturar;
import distribuciones.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import distribuciones.infraestructura.jdbc.EjecucionBaseDeDatos;
import distribuciones.infraestructura.jdbc.sqlstatement.SqlStatement;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioFacturaMysql implements RepositorioFactura {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    private final MapeoFactura mapeoFactura;
    private final RepositorioProductoFacturar repositorioProductoFacturar;

    @SqlStatement(namespace = "factura", value = "crear")
    private static String sqlCrear;

    @SqlStatement(namespace = "factura", value = "obtenerporid")
    private static String sqlObtenerPorId;

    @SqlStatement(namespace = "factura", value = "actualizarestado")
    private static String sqlActualizarEstado;

    public RepositorioFacturaMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate, MapeoFactura mapeoFactura, RepositorioProductoFacturar repositorioProductoFacturar) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
        this.mapeoFactura = mapeoFactura;
        this.repositorioProductoFacturar = repositorioProductoFacturar;
    }

    @Override
    public Long guardar(Factura factura) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id_cliente", factura.getCliente().getId());
        paramSource.addValue("valor_total", factura.getValorTotal());
        paramSource.addValue("estado", factura.getEstado().name());
        Long idFacturaGuardada = this.customNamedParameterJdbcTemplate.crear(paramSource, sqlCrear);
        repositorioProductoFacturar.guardarPorFactura(factura, idFacturaGuardada);
        return idFacturaGuardada;
    }

    @Override
    public Factura obtener(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);
        return EjecucionBaseDeDatos.obtenerUnObjetoONull(() -> this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .queryForObject(sqlObtenerPorId, paramSource, mapeoFactura));
    }

    @Override
    public void actualizarEstado(Factura factura) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", factura.getId());
        paramSource.addValue("estado", factura.getEstado().toString());
        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlActualizarEstado, paramSource);

    }
}
