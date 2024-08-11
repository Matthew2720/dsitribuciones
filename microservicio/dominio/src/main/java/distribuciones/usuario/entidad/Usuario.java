package distribuciones.usuario.entidad;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Usuario {
    private Long id;
    private String username;
    private String password;
    private String correoElectronico;
    private String nombreCompleto;
    private LocalDateTime fechaCreacion;
    private LocalDateTime ultimaFechaInicioSesion;
    private boolean estado;
    private RolUsuario rol;

    public Usuario(String username, String password, String correoElectronico, String nombreCompleto, RolUsuario rol) {
        this.username = username;
        this.password = password;
        this.correoElectronico = correoElectronico;
        this.nombreCompleto = nombreCompleto;
        this.fechaCreacion = LocalDateTime.now();
        this.estado = true;
        this.rol = RolUsuario.COMUN;
    }

    public void actualizarUltimaFechaInicioSesion() {
        this.ultimaFechaInicioSesion = LocalDateTime.now();
    }

    public void desactivarUsuario() {
        this.estado = false;
    }

    public void activarUsuario() {
        this.estado = true;
    }
}
