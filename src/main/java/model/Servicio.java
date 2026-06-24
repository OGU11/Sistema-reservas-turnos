package model;

/*PAQUETES IMPORTADOS*/
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;

/*ANOTACION QUE INDICA QUE LA CLASE ES UNA ENTIDAD*/
@Entity
@Table(name="servicios")
public class Servicio implements Serializable {
    /*VARIABLES ATRIBUTOS + MAPEO DE LOS ATRIBUTOS DE LA BASE DE DATOS*/
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    
    @Column(name="nombre")
    private String nombre;
    
    @Column(name="duracion")
    private String duracion;

    /*CONSTRUCTOR VACIO*/
    public Servicio() {
    }

    /*CONSTRUCTOR*/
    public Servicio(String nombre, String duracion) {
        this.nombre = nombre;
        this.duracion = duracion;
    }

    /*GETTERS Y SETTERS*/
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }
    
    
}
