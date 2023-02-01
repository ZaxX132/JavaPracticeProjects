package GameUtil;
import GameUtil.Functionality.StartGame;
import Models.*;
import Controler.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Login {
    /**
     * Está función sirve para solicitarle al jugador su usuario y contraseña.
     * El jugador tiene en total 3 intentos, una vez la cantidad de intentos llegue a 0 se retornará un usuario vacío.
     * Se cargará la lista de jugadores para realizar la comparación de inicio de sesión.
     * @return retornará un objeto Player al jugador con el cual se inició sesión o si se acabaron los intentos retornará un jugador con valores vacíos.
     * */
    public static Player login(){
        ArrayList<Player> playerList;
        playerList=Controler.PlayerControler.loadPlayers();
        Player plLogin=new Player("","");
        Scanner sc=new Scanner(System.in);
        String user;
        String password;
        int intentos=3;
        while(intentos>=1){
            System.out.println("Su numero de intentos restantes es: " + intentos);
            System.out.println("Por favor ingrese su username: ");
            user=sc.nextLine();
            System.out.println("Por favor ingrese su password");
            password=sc.nextLine();
            for(Player player:playerList){
                if(player.getUser().equals(user)&&player.getPassword().equals(password)){
                    plLogin=player;
                    return plLogin;
                }
            }
            intentos--;
        }
        return plLogin;
    }
    /**
     * Está función sirve para solicitarle al jugador la creación de un nuevo usuario, lo que creará un nuevo objeto Player.
     * Para lo cual se le pedirá escribir el user name que desee y se buscará si aún no existe.
     * Si el user name no está registrado entonces le pedirá que ingrese la contraseña
     * Si todo ocurre con normalidad, se creará al nuevo usuario.
     * */
    public static void createPlayer(){
        String respuesta="";
        int estado=0;
        boolean existe=false;
        ArrayList<Player> existingPlayers;
        existingPlayers=PlayerControler.loadPlayers();
        Scanner sc=new Scanner(System.in);
        String newUser="";
        String newPass="";
        while(estado==0){
            System.out.println("Por favor ingrese el nombre de usuario que desee");
            newUser=sc.nextLine();

            for(Player play:existingPlayers){
                if(play.getUser().equals(newUser) || newUser.equals("")){
                    existe=true;
                }
            }
            if(existe){
                System.out.println("El usuario que ha elegido ya existe o no esta permitido");
                System.out.println("Desea continuar con la creacion de usuario?");
                System.out.println("0. Si");
                System.out.println("1. No");
                respuesta=sc.nextLine();
                while(!respuesta.equals("0") && !respuesta.equals("1")){
                    System.out.println("Por favor ingrese los datos correctos");
                    System.out.println("Desea continuar con la creacion de usuario?");
                    System.out.println("0. Si");
                    System.out.println("1. No");
                    respuesta=sc.nextLine();
                }
                if(respuesta.equals("0")){
                    existe=false;
                    continue;
                }else{
                    System.out.println("Regresamos al menu");
                    break;
                }
            }else{
                estado=1;
                while(estado==1){
                    System.out.println("Esta seguro que desea el usuario: "+ newUser+"?");
                    System.out.println("0. Si");
                    System.out.println("1. No");
                    respuesta=sc.nextLine();
                    if(respuesta.equals("0")){
                        System.out.println("Por favor ingrese su password");
                        newPass=sc.nextLine();
                        PlayerControler.newPlayer(newUser,newPass);
                        System.out.println("Se ha creado su cuenta con exito");
                        break;
                    }else if(respuesta.equals("1")){
                        estado=0;
                    }else{
                        System.out.println("Por favor ingrese una opcion correcta");
                    }
                }

            }
        }
    }

}
