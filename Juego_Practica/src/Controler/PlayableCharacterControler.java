package Controler;
import Models.ChPlayable;
import Models.Player;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class PlayableCharacterControler {
    /**
     * Escribe en el txt guardado en la ruta /datosTXT/PlayersCharacters/user.txt los datos de un nuevo personaje jugable. El id se autoincrementará.
     * Si es que el txt no ha sido creado, lo crea.
     * @param user Es el nombre del usuario al que pertenece el nuevo personaje jugable. Este mismo es el nombre que llevará el txt.
     * @param name Es el nombre que llevará el nuevo personaje jugable
     * @param Class Es la clase del personaje jugable, puede ser Warrior, Rogue o Mage
     * @param level Es el nivel que tendrá el personaje jugable, por defecto se recomienda iniciar en 1
     */
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

    /**
     * Está función retornará una ArrayList de los personajes jugables registrados en el txt dependiendo del usuario
     * @param user El parametro servirá para elegir de que usuario se van a retornar los personajes jugables.
     * @return Retornará el ArrayList de personajes jugables.
     */
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

    /**
     * Comprobará si el archivo txt que guarda a los personajes jugables ha sido creado
     * @param user Sirve parra buscar si la ruta del archivo existe
     * @return Retorna un valor booleano que decidirá si es que el archivo existe o no
     */
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

    /**
     * Servirá para guardar toda un ArrayList ChPlayable en la ruta de personajes jugables del usuario. Estos datos se sobreescribiran, eliminando datos anteriores.
     * Será usado para actualizar los datos de los personajes registrados
     * @param plCharacterList Recibirá el ArrayList que se desea guardar en el txt.
     * @param user Servirá para revisar la ruta del archivo que será sobreescrito.
     */
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
                    contador++;
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

    /**
     * Es la función que realizará el autoincrement en el id de los personajes jugables
     * @param characterList Recibirá un ArrayList de personajes jugables para ver el id más alto
     * @return retornará el número del id más alto más 1.
     */
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
