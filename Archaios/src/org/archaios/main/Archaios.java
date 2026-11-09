package org.archaios.main;

import java.io.File;
import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.archaios.commons.input.UserInput;
import org.archaios.commons.json.JsonManager;
import org.archaios.commons.json.SaveJObject;
import org.archaios.commons.repositories.Regions;
import org.archaios.commons.repositories.Skills;
import org.archaios.commons.repositories.Treasures;
import org.archaios.commons.statistics.Stats;
import org.archaios.commons.statistics.TerrainStatEntry;
import org.archaios.menu.Menu;
import org.archaios.menu.MenuGenerator;
import org.archaios.people.Archeologist;
import org.archaios.regions.Countries.Greece;
import org.archaios.treasures.Treasure;
import org.archaios.zeraf.Zeraf;

import com.google.gson.annotations.Expose;

/**
 * Clase principal que sirve como entrada al programa.
 * 
 * @author Miguel González Obenza
 */
public class Archaios {
    /** El identificador del usuario. */
    private static String idUser;
    /** El identificador del grupo del trabajo. */
    private static final String idGroup = "2DAM2627";
    /** El nombre de la partida. */
    private static String playName;

    /** Repositorio de skills. */
    public static Skills skillRepository;

    /** Repositorio de regiones */
    public static Regions regionsRepository;

    /** Zeraf, encargado de comunicarse con la librería Terra */
    public static Zeraf zeraf;


    // TODO Preguntar a Adrián como funciona el estado de prueba y producción, si viene dado por el museo externo, o se configura en el código o en ejecución.
    /** Estado del museo */
    public static MuseumState museumState;

    /**
     * Método main que inicia la ejecución del programa
     * @param args String[] argumentos de ejecución. Son ignorados.
     */
    public static void main(String[] args) {
        preInit();
        init();
        menu();
        end();
        save();
    }

    /**
     * Inicializa y ejecuta el menú principal.
     */
    private static void menu() {
        Menu mainMenu = new Menu();
        mainMenu.start();
    }

    /**
     * Ejecuta la preinicialización.
     * Inicializa el repositorio de skills.
     */
    private static void preInit() {
        zeraf = Zeraf.createZeraf();
        skillRepository = new Skills();
        regionsRepository = new Regions();
    }

    /**
     * Ejecuta la inicialización.
     * Pide al usuario el código persona, nombre de la partida y primer arqueólogo.
     */
    private static void init() {
        File saveDirectory = new File("./saves");
        boolean newSave = true;

        if (saveDirectory.exists() && saveDirectory.isDirectory()){

            List<File> saveFileCollection = new ArrayList<>();

            for(File possibleSaveFile : saveDirectory.listFiles()){
                if(possibleSaveFile.getName().endsWith(".save")){
                    saveFileCollection.add(possibleSaveFile);
                }
            }
            if(!saveFileCollection.isEmpty()){
                System.out.println("Hay partidas guardadas, ¿quieres cargar alguna?");
                System.out.println("0. No");
                System.out.println("1. Sí");
                int userInput = UserInput.getUserIntegerInRange(0, 2, "Opción no válida");

                if(userInput == 1){
                    newSave = false;

                    System.out.println("¿Qué partida quieres guardar?");
                    for(int i = 0; i < saveFileCollection.size(); i++){
                        System.out.println((i + 1) + ". " + saveFileCollection.get(i).getName().split(".save")[0]);
                    }
                    System.out.println("0. Volver");

                    userInput = UserInput.getUserIntegerInRange(0, saveFileCollection.size() + 1, "Opción no válida");

                    if(userInput == 0){
                        newSave = true;
                    } else {
                        load(saveFileCollection.get(userInput - 1));
                    }

                } else{
                    newSave = true;
                }
            }
        } 
        
        if(newSave){
            System.out.print("Introduce tu código personal único: ");
            idUser = UserInput.getUserString();

            System.out.print("Introduce el nombre de la partida: ");
            playName = UserInput.getUserString();

            System.out.print("Introduce el nombre del arqueólogo: ");
            String archeologistName = UserInput.getUserString();
            Archeologist firstArcheologist = new Archeologist(archeologistName);
            Stats.addArcheologist(firstArcheologist);
        }

    }

    /**
     * Hace limpieza final.
     */
    private static void end() {

    }

    /**
     * Guarda la partida.
     */
    private static void save() {
        JsonManager.save();
        System.out.println("Saved");
    }

    /**
     * Carga la partida
     */
    private static void load(File saveFile) {
        SaveJObject saveJObject = JsonManager.readFromFile(saveFile);

        idUser = saveJObject.getUid();
        playName = saveJObject.getPlayName();
        Stats.setPoints(saveJObject.getPoints());

        Stats.setArcheologistCollection(saveJObject.getArcheologistCollection());

        List<TerrainStatEntry> terrainStatEntryCollection = new ArrayList<>();

        Map<String, Integer> locationSaveStats = saveJObject.getSaveStats().getLocationSaveStats();
        for(Map.Entry<String, Integer> entry : locationSaveStats.entrySet()){
            if(entry.getKey().contains("GRC")){
                TerrainStatEntry terrainStatEntry = new TerrainStatEntry(new Greece());
                terrainStatEntry.setVisits(entry.getValue());
                terrainStatEntryCollection.add(terrainStatEntry);
            }
        }

        Map<String, Integer> treasureSaveStats = saveJObject.getSaveStats().getTreasureSaveStats();
        Treasures treasures = new Treasures();
        for(Map.Entry<String, Integer> entry : treasureSaveStats.entrySet()){
            
            Treasure treasure = treasures.getElementCode(entry.getKey());

            if(treasure.getCode().contains("GRC")){

                TerrainStatEntry terrainStatEntry = null;

                for(TerrainStatEntry terrainStatEntryFind : terrainStatEntryCollection){
                    if(terrainStatEntryFind.getRegion() instanceof Greece){
                        terrainStatEntry = terrainStatEntryFind;
                    }
                }

                terrainStatEntry.addTreasures(Arrays.asList(treasure));
            }
        }

        for(TerrainStatEntry terrainStatEntry : terrainStatEntryCollection){
            Stats.addTerrainStatEntry(terrainStatEntry);
        }
    }

    /**
     * Getter del ID del usuario
     * @return int ID
     */
    public static String getIdUser() {
        return idUser;
    }

    /**
     * Getter del nombre de la partida
     * @return String nombre de la partida
     */
    public static String getPlayName() {
        return playName;
    }
}

