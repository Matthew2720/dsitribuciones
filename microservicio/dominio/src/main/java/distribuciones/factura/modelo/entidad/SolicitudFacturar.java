package distribuciones.factura.modelo.entidad;

import distribuciones.cliente.entidad.Cliente;

import java.util.List;

public record SolicitudFacturar(

        Cliente cliente,
        List<ProductoFacturar> productosFacturar
) {

}
