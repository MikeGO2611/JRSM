package org.archaios.commons.json;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.archaios.commons.statistics.Stats;
import org.archaios.commons.statistics.TerrainStatEntry;
import org.archaios.people.Archeologist;
import org.archaios.treasures.Treasure;

import com.google.gson.annotations.SerializedName;

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

    public SaveJObject(String UID, String partida, List<Archeologist> arqueólogos, int puntos) {
        this.uid = UID;
        this.playName = partida;
        this.archeologistCollection = arqueólogos;
        this.points = puntos;
        this.saveStats = new SaveStats();
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPlayName() {
        return playName;
    }

    public void setPlayName(String playName) {
        this.playName = playName;
    }

    public List<Archeologist> getArcheologistCollection() {
        return archeologistCollection;
    }

    public void setArcheologistCollection(List<Archeologist> archeologistCollection) {
        this.archeologistCollection = archeologistCollection;
    }

    public SaveStats getSaveStats() {
        return saveStats;
    }

    public void setSaveStats(SaveStats saveStats) {
        this.saveStats = saveStats;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public class SaveStats {
        @SerializedName("Tesoros")
        private Map<String, Integer> treasureSaveStats = new HashMap<>();
        @SerializedName("Lugares")
        private Map<String, Integer> locationSaveStats = new HashMap<>();

        /** Constructor que automáticamente genera la información de estadística */
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

        public Map<String, Integer> getTreasureSaveStats() {
            return treasureSaveStats;
        }

        public Map<String, Integer> getLocationSaveStats() {
            return locationSaveStats;
        }
    }

}