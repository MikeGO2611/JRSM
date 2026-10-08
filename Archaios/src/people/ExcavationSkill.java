package people;

public class ExcavationSkill extends Skill {
    private int extraActions;
    private float multiplier;

    public ExcavationSkill(String name, String description, int extraActions, float multiplier) {
        super(name, description, "excavación");
        this.extraActions = extraActions;
        this.multiplier = multiplier;
    }

    @Override
    public int getBonusActions() { return extraActions; }

    @Override
    public float getBonusActionsPercentage() { return multiplier; }
}