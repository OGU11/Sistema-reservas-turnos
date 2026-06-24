package model;

/*PAQUETES IMPORTADOS*/
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;
import persistence.ClienteJpaController;

public class ClienteService {
    
    /*VARIABLES DE PERSISTENCE*/
    private ClienteJpaController clienteJpa;
    private EntityManagerFactory emf;
    private EntityManager em;
    
    /*CONSTRUCTOR*/
    public ClienteService() {
    }

    public ClienteService(ClienteJpaController clienteJpa) {
        this.clienteJpa = clienteJpa;
        emf = Persistence.createEntityManagerFactory("sistemaReservasJPAPU");
    }
    
    
    /*METODO PARA CREAR CLIENTE*/
    public void crearCliente(Cliente cliente){
        clienteJpa.create(cliente); 
    }
    
    /*METODO PARA MODIFICAR NOMBRE*/
    public void modificarNombre(int id, String nombreNuevo){
        em = emf.createEntityManager();
        em.getTransaction().begin();
        Cuenta cuenta = em.find(Cuenta.class, id);
        cuenta.getCliente().setNombre(nombreNuevo); 
        em.getTransaction().commit();
        em.close();
    }
    
    /*METODO PARA MODIFICAR TELEFONO*/
    public void modificarTelefono(int id, int telefonoNuevo){
        em = emf.createEntityManager();
        em.getTransaction().begin();
        Cuenta cuenta = em.find(Cuenta.class, id);
        cuenta.getCliente().setTelefono(telefonoNuevo); 
        em.getTransaction().commit();
        em.close();
    }
    
    /*METODO PARA MODIFICAR EMAIL*/
    public void modificarEmail(int id, String emailNuevo){
        em = emf.createEntityManager();
        em.getTransaction().begin();
        Cuenta cuenta = em.find(Cuenta.class, id);
        cuenta.getCliente().setEmail(emailNuevo); 
        em.getTransaction().commit();
        em.close();
    }
    
    /*METODO PARA TRAER TODOS LOS CLIENTES REGISTRADOS*/
    public List<Cliente> verClientesRegistrados(){
        return clienteJpa.findClienteEntities();
    }
}
