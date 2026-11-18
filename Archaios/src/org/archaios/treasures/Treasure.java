package org.archaios.treasures;

import org.archaios.commons.repositories.ICode;

/**
 * Clase Treasure
 * Contiene los atributos necesarios para ser almacenado.
 * 
 * @author Rubén Hernández
 */
public class Treasure implements ICode {
    private String name;
    private String description;
    private String idCode;
    private ETreasureType type;
    private ETreasureRarity rarity;
    private int parts;
    private int points;

    /**
     * Constructor que genera el código del tesoro a partir de region, type y
     * codeNumber.
     */

    public Treasure(String name, String region, ETreasureType type, String codeNumber, int parts, int points,
            ETreasureRarity rarity, String description) {
        this.name = name;
        if (parts < 10) {
            this.idCode = codeNumber + "_000"+ parts;
        }else if (parts < 100) {
            this.idCode = codeNumber + "_00"+ parts;
        }else if (parts < 1000) {
            this.idCode = codeNumber + "_0"+ parts;
        }else{
            this.idCode = codeNumber + "_"+ parts;
        }
        this.type = type;
        this.rarity = rarity;
        this.parts = parts;
        this.points = points;
        this.description = description;
    }

    /**
     * @return nombre del tesoro.
     */
    public String getName() {
        return name;
    }

    /**
     * @return descripción del tesoro.
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return código del tesoro.
     */
    public String getCode() {
        return idCode;
    }

    /**
     * @return tipo del tesoro.
     */
    public ETreasureType getType() {
        return type;
    }

    /**
     * @return rareza del tesoro.
     */
    public ETreasureRarity getRarity() {
        return rarity;
    }

    /**
     * @return cantidad de partes del tesoro.
     */
    public int getParts() {
        return parts;
    }

    /**
     * @return puntos del tesoro.
     */
    public int getPoints() {
        return points;
    }

}
