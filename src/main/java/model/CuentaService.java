package model;

/*PAQUETES IMPORTADOS*/
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.util.List;
import persistence.CuentaJpaController;
import exceptions.CuentaNoEncontradaException;
import persistence.ClienteJpaController;
import persistence.exceptions.NonexistentEntityException;
import validations.Validaciones;
import view.Vista;

public class CuentaService {

    /*VARIABLES UTILES*/
    private boolean cuentaCreada = false;

    /*VARIABLES DE PERSISTENCE*/
    private CuentaJpaController cuentaJpa;
    private ClienteJpaController clienteJpa;
    private EntityManagerFactory emf;

    /*VARIABLES DE VALIDATIONS*/
    private Validaciones validaciones;

    private Vista vista;

    /*CONSTRUCTOR VACIO*/
    public CuentaService() {
    }

    /*CONSTRUCTOR*/
    public CuentaService(CuentaJpaController cuentaJpa, Validaciones validaciones, Vista vista, ClienteJpaController clienteJpa) {
        this.cuentaJpa = cuentaJpa;
        this.validaciones = validaciones;
        this.vista = vista;
        this.clienteJpa = clienteJpa;
        emf = Persistence.createEntityManagerFactory("sistemaReservasJPAPU");
    }

    /*METODO PARA CREAR CUENTA*/
    public void crearCuenta(Cuenta cuenta) {
        cuentaJpa.create(cuenta);
        cuentaCreada = true;
    }

    /*METODO PARA INICIAR SESION*/
    public Cuenta iniciarSesion() throws CuentaNoEncontradaException {
        //EL METODO FUNCIONA, PERO UTILIZA A LA CLASE "Vista" y "Validaciones" Y NO ES VALIDO EN LA ESTRUCTURA MVC.
        Cuenta cuenta;
        boolean contrasenaCorrecta = false;
        String email = validaciones.validarEmail("Escribe tu gmail: ", vista.getScanner());
        EntityManager em = emf.createEntityManager();
        TypedQuery<Cuenta> consulta = em.createQuery(
                "SELECT c FROM Cuenta c WHERE c.cliente.email = :email", Cuenta.class);
        consulta.setParameter("email", email);
        List<Cuenta> listaCuenta = consulta.getResultList();

        if (!listaCuenta.isEmpty()) {
            cuenta = listaCuenta.get(0);

            while (contrasenaCorrecta == false) {
                String contrasena = validaciones.validarContrasena("Escribe tu contrasena: ", vista.getScanner());
                if (contrasena.equals(cuenta.getContrasena())) {
                    contrasenaCorrecta = true;
                    cuentaCreada = true;
                } else {
                    System.out.println("ERROR: Contrasena incorrecta.");
                }
            }
        } else {
            throw new CuentaNoEncontradaException("ERROR Capturado: Cuenta no encontrada.");
        }

        return cuenta;
    }

    /*METODO PARA MODIFICAR CONTRASENA*/
    public void modificarContrasena(int id, String contrasenaNueva){
        EntityManager em = emf.createEntityManager();
        em = emf.createEntityManager();
        em.getTransaction().begin();
        Cuenta cuenta = em.find(Cuenta.class, id);
        cuenta.setContrasena(contrasenaNueva); 
        em.getTransaction().commit();
        em.close();
    }
    
    /*METODO PARA ELIMINAR UNA CUENTA*/
    public void eliminarCuenta(int id) throws NonexistentEntityException{
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Cuenta cuentaEliminar = em.find(Cuenta.class, id);
        em.getTransaction().commit();
        Cliente clienteEliminar = cuentaEliminar.getCliente();
        cuentaJpa.destroy(cuentaEliminar.getId()); 
        clienteJpa.destroy(clienteEliminar.getId());
        em.close();
    }
    
    /*METODO PARA BUSCAR UNA CUENTA*/
    public List<Cuenta> buscarCuenta(String email) throws CuentaNoEncontradaException{
        EntityManager em = emf.createEntityManager();
        TypedQuery<Cuenta> consulta = (TypedQuery<Cuenta>) em.createQuery(
                "SELECT c FROM Cuenta c WHERE c.cliente.email = :email", Cuenta.class);
        consulta.setParameter("email", email);
        List<Cuenta> cuentaEncontrada = consulta.getResultList();
        if(cuentaEncontrada.isEmpty()){
            throw new CuentaNoEncontradaException("ERROR Capturado: Cuenta no encontrada.");
        }
        return cuentaEncontrada;
    }
    
    /*GETTERS Y SETTER*/
    public boolean isCuentaCreada() {
        return cuentaCreada;
    }

    public void setCuentaCreada(boolean cuentaCreada) {
        this.cuentaCreada = cuentaCreada;
    }

}
