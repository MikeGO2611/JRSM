package org.archaios.excavation;

public enum ETerrainShape {
    ROW("Fila"),
    HORIZONTAL("Horizontal"),
    COLUMN("Columna"),
    VERTICAL("Vertical"),
    SQUARE("Cuadrado");

    private final String name;

    ETerrainShape(String name) {
        this.name = name;
    }
}