package org.archaios.commons.gson;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import org.archaios.main.Archaios;

import com.google.gson.Gson;

public class JsonManager {

    public static void save(String playerId, String playName){
        Gson gson = new Gson();

        File directory = new File("./saves");
        if(!directory.exists()){
            directory.mkdir();
        }

        File saveFile = new File("./saves/" + playName);

        try(BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(saveFile), "UTF-8"))){
            gson.toJson(playerId, bf);
            gson.toJson(playName, bf);
        } catch(FileNotFoundException e){
            e.printStackTrace();
        } catch(UnsupportedEncodingException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
