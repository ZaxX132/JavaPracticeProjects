package GameUtil.Functionality;
import Models.ChHostile;
import Models.ChPlayable;
import java.util.Scanner;
class FightM {
    /**
     * La función peleaPrueba está pensada para ser un evento que haga que dos objetos Characters se enfrenten entre si.
     * Se solicitará un objeto ChPlayable y un ChHostile
     * @param chara Es el personaje jugable escogido para ser controlado en la pelea
     * @param hostile Es el enemigo que el personaje jugable se va a enfrentar
     * @return retornará al personaje jugable con un aumento de experiencia si gana y si no, simplemente se retornará sin ganar nada.
     */
    public static ChPlayable peleaPrueba(ChPlayable chara, ChHostile hostile){
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
        System.out.println("Te has encontrado al enemigo: "+ hostile.getName()+" de nivel " + hostile.getLevel());
        while(true){
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
                            levelUp(chara);
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
                                System.out.println("Has ganado "+hostile.getExperience()+" de experiencia");
                                levelUp(chara);
                                sc.nextLine();
                                return chara;
                            }
                        }
                    }
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
    }
    public static void levelUp(ChPlayable chp){
        Scanner sc=new Scanner(System.in);
        int baseExpBar=100;
        if((chp.getLevel()*baseExpBar)<=chp.getExperienceBar()){
            chp.setLevel(chp.getLevel()+1);
            chp.setExperienceBar(chp.getExperienceBar()-((chp.getLevel()-1)*baseExpBar));
            System.out.println("Has subido de nivel!");
            sc.nextLine();
            System.out.println("Tu nivel actual es: Lvl"+ chp.getLevel());
            sc.nextLine();
            System.out.println("Tu experiencia actual es: exp"+chp.getExperienceBar());
            sc.nextLine();
            System.out.println("Para el siguiente nivel necesitarás: "+(chp.getLevel()*baseExpBar)+" de experiencia");
            sc.nextLine();
        }
    }
    public static void normalAtack(){

    }
}
