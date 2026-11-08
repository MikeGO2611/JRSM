package org.archaios.commons.json;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import org.archaios.commons.statistics.Stats;
import org.archaios.main.Archaios;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonManager {

    public static void save(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        SaveJObject saveJObject = new SaveJObject(Archaios.getIdUser(), Archaios.getPlayName(), Stats.getArcheologistCollection(), Stats.getPoints());

        File directory = new File("./saves");
        if(!directory.exists()){
            directory.mkdir();
        }

        File saveFile = new File("./saves/" +  Archaios.getPlayName());

        try(BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(saveFile), "UTF-8"))){
            gson.toJson(saveJObject, bf);
        } catch(FileNotFoundException e){
            e.printStackTrace();
        } catch(UnsupportedEncodingException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
