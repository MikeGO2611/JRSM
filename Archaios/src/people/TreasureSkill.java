package people;

public class TreasureSkill extends Skill {
    private int common, uncommon, rare, random;

    public TreasureSkill(String name, String description, int common, int uncommon, int rare, int random){
        super(name, description, "tesoro");
        this.common = common;
        this.uncommon = uncommon;
        this.rare = rare;
        this.random = random;
    }

    @Override public int getBonusCommonTreasures(){return common;}
    @Override public int getBonusUncommonTreasures(){return uncommon;}
    @Override public int getBonusRareTreasures(){return rare;}
    @Override public int getBonusRandomTreasures(){return random;}
}
