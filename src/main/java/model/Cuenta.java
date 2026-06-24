package model;

/*PAQUETES IMPORTADOS*/
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.io.Serializable;

/*ANOTACION QUE INDICA QUE LA CLASE ES UNA ENTIDAD*/
@Entity
@Table(name="cuenta")
public class Cuenta implements Serializable {
    /*VARIABLES ATRIBUTOS + MAPEO DE LOS ATRIBUTOS DE LA BASE DE DATOS*/
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    
    @Column(name="contrasena")
    private String contrasena;
    
    @OneToOne
    @JoinColumn(name="id_cliente")
    private Cliente cliente;

    /*CONSTRUCTOR VACIO*/
    public Cuenta() {
    }
    
    /*CONSTRUCTOR*/
    public Cuenta(String contrasena, Cliente cliente) {
        this.contrasena = contrasena;
        this.cliente = cliente;
    }

    /*GETTERS Y SETTERS*/
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    
}
