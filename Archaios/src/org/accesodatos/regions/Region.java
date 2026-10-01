package regions;

import treasures.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Region {
    private final String code;
    private final String name;
    protected List<Treasure> treasures = new ArrayList<>();

    public Region(String code, String name){
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }
    
    public String getName() {
        return name;
    }

    public List<Treasure> getAllTreasures() {
        return treasures;
    }
    
    public List<Treasure> getRarityTreasures(ETreasureRarity rarity){
        List<Treasure> toReturn = new ArrayList<>();
        for (Treasure treasure : treasures) {
            if(treasure.getRarity().equals(rarity)){
                toReturn.add(treasure);
            }
        }
        return toReturn;
    }

    public Treasure getRandomTreasure() {
        Random random = new Random();
        return treasures.get(random.nextInt(treasures.size()));
    }

    public Treasure getRandomRarityTreasure(ETreasureRarity rarity){
        List<Treasure> toReturn = new ArrayList<>();
        for (Treasure treasure : treasures) {
            if(treasure.getRarity().equals(rarity)){
                toReturn.add(treasure);
            }
        }
        Random random = new Random();
        return toReturn.get(random.nextInt(toReturn.size()));
    }
}
