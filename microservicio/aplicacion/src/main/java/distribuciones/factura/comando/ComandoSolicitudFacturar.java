package distribuciones.factura.comando;

import java.util.List;

public record ComandoSolicitudFacturar(
        Long idCliente,
        List<ComandoProductoFacturar> comandoProductosFacturar
) {
}
