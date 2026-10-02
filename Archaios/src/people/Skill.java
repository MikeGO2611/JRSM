package people;

import java.util.List;
import java.util.Map;

public class Skill {
    public int getBonusRandomTreasures(){
        return -1;
    }

    public Map<ETreasureRarity, Integer> getBonusTreasures(){
        return null;
    }

    public float getBonusTreasurePercentage(){
        return -1;
    }

    public int getBonusActions(){
        return -1;
    }

    public float getBonusActionsPercentage(){
        return -1;
    }

    public int getRevelationEmpty(){
        return -1;
    }

    public int getRevelationTreasure(){
        return -1;
    }

    public List<ETerrainShape> getBonusTerrainShapes(){
        return -1;
    }
}
