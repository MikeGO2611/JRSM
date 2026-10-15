package org.archaios.skills;

/**
 * Representa una habilidad orientada a la excavación, proporcionando
 * bonificaciones de acciones directas o multiplicadores sobre las mismas.
 * 
 * @author Javier Búa Martínez
 */
public class ExcavationSkill extends Skill {
    private int extraActions;
    private float multiplier;

    /**
     * Construye una nueva habilidad de excavación.
     * 
     * @param name Nombre de la habilidad.
     * @param description Breve descripción de su efecto.
     * @param extraActions Cantidad fija de acciones adicionales otorgadas.
     * @param multiplier Multiplicador aplicado sobre las acciones disponibles.
     */
    public ExcavationSkill(String name, String description, int extraActions, float multiplier) {
        super(name, description, ESkillType.EXCAVATION);
        this.extraActions = extraActions;
        this.multiplier = multiplier;
    }

    /**
     * Obtiene la cantidad de acciones adicionales fijas que aporta la habilidad.
     * 
     * @return Acciones extra.
     */
    @Override
    public int getBonusActions() {
        return extraActions;
    }

    /**
     * Obtiene el multiplicador porcentual de acciones que aplica la habilidad.
     * 
     * @return Multiplicador de acciones.
     */
    @Override
    public float getBonusActionsPercentage() {
        return multiplier;
    }
}