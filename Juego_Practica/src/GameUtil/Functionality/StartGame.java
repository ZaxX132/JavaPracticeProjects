package GameUtil.Functionality;
import Models.*;
import Controler.*;
import java.util.ArrayList;
import java.util.Scanner;
public class StartGame{

    /**
     * Está función es el menú principal del juego cuando ya se tiene un usuario que haya iniciado sesión.
     * Además la información de los personajes jugables se guardaran en la carpeta PlayersCharacters y tendrán como nombre "nombredeUsuario" + ".txt".
     * Se permite empezar la partida, eligiendo a un personaje jugable y también se permite crear un nuevo personaje jugable.
     * También se puede salir del programa desde aquí.
     * Las opciones momentaneamente no sirven.
     * @param player Este parámetro está pensado para que se use con un objeto Player el cual será retornado por el metodo Login del juego.
     *               El objeto Player tendrá almacenado a los personajes jugables así como los datos del usuario que haya iniciado sesión.
     */
    public static void startGameMenu(Player player){
        String respuesta;
        int estado=0;
        Scanner sc=new Scanner(System.in);
        while(estado==0){
            player.setCharacters(PlayableCharacterControler.loadCharacter(player.getUser()));
            System.out.println("=================================");
            System.out.println("BIENVENIDO: " + player.getUser());
            System.out.println("=================================");
            System.out.println("Este es el menu principal del juego que no tiene nombre");
            System.out.println("1. Empezar partida");
            System.out.println("2. Nuevo personaje");
            System.out.println("3. Opciones");
            System.out.println("0. Salir del juego");

            respuesta=sc.nextLine();

            switch (respuesta) {
                case "2":
                    System.out.println("Ingresando a la creacion de personaje");
                    StartMenuUtil.createPlCharacter(player);
                    break;
                case "1":
                    System.out.println("Empezando partida");
                    ChPlayable elegido = StartMenuUtil.selectCharacter(player);
                    if(!elegido.getName().equals("")){
                        Stages.startGame(elegido);
                        PlayableCharacterControler.savePlCharacter(player.getCharacters(),player.getUser());
                    }
                    break;
                case "3":
                    System.out.println("Ingresando a las opciones");
                    break;
                case "0":
                    System.out.println("Ha salido exitosamente");
                    estado = 1;
                    break;
                default:
                    break;
            }
        }

    }


}
