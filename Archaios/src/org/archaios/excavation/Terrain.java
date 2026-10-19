package org.archaios.excavation;

/**
 * Representa una configuración de terreno utilizada durante la excavación.
 * Un terreno define su tamaño, forma, dimensiones y umbrales de rareza.
 */
public class Terrain {
    private final ETerrainSize size;
    private final ETerrainShape shape;
    private final int rows;
    private final int cols;
    private final int minCommon;
    private final int minUncommon;
    private final int minRare;

    /**
     * Crea una nueva definición de terreno.
     *
     * @param size la categoría de tamaño del terreno
     * @param shape la forma de disposición del terreno
     * @param rows el número de filas del terreno
     * @param cols el número de columnas del terreno
     * @param minCommon el valor mínimo para recursos comunes
     * @param minUncommon el valor mínimo para recursos poco comunes
     * @param minRare el valor mínimo para recursos raros
     */
    public Terrain(ETerrainSize size, ETerrainShape shape, int rows, int cols, int minCommon, int minUncommon, int minRare) {
        this.size = size;
        this.shape = shape;
        this.rows = rows;
        this.cols = cols;
        this.minCommon = minCommon;
        this.minUncommon = minUncommon;
        this.minRare = minRare;
    }

    /**
     * Devuelve el número de filas del terreno.
     *
     * @return el número de filas
     */
    public int getRows() { return rows; }

    /**
     * Devuelve el número de columnas del terreno.
     *
     * @return el número de columnas
     */
    public int getCols() { return cols; }

    /**
     * Devuelve la categoría de tamaño del terreno.
     *
     * @return el tamaño del terreno
     */
    public ETerrainSize getSize() { return size; }

    /**
     * Devuelve la forma de disposición del terreno.
     *
     * @return la forma del terreno
     */
    public ETerrainShape getShape() { return shape; }

    /**
     * Devuelve el umbral mínimo para recursos comunes.
     *
     * @return el mínimo para rareza común
     */
    public int getMinCommon() { return minCommon; }

    /**
     * Devuelve el umbral mínimo para recursos poco comunes.
     *
     * @return el mínimo para rareza poco común
     */
    public int getMinUncommon() { return minUncommon; }

    /**
     * Devuelve el umbral mínimo para recursos raros.
     *
     * @return el mínimo para rareza rara
     */
    public int getMinRare() { return minRare; }
}