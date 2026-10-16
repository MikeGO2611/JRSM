package org.archaios.skills;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.archaios.treasures.ETreasureRarity;
import org.archaios.commons.repositories.ICode;
import org.archaios.excavation.ETerrainShape;

/**
 * Clase base abstracta que define las propiedades y comportamientos comunes
 * de las habilidades de los arqueólogos, implementando {@link ICode} para
 * permitir su almacenamiento en el repositorio.
 * 
 * @author Javier Búa Martínez
 */
public abstract class Skill implements ICode {
    /** Nombre identificativo de la habilidad. */
    private String name;

    /** Descripción detallada del efecto de la habilidad. */
    private String description;

    /** Categoría o tipo de la habilidad. */
    private ESkillType type;

    /** Lista de habilidades requeridas previamente para poder aprender esta. */
    private List<Skill> dependencies = new ArrayList<>();

    /** Lista de habilidades incompatibles con esta. */
    private List<Skill> incompatibilities = new ArrayList<>();

    /**
     * Construye una nueva habilidad base.
     * 
     * @param name Nombre identificativo de la habilidad.
     * @param description Descripción detallada de su efecto.
     * @param type Categoría o tipo de la habilidad.
     */
    public Skill(String name, String description, ESkillType type) {
        this.name = name;
        this.description = description;
        this.type = type;
    }

    /**
     * @return Nombre de la habilidad.
     */
    public String getName() {
        return name;
    }

    /**
     * @return Descripción de la habilidad.
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return Tipo o categoría de la habilidad.
     */
    public ESkillType getType() {
        return type;
    }

    /**
     * @return Lista de habilidades requeridas previamente.
     */
    public List<Skill> getDependencies() {
        return dependencies;
    }

    /**
     * @return Lista de habilidades incompatibles con esta.
     */
    public List<Skill> getIncompatibilities() {
        return incompatibilities;
    }

    /**
     * Añade una habilidad como requisito previo para poder aprender esta.
     * 
     * @param skill Habilidad dependiente.
     */
    public void addDependency(Skill skill) {
        this.dependencies.add(skill);
    }

    /**
     * Añade una habilidad incompatible que impedirá aprender esta si ya se posee.
     * 
     * @param skill Habilidad incompatible.
     */
    public void addIncompatibility(Skill skill) {
        this.incompatibilities.add(skill);
    }

    /**
     * Bonificación por defecto de tesoros aleatorios.
     * 
     * @return Cantidad de tesoros aleatorios extra.
     */
    public int getBonusRandomTreasures() {
        return 0;
    }

    /**
     * Bonificación por defecto de tesoros por rareza.
     * 
     * @return Mapa de tesoros por rareza.
     */
    public Map<ETreasureRarity, Integer> getBonusTreasures() {
        return new HashMap<>();
    }

    /**
     * Porcentaje multiplicador de tesoros extra por defecto.
     * 
     * @return Multiplicador de tesoros.
     */
    public float getBonusTreasurePercentage() {
        return 1.0f;
    }

    /**
     * Acciones extra por defecto.
     * 
     * @return Cantidad de acciones adicionales.
     */
    public int getBonusActions() {
        return 0;
    }

    /**
     * Multiplicador de acciones por defecto.
     * 
     * @return Multiplicador de acciones.
     */
    public float getBonusActionsPercentage() {
        return 1.0f;
    }

    /**
     * Casillas vacías reveladas por defecto.
     * 
     * @return Cantidad de casillas vacías descubiertas.
     */
    public int getRevelationEmpty() {
        return 0;
    }

    /**
     * Pistas de tesoro reveladas por defecto.
     * 
     * @return Cantidad de pistas descubiertas.
     */
    public int getRevelationTreasure() {
        return 0;
    }

    /**
     * Formas de terreno desbloqueadas por defecto.
     * 
     * @return Lista de formas de terreno disponibles.
     */
    public List<ETerrainShape> getBonusTerrainShapes() {
        return new ArrayList<>();
    }

    /**
     * Obtiene el código identificativo único para el repositorio.
     * 
     * @return Nombre de la habilidad como identificador.
     */
    @Override
    public String getCode() {
        return this.name;
    }
}