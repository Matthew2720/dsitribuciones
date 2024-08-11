package distribuciones.factura.modelo.entidad;

import distribuciones.cliente.entidad.Cliente;

import java.math.BigDecimal;
import java.util.List;

public record SolicitudReconstruirFactura(Long id, Cliente cliente, List<ProductoFacturar> productosFacturar,
                                          BigDecimal valorTotal, EstadoFactura estadoFactura) {
}