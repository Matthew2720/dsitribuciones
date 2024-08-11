package distribuciones.factura.modelo.dto;

import distribuciones.factura.modelo.entidad.EstadoFactura;
import java.math.BigDecimal;

public record ResumenFacturaDTO(
        Long id,
        BigDecimal valorTotal,
        EstadoFactura estado
) {
}
