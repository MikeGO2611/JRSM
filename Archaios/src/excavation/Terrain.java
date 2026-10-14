public class Terrain {
    private final TerrainSize size;
    private final TerrainShape shape;
    private final int rows;
    private final int cols;
    private final int minCommon;
    private final int minUncommon;
    private final int minRare;

    public Terrain(TerrainSize size, TerrainShape shape, int rows, int cols, int minCommon, int minUncommon, int minRare) {
        this.size = size;
        this.shape = shape;
        this.rows = rows;
        this.cols = cols;
        this.minCommon = minCommon;
        this.minUncommon = minUncommon;
        this.minRare = minRare;
    }

    public int getRows() { return rows; }
    public int getCols() { return cols; }
    public TerrainSize getSize() { return size; }
    public TerrainShape getShape() { return shape; }
    public int getMinCommon() { return minCommon; }
    public int getMinUncommon() { return minUncommon; }
    public int getMinRare() { return minRare; }
}