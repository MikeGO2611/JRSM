package org.archaios.main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.archaios.commons.input.UserInput;
import org.archaios.commons.json.JsonManager;
import org.archaios.commons.json.SaveJObject;
import org.archaios.commons.logs.LogManager;
import org.archaios.commons.repositories.Regions;
import org.archaios.commons.repositories.Skills;
import org.archaios.commons.statistics.Stats;
import org.archaios.commons.statistics.TerrainStatEntry;
import org.archaios.helpers.CodeHelper;
import org.archaios.menu.Menu;
import org.archaios.people.Archeologist;
import org.archaios.regions.Region;
import org.archaios.regions.Countries.Egypt;
import org.archaios.regions.Countries.Greece;
import org.archaios.regions.Countries.Iberia;
import org.archaios.regions.Countries.Petra;
import org.archaios.regions.Countries.Rome;
import org.archaios.regions.Countries.Sumeria;
import org.archaios.treasures.Treasure;


import zeraf.ESistema;
import zeraf.Zeraf;
import zeraf.ZerafFactory;

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

    public static Zeraf zeraf;

    /** Servicio de Log */
    public static LogManager logManager;

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
                int userInput = UserInput.getUserIntegerInRange(0, 1, "Opción no válida");

                if(userInput == 1){
                    newSave = false;

                    System.out.println("¿Qué partida quieres cargar?");
                    for(int i = 0; i < saveFileCollection.size(); i++){
                        System.out.println((i + 1) + ". " + saveFileCollection.get(i).getName().split(".save")[0]);
                    }
                    System.out.println("0. Volver");

                    userInput = UserInput.getUserIntegerInRange(0, saveFileCollection.size(), "Opción no válida");

                    if(userInput == 0){
                        newSave = true;
                    } else {
                        if (!load(saveFileCollection.get(userInput - 1))) {
                            newSave = true;
                        }
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
        logManager = new LogManager(playName);
        logManager.log("Inicio de la partida");
        zeraf = ZerafFactory.createZeraf(ESistema.MUSEO, idUser, idGroup);
    }

    /**
     * Hace limpieza final.
     */
    private static void end() {

    }

    /**
     * Guarda la partida.
     */
    public  static void save() {
        JsonManager.save();
    }

    /**
     * Carga la partida
     * @param saveFile Archivo de partida guardada a cargar.
     * @return true si la partida se cargó correctamente; false en caso de error.
     */
    private static boolean load(File saveFile) {
        SaveJObject saveJObject = JsonManager.readFromFile(saveFile);
        if (saveJObject == null) {
            System.err.println("No se pudo cargar el archivo de guardado.");
            return false;
        }
        idUser = saveJObject.getUid();
        playName = saveJObject.getPlayName();
        Stats.setPoints(saveJObject.getPoints());

        Stats.setArcheologistCollection(saveJObject.getArcheologistCollection());

        List<TerrainStatEntry> terrainStatEntryCollection = new ArrayList<>();
        for (Archeologist arch : Stats.getArcheologistCollection()) {
            arch.recalculateAfterLoad();
        }
        Map<String, Integer> locationSaveStats = saveJObject.getSaveStats().getLocationSaveStats();

        for(Map.Entry<String, Integer> entry : locationSaveStats.entrySet()){
            TerrainStatEntry terrainStatEntry = null;

            //TODO Usar el repositorio de los terrainsStats
            switch (entry.getKey()) {
                case "EGP":
                    terrainStatEntry = new TerrainStatEntry(new Egypt());
                    break;
                case "GRC":
                    terrainStatEntry = new TerrainStatEntry(new Greece());
                    break;
                case "IBR":
                    terrainStatEntry = new TerrainStatEntry(new Iberia());
                    break;
                case "PTR":
                    terrainStatEntry = new TerrainStatEntry(new Petra());
                    break;
                case "ROM":
                    terrainStatEntry = new TerrainStatEntry(new Rome());
                    break;
                case "SUM":
                    terrainStatEntry = new TerrainStatEntry(new Sumeria());
                    break;
                default:
                    System.err.println("Región no reconocida");
                    break;
            }

            if (terrainStatEntry != null) {
                terrainStatEntry.setVisits(entry.getValue());
                terrainStatEntryCollection.add(terrainStatEntry);
            }
        }

        //TODO Hacerlo bien con HashMaps y un repositorio
        Map<String, Integer> treasureSaveStats = saveJObject.getSaveStats().getTreasureSaveStats();
        for(Map.Entry<String, Integer> entry : treasureSaveStats.entrySet()){

            Treasure treasure = null;

            String entryKey = entry.getKey();

            for(Region region : regionsRepository.getAllElements()){
                if(region.getCode().equals(CodeHelper.getRegion(entryKey))){
                    for(Treasure regionTreasure : region.getAllTreasures()){
                        if(CodeHelper.getFullTreasure(entryKey).equals(CodeHelper.getFullTreasure(regionTreasure.getCode()))){
                            treasure = regionTreasure;
                            int partNumber = Integer.parseInt(CodeHelper.getTreasurePart(entryKey));
                            treasure.setPartNumber(partNumber);
                            
                            for(TerrainStatEntry terrainStatEntry : terrainStatEntryCollection){
                                if(terrainStatEntry.getRegion().equals(region)){
                                    for(int i = 0; i < entry.getValue(); i++){
                                        terrainStatEntry.addLoadedTreasure(treasure);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        for(TerrainStatEntry terrainStatEntry : terrainStatEntryCollection){
            Stats.addTerrainStatEntry(terrainStatEntry);
        }
        return true;
    }

    /**
     * Getter del ID del usuario
     * @return String ID del usuario.
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

