package model;

/*PAQUETES IMPORTADOS*/
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;

/*ANOTACION PARA INDICAR COMO CLASE ENTIDAD*/
@Entity
@Table(name="profesional")
public class Profesional implements Serializable {
    /*VARIABLES ATRIBUTOS + MAPEO DE LOS ATRIBUTOS DE LA BASE DE DATOS*/
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    
    @Column(name="nombre")
    private String nombre;
    
    @Column(name="especialidad")
    private String especialidad;
    
    @Column(name="matricula")
    private int matricula;

    /*CONSTRUCTOR VACIO*/
    public Profesional() {
    }

    /*CONSTRUCTOR*/
    public Profesional(String nombre, String especialidad, int matricula) {
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.matricula = matricula;
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

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }
}
