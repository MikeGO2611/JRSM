

/**
 * Enum para representar los tipos de terreno base y su bono de experiencia.
 */
public enum TerrainSize {
    SMALL("Pequeño", 2),
    MEDIUM("Mediano", 5),
    LARGE("Grande", 10);

    private final String name;
    private final int expBonus;

    TerrainSize(String name, int expBonus) {
        this.name = name;
        this.expBonus = expBonus;
    }

    public int getExpBonus() {
        return expBonus;
    }
}
