package org.archaios.excavation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.archaios.treasures.ETreasureRarity;
import org.archaios.treasures.Treasure;

/**
 * Representa la cuadrícula de excavación donde interactúa el arqueólogo.
 * Gestiona el estado visual de cada casilla y los tesoros ocultos en ella.
 */
public class Grid {
    /**
     * Matriz bidimensional que representa el estado visual de cada celda 
     * (e.g., "." para sin excavar, "x" para vacío, "C" para común).
     */
    private String[][] cells;

    /**
     * Matriz bidimensional que almacena los objetos Treasure ocultos en la cuadrícula.
     * Contiene null si la casilla no tiene ningún tesoro asignado.
     */
    private Treasure[][] hiddenTreasures;

    private boolean[][] revealedEmptyCells;

    private boolean[][] revealedTreasureLocations;

    /**
     * Número de filas de la cuadrícula.
     */
    private int rows;

    /**
     * Número de columnas de la cuadrícula.
     */
    private int cols;

    /**
     * Constructor de la clase Grid.
     * Inicializa las matrices visual y de tesoros según las dimensiones dadas.
     *
     * @param rows Número de filas del terreno.
     * @param cols Número de columnas del terreno.
     */
    public Grid(int rows, int cols) {
        this(rows, cols, ETerrainShape.SQUARE);
    }

    public Grid(int rows, int cols, ETerrainShape shape) {
        int[] dimensions = getDimensions(rows, cols, shape);
        this.rows = dimensions[0];
        this.cols = dimensions[1];
        this.cells = new String[this.rows][this.cols];
        this.hiddenTreasures = new Treasure[this.rows][this.cols];
        this.revealedEmptyCells = new boolean[this.rows][this.cols];
        this.revealedTreasureLocations = new boolean[this.rows][this.cols];
        initializeGrid();
    }

    private int[] getDimensions(int baseRows, int baseCols, ETerrainShape shape) {
        if (baseRows == 5 && baseCols == 5) {
            switch (shape) {
                case ROW:
                    return new int[] { 5, 3 };
                case HORIZONTAL:
                    return new int[] { 10, 2 };
                case COLUMN:
                    return new int[] { 3, 5 };
                case VERTICAL:
                    return new int[] { 2, 10 };
                case SQUARE:
                default:
                    return new int[] { 5, 5 };
            }
        }
        if (baseRows == 10 && baseCols == 10) {
            switch (shape) {
                case ROW:
                    return new int[] { 20, 5 };
                case HORIZONTAL:
                    return new int[] { 30, 3 };
                case COLUMN:
                    return new int[] { 5, 20 };
                case VERTICAL:
                    return new int[] { 3, 30 };
                case SQUARE:
                default:
                    return new int[] { 10, 10 };
            }
        }
        if (baseRows == 15 && baseCols == 15) {
            switch (shape) {
                case ROW:
                    return new int[] { 20, 10 };
                case HORIZONTAL:
                    return new int[] { 40, 5 };
                case COLUMN:
                    return new int[] { 10, 20 };
                case VERTICAL:
                    return new int[] { 5, 40 };
                case SQUARE:
                default:
                    return new int[] { 15, 15 };
            }
        }

        int totalCells = baseRows * baseCols;
        switch (shape) {
            case ROW:
                return new int[] { 1, totalCells };
            case COLUMN:
                return new int[] { totalCells, 1 };
            case HORIZONTAL:
                int horizontalRows = Math.max(1, (int) Math.sqrt(totalCells / 2.0));
                int horizontalCols = (int) Math.ceil((double) totalCells / horizontalRows);
                return new int[] { horizontalRows, horizontalCols };
            case VERTICAL:
                int verticalCols = Math.max(1, (int) Math.sqrt(totalCells / 2.0));
                int verticalRows = (int) Math.ceil((double) totalCells / verticalCols);
                return new int[] { verticalRows, verticalCols };
            case SQUARE:
            default:
                return new int[] { baseRows, baseCols };
        }
    }

    /**
     * Inicializa visualmente la cuadrícula llenando todas las celdas 
     * con el carácter "." que representa una zona sin excavar.
     */
    private void initializeGrid() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                cells[i][j] = "."; // Casilla sin excavar
            }
        }
    }

    /**
     * Método recursivo para generar combinaciones de letras para las columnas 
     * (A, B, C... Z, AA, AB...).
     *
     * @param current La cadena de letras actual generada en la recursión.
     * @param length  La longitud deseada para la combinación de letras actual.
     * @param result  Lista donde se almacenan las combinaciones generadas.
     * @param maxCols Número máximo de columnas requeridas, que actúa como condición de parada.
     */
    private void generateLetters(String current, int length, List<String> result, int maxCols) {
        if (result.size() >= maxCols) return;
        
        if (current.length() == length) {
            result.add(current);
            return;
        }
        
        for (char c = 'A'; c <= 'Z'; c++) {
            generateLetters(current + c, length, result, maxCols);
            if (result.size() >= maxCols) return;
        }
    }

    /**
     * Obtiene una lista con las etiquetas alfabéticas de las columnas de la cuadrícula,
     * utilizando un método recursivo para su generación.
     *
     * @return Lista de cadenas con las letras correspondientes a cada columna.
     */
    public List<String> getColumnLetters() {
        List<String> letters = new ArrayList<>();
        int length = 1;
        while (letters.size() < cols) {
            generateLetters("", length, letters, cols);
            length++;
        }
        return letters;
    }

    /**
     * Imprime el estado actual de la cuadrícula por la salida estándar, 
     * incluyendo las coordenadas alfabéticas (columnas) y numéricas (filas).
     */
    public void printGrid() {
        printGrid(false);
    }

    public void printFullyRevealedGrid() {
        printGrid(true);
    }

    private void printGrid(boolean revealAll) {
        List<String> letters = getColumnLetters();
        System.out.print("   ");
        for (String letter : letters) {
            System.out.print(letter + " ");
        }
        System.out.println();
        
        for (int i = 0; i < rows; i++) {
            System.out.printf("%2d ", (i + 1));
            for (int j = 0; j < cols; j++) {
                String cell = cells[i][j];
                if (revealAll && ".".equals(cell)) {
                    cell = hiddenTreasures[i][j] == null
                            ? "x"
                            : getTreasureSymbol(hiddenTreasures[i][j]);
                } else if (".".equals(cell)) {
                    if (revealedEmptyCells[i][j]) {
                        cell = "E";
                    } else if (revealedTreasureLocations[i][j]) {
                        cell = "?";
                    }
                }
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    public int countTreasures(ETreasureRarity rarity) {
        String symbol = rarity.getCode();
        int count = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (symbol.equals(cells[row][col])
                        || (hiddenTreasures[row][col] != null
                                && hiddenTreasures[row][col].getRarity() == rarity)) {
                    count++;
                }
            }
        }
        return count;
    }

    public void revealSkillClues(int emptyCellCount, int treasureLocationCount) {
        Random random = new Random();
        revealRandomCells(emptyCellCount, false, random);
        revealRandomCells(treasureLocationCount, true, random);
    }

    private void revealRandomCells(int count, boolean treasureLocations, Random random) {
        List<int[]> candidates = new ArrayList<>();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (!".".equals(cells[row][col])) {
                    continue;
                }
                if (treasureLocations ? hiddenTreasures[row][col] != null
                        : hiddenTreasures[row][col] == null) {
                    candidates.add(new int[] { row, col });
                }
            }
        }

        for (int i = 0; i < count && !candidates.isEmpty(); i++) {
            int[] position = candidates.remove(random.nextInt(candidates.size()));
            if (treasureLocations) {
                revealedTreasureLocations[position[0]][position[1]] = true;
            } else {
                revealedEmptyCells[position[0]][position[1]] = true;
            }
        }
    }

    private String getTreasureSymbol(Treasure treasure) {
        switch (treasure.getRarity()) {
            case COMMON: return "C";
            case UNCOMMON: return "I";
            case RARE: return "R";
            default: throw new IllegalArgumentException("Rareza de tesoro desconocida.");
        }
    }

    /**
     * Coloca un tesoro oculto en una coordenada específica de la cuadrícula.
     *
     * @param row      Fila donde se colocará el tesoro (0 indexado).
     * @param col      Columna donde se colocará el tesoro (0 indexado).
     * @param treasure Objeto Treasure a ocultar.
     */
    public void setHiddenTreasure(int row, int col, Treasure treasure) {
        if (row >= 0 && row < rows && col >= 0 && col < cols) {
            this.hiddenTreasures[row][col] = treasure;
        }
    }

    boolean hasHiddenTreasure(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols
                && hiddenTreasures[row][col] != null;
    }

    /**
     * Realiza la acción de excavar en una coordenada específica.
     * Actualiza el estado visual de la celda a "x" (vacío) o a la inicial 
     * de la rareza del tesoro (C, I, R) si se encuentra algo.
     *
     * @param row Fila a excavar.
     * @param col Columna a excavar.
     * @return El tesoro encontrado en esa posición, o null si estaba vacía.
     */
    public Treasure dig(int row, int col) {
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            return null;
        }

        Treasure found = hiddenTreasures[row][col];
        if (found != null) {
            cells[row][col] = getTreasureSymbol(found);
            hiddenTreasures[row][col] = null; // Eliminar para evitar múltiples recolecciones
        } else {
            cells[row][col] = "x"; // Casilla vacía
        }
        return found;
    }

    /**
     * Obtiene el número total de filas de la cuadrícula.
     *
     * @return Cantidad de filas.
     */
    public int getRows() { return rows; }

    /**
     * Obtiene el número total de columnas de la cuadrícula.
     *
     * @return Cantidad de columnas.
     */
    public int getCols() { return cols; }

    /**
     * Calcula y obtiene el número total de celdas que conforman la cuadrícula.
     *
     * @return Total de celdas (filas * columnas).
     */
    public int getTotalCells() { return rows * cols; }
}