package org.archaios.commons.json;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.archaios.commons.statistics.Stats;
import org.archaios.commons.statistics.TerrainStatEntry;
import org.archaios.people.Archeologist;
import org.archaios.treasures.Treasure;

import com.google.gson.annotations.SerializedName;

/**
 * Reune los estados, variables y listas apropiadas para guardar fácilmente en un JSON.
 * @author Miguel González Obenza
 */
public class SaveJObject {
    @SerializedName("UID")
    private String uid;
    @SerializedName("Partida")
    private String playName;
    @SerializedName("Arqueólogos")
    private List<Archeologist> archeologistCollection;
    @SerializedName("Estadísticas")
    private SaveStats saveStats;
    @SerializedName("Puntos")
    private int points;

    /**
     * Constructor del SaveJObject
     * @param UID String UID del jugador
     * @param partida String nombre de la partida
     * @param arqueólogos List<Archeologist> de arqueólogos
     * @param puntos int puntos de la partida
     */
    public SaveJObject(String UID, String partida, List<Archeologist> arqueólogos, int puntos) {
        this.uid = UID;
        this.playName = partida;
        this.archeologistCollection = arqueólogos;
        this.points = puntos;
        this.saveStats = new SaveStats();
    }

    /**
     * Obtiene el UID del SaveJObject
     * @return String UID
     */
    public String getUid() {
        return uid;
    }

    /**
     * Setea el UID del SaveJObject
     * @param uid String UID
     */
    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     * Obtiene el nombre de la partida guardado en el SaveJObject
     * @return String nombre de la partida
     */
    public String getPlayName() {
        return playName;
    }

    /**
     * Setea el nombre de la partida en el SaveJObject
     * @param playName String nombre de la partida
     */
    public void setPlayName(String playName) {
        this.playName = playName;
    }

    /**
     * Obtiene la lista de todos los arqueólogos en el SaveJObject
     * @return List<Archeologist> de arqueólogos
     */
    public List<Archeologist> getArcheologistCollection() {
        return archeologistCollection;
    }

    /**
     * Setear la lista de todos los arqueólogos en el SaveJObject
     * @param archeologistCollection List<Archeologist> de arqueólogos
     */
    public void setArcheologistCollection(List<Archeologist> archeologistCollection) {
        this.archeologistCollection = archeologistCollection;
    }

    /**
     * Obtiene el <code>SaveStats</code> del SaveJObject
     * @return SaveStats con los stats del SaveJObject
     */
    public SaveStats getSaveStats() {
        return saveStats;
    }

    /**
     * Setea el <code>SaveStats</code> del SaveJObject
     * @param saveStats SaveStats con los stats del SaveJObject
     */
    public void setSaveStats(SaveStats saveStats) {
        this.saveStats = saveStats;
    }

    /**
     * Obtiene los puntos del SaveJObject
     * @return int puntos
     */
    public int getPoints() {
        return points;
    }

    /**
     * Obtiene los puntos del SaveJObject
     * @param points int puntos
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * Clase auxiliar para contener los tesoros y lugares.
     * @author Miguel González Obenza
     */
    public class SaveStats {
        @SerializedName("Tesoros")
        private Map<String, Integer> treasureSaveStats = new HashMap<>();
        @SerializedName("Lugares")
        private Map<String, Integer> locationSaveStats = new HashMap<>();

        /**
         * Constructor que automáticamente genera la información de estadística
        */
        public SaveStats() {

            for (TerrainStatEntry terrainStat : Stats.getTerrainStatEntryCollection()) {
                for (Treasure treasure : terrainStat.getTreasures()) {

                    String treasureCode = treasure.getCode();

                    if (treasureSaveStats.containsKey(treasureCode)) {
                        int count = treasureSaveStats.get(treasureCode);
                        treasureSaveStats.put(treasureCode, count + 1);
                    } else {
                        treasureSaveStats.put(treasureCode, 1);
                    }
                }
            }

            for (TerrainStatEntry terrainStat : Stats.getTerrainStatEntryCollection()) {
                locationSaveStats.put(terrainStat.getRegion().getCode(), terrainStat.getVisits());
            }
        }

        /**
         * Obtiene el mapa con los tesoros y número de veces que aparecen.
         * @return Map<String, Integer>
         */
        public Map<String, Integer> getTreasureSaveStats() {
            return treasureSaveStats;
        }

        /**
         * Obtiene el mapa con la ubicación y número de veces que se excavó en ella.
         * @return Map<String, Integer>
         */
        public Map<String, Integer> getLocationSaveStats() {
            return locationSaveStats;
        }
    }

}