import GameUtil.Functionality.StartGame;
import Models.*;
import GameUtil.*;
public class Main {
    public static void main(String[] args) {
        Player selectPlayer;
        selectPlayer=UIMenu.showMenu();
        if(selectPlayer.getUser()==""){
            System.out.println("El juego procede a cerrarse.");
        }else{
            System.out.println("Bienvenido al juego: "+selectPlayer.getUser());
            StartGame.startGameMenu(selectPlayer);
        }
    }
}
