package model;

/*PAQUETES IMPORTADOS*/
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import model.Turno.Estado;

/*ANOTACION QUE INDICA QUE LA CLASE ES UNA ENTIDAD*/
@Entity
@Table(name="turno")
public class Turno implements Serializable {
    /*ENUM*/
    public enum Estado{
        DISPONIBLE, RESERVADO, CONFIRMADO, ATENDIDO
    }
    
    /*VARIABLES ATRIBUTOS + MAPEO DE LOS ATRIBUTOS DE LA BASE DE DATOS*/
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    
    @Column(name="fechaHora")
    private LocalDate fechaHora;
    
    @Enumerated(EnumType.STRING)
    @Column(name="estado")
    private Estado estado;
    
    @ManyToOne
    @JoinColumn(name="cliente_id")
    private Cliente clienteId;
    
    @ManyToOne
    @JoinColumn(name="profesional_id")
    private Profesional profesionalId;
    
    @ManyToOne
    @JoinColumn(name="servicio_id")
    private Servicio servicio;

    /*CONSTRUCTOR VACIO*/
    public Turno() {
    }

    /*CONSTRUCTOR*/
    public Turno(LocalDate fechaHora, Estado estado, Cliente clienteId, Profesional profesionalId, Servicio servicio) {
        this.fechaHora = fechaHora;
        this.estado = estado;
        this.clienteId = clienteId;
        this.profesionalId = profesionalId;
        this.servicio = servicio;
    }

    /*GETTERS Y SETTERS*/
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDate fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Cliente getClienteId() {
        return clienteId;
    }

    public void setClienteId(Cliente clienteId) {
        this.clienteId = clienteId;
    }

    public Profesional getProfesionalId() {
        return profesionalId;
    }

    public void setProfesionalId(Profesional profesionalId) {
        this.profesionalId = profesionalId;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }
}
