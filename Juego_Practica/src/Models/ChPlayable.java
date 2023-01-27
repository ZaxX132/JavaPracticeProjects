package Models;

public class ChPlayable extends Character{
    private String user;
    private int experienceBar;

    public ChPlayable(String id, String name, String charClass, int level,String user,int expirienceBar) {
        super(id, name, charClass, level);
        this.user=user;
        this.experienceBar=expirienceBar;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getExperienceBar() {
        return experienceBar;
    }

    public void setExperienceBar(int experienceBar) {
        this.experienceBar = experienceBar;
    }
}
