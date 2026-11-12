package org.archaios.commons.logs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogManager {

    private File logFile;
    private File errorLogFile;

    public LogManager(String playName){
        File logDirectory = new File("./log/register/");
        
        errorLogFile = new File("./log/errors.log");
        logFile = new File("./log/register/" + playName + ".log");

        if(!logDirectory.exists()){
            logDirectory.mkdirs();
        }
        
        if(!logFile.exists()){
            try {
                logFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(!errorLogFile.exists()){
            try{
                errorLogFile.createNewFile();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public void log(String text){
        String logLine = "[" + getFormatedCurrentTime() + "] " + text;
        writeToFile(logLine, logFile);
    }

    public void logError(String text){
        String logLine = "[" + getFormatedCurrentTime() + "] " + text;
        writeToFile(logLine, errorLogFile);
    }

    private String getFormatedCurrentTime(){
       return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    }

    private void writeToFile(String text, File file){
        try(BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), StandardCharsets.UTF_8))){
            bf.write(text);
            bf.newLine();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
