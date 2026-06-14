package view;

import java.util.Scanner;

public class Vista {
    Scanner s = new Scanner(System.in);
    public void mensajePrincipal(){
        System.out.print("-- Bienvenido al Sistema de Reservas de Turnos --\n"
                + "Te vamos a pedir que te registres y crees tu Cuenta. Si ya tienes una cuenta, puedes iniciar sesion.\n"
                + "Presiona '1' si quieres CREAR UNA CUENTA. O presiona '2' si quieres INICIAR SESION: \n");
    }
    
    public void registrarse(){
        System.out.println("-- CREAR CUENTA --\n"
                + "RECUERDA VERIFICAR HABER PUESTO CORRECTAMENTE LOS DATOS!");
    }
    
    public void iniciarSesion(){
        System.out.println("-- INICIAR SESION --\n"
                + "Te vamos a pedir que ingreses tu GMAIL y CONTRASEÑA\n");
    }
    
    public void mostrarMensaje(String msj){
        System.out.println(msj);
    }
    
    public void menuDeOpciones() {
        System.out.print("-- MENU DE OPCIONES --\n"
                + "1- Crear un turno\n"
                + "2- Ver turno/s\n"
                + "3- Reprogramar un turno\n"
                + "4- Cancelar un turno\n"
                + "5- Crear servicio\n"
                + "6- Modificar un servicio\n"
                + "7- Eliminar un servicio\n"
                + "Elige una operacion para realizar: ");
    }
    
    public String getInputString(){return s.nextLine();}
    public int getInputInt(){return s.nextInt();}
    public void limpiarBuffed(){s.nextLine();}
    public void cerrarScanner(){s.close();}
    public Scanner getScanner(){return s;}
}
