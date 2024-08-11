package distribuciones.factura.comando.manejador;

import distribuciones.factura.comando.ComandoAnular;
import distribuciones.factura.puerto.repositorio.RepositorioFactura;
import distribuciones.factura.servicio.ServicioAnular;
import distribuciones.manejador.ManejadorComando;
import org.springframework.stereotype.Component;

@Component
public class ManejadorAnular implements ManejadorComando<ComandoAnular> {

    private final ServicioAnular servicioAnular;
    private final RepositorioFactura repositorioFactura;

    public ManejadorAnular(ServicioAnular servicioAnular, RepositorioFactura repositorioFactura) {
        this.servicioAnular = servicioAnular;
        this.repositorioFactura = repositorioFactura;
    }

    @Override
    public void ejecutar(ComandoAnular comandoAnular) {
        servicioAnular.ejecutar(repositorioFactura.obtener(comandoAnular.idFactura()));
    }
}
