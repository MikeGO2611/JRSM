package org.archaios.excavation;

/**
 * Enumeración de las categorías de tamaño disponibles para el terreno de excavación.
 * Cada tamaño está asociado a un nombre visible y a un valor de bonificación de experiencia.
 */
public enum ETerrainSize {
    /** Terreno pequeño con una bonificación de experiencia baja. */
    SMALL("Pequeño", 2, 5, 5),
    /** Terreno mediano con una bonificación de experiencia moderada. */
    MEDIUM("Mediano", 5, 10, 10),
    /** Terreno grande con una bonificación de experiencia alta. */
    LARGE("Grande", 10, 15, 15);

    private final String name;
    private final int expBonus;
    private final int rows;
    private final int cols;

    /**
     * Crea un tamaño de terreno.
     *
     * @param name el nombre visible asociado a este tamaño
     * @param expBonus la bonificación de experiencia otorgada por este tamaño de terreno
     * @param rows el número de filas de la cuadrícula
     * @param cols el número de columnas de la cuadrícula
     */
    ETerrainSize(String name, int expBonus, int rows, int cols) {
        this.name = name;
        this.expBonus = expBonus;
        this.rows = rows;
        this.cols = cols;
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

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }
}