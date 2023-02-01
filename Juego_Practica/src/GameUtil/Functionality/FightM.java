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
        double speed=chara.getAgi();
        double hostileSpeed=hostile.getAgi();
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
                        hostileHp=cNormalAtk(chara,hostile,hostileHp);
                        if(hostileHp<=0){break;}
                        vidaActual=enemyAction(chara,hostile,vidaActual);
                    }else{
                        vidaActual=enemyAction(chara,hostile,vidaActual);
                        if(vidaActual<=0){break;}
                        hostileHp=cNormalAtk(chara,hostile,hostileHp);
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
                    break;
            }

            if(hostileHp<=0){
                enemyDefeated(chara,hostile);
                return chara;
            }
            if(vidaActual<=0){
                System.out.println("Te han matado");
                sc.nextLine();
                return chara;
            }
        }
    }
    public static void enemyDefeated(ChPlayable chp, ChHostile hostile){
        Scanner sc=new Scanner(System.in);
        System.out.println("El enemigo ha sido derrotado");
        levelUp(chp,hostile);
    }
    public static void levelUp(ChPlayable chp, ChHostile hostile){
        Scanner sc=new Scanner(System.in);
        int baseExpBar=100;
        chp.setExperienceBar(chp.getExperienceBar()+hostile.getExperience());
        if((chp.getLevel()*baseExpBar)<=chp.getExperienceBar()){
            chp.setLevel(chp.getLevel()+1);
            chp.setExperienceBar(chp.getExperienceBar()-((chp.getLevel()-1)*baseExpBar));
            System.out.println("Has ganado: " + hostile.getExperience()+" exp");
            sc.nextLine();
            System.out.println("Has subido de nivel!");
            sc.nextLine();
            System.out.println("Tu nivel actual es: Lvl"+ chp.getLevel());
            sc.nextLine();
            System.out.println("Tu experiencia actual es: exp"+chp.getExperienceBar());
            sc.nextLine();
            System.out.println("Para el siguiente nivel necesitarás: "+(chp.getLevel()*baseExpBar)+" de experiencia");
        }else{
            System.out.println("Haz ganado: " + hostile.getExperience()+" exp");
            System.out.println("Tu experiencia actual es: "+chp.getExperienceBar()+ " exp");
        }
        sc.nextLine();
    }
    public static double cNormalAtk(ChPlayable chp,ChHostile chh,double actualHHp){
        Scanner sc=new Scanner(System.in);
        double atackDamage=chp.getStr()*2.5;
        double hArmor=chh.getArmor();
        double damageDealt=atackDamage;
        actualHHp=actualHHp-damageDealt;
        System.out.println("Haz realizado " +atackDamage+" de daño");
        sc.nextLine();
        return actualHHp;
    }
    public static double enemyAction(ChPlayable chp,ChHostile chh,double actualCHp){
        Scanner sc=new Scanner(System.in);
        double hAtackDamage=chh.getStr()*2.5;
        double cArmor=chp.getArmor();
        double damageDealt=hAtackDamage;
        actualCHp=actualCHp-damageDealt;
        System.out.println("Te han realizado "+ damageDealt);
        sc.nextLine();
        return actualCHp;
    }
}
