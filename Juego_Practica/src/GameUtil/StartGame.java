package GameUtil;
import Models.*;
import Controler.*;
import java.util.ArrayList;
import java.util.Scanner;
public class StartGame {

    public static void startGameMenu(Player player){
        String respuesta;
        int estado=0;
        player.setCharacters(PlayableCharacterControler.loadCharacter(player.getUser()));
        Scanner sc=new Scanner(System.in);
        while(estado==0){
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
                    createPlCharacter(player);
                    player.setCharacters(PlayableCharacterControler.loadCharacter(player.getUser()));

                    break;
                case "1":
                    System.out.println("Empezando partida");
                    ChPlayable elegido = selectCharacter(player);
                    if(elegido.getName().equals("")){

                    }else{
                        startGame(elegido);
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
    public static void createPlCharacter(Player player){
        Scanner sc=new Scanner(System.in);
        String nombre="";
        String Class="";
        String respuesta="";
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
                confirmation=false;
                respuesta="";
                break;
            }else if(respuesta.equals("1")){
                System.out.println("Se descartó el nombre: "+nombre+"");
                continue;
            }
        }
        confirmation=true;
        while(confirmation){
            System.out.println("Ahora procederá a escoger la clase de su personaje");
            System.out.println("Escriba solo el numero de las siguientes opciones: ");
            System.out.println("0. Warrior");
            System.out.println("1. Rogue");
            System.out.println("2. Mage");
            respuesta=sc.nextLine();
            while(!respuesta.equals("0") && !respuesta.equals("1") && !respuesta.equals("2")){
                System.out.println("Por favor ingrese los datos correctos");
                System.out.println("Escriba solo el numero de las siguientes opciones: ");
                System.out.println("0. Warrior");
                System.out.println("1. Rogue");
                System.out.println("2. Mage");
                respuesta=sc.nextLine();
            }
            if(respuesta.equals("0")){
                Class="Warrior";
            }
            else if(respuesta.equals("1")){
                Class="Rogue";
            }else if(respuesta.equals("2")){
                Class="Mage";
            }
            System.out.println("La clase elegida es: " + Class);
            System.out.println("Esta de acuerdo?");
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
                System.out.println("La clase del personaje ha sido elegida: "+ Class);
                confirmation=false;
                respuesta="";
                break;
            }else if(respuesta.equals("1")){
                System.out.println("Vuelva a escoger una de las opciones: ");
                continue;
            }
        }
        PlayableCharacterControler.newCharacter(player.getUser(),nombre,Class,1);
    }
    public static void startGame(ChPlayable chPlayable){
        ChHostile hostile=new ChHostile("1","Pikachu","Rogue",1,70);
        System.out.println("Esta es la historia de: " + chPlayable.getName());
        System.out.println("Un "+ chPlayable.getCharClass() + " que realizará una pelea de exibición para probar el funcionamiento del juego");
        if(hoguera(chPlayable)){
            peleaPrueba(chPlayable,hostile);
        }else{
            System.out.println("Has preferido abandonar la partida");
        }

    }
    public static ChPlayable selectCharacter(Player player){

        Scanner sc=new Scanner(System.in);
        int opcion=0;
        int respuesta=-1;
        ArrayList<ChPlayable> plChList;
        plChList=player.getCharacters();
        for (ChPlayable ch:plChList) {
            opcion++;
            System.out.println(opcion+" Nombre: "+ch.getName()+" Clase: "+ ch.getCharClass()+ " Nivel: "+ch.getLevel());
        }
        System.out.println("0 "+"Salir de la selección");
        System.out.println("Escriba la opcion: ");
        while(respuesta==-1){
            try{
                respuesta=Integer.parseInt(sc.nextLine());
            }catch(Exception ex){
                System.out.println("Por favor ingrese un numero dentro de las opciones");
            }
            if (respuesta>=0 && respuesta<=9){

            }else if(respuesta!=-1){
                System.out.println("Por favor eliga un numero dentro de las opciones");
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
                    return ch;
                }
            }
        }
        return new ChPlayable("'1","","",0,"",-1);
    }
    public static ChPlayable peleaPrueba(ChPlayable chara,ChHostile hostile){
        Scanner sc=new Scanner(System.in);
        String respuesta="";
        double Atackdamage=chara.getStr()*2.5;
        double hostileAtackD=hostile.getStr()*2.5;
        double speed=chara.getAgi();
        double hostileSpeed=hostile.getAgi();
        double armor=chara.getArmor();
        double hostileArmor= hostile.getArmor();
        double vidaActual=chara.getHp();
        double hostileHp=hostile.getHp();
        Boolean estado=true;
        System.out.println("Te has encontrado al enemigo: "+ hostile.getName()+" de nivel " + hostile.getLevel());
        while(estado){
            System.out.println("Hp: "+ vidaActual);
            System.out.println("Mana: "+ chara.getMana());
            System.out.println("Enemy Hp: "+ hostileHp);
            System.out.println("0. Atacar");
            System.out.println("1. Habilidades");
            System.out.println("2. Objetos");
            respuesta=sc.nextLine();
            switch (respuesta){
                case "0":
                    if(speed>=hostileSpeed){
                        hostileHp=hostileHp-Atackdamage;
                        System.out.println("Has realizado "+ Atackdamage);
                        sc.nextLine();
                        if(hostileHp<=0){
                            System.out.println("El enemigo ha sido derrotado");
                            chara.setExperienceBar(chara.getExperienceBar()+hostile.getExperience());
                            System.out.println("Has ganado "+hostile.getExperience()+" de experiencia");
                            sc.nextLine();
                            return chara;
                        }else{
                            vidaActual=vidaActual-hostileAtackD;
                            System.out.println("Te han realizado "+ hostileAtackD);
                            sc.nextLine();
                            if(vidaActual<=0){
                                System.out.println("Te han matado");
                                sc.nextLine();
                                return chara;
                            }
                        }

                    }else if(speed<hostileSpeed){
                        vidaActual=vidaActual-hostileAtackD;
                        System.out.println("Te han realizado "+ hostileAtackD);
                        sc.nextLine();
                        if(vidaActual<=0){
                            System.out.println("Te han matado");
                            return chara;
                        }else{
                            hostileHp=hostileHp-Atackdamage;
                            System.out.println("Has realizado "+ Atackdamage);
                            sc.nextLine();
                            if(hostileHp<=0){
                                System.out.println("El enemigo ha sido derrotado");
                                chara.setExperienceBar(chara.getExperienceBar()+hostile.getExperience());
                                System.out.println("Has ganado "+hostile.getExperience()+" de experiencia");;
                                sc.nextLine();
                                return chara;
                            }
                        }
                    }
                    System.out.println("");
                    break;
                case "1":
                    System.out.println("No tienes habilidades");
                    break;
                case "2":
                    System.out.println("No tienes objetos");
                    break;
                default:
                    System.out.println("Por favor ingrese una opción correcta");
            }
        }

        return chara;
    }
    public static boolean hoguera(ChPlayable chp){
        String respuesta="";
        Boolean estado=true;
        System.out.println("Estás en la preparación para el siguiente combate");
        System.out.println("Aquí por ahora podrás ver tus estadísticas, decidir abandonar o empezar la pelea");
        Scanner sc=new Scanner(System.in);
        while(estado){
            System.out.println("====================");
            System.out.println("0. Ver estadísticas");
            System.out.println("1. Empezar pelea");
            System.out.println("2. Abandonar partida");
            respuesta=sc.nextLine();
            switch (respuesta){
                case "0":
                    System.out.println("Clase: "+chp.getCharClass());
                    System.out.println("Nivel: "+ chp.getLevel());
                    System.out.println("Barra de EXP: "+ chp.getExperienceBar());
                    System.out.println("Vida: "+chp.getHp());
                    System.out.println("Mana: "+ chp.getMana());
                    System.out.println("Armadura: "+chp.getArmor());
                    System.out.println("Str: "+chp.getStr());
                    System.out.println("Agi: "+ chp.getAgi());
                    System.out.println("Int: "+ chp.getInte());
                    System.out.println("Enter para continuar");
                    sc.nextLine();
                    break;
                case "1":
                    return true;
                case "2":
                    return false;
                default:
                    System.out.println("Ingrese una opcion correcta");
            }
        }
        return false;
    }
}
