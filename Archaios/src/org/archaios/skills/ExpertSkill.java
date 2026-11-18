package org.archaios.skills;

/**
 * Representa una habilidad de tipo especialista en terrenos difíciles,
 * permitiendo fijar el coste de excavación de un terreno específico o
 * demoler/transformar casillas de un terreno determinado antes de excavar.
 * 
 * @author Javier Búa Martínez
 */
public class ExpertSkill extends Skill {
    /** Símbolo del terreno sobre el que actúa la habilidad. */
    private char targetTerrainSymbol;

    /** Coste fijo de excavación asignado a este terreno. */
    private int establishedCost;

    /** Cantidad de casillas de este terreno transformadas a tierra básica al inicio. */
    private int removedTerrainAmount;

    /**
     * Construye una nueva habilidad de especialista en terrenos difíciles.
     * 
     * @param name Nombre de la habilidad.
     * @param description Breve descripción de su efecto.
     * @param targetTerrainSymbol Símbolo del terreno al que afecta.
     * @param establishedCost Coste fijado de excavación.
     * @param removedTerrainAmount Cantidad de casillas que se transforman a tierra básica.
     */
    public ExpertSkill(String name, String description, char targetTerrainSymbol, int establishedCost, int removedTerrainAmount) {
        super(name, description, ESkillType.EXPERT);
        this.targetTerrainSymbol = targetTerrainSymbol;
        this.establishedCost = establishedCost;
        this.removedTerrainAmount = removedTerrainAmount;
    }

    /**
     * @param terrainSymbol Terreno a excavar.
     * @return El coste de excavar.
     */
    @Override
    public int getTerrainCost(char terrainSymbol) {
        if (this.targetTerrainSymbol == terrainSymbol && this.establishedCost != -1) {
            return this.establishedCost;
        }
        return -1;
    }

    /**
     * @param terrainSymbol Terreno a excavar.
     * @return Cantidad de casillas a transformar.
     */
    @Override
    public int getRemovedTerrainAmount(char terrainSymbol) {
        if (this.targetTerrainSymbol == terrainSymbol) {
            return this.removedTerrainAmount;
        }
        return 0;
    }

    /** @return Símbolo del terreno al que aplica la habilidad. */
    public char getTargetTerrainSymbol() {
        return this.targetTerrainSymbol;
    }

    /** @return Coste fijado de excavación. */
    public int getEstablishedCost() {
        return this.establishedCost;
    }

    /** @return Cantidad de casillas que demuele o transforma. */
    public int getRemovedTerrainAmount() {
        return this.removedTerrainAmount;
    }
}