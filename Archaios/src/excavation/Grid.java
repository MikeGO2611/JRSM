

import java.util.ArrayList;
import java.util.List;

/**
 * Representa la cuadrícula de excavación, manejando sus dimensiones, 
 * el estado de las casillas y la generación de coordenadas.
 */
public class Grid {
    private final int rows;
    private final int cols;
    private final char[][] cells;
    private final List<String> columnHeaders;

    /**
     * Constructor para inicializar una nueva rejilla.
     *
     * @param rows Número de filas.
     * @param cols Número de columnas.
     */
    public Grid(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.cells = new char[rows][cols];
        this.columnHeaders = new ArrayList<>();
        initializeCells();
        generateHeaders("", 0, cols);
    }

    private void initializeCells() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                cells[i][j] = '.';
            }
        }
    }

    private void generateHeaders(String prefix, int level, int desiredLength) {
        if (columnHeaders.size() == desiredLength) {
            return;
        }
        for (char c = 'A'; c <= 'Z'; c++) {
            if (columnHeaders.size() == desiredLength) break;
            columnHeaders.add(prefix + c);
        }
        if (columnHeaders.size() < desiredLength) {
            generateHeaders(prefix + "A", level + 1, desiredLength);
        }
    }

    /**
     * Obtiene el número total de casillas en la rejilla.
     *
     * @return El número total de casillas.
     */
    public int getTotalCells() {
        return rows * cols;
    }

    /**
     * Actualiza el estado de una casilla específica tras ser excavada.
     *
     * @param row    Índice de la fila.
     * @param col    Índice de la columna.
     * @param symbol Símbolo a establecer ('x', 'C', 'I', 'R').
     */
    public void digCell(int row, int col, char symbol) {
        this.cells[row][col] = symbol;
    }

    /**
     * Imprime el estado actual de la rejilla.
     *
     * @param revealAll Si es true, muestra todos los tesoros ocultos.
     */
    public void printGrid(boolean revealAll) {
        // Implementación de la impresión
    }
}
