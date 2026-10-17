package org.archaios.excavation;

import java.util.ArrayList;
import java.util.List;
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
        this.rows = rows;
        this.cols = cols;
        this.cells = new String[rows][cols];
        this.hiddenTreasures = new Treasure[rows][cols];
        initializeGrid();
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
        List<String> letters = getColumnLetters();
        System.out.print("   ");
        for (String letter : letters) {
            System.out.print(letter + " ");
        }
        System.out.println();
        
        for (int i = 0; i < rows; i++) {
            System.out.printf("%2d ", (i + 1));
            for (int j = 0; j < cols; j++) {
                System.out.print(cells[i][j] + " ");
            }
            System.out.println();
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
            ETreasureRarity rarity = found.getRarity();
            switch (rarity) {
                case COMMON: cells[row][col] = "C"; break;
                case UNCOMMON: cells[row][col] = "I"; break;
                case RARE: cells[row][col] = "R"; break;
            }
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