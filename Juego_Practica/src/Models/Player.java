package Models;
import java.util.ArrayList;
public class Player {
    private String user;
    private String password;
    ArrayList<ChPlayable> characters;

    public void showPlayer(){
        System.out.println(user);
    }
    public Player(){
    }
    public Player(String user1,String password1){
        user=user1;
        password=password1;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<ChPlayable> getCharacters() {
        return characters;
    }

    public void setCharacters(ArrayList<ChPlayable> characters) {
        this.characters = characters;
    }
}
