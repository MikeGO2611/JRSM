package org.archaios.skills;

import java.util.HashMap;
import java.util.Map;

import org.archaios.treasures.ETreasureRarity;

/**
 * Representa una habilidad orientada a la obtención y mejora de tesoros,
 * permitiendo garantizar tesoros adicionales aleatorios o clasificados por rareza.
 * 
 * @author Javier Búa Martínez
 */
public class TreasureSkill extends Skill {
    /** Cantidad de tesoros aleatorios adicionales garantizados. */
    private int randomTreasures;

    /** Mapa con las cantidades garantizadas de tesoros agrupadas por rareza. */
    private Map<ETreasureRarity, Integer> bonusTreasures = new HashMap<>();
    
    /**
     * Construye una nueva habilidad de tesoro configurando los extras garantizados.
     * 
     * @param name Nombre de la habilidad.
     * @param description Breve descripción de su efecto.
     * @param randomTreasures Cantidad de tesoros aleatorios extra garantizados.
     * @param common Cantidad mínima de tesoros comunes garantizados.
     * @param uncommon Cantidad mínima de tesoros infrecuentes garantizados.
     * @param rare Cantidad mínima de tesoros raros garantizados.
     */
    public TreasureSkill(String name, String description, int randomTreasures, int common, int uncommon, int rare) {
        super(name, description, ESkillType.TREASURE);
        this.randomTreasures = randomTreasures;

        // Solo se registran aquellas rarezas con bonificación positiva
        if (common > 0) {
            bonusTreasures.put(ETreasureRarity.COMMON, common);
        }
        if (uncommon > 0) {
            bonusTreasures.put(ETreasureRarity.UNCOMMON, uncommon);
        }
        if (rare > 0) {
            bonusTreasures.put(ETreasureRarity.RARE, rare);
        }
    }

    /** @return Número de tesoros aleatorios extra. */
    @Override
    public int getBonusRandomTreasures() {
        return this.randomTreasures;
    }

    /** @return Mapa asociando cada rareza con su cantidad garantizada. */
    @Override
    public Map<ETreasureRarity, Integer> getBonusTreasures() {
        return this.bonusTreasures;
    }
}