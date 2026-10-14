package skills;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import treasures.ETreasureRarity;
import excavation.ETerrainShape;
import repositories.ICode;

public abstract class Skill implements ICode {
    private String name;
    private String description;
    private ESkillType type;
    private List<Skill> dependencies = new ArrayList<>();
    private List<Skill> incompatibilities = new ArrayList<>();

    public Skill(String name, String description, ESkillType type) {
        this.name = name;
        this.description = description;
        this.type = type;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public ESkillType getType() { return type; }
    public List<Skill> getDependencies() { return dependencies; }
    public List<Skill> getIncompatibilities() { return incompatibilities; }

    public void addDependency(Skill skill) { this.dependencies.add(skill); }
    public void addIncompatibility(Skill skill) { this.incompatibilities.add(skill); }

    public int getBonusRandomTreasures() { return 0; }
    public Map<ETreasureRarity, Integer> getBonusTreasures() { return new HashMap<>(); }
    public float getBonusTreasurePercentage() { return 1.0f; }
    public int getBonusActions() { return 0; }
    public float getBonusActionsPercentage() { return 1.0f; }
    public int getRevelationEmpty() { return 0; }
    public int getRevelationTreasure() { return 0; }
    public List<ETerrainShape> getBonusTerrainShapes() { return new ArrayList<>(); }

    @Override
    public String getCode() {
        return this.name;
    }
}