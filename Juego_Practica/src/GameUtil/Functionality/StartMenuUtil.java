package GameUtil.Functionality;

import Controler.PlayableCharacterControler;
import Models.ChPlayable;
import Models.Player;

import java.util.ArrayList;
import java.util.Scanner;

class StartMenuUtil {

    /**
     * Está función es un flujo que el usuario usará para poder crear nuevos personajes jugables, lo que le pedirá los datos necesarios para la creación de uno.
     * @param player Es el objeto Player recibido en el metodo principal, para poder guardar en el txt los datos del nuevo personaje jugable
     */
    public static void createPlCharacter(Player player){
        Scanner sc=new Scanner(System.in);
        String nombre="";
        String Class="";
        String respuesta;
        boolean confirmation=true;
        System.out.println("Bienvenido a la creación de personaje");
        System.out.println("En este apartado podrá escoger la clase y el nombre de su nuevo personaje");
        while(confirmation){
            System.out.println("Por favor ingrese en la siguiente linea el nombre de su personaje: ");
            nombre=sc.nextLine();
            while(nombre.equals("")){
                System.out.println("Por favor el nombre no puede estar vacío");
                nombre=sc.nextLine();
            }
            System.out.println("Esta seguro de que desea el nombre: "+nombre+"?");
            System.out.println("0. Si");
            System.out.println("1. No");
            respuesta=sc.nextLine();
            while(!respuesta.equals("0") && !respuesta.equals("1")){
                System.out.println("Por favor ingrese los datos correctos");
                System.out.println("Esta seguro de que desea el nombre: "+nombre+"?");
                System.out.println("0. Si");
                System.out.println("1. No");
                respuesta=sc.nextLine();
            }
            if(respuesta.equals("0")){
                System.out.println("El nombre del personaje ha sido elegido: "+ nombre);
                sc.nextLine();
                break;
            }else if(respuesta.equals("1")){
                System.out.println("Se descartó el nombre: "+nombre+"");
                sc.nextLine();
            }
        }
        confirmation=true;
        int estado=1;
        while(confirmation){
            while(estado==1){
                System.out.println("Escriba solo el numero de las siguientes opciones: ");
                System.out.println("0. Warrior");
                System.out.println("1. Rogue");
                System.out.println("2. Mage");
                respuesta=sc.nextLine();
                switch (respuesta) {
                    case "0":
                        Class = "Warrior";
                        estado=2;
                        break;
                    case "1":
                        Class = "Rogue";
                        estado=2;
                        break;
                    case "2":
                        Class = "Mage";
                        estado=2;
                        break;
                    default:
                        System.out.println("Por favor ingrese los datos correctos");
                        sc.nextLine();
                }
            }
            estado=3;
            while(estado==3){
                System.out.println("La clase elegida es: " + Class);
                System.out.println("Esta de acuerdo?");
                System.out.println("0. Si");
                System.out.println("1. No");
                respuesta=sc.nextLine();
                switch (respuesta){
                    case "0":
                        System.out.println("La clase del personaje ha sido elegida: "+ Class);
                        sc.nextLine();
                        estado=1;
                        confirmation=false;
                        break;
                    case "1":
                        System.out.println("Vuelva a escoger una de las opciones: ");
                        sc.nextLine();
                        estado=1;
                        break;
                    default:
                        System.out.println("Por favor ingrese los datos correctos");
                        sc.nextLine();
                        break;
                }
            }
        }
        PlayableCharacterControler.newCharacter(player.getUser(),nombre,Class,1);
    }
    /**
     * Está función es un flujo que el usuario usará para poder elegir el personaje jugable para empezar la aventura de prueba.
     * @param player Se recibe como parametro el objeto Player para poder cargar los personajes que el usuario tiene actualmente
     * @return se retornará el personaje jugable escogido por el usuario
     */
    public static ChPlayable selectCharacter(Player player){
        Scanner sc=new Scanner(System.in);
        int opcion=0;
        int respuesta=-1;
        ArrayList<ChPlayable> plChList;
        plChList=player.getCharacters();
        while(respuesta==-1){
            opcion=0;
            for (ChPlayable ch:plChList) {
                opcion++;
                System.out.println(opcion+" Nombre: "+ch.getName()+" Clase: "+ ch.getCharClass()+ " Nivel: "+ch.getLevel());
            }
            System.out.println("0 "+"Salir de la selección");
            System.out.println("Escriba la opcion: ");
            try{
                respuesta=Integer.parseInt(sc.nextLine());
            }catch(Exception ex){
                System.out.println("Por favor ingrese un numero dentro de las opciones");
                sc.nextLine();
            }
            if(respuesta<=-1 || respuesta>opcion){
                System.out.println("Por favor eliga un numero dentro de las opciones");
                sc.nextLine();
                respuesta=-1;
            }
        }
        opcion=0;
        if(respuesta==0){
            System.out.println("Se procede a salir del modo de seleccion");
            return new ChPlayable("'1","","",0,"",-1);
        }else{
            for (ChPlayable ch:plChList) {
                opcion++;
                if(opcion==respuesta){
                    System.out.println("Se seleccionó al personaje "+" Nombre: "+ch.getName()+" Clase: "+ ch.getCharClass()+ " Nivel: "+ch.getLevel());
                    sc.nextLine();
                    return ch;
                }
            }
        }
        return new ChPlayable("'1","","",0,"",-1);
    }
}
