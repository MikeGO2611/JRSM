public enum TerrainShape {
    ROW("Fila"),
    HORIZONTAL("Horizontal"),
    COLUMN("Columna"),
    VERTICAL("Vertical"),
    SQUARE("Cuadrado");

    private final String name;

    TerrainShape(String name) {
        this.name = name;
    }
}