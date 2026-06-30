package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.Duration;

@Entity
@Table(name="profesionalservicio")
public class ProfesionalServicio implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    
    @ManyToOne
    @JoinColumn(name="profesional_id")
    private Profesional profesional;
    
    @ManyToOne
    @JoinColumn(name="servicio_id")
    private Servicio servicio;
    
    @Column(name="duracion")
    private Duration duracion;
    
    @Column(name="precio")
    private int precio;
    
    @Column(name="activo")
    private boolean activo;

    public ProfesionalServicio() {
    }

    public ProfesionalServicio(Profesional profesional, Servicio servicio, Duration duracion, int precio, boolean activo) {
        this.profesional = profesional;
        this.servicio = servicio;
        this.duracion = duracion;
        this.precio = precio;
        this.activo = activo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Profesional getProfesional() {
        return profesional;
    }

    public void setProfesional(Profesional profesional) {
        this.profesional = profesional;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public Duration getDuracion() {
        return duracion;
    }

    public void setDuracion(Duration duracion) {
        this.duracion = duracion;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    
}
