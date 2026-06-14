package model;

/*PAQUETES IMPORTADOS*/
import jakarta.persistence.Cache;
import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceUnitUtil;
import jakarta.persistence.Query;
import jakarta.persistence.SynchronizationType;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.metamodel.Metamodel;
import java.util.List;
import java.util.Map;
import persistence.CuentaJpaController;

public class CuentaService {
    /*VARIABLES UTILES*/
    private boolean cuentaCreada = false;
    
    
    /*VARIABLES DE PERSISTENCE*/
    private CuentaJpaController cuentaJpa;
    private EntityManagerFactory emf;
    
    /*CONSTRUCTOR*/
    public CuentaService() {
    }
    public CuentaService(CuentaJpaController cuentaJpa) {
        this.cuentaJpa = cuentaJpa;
        emf = Persistence.createEntityManagerFactory("sistemaReservasJPAPU");
    }
    
    /*METODO PARA CREAR CUENTA*/
    public void crearCuenta(Cuenta cuenta){
        cuentaJpa.create(cuenta);
        cuentaCreada = true;
    }
    
    /*METODO PARA INICIAR SESION*/
    public boolean iniciarSesion(String email, String contrasena){
        boolean cuentaEncontrada = false;
        EntityManager em = emf.createEntityManager();
        TypedQuery<Cuenta> consulta = em.createQuery(
                "SELECT c FROM Cuenta c WHERE c.cliente.email = :email", Cuenta.class);
        consulta.setParameter("email", email);
        List<Cuenta> listaCuenta = consulta.getResultList();
        if (!listaCuenta.isEmpty()) { 
            Cuenta cuenta = listaCuenta.get(0);
            if(contrasena.equals(cuenta.getContrasena())){
                cuentaEncontrada = true;
                cuentaCreada = true;
            }
        } else {System.out.println("ERROR: Cuenta no encontrada.");}//AQUI IRIA UNA EXCEPCION PERSONALIZADA EN EL FUTURO
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
