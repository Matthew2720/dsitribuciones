package distribuciones.producto.adaptador.repositorio;

import distribuciones.infraestructura.jdbc.MapperResult;
import distribuciones.producto.entidad.Producto;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MapeoProducto implements RowMapper<Producto>, MapperResult {

    @Override
    public Producto mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        long id = resultSet.getLong("id");
        String nombre = resultSet.getString("nombre");
        boolean aplicaIva = resultSet.getBoolean("aplica_iva");
        BigDecimal valor = resultSet.getBigDecimal("valor");

        return Producto.reconstruir(id, nombre, aplicaIva, valor);
    }

}
