package GameUtil.Functionality;

import Models.ChHostile;
import Models.ChPlayable;

import java.util.Scanner;

class Stages {
    /**
     * La función startGame tendrá el objetivo de ser el juego ya iniciado, por lo que el usuario habrá tenido previamente que escoger un personaje jugable.
     * @param chPlayable Es el personaje jugable que el usuario escogió para iniciar la aventura de prueba
     */
    public static void startGame(ChPlayable chPlayable){
        ChHostile hostile=new ChHostile("1","Pikachu","Rogue",1,70);
        System.out.println("Esta es la historia de: " + chPlayable.getName());
        System.out.println("Un "+ chPlayable.getCharClass() + " que realizará una pelea de exibición para probar el funcionamiento del juego");
        if(hoguera(chPlayable)){
            FightM.peleaPrueba(chPlayable,hostile);

        }else{
            System.out.println("Has preferido abandonar la partida");
        }

    }
    /**
     * La función hoguera servirá para que el usuario pueda ver las estadísticas del personaje jugable escogido.
     * Además, aquí es donde se decidirá si el personaje jugable va a pelear o si decide abandonar
     * @param chp es el personaje jugable que se ha escogido
     * @return retorna un boolean, que significará si el usuario ha decidido pelear o abandonar
     */
    public static boolean hoguera(ChPlayable chp){
        String respuesta;
        System.out.println("Estás en la preparación para el siguiente combate");
        System.out.println("Aquí por ahora podrás ver tus estadísticas, decidir abandonar o empezar la pelea");
        Scanner sc=new Scanner(System.in);
        while(true){
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
                    sc.nextLine();
            }
        }
    }
}
