package distribuciones.infraestructura.jdbc;

public interface EjecutarBD<T> {
    T ejecutar();
}