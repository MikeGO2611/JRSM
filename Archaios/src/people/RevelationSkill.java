package people;

public class RevelationSkill extends Skill {
    private int emptyRevealed;
    private int questionMarks;

    public RevelationSkill(String name, String description, int emptyRevealed, int questionMarks) {
        super(name, description, "revelación");
        this.emptyRevealed = emptyRevealed;
        this.questionMarks = questionMarks;
    }

    @Override public int getRevelationEmpty() { return emptyRevealed; }
    @Override public int getRevelationTreasure() { return questionMarks; }
}