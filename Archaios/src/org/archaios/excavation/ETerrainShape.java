package org.archaios.excavation;

/**
 * Enumeración de los patrones de terreno que se pueden utilizar para organizar las celdas de excavación.
 * Cada constante incluye un nombre visible que se usa en la interfaz o en la serialización.
 */
public enum ETerrainShape {
    /** Terreno organizado en una sola fila. */
    S_ROW("Fila", new int[][] { { 5, 3 }, { 20, 5 }, { 20, 10 } }),
    /** Terreno organizado de forma horizontal. */
    S_HORIZONTAL("Horizontal", new int[][] { { 10, 2 }, { 30, 3 }, { 40, 5 } }),
    /** Terreno organizado en una sola columna. */
    S_COLUMN("Columna", new int[][] { { 3, 5 }, { 5, 20 }, { 10, 20 } }),
    /** Terreno organizado de forma vertical. */
    S_VERTICAL("Vertical", new int[][] { { 2, 10 }, { 3, 30 }, { 5, 40 } }),
    /** Terreno organizado en una disposición cuadrada. */
    S_SQUARE("Cuadrado", new int[][] { { 5, 5 }, { 10, 10 }, { 15, 15 } });

    /** Nombre localizado de la forma del terreno. */
    private final String name;
    /** Dimensiones (filas, columnas) para terrenos pequeño, mediano y grande. */
    private final int[][] dimensions;

    /**
     * Crea una forma de terreno.
     *
     * @param name el nombre visible asociado a esta forma
     * @param dimensions dimensiones para los tamaños pequeño, mediano y grande
     */
    ETerrainShape(String name, int[][] dimensions) {
        this.name = name;
        this.dimensions = dimensions;
    }

    /**
     * Devuelve las dimensiones de la cuadrícula para el tamaño base indicado.
     *
     * @param baseRows filas del tamaño de terreno
     * @param baseCols columnas del tamaño de terreno
     * @return arreglo con filas y columnas de la cuadrícula
     */
    public int[] getDimensions(int baseRows, int baseCols) {
        if (baseRows == 5 && baseCols == 5) {
            return dimensions[0].clone();
        }
        if (baseRows == 10 && baseCols == 10) {
            return dimensions[1].clone();
        }
        if (baseRows == 15 && baseCols == 15) {
            return dimensions[2].clone();
        }

        int totalCells = baseRows * baseCols;
        switch (this) {
            case S_ROW:
                return new int[] { 1, totalCells };
            case S_COLUMN:
                return new int[] { totalCells, 1 };
            case S_HORIZONTAL:
                int horizontalRows = Math.max(1, (int) Math.sqrt(totalCells / 2.0));
                int horizontalCols = (int) Math.ceil((double) totalCells / horizontalRows);
                return new int[] { horizontalRows, horizontalCols };
            case S_VERTICAL:
                int verticalCols = Math.max(1, (int) Math.sqrt(totalCells / 2.0));
                int verticalRows = (int) Math.ceil((double) totalCells / verticalCols);
                return new int[] { verticalRows, verticalCols };
            case S_SQUARE:
            default:
                return new int[] { baseRows, baseCols };
        }
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