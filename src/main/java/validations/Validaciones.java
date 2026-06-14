package validations;

import java.util.Scanner;

public class Validaciones {
    
    public String validarNombre(String mensaje, Scanner s) {
        boolean repetir = true;
        String nombre = "";
        while(repetir){
            System.out.println(mensaje);
            nombre = s.nextLine();
            if(nombre.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñÜü\\s'-]{2,50}$")){
                repetir = false;
            } else {
                System.out.println("ERROR: Nombre invalido.");//UTILIZAR EXCEPCION PERSONALIZADA
            }  
        }
        return nombre;
    }
    
    public int validarTelefono(String mensaje, Scanner s) {
        boolean repetir = true;
        int telefono = 0;
        while(repetir){
            System.out.println(mensaje);
            telefono = s.nextInt();
            s.nextLine();
            String telefonoString = String.valueOf(telefono);
            if(telefonoString.matches("^\\+?[1-9]\\d{7,14}$")){
                repetir = false;
            } else {
                System.out.println("ERROR: Numero de telefono invalido invalido.\n"
                        + "TIPs: Asegurate de que tu numero de telefono tenga MINIMO 9 NUMEROS.");//UTILIZAR EXCEPCION PERSONALIZADA
            }  
        }
        return telefono;
    }
    
    public String validarEmail(String mensaje, Scanner s) {
        boolean repetir = true;
        String email = "";
        while(repetir){
            System.out.println(mensaje);
            email = s.nextLine();
            if(email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")){
                repetir = false;
            } else {
                System.out.println("ERROR: Email invalido.\n"
                        + "TIPs: Asegurate de escribir tu correo en el formato correcto, respetando que tenga el '@' y la extension '.com' o la que utilices.");//UTILIZAR EXCEPCION PERSONALIZADA
            }  
        }
        return email;
    }
    
    public String validarContrasena(String mensaje, Scanner s) {
        boolean repetir = true;
        String contrasena = "";
        while(repetir){
            System.out.println(mensaje);
            contrasena = s.nextLine();
            if(contrasena.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&._\\-])[A-Za-z\\d@$!%*?&._\\-]{8,}$")){
                repetir = false;
            } else {
                System.out.println("ERROR: Contrasena invalida.\n"
                        + "TIPs: Asegurate de que tu contrasena: contenga MINIMO 8 CARACTERES, contenga MINIMO UNA letra MINUSCULA y UNA letra MAYUSCULA, contenga AL MENOS UN NUMERO, y que contenga MINIMO UN CARACTER ESPECIAL(@$!%*?&._-).");//UTILIZAR EXCEPCION PERSONALIZADA
            }  
        }
        return contrasena;
    }
    
}

/*
telefono: ^\+?[1-9]\d{7,14}$
emial: ^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$
contrasena: ^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&._\-])[A-Za-z\d@$!%*?&._\-]{8,}$
*/