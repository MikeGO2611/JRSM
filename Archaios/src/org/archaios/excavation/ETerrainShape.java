package org.archaios.excavation;

/**
 * Enumeración de los patrones de terreno que se pueden utilizar para organizar las celdas de excavación.
 * Cada constante incluye un nombre visible que se usa en la interfaz o en la serialización.
 */
public enum ETerrainShape {
    /** Terreno organizado en una sola fila. */
    ROW("Fila"),
    /** Terreno organizado de forma horizontal. */
    HORIZONTAL("Horizontal"),
    /** Terreno organizado en una sola columna. */
    COLUMN("Columna"),
    /** Terreno organizado de forma vertical. */
    VERTICAL("Vertical"),
    /** Terreno organizado en una disposición cuadrada. */
    SQUARE("Cuadrado");

    private final String name;

    /**
     * Crea una forma de terreno.
     *
     * @param name el nombre visible asociado a esta forma
     */
    ETerrainShape(String name) {
        this.name = name;
    }

    /**
     * Devuelve el nombre visible de esta forma de terreno.
     *
     * @return el nombre localizado de la forma
     */
    public String getName() {
        return name;
    }
}