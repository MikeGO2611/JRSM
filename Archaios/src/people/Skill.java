package people;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Skill {
    private String name;
    private String description;
    private String type;
    private List<Skill> dependencies = new ArrayList<>();
    private List<Skill> incompatibilities = new ArrayList<>();

    public Skill(String name, String description, String type){
        this.name = name;
        this.description = description;
        this.type = type;
    }
    

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
