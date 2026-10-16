package org.archaios.skills;

/**
 * Representa una habilidad de tipo especialista, permitiendo fijar costes
 * de excavación en terrenos concretos o eliminar casillas de un tipo de terreno.
 * 
 * @author Javier Búa Martínez
 */
public class ExpertSkill extends Skill {
    /** Coste fijo de extracción asignado al tipo de terreno correspondiente. */
    private int establishedCost;

    /** Tipo de terreno al que se aplica el coste fijo de excavación. */
    private String costTerrainType;

    /** Cantidad de casillas de terreno eliminadas automáticamente al inicio. */
    private int removedTerrainAmount;

    /** Tipo de terreno que se ve afectado por la eliminación de casillas. */
    private String removedTerrainType;

    /**
     * Construye una nueva habilidad de especialista.
     * 
     * @param name Nombre de la habilidad.
     * @param description Breve descripción de su efecto.
     * @param establishedCost Coste fijo de extracción asignado al terreno indicado.
     * @param costTerrainType Tipo de terreno al que se le aplica el coste fijo.
     * @param removedTerrainAmount Cantidad de casillas de terreno eliminadas al inicio.
     * @param removedTerrainType Tipo de terreno que se elimina.
     */
    public ExpertSkill(String name, String description, int establishedCost, String costTerrainType, int removedTerrainAmount, String removedTerrainType) {
        super(name, description, ESkillType.EXPERT);
        this.establishedCost = establishedCost;
        this.costTerrainType = costTerrainType;
        this.removedTerrainAmount = removedTerrainAmount;
        this.removedTerrainType = removedTerrainType;
    }

    /** @return Coste fijado por la habilidad. */
    public int getEstablishedCost() {
        return this.establishedCost;
    }

    /** @return Nombre o identificador del tipo de terreno. */
    public String getCostTerrainType() {
        return this.costTerrainType;
    }

    /** @return Número de casillas eliminadas. */
    public int getRemovedTerrainAmount() {
        return this.removedTerrainAmount;
    }

    /** @return Nombre o identificador del tipo de terreno eliminado. */
    public String getRemovedTerrainType() {
        return this.removedTerrainType;
    }
}