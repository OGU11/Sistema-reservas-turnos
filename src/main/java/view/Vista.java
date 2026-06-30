package view;

/*PAQUETES IMPORTADOS*/
import java.util.List;
import java.util.Scanner;
import model.Cliente;
import model.Cuenta;

public class Vista {
    Scanner s = new Scanner(System.in);
    /*MENSAJES PARA EL APARTADO PRINCIPAL DEL SISTEMA*/
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
                + "Te vamos a pedir que ingreses tu GMAIL y CONTRASEÑA");
    }
    
    /*MENSAJES PARA EL MENU DE OPCIONES (FUNCIONALIDADES DEL SISTEMA)*/
    public void menuDeOpciones() {
        System.out.print("-- MENU DE OPCIONES --\n"
                + "1- Modificar datos de mi cuenta\n"
                + "2- Eliminar una cuenta\n"
                + "3- Buscar una cuenta\n"
                + "4- Mostrar listado de clientes registrados\n"
                + "5- Crear servicio\n"
                + "6- Modificar un servicio\n"
                + "7- Eliminar un servicio\n"
                + "8- Crear un turno\n"
                + "9- Ver turno/s\n"
                + "10- Reprogramar un turno\n"
                + "11- Cancelar un turno\n"
                + "12- Salir\n"
                + "Elige una operacion para realizar: ");
    }
    
    /*MENSAJES PARA EL APARTADO DE MODIFICAR DATOS*/
    public void mensajeModificarDatos(){
        System.out.print("-- MODIFICAR DATOS --\n"
                + "1- Modificar nombre\n"
                + "2- Modificar telefono\n"
                + "3- Modificar email\n"
                + "4- Modificar contrasena\n"
                + "Indica que datos quieres modificar: ");
    }
    
    public void mensajeModificarNombre(){
        System.out.print("-- MODIFICAR NOMBRE --\n");
    }
    
    public void mensajeModificarTelefono(){
        System.out.print("-- MODIFICAR TELEFONO --\n");
    }
    
    public void mensajeModificarEmail(){
        System.out.print("-- MODIFICAR EMAIL --\n");
    }
    
    public void mensajeModificarContrasena(){
        System.out.print("-- MODIFICAR CONTRASENA --\n");
    }
    
    /*MENSAJES PARA EL APARTADO DE ELIMINAR CUENTA*/
    public void mensajeEliminarCuenta(){
        System.out.print("-- ELIMINAR UNA CUENTA --\n"
                + "CUIDADO: Ten en cuenta que esta es una decision IRREVERSIBLE. Al ELIMINAR UNA CUENTA se eliminara automaticamente el CLIENTE ASOCIADO a la misma.\n"
                + "ID de la cuenta a eliminar: ");
    }
    
    /*MENSAJES PARA EL APARTADO DE BUSCAR UNA CUENTA*/
    public void mensajeBuscarUnaCuenta(){
        System.out.print("-- BUSCAR CUENTA --\n");
    }
    
    public void mostrarDatosCuentaEncontrada(List<Cuenta> cuentaEncontrada){
        for(Cuenta cuenta : cuentaEncontrada){
            System.out.println("ID Cuenta: " + cuenta.getId() +"\n"
                             + "Contrasena: " + cuenta.getContrasena() +"\n"
                             + "ID Cliente: " + cuenta.getCliente().getId() + "\n"
                             + "Nombre: " + cuenta.getCliente().getNombre() + "\n"
                             + "Email: " + cuenta.getCliente().getEmail() + "\n"
                             + "Telefono: " + cuenta.getCliente().getTelefono() + "\n"
            );
        }
    }
    
    /*MENSAJES PARA EL APARTADO DE TRAER LISTADO DE CLIENTES*/
    public void mensajeVerClientes(){
        System.out.print("-- MOSTRAR LISTADO DE CLIENTES REGISTRADOS --\n");
    }
    
    public void mostrarClientes(List<Cliente> listaClientes){
        for (Cliente cliente : listaClientes) {
            System.out.println("ID Cliente: " + cliente.getId() + "\n"
                            + "Nombre: " + cliente.getNombre() + "\n"
                            + "Email: " + cliente.getEmail() + "\n"
                            + "Telefono: " + cliente.getTelefono() + "\n");
        }
    }
    
    /*METODOS UTILES*/
    public void mostrarMensaje(String msj){System.out.println(msj);}
    public String getInputString(){return s.nextLine();}
    public int getInputInt(){return s.nextInt();}
    public void limpiarBuffed(){s.nextLine();}
    public void cerrarScanner(){s.close();}
    public Scanner getScanner(){return s;}
}
