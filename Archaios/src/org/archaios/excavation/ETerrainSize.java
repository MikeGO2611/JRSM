package org.archaios.excavation;

public enum ETerrainSize {
    SMALL("Pequeño", 2),
    MEDIUM("Mediano", 5),
    LARGE("Grande", 10);

    private final String name;
    private final int expBonus;

    ETerrainSize(String name, int expBonus) {
        this.name = name;
        this.expBonus = expBonus;
    }

    public int getExpBonus() {
        return expBonus;
    }
}