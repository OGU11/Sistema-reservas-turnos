package model;

/*PAQUETES IMPORTADOS*/
import persistence.ClienteJpaController;

public class ClienteService {
    
    /*VARIABLES DE PERSISTENCE*/
    private ClienteJpaController clienteJpa;
    
    /*CONSTRUCTOR*/
    public ClienteService() {
    }

    public ClienteService(ClienteJpaController clienteJpa) {
        this.clienteJpa = clienteJpa;
    }
    
    
    /*METODO PARA CREAR CLIENTE*/
    public void crearCliente(Cliente cliente){
        clienteJpa.create(cliente); 
    }
}
