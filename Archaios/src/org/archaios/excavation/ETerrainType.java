package org.archaios.excavation;

import java.util.Random;

/**
 * Tipos de terreno disponibles durante una excavación.
 */
public enum ETerrainType {
    /** Tierra básica: cuesta una acción y no tiene más niveles. */
    EARTH('·', 1, 1, null),
    /** Terreno compacto: requiere dos acciones para excavarse. */
    COMPACT_GROUND('-', 2, 2, null),
    /** Arena: requiere aleatoriamente una o dos acciones. */
    SAND('~', 1, 2, null),
    /** Piedra: cuesta una acción y deja expuesta la tierra básica. */
    STONE('^', 1, 1, EARTH);

    /** Símbolo visible que identifica el terreno. */
    private final char symbol;
    /** Coste mínimo de excavación, incluido. */
    private final int minCost;
    /** Coste máximo de excavación, incluido. */
    private final int maxCost;
    /** Siguiente nivel que queda expuesto, o {@code null} si no hay otro. */
    private final ETerrainType nextLayer;

    /**
     * Define un terreno y su progresión de niveles.
     *
     * @param symbol símbolo visible del terreno
     * @param minCost coste mínimo de excavación
     * @param maxCost coste máximo de excavación
     * @param nextLayer siguiente nivel, o {@code null} si es el último
     */
    ETerrainType(char symbol, int minCost, int maxCost, ETerrainType nextLayer) {
        this.symbol = symbol;
        this.minCost = minCost;
        this.maxCost = maxCost;
        this.nextLayer = nextLayer;
    }

    /**
     * Devuelve el símbolo visible del terreno.
     *
     * @return símbolo del terreno
     */
    public char getSymbol() {
        return symbol;
    }

    /**
     * Devuelve el nivel que queda debajo de este terreno.
     *
     * @return siguiente nivel o {@code null} si este es el nivel final
     */
    public ETerrainType getNextLayer() {
        return nextLayer;
    }

    /**
     * Calcula aleatoriamente el coste de excavación dentro del rango definido.
     *
     * @param random generador de números aleatorios
     * @return coste entre el mínimo y el máximo, ambos incluidos
     * @throws NullPointerException si {@code random} es {@code null}
     */
    public int rollCost(Random random) {
        return minCost + random.nextInt(maxCost - minCost + 1);
    }

    /**
     * Devuelve el símbolo usado por la región para indicar este terreno.
     *
     * @return símbolo de terreno usado en la distribución regional
     */
    public String getRegionSymbol() {
        return this == EARTH ? "." : String.valueOf(symbol);
    }
}
