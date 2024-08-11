package distribuciones.configuracion;


import distribuciones.factura.puerto.repositorio.RepositorioFactura;
import distribuciones.factura.servicio.ServicioAnular;
import distribuciones.factura.servicio.ServicioFacturar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicio {

    @Bean
    public ServicioFacturar servicioFacturar(RepositorioFactura repositorioFactura) {
        return new ServicioFacturar(repositorioFactura);
    }

    @Bean
    public ServicioAnular servicioAnular(RepositorioFactura repositorioFactura) {
        return new ServicioAnular(repositorioFactura);
    }


	

} 
