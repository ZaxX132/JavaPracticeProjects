package Controler;
import Models.ChPlayable;
import Models.Player;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class PlayableCharacterControler {
    public static void newCharacter(String user,String name,String Class,int level){
        String ruta="src/datosTXT/PlayersCharacters/"+user+".txt";
        int id=nuevoId(loadCharacter(user));
        Path path= Paths.get(ruta);
        if(path.toFile().isFile()){
            if (comprobarArchivoVacio(user)){
                try{
                    FileWriter fw=new FileWriter(ruta,true);
                    BufferedWriter bw=new BufferedWriter(fw);
                    bw.newLine();
                    bw.write(user+","+id+","+name+","+Class+","+level+","+0);
                    bw.close();
                }catch(IOException ex){
                    System.out.println("Un error ha ocurrido al crear un personaje: " + ex);
                }
            }else{
                try{
                    FileWriter fw=new FileWriter(ruta);
                    BufferedWriter bw=new BufferedWriter(fw);
                    bw.write(user+","+id+","+name+","+Class+","+level+","+0);
                    bw.close();
                }catch (IOException ex){
                    System.out.println("Un error ha ocurrido al crear un personaje: " + ex);
                }

            }
        }else{
            try{
                FileWriter fw=new FileWriter(ruta);
                BufferedWriter bw=new BufferedWriter(fw);
                bw.write(user+","+id+","+name+","+Class+","+level+","+0);
                bw.close();
            }catch (IOException ex){
                System.out.println("Un error ha ocurrido al crear un personaje: " + ex);
            }
        }

    }
    public static ArrayList<ChPlayable> loadCharacter(String user){
        String ruta="src/datosTXT/PlayersCharacters/"+user+".txt";
        ArrayList<ChPlayable> characterList=new ArrayList<>();
        ChPlayable character;
        int id=0;
        String linea="";
        String[] resultadoSplit;
        Path path= Paths.get(ruta);
        if(path.toFile().isFile()){
            if (comprobarArchivoVacio(user)){
                try{
                    FileReader fr=new FileReader(ruta);
                    BufferedReader br=new BufferedReader(fr);
                    while((linea=br.readLine())!=null){
                        resultadoSplit=linea.split(",");
                        character=new ChPlayable(resultadoSplit[1],resultadoSplit[2],resultadoSplit[3]
                        ,Integer.parseInt(resultadoSplit[4]),resultadoSplit[0],Integer.parseInt(resultadoSplit[5]));
                        characterList.add(character);
                    }
                }catch(IOException ex){
                    System.out.println("No se ha podido cargar los personajes: " + ex);
                }
                System.out.println(characterList);
            }else{
                System.out.println("El usuario: " + user+ " no tiene personajes creados en este momento");
            }
        }else{
                System.out.println("No se han creado personajes jugables para el usuario: " + user);
        }
        return characterList;
    }
    public static Boolean comprobarArchivoVacio(String user){
        try {
            FileReader fr = new FileReader("src/datosTXT/PlayersCharacters/"+user+".txt");
            BufferedReader br = new BufferedReader(fr);
            if((br.readLine()) != null) {
                br.close();
                return true;
            }else{
                br.close();
                return false;
            }
        } catch (IOException ex) {
            System.out.println("Un error ha ocurrido al cargar el archivo: " + ex);
        }
        return false;
    }
    public static void savePlCharacter(ArrayList<ChPlayable> plCharacterList,String user){
        String ruta="src/datosTXT/PlayersCharacters/"+user+".txt";
        int id=0;
        int contador=0;
        try{
            FileWriter fw=new FileWriter(ruta);
            BufferedWriter bw=new BufferedWriter(fw);
            for (ChPlayable plch:plCharacterList) {
                if(contador==0){
                    bw.write(plch.getUser()+","+plch.getId()+
                            ","+plch.getName()+ ","+plch.getCharClass()+
                            ","+plch.getLevel()+","+plch.getExperienceBar());
                }else{
                    bw.newLine();
                    bw.write(plch.getUser()+","+plch.getId()+
                            ","+plch.getName()+ ","+plch.getCharClass()+
                            ","+plch.getLevel()+","+plch.getExperienceBar());
                }
            }
            bw.close();
        }catch(IOException ex){
            System.out.println("Error a la hora de guardar a los personajes");
        }
    }
    public static int nuevoId(ArrayList<ChPlayable> characterList){
        int maxId=0;
        for (ChPlayable chp:characterList) {
            if(Integer.parseInt(chp.getId())>maxId){
                maxId=Integer.parseInt(chp.getId());
            }
        }
        return maxId+1;
    }
}
