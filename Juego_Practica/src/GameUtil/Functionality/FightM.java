package GameUtil.Functionality;
import Models.ChHostile;
import Models.ChPlayable;
import Models.Hability;

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
        chara.setStatus("");
        chara.setStatusTurn(0);
        hostile.setStatus("");
        hostile.setStatusTurn(0);
        Scanner sc=new Scanner(System.in);
        String respuesta;
        Hability habi;
        boolean turnoActivo=false;
        double status=1;
        double statusH;
        double speed=chara.getAgi();
        double hostileSpeed=hostile.getAgi();
        double vidaActual=chara.getHp();
        double hostileHp=hostile.getHp();
        int mana=chara.getMana();
        System.out.println("Te has encontrado al enemigo: "+ hostile.getName()+" de nivel " + hostile.getLevel());
        while(true){
            if(!turnoActivo){
                status=statusEffectsM.statusEffect(chara);
                if(status>1){
                    vidaActual=vidaActual-status;
                }
                if(vidaActual<=0){
                    System.out.println("Te han matado");
                    sc.nextLine();
                    return chara;
                }
            }
            respuesta=selectOption(vidaActual,mana,hostileHp,status);
            switch (respuesta){
                case "0":
                    if(speed>=hostileSpeed){
                        hostileHp=cNormalAtk(chara,hostile,hostileHp);
                        //Accion enemiga
                        if(hostileHp<=0){break;}
                        statusH=statusEffectsM.statusEffect(hostile);
                        if (statusH==1){
                            vidaActual=enemyAction(chara,hostile,vidaActual);
                        }else if(statusH>1){
                            hostileHp=hostileHp-statusH;
                            if(hostileHp<=0){break;}
                            vidaActual=enemyAction(chara,hostile,vidaActual);
                        }
                        //
                    }else{
                        //Accion enemiga
                        statusH=statusEffectsM.statusEffect(hostile);
                        if (statusH==1){
                            vidaActual=enemyAction(chara,hostile,vidaActual);
                        }else if(statusH>1){
                            hostileHp=hostileHp-statusH;
                            if(hostileHp<=0){break;}
                            vidaActual=enemyAction(chara,hostile,vidaActual);
                        }
                        if(vidaActual<=0){break;}
                        //
                        hostileHp=cNormalAtk(chara,hostile,hostileHp);
                    }
                    turnoActivo=false;
                    break;
                case "1":
                    habi=HabilitiesM.selectHability(chara,mana);
                    if(habi.getId().equals("0")){
                        turnoActivo=true;
                        break;
                    }
                    mana=mana-HabilitiesM.manaUse(habi);
                    if(speed>=hostileSpeed){
                        hostileHp= hostileHp - HabilitiesM.useHabilityDamage(habi,chara,hostile);
                        if(hostileHp<=0){break;}
                        // Accion enemiga
                        statusH=statusEffectsM.statusEffect(hostile);
                        if (statusH==1){
                            vidaActual=enemyAction(chara,hostile,vidaActual);
                        }else if(statusH>1){
                            hostileHp=hostileHp-statusH;
                            if(hostileHp<=0){break;}
                            vidaActual=enemyAction(chara,hostile,vidaActual);
                        }
                        //
                    }else{
                        //Accion enemiga
                        statusH=statusEffectsM.statusEffect(hostile);
                        if (statusH==1){
                            vidaActual=enemyAction(chara,hostile,vidaActual);
                        }else if(statusH>1){
                            hostileHp=hostileHp-statusH;
                            if(hostileHp<=0){break;}
                            vidaActual=enemyAction(chara,hostile,vidaActual);
                        }
                        //
                        if(vidaActual<=0){break;}
                        hostileHp= hostileHp - HabilitiesM.useHabilityDamage(habi,chara,hostile);
                    }
                    turnoActivo=false;
                    break;
                case "2":
                    System.out.println("No tienes objetos");
                    break;
                case"pierdesturno":
                    vidaActual=enemyAction(chara,hostile,vidaActual);
                    turnoActivo=false;
                    if(vidaActual<=0){break;}
                default:
                    System.out.println("Por favor ingrese una opción correcta");
                    break;
            }
            if(hostileHp<=0){
                enemyDefeated(chara,hostile);
                return chara;
            }
        }
    }
    public static String selectOption(double vidaActual,int mana,double hostileHp,double status){
        Scanner sc=new Scanner(System.in);
        String respuesta;
        while(true){
            System.out.println("Hp: "+ vidaActual);
            System.out.println("Mana: "+ mana);
            System.out.println("Enemy Hp: "+ hostileHp);
            if (status>=1){

                System.out.println("0. Atacar");
                System.out.println("1. Habilidades");
                System.out.println("2. Objetos");
                respuesta=sc.nextLine();
                switch (respuesta){
                    case "0":
                    case "1":
                    case "2":
                        return respuesta;
                    default:
                        System.out.println("Por favor ingrese una opción correcta");
                }
            }
            else{
                System.out.println("No te puedes mover");
                respuesta="pierdesturno";
                return respuesta;
            }
        }

    }
    public static void enemyDefeated(ChPlayable chp, ChHostile hostile){
        System.out.println("El enemigo "+hostile.getName() +" ha sido derrotado");
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
        double atackDamage=2;//chp.getStr()*2.5;
        double hArmor=chh.getArmor();
        double damageDealt=atackDamage;
        actualHHp=actualHHp-damageDealt;
        System.out.println("Haz realizado " +atackDamage+" de daño");
        sc.nextLine();
        return actualHHp;
    }
    public static double enemyAction(ChPlayable chp,ChHostile chh,double actualCHp){
        Scanner sc=new Scanner(System.in);
        double hAtackDamage=chh.getStr();
        double cArmor=chp.getArmor();
        double damageDealt=hAtackDamage;
        actualCHp=actualCHp-damageDealt;
        System.out.println("Te han realizado "+ damageDealt);
        sc.nextLine();
        return actualCHp;
    }
}
