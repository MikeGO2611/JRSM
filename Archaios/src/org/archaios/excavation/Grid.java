package org.archaios.excavation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.archaios.regions.Region;
import org.archaios.regions.Countries.ITerDif;
import org.archaios.treasures.ETreasureRarity;
import org.archaios.treasures.Treasure;

/**
 * Representa la cuadrícula de excavación donde interactúa el arqueólogo.
 * Gestiona el estado visual de cada casilla y los tesoros ocultos en ella.
 */
public class Grid {
    /**
     * Matriz bidimensional que representa el terreno actual o el resultado
     * de una celda excavada.
     */
    private String[][] cells;

    /**
     * Matriz bidimensional que almacena los objetos Treasure ocultos en la cuadrícula.
     * Contiene null si la casilla no tiene ningún tesoro asignado.
     */
    private Treasure[][] hiddenTreasures;

    /** Indica las celdas vacías cuya ubicación se ha revelado mediante habilidades. */
    private boolean[][] revealedEmptyCells;

    /** Indica las celdas con tesoros cuya ubicación se ha revelado mediante habilidades. */
    private boolean[][] revealedTreasureLocations;

    /** Terreno actual y coste de excavación de cada nivel. */
    private ETerrainType[][] terrainTypes;
    /** Coste asignado a cada nivel de terreno. */
    private int[][] terrainCosts;
    /** Indica qué casillas ya se han excavado por completo. */
    private boolean[][] excavated;

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
        this(rows, cols, ETerrainShape.S_SQUARE);
    }

    /**
     * Crea una cuadrícula con las dimensiones y la disposición indicadas.
     *
     * @param rows número base de filas
     * @param cols número base de columnas
     * @param shape forma que determina la distribución de las celdas
     */
    public Grid(int rows, int cols, ETerrainShape shape) {
        this(rows, cols, shape, null);
    }

    /**
     * Crea una cuadrícula y distribuye sus terrenos según la región.
     *
     * @param rows número base de filas
     * @param cols número base de columnas
     * @param shape forma que determina la distribución de las celdas
     * @param region región de la excavación
     */
    public Grid(int rows, int cols, ETerrainShape shape, Region region) {
        int[] dimensions = shape.getDimensions(rows, cols);
        this.rows = dimensions[0];
        this.cols = dimensions[1];
        this.cells = new String[this.rows][this.cols];
        this.hiddenTreasures = new Treasure[this.rows][this.cols];
        this.revealedEmptyCells = new boolean[this.rows][this.cols];
        this.revealedTreasureLocations = new boolean[this.rows][this.cols];
        this.terrainTypes = new ETerrainType[this.rows][this.cols];
        this.terrainCosts = new int[this.rows][this.cols];
        this.excavated = new boolean[this.rows][this.cols];
        initializeGrid(region);
    }

    /**
     * Inicializa los terrenos de la cuadrícula y fija su coste para evitar
     * que un coste aleatorio cambie al reintentar una casilla.
     *
     * @param region región que determina los terrenos, o {@code null} para usar tierra básica
     */
    private void initializeGrid(Region region) {
        Random random = new Random();
        ITerDif difficultRegion = region instanceof ITerDif ? (ITerDif) region : null;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                ETerrainType terrain = difficultRegion == null
                        ? ETerrainType.EARTH
                        : chooseTerrain(difficultRegion, random);
                terrainTypes[i][j] = terrain;
                terrainCosts[i][j] = terrain.rollCost(random);
                cells[i][j] = String.valueOf(terrain.getSymbol());
            }
        }
    }

    /**
     * Elige un terreno usando las probabilidades declaradas por la región.
     *
     * @param region región que proporciona las probabilidades
     * @param random generador de números aleatorios
     * @return tipo de terreno elegido
     * @throws IllegalStateException si las probabilidades son inválidas o no hay terrenos definidos
     */
    private ETerrainType chooseTerrain(ITerDif region, Random random) {
        double totalWeight = 0;
        double[] weights = new double[ETerrainType.values().length];
        ETerrainType[] terrainOptions = ETerrainType.values();

        for (int i = 0; i < terrainOptions.length; i++) {
            float weight = region.getTerrainPercent(terrainOptions[i].getRegionSymbol());
            if (Float.isNaN(weight) || weight < 0 || weight > 1) {
                throw new IllegalStateException("Porcentaje de terreno no válido en la región.");
            }
            weights[i] = weight;
            totalWeight += weight;
        }
        if (totalWeight <= 0) {
            throw new IllegalStateException("La región no define terrenos para la excavación.");
        }

        double selection = random.nextDouble() * totalWeight;
        for (int i = 0; i < terrainOptions.length; i++) {
            selection -= weights[i];
            if (selection < 0) {
                return terrainOptions[i];
            }
        }
        return terrainOptions[terrainOptions.length - 1];
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

    /**
     * Imprime la cuadrícula mostrando también el contenido de las celdas aún sin excavar.
     */
    public void printFullyRevealedGrid() {
        printGrid(true);
    }

    /**
     * Imprime la cuadrícula, opcionalmente revelando el contenido de todas sus celdas.
     *
     * @param revealAll indica si se deben mostrar los tesoros y vacíos aún ocultos
     */
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
                if (!excavated[i][j] && revealAll) {
                    cell = hiddenTreasures[i][j] == null
                            ? "x"
                            : getTreasureSymbol(hiddenTreasures[i][j]);
                } else if (!excavated[i][j]) {
                    if (revealedEmptyCells[i][j]) {
                        cell = "E";
                    } else if (revealedTreasureLocations[i][j]) {
                        cell = "?";
                    } else {
                        cell = String.valueOf(terrainTypes[i][j].getSymbol());
                    }
                }
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    /**
     * Cuenta las celdas que contienen tesoros de la rareza especificada,
     * tanto ocultos como ya descubiertos.
     *
     * @param rarity rareza de los tesoros que se contarán
     * @return número de tesoros de esa rareza presentes en la cuadrícula
     */
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

    /**
     * Revela al azar la cantidad indicada de celdas vacías y ubicaciones de tesoros.
     *
     * @param emptyCellCount número de celdas vacías que se revelarán
     * @param treasureLocationCount número de ubicaciones de tesoros que se revelarán
     */
    public void revealSkillClues(int emptyCellCount, int treasureLocationCount) {
        Random random = new Random();
        revealRandomCells(emptyCellCount, false, random);
        revealRandomCells(treasureLocationCount, true, random);
    }

    /**
     * Selecciona y marca al azar celdas aún sin excavar del tipo solicitado.
     *
     * @param count cantidad máxima de celdas que se marcarán
     * @param treasureLocations {@code true} para ubicar tesoros; {@code false} para revelar vacíos
     * @param random generador de números aleatorios que se utilizará
     */
    private void revealRandomCells(int count, boolean treasureLocations, Random random) {
        List<int[]> candidates = new ArrayList<>();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (excavated[row][col]) {
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

    /**
     * Obtiene el símbolo que representa la rareza de un tesoro en la cuadrícula.
     *
     * @param treasure tesoro cuyo símbolo se quiere obtener
     * @return símbolo correspondiente a su rareza
     * @throws IllegalArgumentException si la rareza no está reconocida
     */
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

    /**
     * Comprueba si una coordenada válida contiene un tesoro oculto.
     *
     * @param row índice de fila, desde cero
     * @param col índice de columna, desde cero
     * @return {@code true} si la posición es válida y contiene un tesoro
     */
    boolean hasHiddenTreasure(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols
                && hiddenTreasures[row][col] != null;
    }

    /**
     * Excava un nivel en una coordenada. Si queda otro nivel, lo deja expuesto;
     * si no, marca la celda como vacía o devuelve el tesoro encontrado.
     *
     * @param row Fila a excavar.
     * @param col Columna a excavar.
     * @return El tesoro encontrado en esa posición, o null si estaba vacía.
     */
    public Treasure dig(int row, int col) {
        if (row < 0 || row >= rows || col < 0 || col >= cols || excavated[row][col]) {
            return null;
        }

        ETerrainType nextLayer = terrainTypes[row][col].getNextLayer();
        if (nextLayer != null) {
            terrainTypes[row][col] = nextLayer;
            terrainCosts[row][col] = nextLayer.rollCost(new Random());
            cells[row][col] = String.valueOf(nextLayer.getSymbol());
            return null;
        }

        Treasure found = hiddenTreasures[row][col];
        if (found != null) {
            cells[row][col] = getTreasureSymbol(found);
            hiddenTreasures[row][col] = null; // Eliminar para evitar múltiples recolecciones
        } else {
            cells[row][col] = "x"; // Casilla vacía
        }
        excavated[row][col] = true;
        return found;
    }

    /**
     * Comprueba si una coordenada válida ya fue excavada por completo.
     *
     * @param row índice de fila, desde cero
     * @param col índice de columna, desde cero
     * @return {@code true} si la posición ya fue excavada
     */
    public boolean isDug(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols
                && excavated[row][col];
    }

    /**
     * Obtiene el coste ya asignado al nivel actual de una casilla.
     *
     * @param row índice de fila, desde cero
     * @param col índice de columna, desde cero
     * @return coste de acciones del nivel actual
     */
    public int getTerrainCost(int row, int col) {
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            throw new IndexOutOfBoundsException("Coordenada fuera de la cuadrícula.");
        }
        return terrainCosts[row][col];
    }

    /**
     * Sustituye por tierra básica la cantidad indicada de piedras aleatorias.
     *
     * @param amount cantidad máxima de piedras que se transformarán
     */
    public void demolishStones(int amount) {
        List<int[]> stones = new ArrayList<>();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (!excavated[row][col] && terrainTypes[row][col] == ETerrainType.STONE) {
                    stones.add(new int[] { row, col });
                }
            }
        }

        Random random = new Random();
        for (int i = 0; i < amount && !stones.isEmpty(); i++) {
            int[] position = stones.remove(random.nextInt(stones.size()));
            terrainTypes[position[0]][position[1]] = ETerrainType.EARTH;
            terrainCosts[position[0]][position[1]] = ETerrainType.EARTH.rollCost(random);
            cells[position[0]][position[1]] = String.valueOf(ETerrainType.EARTH.getSymbol());
        }
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

    /**
     * Devuelve el símbolo del nivel de terreno que está expuesto en una casilla.
     *
     * @param row índice de fila, desde cero
     * @param col índice de columna, desde cero
     * @return símbolo del nivel actual
     * @throws IndexOutOfBoundsException si la coordenada queda fuera de la cuadrícula
     */
    public char getTerrainAt(int row, int col) {
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            throw new IndexOutOfBoundsException("Coordenada fuera de la cuadrícula.");
        }
        return terrainTypes[row][col].getSymbol();
    }
}