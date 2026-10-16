package org.archaios.skills;

/**
 * Representa una habilidad de tipo revelación, permitiendo desvelar casillas
 * vacías o pistas de tesoros en el terreno antes de comenzar la excavación.
 * 
 * @author Javier Búa Martínez
 */
public class RevelationSkill extends Skill {
    /** Cantidad de casillas vacías descubiertas al inicio del terreno. */
    private int emptyRevealed;

    /** Cantidad de pistas con interrogación de tesoro descubiertas. */
    private int questionMarks;

    /**
     * Construye una nueva habilidad de revelación.
     * 
     * @param name Nombre de la habilidad.
     * @param description Breve descripción de su efecto.
     * @param emptyRevealed Cantidad de casillas vacías desveladas.
     * @param questionMarks Cantidad de pistas con interrogación descubiertas.
     */
    public RevelationSkill(String name, String description, int emptyRevealed, int questionMarks) {
        super(name, description, ESkillType.EXPERT);
        this.emptyRevealed = emptyRevealed;
        this.questionMarks = questionMarks;
    }

    /**
     * Obtiene la cantidad de casillas vacías reveladas.
     * 
     * @return Número de casillas vacías descubiertas.
     */
    @Override
    public int getRevelationEmpty() {
        return emptyRevealed;
    }

    /**
     * Obtiene la cantidad de pistas de interrogación o pistas de tesoro reveladas.
     * 
     * @return Número de pistas desveladas.
     */
    @Override
    public int getRevelationTreasure() {
        return questionMarks;
    }
}