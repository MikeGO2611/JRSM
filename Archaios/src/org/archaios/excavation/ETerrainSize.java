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

    /** Nombre localizado del tamaño del terreno. */
    private final String name;
    /** Experiencia adicional otorgada por completar una excavación de este tamaño. */
    private final int expBonus;
    /** Número base de filas de la cuadrícula para este tamaño. */
    private final int rows;
    /** Número base de columnas de la cuadrícula para este tamaño. */
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
    
    /**
     * Devuelve el nombre localizado del tamaño.
     *
     * @return nombre en español
     */
    public String getName() { return this.name; }

    /**
     * Devuelve el número base de filas asociado al tamaño.
     *
     * @return número de filas
     */
    public int getRows() {
        return rows;
    }

    /**
     * Devuelve el número base de columnas asociado al tamaño.
     *
     * @return número de columnas
     */
    public int getCols() {
        return cols;
    }
}