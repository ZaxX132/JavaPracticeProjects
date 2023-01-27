package GameUtil;
import java.util.ArrayList;
import java.util.Scanner;
import Controler.PlayerControler;
import Models.*;

public class PapiMode {
    /**
     * Esta función sirve para mostrar la interfaz de texto del modo PAPI
     * en el que se mostrarán las siguientes opciones
     * Salir del modo papi, ver usuarios creados, eliminar usuario y modificar usuario
     */
    public static void papimodeUI(){
        ArrayList<Player> playerList;
        String respuesta;
        int estado=0;
        Scanner sc=new Scanner(System.in);
        while(estado==0){
            System.out.println("===============================");
            System.out.println("Ahora se encuentra en el modo PAPI");
            System.out.println("===============================");
            System.out.println("Por favor escriba solo los numeros de las opciones");
            System.out.println("0. Salir del modo papi");
            System.out.println("1. Ver usuarios creados");
            System.out.println("2. Eliminar usuario");
            System.out.println("3. Modificar usuario");

            respuesta=sc.nextLine();

            switch (respuesta) {
                case "1":
                    verUsuariosUI();
                    break;
                case "2":
                    eliminarUsuarioUI();
                    break;
                case "3":
                    modificarUsuarioUI();
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
    }

    /**
     * Esta función le solicitará al administrador del modo papi si desea mostrar las contraseñas.
     * Luego procederá a mostrar a los usuarios creados.
     */
    public static void verUsuariosUI(){
        ArrayList<Player> playerList;
        boolean estado=true;
        String respuesta;
        Scanner sc=new Scanner(System.in);
        while(estado){
            System.out.println("Desea mostrar las contraseñas?");
            System.out.println("0. Si");
            System.out.println("1. No");
            respuesta = sc.nextLine();

            switch (respuesta){
                case "0":
                    playerList=PlayerControler.loadPlayers();
                    for (Player pla:playerList) {
                        System.out.println(pla.getUser()+" "+ pla.getPassword());
                    }
                    estado=false;
                    break;
                case "1":
                    playerList=PlayerControler.loadPlayers();
                    for (Player pla:playerList) {
                        System.out.println(pla.getUser());
                    }
                    estado=false;
                default:
                    System.out.println("Por favor ingrese los datos correctos");
                    break;
            }
        }


        System.out.println("Se procede a listar a los usuarios creados");

    }

    /**
     * Esta función solicitará al administrador del modo papi que ingrese el usuario que desee eliminar.
     * Luego se pedirá una confirmación si es que el usuario existe.
     * finalmente se eliminará y saldrá un mensaje de que la eliminación fue realizada con éxito.
     */
    public static void eliminarUsuarioUI(){
        ArrayList<Player> playerList;
        String respuesta="";
        boolean estado=true;
        Scanner sc=new Scanner(System.in);
        String userExist="-1";
        playerList=PlayerControler.loadPlayers();
        System.out.println("Por favor ingrese el usuario que desea eliminar");
        String userEl;
        userEl = sc.nextLine();
        for (Player pla:playerList) {
            if(userEl.equals(pla.getUser())){
                while(estado){
                    System.out.println("Esta seguro que desea eliminar al usuario: "+ pla.getUser()+ "?");
                    System.out.println("0. Si");
                    System.out.println("1. No");
                    respuesta=sc.nextLine();
                    switch (respuesta){
                        case "0":
                            PlayerControler.deletePlayer(pla.getUser());
                            System.out.println("Se eliminó con éxito al usuario: " + pla.getUser());
                            userExist="1";
                            estado=false;
                            break;
                        case "1":
                            System.out.println("Se procedio a cancelar el proceso");
                            userExist="1";
                            estado=false;
                            break;
                        default:
                            System.out.println("Por favor ingrese los datos corrects");
                    }
                }
            }
        }
        if (userExist.equals("-1")){
            System.out.println("No se encontro al usuario que deseas eliminar");
        }
    }

    /**
     * Esta función sirve para modificar a un usuario especifico. Se le solicitará al administardor ingresar el nombre de usuario que desee modificar.
     * Luego tendrá que seleccionar si se desea modificar el usuario o la contraseña.
     */
    public static void modificarUsuarioUI(){
        ArrayList<Player> playerList;
        playerList=PlayerControler.loadPlayers();
        String estado="0";
        String usuModi="";
        String respuesta="";
        Scanner sc=new Scanner(System.in);
        System.out.println("Por favor, ingrese el usuario de la cuenta que desea modificar");
        usuModi=sc.nextLine();
        for (Player pla:playerList) {

            if (usuModi.equals(pla.getUser())){
                estado="1";
                System.out.println("Esta seguro de modificar el usuario:" + pla.getUser());
                System.out.println("0. Si");
                System.out.println("1. No");
                respuesta=sc.nextLine();
                while(!respuesta.equals("0") && !respuesta.equals("1")){
                    System.out.println("Por favor ingrese los datos corrects");
                    System.out.println("Esta seguro de modificar el usuario:" + pla.getUser());
                    System.out.println("0. Si");
                    System.out.println("1. No");
                    respuesta=sc.nextLine();
                }
                if(respuesta.equals("0")){
                    System.out.println("Que es lo que desea modificar?: ");
                    System.out.println("0. Contraseña");
                    System.out.println("1. Nombre de Usuario");
                    respuesta=sc.nextLine();
                    while(!respuesta.equals("0") && !respuesta.equals("1")){
                        System.out.println("Por favor ingrese los datos corrects");
                        System.out.println("Que es lo que desea modificar?: ");
                        System.out.println("0. Contraseña");
                        System.out.println("1. Nombre de Usuario");
                        System.out.println("2. Deseo salir");
                        respuesta=sc.nextLine();
                    }
                    if(respuesta.equals("0")){
                        String cambioContra="";
                        String confiContra="";
                        System.out.println("Por favor introduzca la nueva contraseña");
                        cambioContra=sc.nextLine();
                        System.out.println("Por favor confirme la nueva contraseña");
                        confiContra=sc.nextLine();
                        if(cambioContra.equals(confiContra)){
                            estado="1";
                            pla.setPassword(cambioContra);
                            System.out.println("La contraseña ha sido modificada con éxito");
                        }else{
                            estado="1";
                            System.out.println("Las contraseñas no coinciden");
                            System.out.println("Se procede a salir");
                        }
                        break;

                    }else if(respuesta.equals("1")){
                        String cambioUsuario="";
                        String confiUsuario="";
                        System.out.println("Por favor introduzca el nuevo usuario");
                        cambioUsuario=sc.nextLine();
                        System.out.println("Por favor confirme que desea el usuario escribiendolo nuevamente");
                        confiUsuario=sc.nextLine();
                        if(cambioUsuario.equals(confiUsuario)){
                            estado="1";
                            pla.setUser(cambioUsuario);
                            System.out.println("El usuario se a cambiado con éxito");
                        }else {
                            estado="1";
                            System.out.println("Los usuarios no coinciden");
                            System.out.println("Se procede a salir");
                        }
                    }

                }else if(respuesta.equals("1")){
                    estado="1";
                    System.out.println("Se procede a salir");
                    break;
                }

            }
        }
        PlayerControler.savePlayers(playerList);
        if(estado.equals("0")){
            System.out.println("No se encontró al usuario que desea modificar");
        }
        }
    }


