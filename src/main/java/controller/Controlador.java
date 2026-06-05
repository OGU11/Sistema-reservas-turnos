package controller;

/*PAQUETES IMPORTADOS*/
import model.*;
import view.Vista;
import persistence.*;

public class Controlador {

    /*VARIABLES UTILES*/
    boolean ejecutarPrograma = true;
    
    /*VARIABLES DE MODEL*/
    private Cliente cliente;
    private Cuenta cuenta;
    private Profesional profesional;
    private Turno turno;
    private Servicio servicio;
    private ClienteService serviceCliente;
    private CuentaService serviceCuenta;
    private ProfesionalService serviceProfesional;
    private TurnoService serviceTurno;
    private ServicioService serviceServicio;
    
    /*VARIABLES DE VIEW*/
    private Vista vista;
    
    /*VARIABLES DE PERSISTENCE*/
    private ClienteJpaController clienteJpa;
    private CuentaJpaController cuentaJpa;
    private ProfesionalJpaController profesionalJpa;
    private TurnoJpaController turnoJpa;
    private ServicioJpaController servicioJpa;
    
    /*CONSTRUCTOR*/
    public Controlador(Cliente cliente, Cuenta cuenta, Profesional profesional, Turno turno, Servicio servicio, ClienteService serviceCliente, CuentaService serviceCuenta, ProfesionalService serviceProfesional, TurnoService serviceTurno, ServicioService serviceServicio, Vista vista, ClienteJpaController clienteJpa, CuentaJpaController cuentaJpa, ProfesionalJpaController profesionalJpa, TurnoJpaController turnoJpa, ServicioJpaController servicioJpa) {
        this.cliente = cliente;
        this.cuenta = cuenta;
        this.profesional = profesional;
        this.turno = turno;
        this.servicio = servicio;
        this.serviceCliente = serviceCliente;
        this.serviceCuenta = serviceCuenta;
        this.serviceProfesional = serviceProfesional;
        this.serviceTurno = serviceTurno;
        this.serviceServicio = serviceServicio;
        this.vista = vista;
        this.clienteJpa = clienteJpa;
        this.cuentaJpa = cuentaJpa;
        this.profesionalJpa = profesionalJpa;
        this.turnoJpa = turnoJpa;
        this.servicioJpa = servicioJpa;
    }
    
    public void ejecutar(){
        while(ejecutarPrograma){
            vista.mensajePrincipal();
            int decision = vista.getInputInt();
            vista.limpiarBuffed();
            if(decision == 1){
                
            }
        }
    }
}
