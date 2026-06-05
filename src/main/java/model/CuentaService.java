package model;

/*PAQUETES IMPORTADOS*/
import persistence.CuentaJpaController;

public class CuentaService {
    /*VARIABLES UTILES*/
    private boolean cuentaCreada;
    
    /*VARIABLES DE PERSISTENCE*/
    private CuentaJpaController cuentaJpa;
    
    public void crearCuenta(Cuenta cuenta){
        cuentaJpa.create(cuenta);
    }
}
