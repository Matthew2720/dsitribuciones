package distribuciones.factura.puerto.dao;

import distribuciones.factura.modelo.dto.ResumenFacturaDTO;

import java.util.List;

public interface DaoFactura {

    List<ResumenFacturaDTO> obtenerResumenDeFacturasAnuladas();
}
