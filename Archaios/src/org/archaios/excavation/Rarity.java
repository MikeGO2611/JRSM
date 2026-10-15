package org.archaios.excavation;

public enum Rarity {
    COMMON('C', 60),
    UNCOMMON('I', 30),
    RARE('R', 10);

    private final char symbol;
    private final int baseProbability;

    Rarity(char symbol, int baseProbability) {
        this.symbol = symbol;
        this.baseProbability = baseProbability;
    }

    public char getSymbol() {
        return symbol;
    }
}