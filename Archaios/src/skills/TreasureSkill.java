package skills;
import java.util.HashMap;
import java.util.Map;
import treasures.ETreasureRarity;

public class TreasureSkill extends Skill {
    private int randomTreasures;
    private Map<ETreasureRarity, Integer> bonusTreasures = new HashMap<>();

    public TreasureSkill(String name, String description, int randomTreasures, int common, int uncommon, int rare) {
        super(name, description, ESkillType.TREASURE);
        this.randomTreasures = randomTreasures;

        if (common > 0) {
            bonusTreasures.put(ETreasureRarity.COMMON, common);
        }
        if (uncommon > 0) {
            bonusTreasures.put(ETreasureRarity.UNCOMMON, uncommon);
        }
        if (rare > 0) {
            bonusTreasures.put(ETreasureRarity.RARE, rare);
        }
    }

    @Override
    public int getBonusRandomTreasures() {
        return this.randomTreasures;
    }

    @Override
    public Map<ETreasureRarity, Integer> getBonusTreasures() {
        return this.bonusTreasures;
    }
}