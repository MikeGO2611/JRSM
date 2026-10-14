import java.util.ArrayList;
import java.util.List;

public class Grid {
    private final int rows;
    private final int cols;
    private final char[][] cells;
    private final List<String> columnHeaders;

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

    public int getRows() { return rows; }
    public int getCols() { return cols; }
    
    public int getTotalCells() {
        return rows * cols;
    }
    
    public char getCell(int row, int col) {
        return this.cells[row][col];
    }

    public void digCell(int row, int col, char symbol) {
        this.cells[row][col] = symbol;
    }

    public void printGrid(boolean revealAll) {
        System.out.print("   "); 
        for (String header : columnHeaders) {
            System.out.print(header + " ");
        }
        System.out.println();

        for (int i = 0; i < rows; i++) {
            int rowNum = i + 1;
            if (rowNum < 10) {
                System.out.print(rowNum + "  ");
            } else {
                System.out.print(rowNum + " ");
            }

            for (int j = 0; j < cols; j++) {
                char cellValue = cells[i][j];
                if (!revealAll && cellValue != '?' && cellValue != 'x' && cellValue != 'C' && cellValue != 'I' && cellValue != 'R') {
                    System.out.print(". ");
                } else {
                    System.out.print(cellValue + " ");
                }
            }
            System.out.println();
        }
    }
}