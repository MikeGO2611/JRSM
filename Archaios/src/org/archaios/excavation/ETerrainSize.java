package org.archaios.excavation;

/**
 * Enumeración de las categorías de tamaño disponibles para el terreno de excavación.
 * Cada tamaño está asociado a un nombre visible y a un valor de bonificación de experiencia.
 */
public enum ETerrainSize {
    /** Terreno pequeño con una bonificación de experiencia baja. */
    SMALL("Pequeño", 2),
    /** Terreno mediano con una bonificación de experiencia moderada. */
    MEDIUM("Mediano", 5),
    /** Terreno grande con una bonificación de experiencia alta. */
    LARGE("Grande", 10);

    private final String name;
    private final int expBonus;

    /**
     * Crea un tamaño de terreno.
     *
     * @param name el nombre visible asociado a este tamaño
     * @param expBonus la bonificación de experiencia otorgada por este tamaño de terreno
     */
    ETerrainSize(String name, int expBonus) {
        this.name = name;
        this.expBonus = expBonus;
    }

    /**
     * Devuelve la bonificación de experiencia de este tamaño de terreno.
     *
     * @return la bonificación de experiencia otorgada por este tamaño
     */
    public int getExpBonus() {
        return expBonus;
    }
    
    /** @return Devuelve el nombre en español */
    public String getName() { return this.name; }
}