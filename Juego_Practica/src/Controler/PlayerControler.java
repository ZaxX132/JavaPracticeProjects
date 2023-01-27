package Controler;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import Models.Player;
public class PlayerControler {

    /**
     * Esta función sirve para crear un nuevo jugador, para lo cual los parámetros a ingresar son el usuario y la contraseña.
     * Si todo ocurre con normalidad se escribirá al final del txt que almacena a los jugadores.
     *
     * @param user Es el valor que el jugador le quiere asignar a su usuario.
     * @param password Es el valor que el jugador le quiere colocar a su contraseña.
     * */
    public static void newPlayer(String user, String password) {
        try {
            String ruta="src/datosTXT/Players.txt";
            FileWriter fw = new FileWriter(ruta, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.newLine();
            bw.write(user + "," + password);
            bw.close();
        } catch (IOException ex) {
            System.out.println("Un error ha ocurrido al crear un personaje: " + ex);
        }
    }
    /**
     * Esta función sirve para cargar la lista de todos los jugadores registrados en el juego.
     *
     * @return retornará una lista con los datos de los jugadores creados.
     * */
    public static ArrayList<Player> loadPlayers() {
        String username;
        String[] resultadoSplit;
        String password;
        String linea;
        ArrayList<Player> playerList = new ArrayList<>();
        try {
            FileReader fr = new FileReader("src/datosTXT/Players.txt");
            BufferedReader br = new BufferedReader(fr);
            while ((linea = br.readLine()) != null) {
                resultadoSplit = linea.split(",");
                username = resultadoSplit[0];
                password = resultadoSplit[1];
                playerList.add(new Player(username, password));
            }
            br.close();
        } catch (IOException ex) {
            System.out.println("Un error ha ocurrido al cargar el archivo: " + ex);
        }
        return playerList;
    }
    /**
     * Esta función sirve para eliminar los datos de un jugador en especifico.
     * @param eluser se trata del user del jugador que se quiere eliminar.
     * */
    public static void deletePlayer(String eluser){
        ArrayList<Player> playerList;
        playerList=loadPlayers();
        for(Player pla:playerList){
            if(pla.getUser().equals(eluser)){
                playerList.remove(pla);
                break;
            }
        }
        savePlayers(playerList);

    }
    /**
     * Está función sirve para guardar una lista de jugadores en el txt que almacena a todos los jugadores.
     * La lista se sobreescribirá por lo que tiene que tener previamente los datos existentes en el txt que posteriormente serán modificados y guardados.
     *
     * @param playerList  Se trata de la lista de jugadores que se sobreescribirá en el txt que almacena a todos los jugadores.
     * */
    public static void savePlayers(ArrayList<Player> playerList){
        try {
            FileWriter fw = new FileWriter("src/datosTXT/Players.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            int contador=0;
            for(Player pla:playerList){
                try {
                    if (contador==0){
                        bw.write( pla.getUser() + "," + pla.getPassword());
                        contador++;
                    }else{
                        bw.newLine();
                        bw.write( pla.getUser() + "," + pla.getPassword());
                    }
                }catch (IOException ex){
                    System.out.println("Error a la hora de escribir los jugadores actualizados: "+ex);
                }
            }
            bw.close();
        } catch (IOException ex) {
            System.out.println("No se pudo escribir la lista de usuarios actualizada: "+ex);
        }
    }
    public static Boolean comprobarArchivo(){
        return true;
    }
    public static Boolean comprovarUsuario(ArrayList<Player> playerList,String user){
        for (Player pla:playerList) {
            if(pla.getUser().equals(user)){
                System.out.println("El usuario ya existe");
                return true;
            }
        }
        return false;
    }
}

