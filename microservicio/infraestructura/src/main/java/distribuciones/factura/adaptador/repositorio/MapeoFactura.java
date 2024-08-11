package distribuciones.factura.adaptador.repositorio;

import distribuciones.cliente.puerto.RepositorioCliente;
import distribuciones.factura.modelo.entidad.EstadoFactura;
import distribuciones.factura.modelo.entidad.Factura;
import distribuciones.factura.modelo.entidad.SolicitudReconstruirFactura;
import distribuciones.factura.puerto.repositorio.RepositorioProductoFacturar;
import distribuciones.infraestructura.jdbc.MapperResult;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MapeoFactura implements RowMapper<Factura>, MapperResult {

    private final RepositorioCliente repositorioCliente;
    private final RepositorioProductoFacturar repositorioProductoFacturar;

    public MapeoFactura(RepositorioCliente repositorioCliente, RepositorioProductoFacturar repositorioProductoFacturar) {
        this.repositorioCliente = repositorioCliente;
        this.repositorioProductoFacturar = repositorioProductoFacturar;
    }

    @Override
    public Factura mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        long id = resultSet.getLong("id");
        long idCliente = resultSet.getLong("id_cliente");
        BigDecimal valorTotal = resultSet.getBigDecimal("valor_total");
        EstadoFactura estado = EstadoFactura.valueOf(resultSet.getString("estado"));

        return Factura.reconstruir(
                new SolicitudReconstruirFactura(id, repositorioCliente.obtener(idCliente), repositorioProductoFacturar.obtenerPorFactura(id), valorTotal, estado));
    }

}
