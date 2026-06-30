package controller;

/*PAQUETES IMPORTADOS*/
import exceptions.CuentaDuplicadaException;
import exceptions.CuentaNoEncontradaException;
import java.util.InputMismatchException;
import java.util.List;
import model.*;
import view.Vista;
import persistence.*;
import persistence.exceptions.NonexistentEntityException;
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
    private Especialidad especialidad;
    private ProfesionalServicio profesionalServicio;
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
    private EspecialidadJpaController especialidadJpa;
    private ProfesionalServicioJpaController profesionalServicioJpa;

    /*VARIABLES DE VALIDATIONS*/
    private Validaciones validaciones;

    /*CONSTRUCTOR*/
    public Controlador(Cliente cliente, Cuenta cuenta, Profesional profesional, Turno turno, Servicio servicio, Especialidad especialidad, ProfesionalServicio profesionalServicio, ClienteService serviceCliente, CuentaService serviceCuenta, ProfesionalService serviceProfesional, TurnoService serviceTurno, ServicioService serviceServicio, Vista vista, ClienteJpaController clienteJpa, CuentaJpaController cuentaJpa, ProfesionalJpaController profesionalJpa, TurnoJpaController turnoJpa, ServicioJpaController servicioJpa, EspecialidadJpaController especialidadJpa, ProfesionalServicioJpaController profesionalServicioJpa, Validaciones validaciones) {
        this.cliente = cliente;
        this.cuenta = cuenta;
        this.profesional = profesional;
        this.turno = turno;
        this.servicio = servicio;
        this.especialidad = especialidad;
        this.profesionalServicio = profesionalServicio;
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
        this.especialidadJpa = especialidadJpa;
        this.profesionalServicioJpa = profesionalServicioJpa;
        this.validaciones = validaciones;
    }

    /*METODO PARA EJECUTAR EL SISTEMA*/
    public void ejecutar() throws CuentaNoEncontradaException, NonexistentEntityException, CuentaDuplicadaException {
        /*BUCLE PARA EJECUTAR EL PROGRAMA CONSTANTEMENTE*/
        while (ejecutarPrograma) {
            /*TRY-CATCH QUE ENVUELVE TODO EL CODIGO PARA CAPTURAR EXCEPCION DE DATOS NO VALIDOS*/
            try {
                /*IF QUE VERIFICA SI EL USUARIO SE CREÓ UNA CUENTA O INICIÓ SESION - OBLIGA AL USUARIO A HACERLO*/
                if (serviceCuenta.isCuentaCreada() != true) {
                    /*MENSAJE PRINCIPAL DEL SISTEMA - CREAR UNA CUENTA O INICIAR SESION*/
                    vista.mensajePrincipal();
                    int decision = vista.getInputInt();
                    vista.limpiarBuffed();

                    if (decision == 1) {
                        /*ALGORITMO PARA REGISTRARSE (CREAR UNA CUENTA)*/
                        vista.registrarse();
                        String nombre = validaciones.validarNombre("Ingresa tu nombre: ", vista.getScanner());
                        int telefono = validaciones.validarTelefono("Ingresa tu telefono: ", vista.getScanner());
                        String email = validaciones.validarEmail("Ingresa tu email: ", vista.getScanner());
                        String contrasena = validaciones.validarContrasena("Ingresa tu contrasena: ", vista.getScanner());
                        Cliente cliente = new Cliente(nombre, telefono, email);
                        Cuenta cuentaGenerada = new Cuenta(contrasena, cliente);
                        
                        /*TRY-CATCH PARA CAPTURAR EXCEPCION*/
                        try{
                            if(serviceCuenta.validarCuentaDuplicada(telefono, email, contrasena) != true){
                                serviceCliente.crearCliente(cliente);
                                serviceCuenta.crearCuenta(cuentaGenerada);
                                vista.mostrarMensaje("Cuenta creada satisfactoriamente!\n");
                                cuenta = cuentaGenerada;
                            }
                        }catch(CuentaDuplicadaException e){
                            vista.mostrarMensaje("ERROR Capturado: El/los dato/s que has ingresado ya fueron utilizados. Intenta con otros.\n");
                        } 
                    }
                    if (decision == 2) {
                        /*ALGORITMO PARA INICIAR SESION*/
                        vista.iniciarSesion();

                        /*TRY-CATCH PARA CAPTURAR UNA EXCEPCION*/
                        try {
                            cuenta = serviceCuenta.iniciarSesion();
                            vista.mostrarMensaje("Sesion iniciada correctamente!\n");
                        } catch (CuentaNoEncontradaException e) {
                            vista.mostrarMensaje("ERROR Capturado: " + e.getMessage());
                        }
                    } else if (decision != 1 && decision != 2) {
                        vista.mostrarMensaje("ERROR: Dato incorrecto.\n");
                    }
                } else if (serviceCuenta.isCuentaCreada() == true) {
                    /*MENSAJE DE OPCIONES Y ENTRADA DE DATO DEL USUARIO*/
                    vista.menuDeOpciones();
                    int operacion = vista.getInputInt();
                    vista.limpiarBuffed();

                    /*SWITCH CON TODAS LAS OPCIONES DEL SISTEMA*/
                    switch (operacion) {
                        case 1:
                            /*MODIFICAR DATOS DE MI CUENTA*/
                            vista.mensajeModificarDatos();
                            int modificar = vista.getInputInt();
                            vista.limpiarBuffed();
                            switch (modificar) {
                                case 1:
                                    /*MODIFICAR NOMBRE*/
                                    vista.mensajeModificarNombre();
                                    String nombreNuevo = validaciones.validarNombre("Nombre nuevo: ", vista.getScanner());
                                    serviceCliente.modificarNombre(cuenta.getId(), nombreNuevo);
                                    vista.mostrarMensaje("Nombre modificado exitosamente!\n");
                                    break;
                                case 2:
                                    /*MODIFICAR TELEFONO*/
                                    vista.mensajeModificarTelefono();
                                    int telefonoNuevo = validaciones.validarTelefono("Numero de telefono nuevo: ", vista.getScanner());
                                    serviceCliente.modificarTelefono(cuenta.getId(), telefonoNuevo);
                                    vista.mostrarMensaje("Numero de telefono modificado exitosamente!\n");
                                    break;
                                case 3:
                                    /*MODIFICAR EMAIL*/
                                    vista.mensajeModificarEmail();
                                    String emailNuevo = validaciones.validarEmail("Email nuevo: ", vista.getScanner());
                                    serviceCliente.modificarEmail(cuenta.getId(), emailNuevo);
                                    vista.mostrarMensaje("Email modificado exitosamente!\n");
                                    break;
                                case 4:
                                    /*MODIFICAR CONTRASENA*/
                                    vista.mensajeModificarContrasena();
                                    String contrasenaNueva = validaciones.validarContrasena("Contrasena nueva: ", vista.getScanner());
                                    serviceCuenta.modificarContrasena(cuenta.getId(), contrasenaNueva);
                                    vista.mostrarMensaje("Contrasena modificada exitosamente!\n");
                                    break;
                                default:
                                    vista.mostrarMensaje("ERROR: Dato no reconocido.\n");
                                    break;
                            }
                            break;
                        case 2:
                            /*ELIMINAR UNA CUENTA*/
                            vista.mensajeEliminarCuenta();
                            int idCuentaEliminada = vista.getInputInt();
                            vista.limpiarBuffed();

                            /*TRY-CATCH PARA CAPTURAR LA EXCEPCION*/
                            try {
                                serviceCuenta.eliminarCuenta(idCuentaEliminada);
                                vista.mostrarMensaje("Cuenta eliminada correctamente!\n");
                            } catch (NonexistentEntityException e) {
                                vista.mostrarMensaje("ERROR Capturado: La cuenta con el ID " + idCuentaEliminada + " no existe.\n");
                            }
                            break;
                        case 3:
                            /*BUSCAR CUENTA*/
                            vista.mensajeBuscarUnaCuenta();

                            /*TRY-CATCH PARA CAPTURAR LA EXCEPCION*/
                            try {
                                String email = validaciones.validarEmail("Email de la cuenta: ", vista.getScanner());
                                List<Cuenta> cuentaEncontrada = serviceCuenta.buscarCuenta(email);
                                vista.mostrarDatosCuentaEncontrada(cuentaEncontrada);
                            } catch (CuentaNoEncontradaException e) {
                                vista.mostrarMensaje("ERROR Capturado: Cuenta no encontrada\n");
                            }
                            break;
                        case 4:
                            /*MOSTRAR LISTADO DE CLIENTES*/
                            vista.mensajeVerClientes();
                            List<Cliente> listaClientes = serviceCliente.verClientesRegistrados();
                            vista.mostrarClientes(listaClientes);
                            break;
                        case 5:
                            /*CREAR UN SERVICIO*/
                            break;
                        case 6:
                            /*MODIFICAR SERVICIO*/
                            break;
                        case 7:
                            /*ELIMINAR SERVICIO*/
                            break;
                        case 8:
                            /*CREAR UN TURNO*/
                            break;
                        case 9:
                            /*VER TURNO/S*/
                            break;
                        case 10:
                            /*REPROGRAMAR UN TURNO*/
                            break;
                        case 11:
                            /*CANCELAR DE TURNO*/
                            break;
                        case 12:
                            /*SALIR*/
                            vista.mostrarMensaje("Vuelve pronto!");
                            vista.cerrarScanner();
                            ejecutarPrograma = false;
                            break;
                        default:
                            vista.mostrarMensaje("ERROR: Dato no reconocido.\n");
                            break;
                    }
                }
            } catch (InputMismatchException e) {
                vista.mostrarMensaje("ERROR: Dato invalido.\n");
                vista.limpiarBuffed();
            }
        }

    }
}
