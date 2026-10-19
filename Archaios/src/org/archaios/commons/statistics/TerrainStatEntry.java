package org.archaios.commons.statistics;

import java.util.ArrayList;
import java.util.List;

import org.archaios.regions.Region;
import org.archaios.treasures.Treasure;

public class TerrainStatEntry {
    private Region region;
    private int visits = 1;
    private List<Treasure> foundTreasureCollection = new ArrayList<>();
    private int treasureDigCount = 0;

    public TerrainStatEntry(Region region){
        this.region = region;
        visits = 1;
        treasureDigCount = 0;
    }
    
    public TerrainStatEntry(Region region, int treasureDigCount){
        this(region);
        this.treasureDigCount = treasureDigCount;
    }

    public TerrainStatEntry(Region region, List<Treasure> foundTreasureCollection, int treasureDigCount){
        this(region, treasureDigCount);
        this.foundTreasureCollection = foundTreasureCollection;
    }

    public Region getRegion(){
        return this.region;
    }

    public void addTreasures(List<Treasure> foundTreasures){
        visits++;
        if(!foundTreasures.isEmpty()){
            treasureDigCount += foundTreasures.size();

            for (Treasure treasure : foundTreasures) {
                if(!foundTreasureCollection.contains(treasure)) {
                    foundTreasureCollection.add(treasure);
                }
            }

            sortTreasures();
        }
    }

    public int getVisits(){
        return visits;
    }

    public int getTreasureDigCount(){
        return treasureDigCount;
    }

    public List<Treasure> getTreasures(){
        return foundTreasureCollection;
    }

    private void sortTreasures(){
        foundTreasureCollection.sort(
            (Treasure t1, Treasure t2) -> {
                if(!t1.getName().equals(t2.getName())){
                    return t1.getName().compareTo(t2.getName());
                } else {
                    return t1.getCode().compareTo(t2.getCode());
                }
            }
        );
    }
}
