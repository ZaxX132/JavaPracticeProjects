package Models;
import java.util.ArrayList;
public class Character {
    private String id; //Autoincrement
    private String name;
    private String charClass;
    private int level=1;
    private double str;
    private double agi;
    private double inte;
    private double hp;
    private double armor;
    private int mana;
    private ArrayList<Hability> habilities;
    private String status;
    private int statusTurn;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getStatusTurn() {
        return statusTurn;
    }

    public void setStatusTurn(int statusTurn) {
        this.statusTurn = statusTurn;
    }

    public Character(String id, String name, String charClass, int level) {
        this.id = id;
        this.name = name;
        this.charClass = charClass;
        this.level = level;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharClass() {
        return charClass;
    }

    public void setCharClass(int charClass) {
        if(charClass==0){
            this.charClass="Warrior";
        }else if(charClass==1){
            this.charClass="Rogue";
        }else if(charClass==2){
            this.charClass="Mage";
        }else{
            System.out.println("La opcion elegida no existe.");
        }
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public double getStr() {
        str=5.0;
        switch (getCharClass()){

            case "Warrior":
                return str+(getLevel()*5);
            case "Mage":
                return str+(getLevel()*2);
            case "Rogue":
                return str+(getLevel()*3);
            default:
                return str;
        }
    }

    public void setStr(double str) {
        this.str = str;
    }
    public double getAgi() {
        agi=5;
        switch (getCharClass()){
            case "Warrior":
                return agi+(getLevel()*3);
            case "Mage":
                return agi+(getLevel()*2);
            case "Rogue":
                return agi+(getLevel()*5);
            default:
                return agi;
        }
    }

    public void setAgi(double agi) {
        this.agi = agi;
    }

    public double getInte() {
        inte=5;
        switch (getCharClass()){
            case "Warrior":
                return inte+(getLevel()*1);
            case "Mage":
                return inte+(getLevel()*5);
            case "Rogue":
                return inte+(getLevel()*3);
            default:
                return inte;
        }
    }

    public void setInte(double inte) {
        this.inte = inte;
    }

    public double getHp() {
        hp=100;
        switch (getCharClass()){
            case "Warrior":
                return hp+(getLevel()*10);
            case "Mage":
                return hp+(getLevel()*5);
            case "Rogue":
                return hp+(getLevel()*6);
            default:
                return hp;
        }
    }

    public void setHp(double hp) {
        this.hp = hp;
    }

    public double getArmor() {
        armor=5;
        switch (getCharClass()){
            case "Warrior":
                return armor+(getLevel()*5);
            case "Mage":
                return armor+(getLevel()*2);
            case "Rogue":
                return armor+(getLevel()*3);
            default:
                return armor;
        }
    }

    public void setArmor(double armor) {
        this.armor = armor;
    }

    public int getMana() {
        mana=100;
        switch (getCharClass()){
            case "Warrior":
                return mana+(getLevel()*3);
            case "Mage":
                return mana+(getLevel()*10);
            case "Rogue":
                return mana+(getLevel()*4);
            default:
                return mana;
        }
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public ArrayList<Hability> getHabilities() {
        return habilities;
    }

    public void setHabilities(ArrayList<Hability> habilities) {
        this.habilities = habilities;
    }
}
