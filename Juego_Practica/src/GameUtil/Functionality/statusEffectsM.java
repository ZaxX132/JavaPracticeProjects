package GameUtil.Functionality;

import Models.Character;
import java.util.Scanner;

public class statusEffectsM {
    public static double statusEffect(Character chh){

        Scanner sc=new Scanner(System.in);
        double efecto=0;
        switch (chh.getStatus()){
            case "burned":
                efecto=statusBurned(chh);
                efecto=Math.round(efecto*100)/100;
                if(efecto>1){
                    System.out.println(chh.getName()+" reciente la quemadura y recibe "+ efecto +" de daño");
                }
                sc.nextLine();
                return efecto;
            case "bleeding":
                efecto=statusBleed(chh);
                efecto=Math.round(efecto*100)/100;
                if(efecto>1) {
                    System.out.println(chh.getName() + " está sangrando y recibe "+ efecto +" de daño");
                }
                sc.nextLine();
                return efecto;
            case "stunned":
                efecto=statusStunned(chh);
                if(efecto<1) {
                    System.out.println(chh.getName() + " está noqueado y no se puede mover");
                }
                sc.nextLine();
                return efecto;
            case "frozen":
                efecto=statusFrozen(chh);
                if(efecto<1) {
                    System.out.println(chh.getName() + " está congelado y no se puede mover");
                }
                sc.nextLine();
                return efecto;
            default:
                return 1;
        }

    }
    public static double statusBurned(Character ch){
        if(ch.getStatusTurn()>0){
            ch.setStatusTurn(ch.getStatusTurn()-1);
            return ch.getHp()*0.05;
        }else{
            ch.setStatus("");
        }
        System.out.println(ch.getName()+ " ya no está quemado");
        return 1;
    }
    public static double statusBleed(Character ch){
        if(ch.getStatusTurn()>0){
            ch.setStatusTurn(ch.getStatusTurn()-1);
            return ch.getHp()*0.03;
        }else{
            ch.setStatus("");
        }
        System.out.println(ch.getName()+ " ya no está sangrado");
        return 1;
    }
    public static double statusStunned(Character ch){
        if(ch.getStatusTurn()>0){
            ch.setStatusTurn(ch.getStatusTurn()-1);
            return -1;
        }else{
            ch.setStatus("");
        }
        System.out.println(ch.getName()+ " se liberó del Stun");
        return 1;
    }
    public static double statusFrozen(Character ch){
        if(ch.getStatusTurn()>0){
            ch.setStatusTurn(ch.getStatusTurn()-1);
            return -1;
        }else{
            ch.setStatus("");
        }
        System.out.println(ch.getName()+ "se liberó del congelamiento");
        return 1;
    }
    public static void hAddStatusburned(Character chh){
        System.out.println(chh.getName()+" está quemado");
        chh.setStatus("burned");
        chh.setStatusTurn(3);
    }
    public static void hAddStatusBleeding(Character chh){
        System.out.println(chh.getName()+" está sangrando");
        chh.setStatus("bleeding");
        chh.setStatusTurn(5);
    }
    public static void hAddStatusStunned(Character chh){
        System.out.println(chh.getName()+" está noqueado");
        chh.setStatus("stunned");
        chh.setStatusTurn(2);
    }
    public static void hAddStatusFrozen(Character chh){
        System.out.println(chh.getName()+" está congelado");
        chh.setStatus("frozen");
        chh.setStatusTurn(3);
    }
}
