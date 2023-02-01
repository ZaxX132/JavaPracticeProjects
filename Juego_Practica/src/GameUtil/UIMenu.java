package GameUtil;
import java.util.Scanner;
import Models.*;
public class UIMenu {
    /**
     * Este es el menú de entrada del juego, en este metodo se espera que el usuario inicie sesión, o en su defecto ingrese a las opciones de crear cuenta o ingresar al modo papi.
     * @return El objetivo principal es que se retorne un objeto Player si es que el usuario logró iniciar sesión o sino retornará un objeto player vacío.
     */
    public static Player showMenu(){
        Player startgamePlayer=new Player("","");
        String respuesta;
        int estado=0;
        Scanner sc=new Scanner(System.in);
        while(estado==0){
            System.out.println("===============================");
            System.out.println("Bienvenidos al juego sin nombre");
            System.out.println("===============================");
            System.out.println("Por favor escriba solo los numeros de las opciones");
            System.out.println("0. Salir del programa");
            System.out.println("1. Iniciar sesion");
            System.out.println("2. Crear cuenta");
            System.out.println("3. Modo Papi");

            respuesta=sc.nextLine();

            switch (respuesta) {
                case "1":
                    startgamePlayer = Login.login();
                    if (startgamePlayer.getUser().equals("")) {
                        System.out.println("Ha sobrepasado el limite de intentos");
                        estado = 1;
                    } else {
                        return startgamePlayer;
                    }
                    break;
                case "2":
                    System.out.println("Por favor proceda con el registro de su cuenta");
                    Login.createPlayer();
                    break;
                case "3":
                    System.out.println("Ha ingresado al modo papi");
                    PapiMode.papimodeUI();
                    break;
                case "0":
                    System.out.println("Ha salido exitosamente");
                    estado = 1;
                    break;
                default:
                    System.out.println("Por favor ingrese una de las opciones existentes");
                    break;
            }
        }

        return startgamePlayer;
    }
}
