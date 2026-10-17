package org.archaios.regions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.archaios.commons.repositories.ICode;
import org.archaios.treasures.ETreasureRarity;
import org.archaios.treasures.Treasure;

/**
 * Clase Region
 * Contiene los atributos necesarios para ser almacenado y métodos para obtener tesoros.
 * @author Rubén Hernández
 */
public abstract class Region implements ICode {
    private final String code;
    private final String name;
    /**
     * Lista de tesoros de la región.
     */ 
    protected List<Treasure> treasures = new ArrayList<>();

    /**
     * Constructor de región, dado un código y un nombre.
     * @param code
     * @param name
     */
    public Region(String code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * @return código de la región.
     */
    public String getCode() {
        return code;
    }

    /**
     * @return nombre de la región.
     */
    public String getName() {
        return name;
    }

    /**
     * @return devuelve una lista de todos los tesoros de la región.
     */
    public List<Treasure> getAllTreasures() {
        return treasures;
    }

    /**
     * Getter de todos los tesoros de una rareza especificada.
     * @param rarity
     * @return Lista de tesoros de la rareza especificada.
     */
    public List<Treasure> getRarityTreasures(ETreasureRarity rarity) {
        List<Treasure> toReturn = new ArrayList<>();
        for (Treasure treasure : treasures) {
            if (treasure.getRarity().equals(rarity)) {
                toReturn.add(treasure);
            }
        }
        return toReturn;
    }

    /**
     * Getter de un tesoro aleatorio, independientemente de la rareza.
     * @return Tesoro aleatorio.
     */
    public Treasure getRandomTreasure() {
        Random random = new Random();
        return treasures.get(random.nextInt(treasures.size()));
    }

    /**
     * Devuelve un tesoro aleatorio de la rareza especificada.
     * @param rarity
     * @return Tesoro aleatorio de la rareza específica.
     */
    public Treasure getRandomRarityTreasure(ETreasureRarity rarity) {
        List<Treasure> toReturn = new ArrayList<>();
        for (Treasure treasure : treasures) {
            if (treasure.getRarity().equals(rarity)) {
                toReturn.add(treasure);
            }
        }
        Random random = new Random();
        return toReturn.get(random.nextInt(toReturn.size()));
    }
}
