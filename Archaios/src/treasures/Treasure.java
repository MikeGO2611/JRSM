package treasures;

public class Treasure {
    private String name;
    private String description;
    private String idCode;
    private ETreasureType type;
    private ETreasureRarity rarity;
    private int parts;
    private int points;

    public Treasure(String name, String region, ETreasureType type, String codeNumber, int parts, int points,
            ETreasureRarity rarity, String description) {
        this.name = name;
        this.idCode = region + "_" + type.getCode() + "_" + codeNumber;
        this.type = type;
        this.rarity = rarity;
        this.parts = parts;
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCode() {
        return idCode;
    }

    public ETreasureType getType() {
        return type;
    }

    public ETreasureRarity getRarity() {
        return rarity;
    }

    public int getParts() {
        return parts;
    }

    public int getPoints() {
        return points;
    }

}
