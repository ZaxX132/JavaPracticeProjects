package GameUtil.Functionality;
import Models.ChHostile;
import Models.ChPlayable;
import Models.Hability;

import java.util.Scanner;
public class HabilitiesM {
    public static Hability selectHability(ChPlayable chp,int mana){
        Hability habi= new Hability("0");
        Scanner sc=new Scanner(System.in);
        int options=1;
        int select;
        while (true){
            System.out.println("===============");
            System.out.println("Selección de habilidades");
            System.out.println("===============");
            for (Hability hab:chp.getHabilities()) {
                System.out.println(options+". "+hab.getName()+ "mana: "+ hab.getManaUse());
                options++;
            }
            System.out.println("0. Salir de selección");
            System.out.println("===============");
            try{
                select=Integer.parseInt(sc.nextLine());
                if(select<=options && select>=0){
                    options=1;
                    if(select==0){
                        return habi;
                    }
                    for (Hability hab:chp.getHabilities()) {
                        if(select==options){
                            if(hab.getManaUse()>mana){
                                System.out.println("No tienes el mana suficiente");
                                sc.nextLine();
                                break;
                            }
                            return hab;
                        }
                        options++;
                    }
                }
            }catch(Exception ex){
                System.out.println("Por favor, escriba solo números");
            }
        }
    }
    public static double useHability(Hability hab,ChPlayable chp, ChHostile chh){
        Scanner sc=new Scanner(System.in);
        double totalDamage;
        double prob=hab.getProbEffect();
        double min=0.01;
        double range=prob-min+1;
        switch (hab.getEstatEscalado()) {
            case "str":
                totalDamage = hab.getBaseDamage() + hab.getEscalado() * chp.getStr();
                break;
            case "agi":
                totalDamage = hab.getBaseDamage() + hab.getEscalado() * chp.getAgi();
                break;
            case "int":
                totalDamage = hab.getBaseDamage() + hab.getEscalado() * chp.getInte();
                break;
            default:
                totalDamage = hab.getBaseDamage();
                break;
        }
        double acierto=(Math.random()*range)+min;
        if (acierto<=prob){
            switch (hab.getEffect()){
                case "fire":
                    System.out.println(chh.getName()+" se ha quemado");
                    break;
                case "blood":
                    System.out.println(chh.getName()+" está sangrando");
                    break;
                case "stun":
                    System.out.println(chh.getName()+ " está noqueado");
                    break;
            }
        }

        System.out.println("Haz usado la habilidad "+ hab.getName());
        System.out.println("Haz realizado "+ totalDamage);
        sc.nextLine();
        return totalDamage;
    }
    public static Hability learnHability(Hability hab1, Hability hab2){
        Scanner sc=new Scanner(System.in);
        System.out.println("Elije la habilidad que deseas aprender");
        String respuesta="";
        while(true){

            System.out.println("1. "+ hab1.getName() + "desc: " + hab1.getDescripcion());
            System.out.println("2. "+ hab2.getName() + "desc: " + hab2.getDescripcion());
            respuesta=sc.nextLine();
            switch (respuesta){
                case "1":
                    System.out.println("Haz elegido la habilidad: " +hab1.getName());
                    return hab1;
                case "2":
                    System.out.println("Haz elegido la habilidad: " +hab2.getName());
                    return hab2;
                default:
                    System.out.println("Escriba solo el numero de la opcion");
            }
        }

    }

}
