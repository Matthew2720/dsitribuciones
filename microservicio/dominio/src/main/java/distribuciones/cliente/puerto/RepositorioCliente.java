package distribuciones.cliente.puerto;

import distribuciones.cliente.entidad.Cliente;

public interface RepositorioCliente {

    Cliente obtener(Long id);
}
