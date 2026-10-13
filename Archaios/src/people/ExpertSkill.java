package people;

public class ExpertSkill extends Skill {
    private int establishedCost;
    private String costTerrainType;
    private int removedTerrainAmount;
    private String removedTerrainType;

    public ExpertSkill(String name, String description, int establishedCost, String costTerrainType, int removedTerrainAmount, String removedTerrainType) {
        super(name, description, ESkillType.EXPERT);
        this.establishedCost = establishedCost;
        this.costTerrainType = costTerrainType;
        this.removedTerrainAmount = removedTerrainAmount;
        this.removedTerrainType = removedTerrainType;
    }

    public int getEstablishedCost() {
        return this.establishedCost;
    }

    public String getCostTerrainType() {
        return this.costTerrainType;
    }

    public int getRemovedTerrainAmount() {
        return this.removedTerrainAmount;
    }

    public String getRemovedTerrainType() {
        return this.removedTerrainType;
    }
}