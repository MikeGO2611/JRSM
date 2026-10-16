package org.archaios.skills;

import java.util.List;

import org.archaios.excavation.ETerrainShape;

/**
 * Representa una habilidad que desbloquea nuevas formas de excavación de terreno.
 * 
 * @author Javier Búa Martínez
 */
public class TerrainSkill extends Skill {
    /** Lista de formas geométricas de terreno que desbloquea esta habilidad. */
    private List<ETerrainShape> shapes;

    /**
     * Construye una nueva habilidad de terreno.
     * 
     * @param name Nombre de la habilidad.
     * @param description Breve descripción de su efecto.
     * @param shapes Lista de formas geométricas de terreno que desbloquea.
     */
    public TerrainSkill(String name, String description, List<ETerrainShape> shapes) {
        super(name, description, ESkillType.TERRAIN);
        this.shapes = shapes;
    }

    /**
     * Devuelve las formas de terreno asociadas a esta habilidad.
     * 
     * @return Lista de formas de terreno desbloqueadas.
     */
    @Override
    public List<ETerrainShape> getBonusTerrainShapes() {
        return shapes;
    }
}