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
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getType() { return type; }
    public List<Skill> getDependencies() { return dependencies; }
    public List<Skill> getIncompatibilities() { return incompatibilities; }

    public void addDependency(Skill skill) { this.dependencies.add(skill); }
    public void addIncompatibility(Skill skill) { this.incompatibilities.add(skill); }

    public int getBonusActions() { return 0; }
    public float getBonusActionsPercentage() { return 1.0f; } 
    public int getBonusRandomTreasures() { return 0; }
    public int getBonusCommonTreasures() { return 0; }
    public int getBonusUncommonTreasures() { return 0; }
    public int getBonusRareTreasures() { return 0; }
    public int getRevelationEmpty() { return 0; }
    public int getRevelationTreasure() { return 0; }
    public List<ETerrainShape> getBonusTerrainShapes() { return new ArrayList<>(); }
}
