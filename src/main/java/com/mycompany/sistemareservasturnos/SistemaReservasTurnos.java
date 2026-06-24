package com.mycompany.sistemareservasturnos;

/*PAQUETES IMPORTADOS*/
import model.*;
import controller.Controlador;
import exceptions.CuentaNoEncontradaException;
import persistence.*;
import persistence.exceptions.NonexistentEntityException;
import validations.Validaciones;
import view.Vista;

public class SistemaReservasTurnos {

    public static void main(String[] args) throws CuentaNoEncontradaException, NonexistentEntityException{
        /*VARIABLES DE PERSISTENCE*/
        ClienteJpaController clienteJpa = new ClienteJpaController();
        CuentaJpaController cuentaJpa = new CuentaJpaController();
        ProfesionalJpaController profesionalJpa = new ProfesionalJpaController();
        ServicioJpaController servicioJpa = new ServicioJpaController();
        TurnoJpaController turnoJpa = new TurnoJpaController();
        
        /*VARIABLES DE VALIDATIONS*/
        Validaciones validaciones = new Validaciones();
        
        /*VARIABLES DE VIEW*/
        Vista vista = new Vista();
        
        /*VARIABLES DE MODEL*/
        Cliente cliente = new Cliente();
        Cuenta cuenta = new Cuenta();
        Profesional profesional = new Profesional();
        Servicio servicio = new Servicio();
        Turno turno = new Turno();
        ClienteService clienteServicio = new ClienteService(clienteJpa); 
        CuentaService cuentaServicio = new CuentaService(cuentaJpa, validaciones, vista, clienteJpa); 
        ProfesionalService profesionalServicio = new ProfesionalService();
        ServicioService serviceServicio = new ServicioService();
        TurnoService turnoServicio = new TurnoService();
        
        /*VARIABLES DE CONTROLLER*/
        Controlador controlador = new Controlador(cliente, cuenta, profesional, turno, servicio, clienteServicio, cuentaServicio, profesionalServicio, turnoServicio, serviceServicio, vista, clienteJpa, cuentaJpa, profesionalJpa, turnoJpa, servicioJpa, validaciones);
        /*METODO ejecutar DE CONTROLADOR*/
        controlador.ejecutar();   
    }
}
