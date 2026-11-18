package org.archaios.commons.json;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import org.archaios.commons.statistics.Stats;
import org.archaios.main.Archaios;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonManager {

    public static void save(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        SaveJObject saveJObject = new SaveJObject(Archaios.getIdUser(), Archaios.getPlayName(), Stats.getArcheologistCollection(), Stats.getPoints());

        

        File saveFile = new File("./saves/" +  Archaios.getPlayName() + ".save");

        try(BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(saveFile), StandardCharsets.UTF_8))){
            File directory = new File("./saves");
            if(!directory.exists()){
                directory.mkdir();
            }
            gson.toJson(saveJObject, bf);
        } catch(FileNotFoundException e){
            Archaios.logManager.logError("Error en JsonManager. No se ha encontrado el archivo de guardado: " + e.getMessage());
        } catch(UnsupportedEncodingException e){
            Archaios.logManager.logError("Error en JsonManager. Tipo de CharacterEncoding no soportado: " + e.getMessage());
        } catch (IOException e) {
            Archaios.logManager.logError("Error en JsonManager. Error de IO al guardar: " + e.getMessage());
        }
    }

    public static SaveJObject readFromFile(File saveFile){
        Gson gson = new Gson();
        SaveJObject saveJObject = null;

        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(saveFile), "UTF-8"))){
            saveJObject = gson.fromJson(br, SaveJObject.class);
        } catch(IOException e){
            Archaios.logManager.logError("Error en JsonManager. Error de IO al leer: " + e.getMessage());
        }

        return saveJObject;
    }
}
