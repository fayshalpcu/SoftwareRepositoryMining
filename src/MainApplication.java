import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainApplication {
    private static ArrayList<Person> theList;
    public static void main(String[] args) {
        File theFile = new File("persons.json");

        if(theFile.exists()) { // Read from the file, print content
            try{
                FileReader fileReader = new FileReader(theFile);
                Type type = new TypeToken<ArrayList<Person>>(){}.getType();
                Gson gson = new Gson();
                theList = gson.fromJson(fileReader, type);
                fileReader.close();
                for (Person p: theList) {
                    System.out.println(p.getName());
                }

            } catch (FileNotFoundException e) {
                System.err.println("Error in creating a FileReader object. ");
            } catch (IOException e) {
                System.err.println("Error in closing the file.");
            }

        } else { // Create an array List, write to the file
            theList = new ArrayList<>();
            theList.add(new Person(theFile.getName()));
            try {
                FileWriter fileWriter = new FileWriter(theFile);
                Gson gson = new Gson();
                gson.toJson(theList, fileWriter);
                fileWriter.close();
            } catch (IOException e) {
                System.err.println("Error in writing the file");
            }
        }

    }
}
