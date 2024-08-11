package distribuciones.factura.modelo.entidad;

import distribuciones.cliente.entidad.Cliente;
import distribuciones.dominio.ValidadorArgumento;
import distribuciones.dominio.excepcion.ExcepcionValorInvalido;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Factura {

    public static final double DESCUENTO_CLIENTE_PREFERENCIAL = 0.2;
    public static final double DESCUENTO_CLIENTE_ESPECIAL = 0.1;

    private Long id;
    private Cliente cliente;
    private List<ProductoFacturar> productosFacturar;
    private BigDecimal valorTotal;
    private EstadoFactura estado;

    private Factura(SolicitudFacturar solicitudFacturar) {
        this.cliente = solicitudFacturar.cliente();
        this.productosFacturar = new ArrayList<>(solicitudFacturar.productosFacturar());
        this.valorTotal = calcularvalorTotal(solicitudFacturar.productosFacturar());
        aplicarDescuento();
        this.estado = EstadoFactura.ACTIVA;
    }

    private Factura(SolicitudReconstruirFactura solicitudReconstruirFactura) {
        this.cliente = solicitudReconstruirFactura.cliente();
        this.productosFacturar = new ArrayList<>(solicitudReconstruirFactura.productosFacturar());
        this.id = solicitudReconstruirFactura.id();
        this.valorTotal = solicitudReconstruirFactura.valorTotal();
        this.estado = solicitudReconstruirFactura.estadoFactura();
    }

    private BigDecimal calcularvalorTotal(List<ProductoFacturar> productosFacturar) {
        return productosFacturar.stream()
                .map(ProductoFacturar::calcularTotalConIva)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private void aplicarDescuento() {
        if (this.cliente.esTipoPreferencial()) {
            this.valorTotal = valorTotal.subtract(valorTotal.multiply(BigDecimal.valueOf(DESCUENTO_CLIENTE_PREFERENCIAL)));
        } else if (this.cliente.esTipoEspecial()) {
            this.valorTotal = valorTotal.subtract(valorTotal.multiply(BigDecimal.valueOf(DESCUENTO_CLIENTE_ESPECIAL)));
        }
    }

    public void anular() {
        if(this.cliente.esTipoComun()){
            throw new ExcepcionValorInvalido("No se puede anular la factura de un cliente comun");
        }
        this.estado = EstadoFactura.ANULADA;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public List<ProductoFacturar> getProductos() {
        return Collections.unmodifiableList(productosFacturar);
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public Long getId() {
        return id;
    }

    public static Factura crear(SolicitudFacturar solicitudFacturar) {
        ValidadorArgumento.validarObligatorio(solicitudFacturar.cliente(), "El cliente es requerido para facturar");
        ValidadorArgumento.validarNoVacio(solicitudFacturar.productosFacturar(), "No se puede crear una factura sin productos");
        return new Factura(solicitudFacturar);
    }

    public static Factura reconstruir(SolicitudReconstruirFactura solicitudReconstruirFactura) {
        ValidadorArgumento.validarObligatorio(solicitudReconstruirFactura.cliente(), "El cliente es requerido para facturar");
        ValidadorArgumento.validarNoVacio(solicitudReconstruirFactura.productosFacturar(), "No se puede crear una factura sin productos");
        ValidadorArgumento.validarObligatorio(solicitudReconstruirFactura.id(), "El id es requerido");
        if (solicitudReconstruirFactura.valorTotal().compareTo(BigDecimal.ZERO) <= 0) {
            throw new ExcepcionValorInvalido("El total no puede ser menor a cero");
        }
        return new Factura(solicitudReconstruirFactura);
    }


    public Boolean esAnulada() {
        return this.estado.equals(EstadoFactura.ANULADA);
    }

    public boolean esActiva() {
        return this.estado.equals(EstadoFactura.ACTIVA);
    }

    public EstadoFactura getEstado() {
        return estado;
    }
}
