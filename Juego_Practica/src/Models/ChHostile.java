package Models;

public class ChHostile extends Character{
    private int experience;
    public ChHostile(String id, String name, String charClass, int level,int experience) {
        super(id, name, charClass, level);
        this.experience=experience;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }
}
