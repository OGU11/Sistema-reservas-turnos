package controller;

/*PAQUETES IMPORTADOS*/
import java.util.InputMismatchException;
import model.*;
import view.Vista;
import persistence.*;
import validations.Validaciones;

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

    /*VARIABLES DE VALIDATIONS*/
    private Validaciones validaciones;

    /*CONSTRUCTOR*/
    public Controlador(Cliente cliente, Cuenta cuenta, Profesional profesional, Turno turno, Servicio servicio, ClienteService serviceCliente, CuentaService serviceCuenta, ProfesionalService serviceProfesional, TurnoService serviceTurno, ServicioService serviceServicio, Vista vista, ClienteJpaController clienteJpa, CuentaJpaController cuentaJpa, ProfesionalJpaController profesionalJpa, TurnoJpaController turnoJpa, ServicioJpaController servicioJpa, Validaciones validaciones) {
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
        this.validaciones = validaciones;
    }

    public void ejecutar() {
        while (ejecutarPrograma) {
            try {
                if (serviceCuenta.isCuentaCreada() != true) {
                    vista.mensajePrincipal();
                    int decision = vista.getInputInt();
                    vista.limpiarBuffed();
                    if (decision == 1) {
                        vista.registrarse();
                        String nombre = validaciones.validarNombre("Ingresa tu nombre: ", vista.getScanner());
                        int telefono = validaciones.validarTelefono("Ingresa tu telefono: ", vista.getScanner());
                        String email = validaciones.validarEmail("Ingresa tu email: ", vista.getScanner());
                        String contrasena = validaciones.validarContrasena("Ingresa tu contrasena: ", vista.getScanner());
                        Cliente cliente = new Cliente(nombre, telefono, email);
                        Cuenta cuenta = new Cuenta(contrasena, cliente);
                        serviceCliente.crearCliente(cliente);
                        serviceCuenta.crearCuenta(cuenta);
                        vista.mostrarMensaje("Cuenta creada satisfactoriamente!\n");
                    } 
                    if(decision == 2) {
                        vista.iniciarSesion();
                        String email = validaciones.validarEmail("Escribe tu Gmail: ", vista.getScanner());
                        String contrasena = validaciones.validarContrasena("Escribe tu contrasena: ", vista.getScanner());
                        if(serviceCuenta.iniciarSesion(email, contrasena) == true){
                            vista.mostrarMensaje("Sesion iniciada correctamente!"); 
                        } else {vista.mostrarMensaje("ERROR: Contrasena incorrecta.");} 
                    } else {
                        vista.mostrarMensaje("ERROR: Dato incorrecto."); 
                    }
                }
            } catch (InputMismatchException e) {
                vista.mostrarMensaje("ERROR: Dato invalido.");
                vista.limpiarBuffed();
            }
        }

    }
}
