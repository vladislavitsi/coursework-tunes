package control;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileHandler {
    public static String getTextFromFile(String file){
        StringBuilder content = new StringBuilder();
        try (BufferedReader in = new BufferedReader(
                new FileReader(file))){
            String inputLine;
            while ((inputLine = in.readLine()) != null){
                content.append(inputLine).append("\n");
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return content.toString();
    }
}
