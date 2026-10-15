package org.archaios.treasures;

/**
 * Clase Treasure
 * Contiene los atributos necesarios para ser almacenado.
 * @author Rubén Hernández
 */
public class Treasure {
    private String name;
    private String description;
    private String idCode;
    private ETreasureType type;
    private ETreasureRarity rarity;
    private int parts;
    private int points;

    /**
     * Constructor que genera el código del tesoro a partir de region, type y codeNumber.
     */

    public Treasure(String name, String region, ETreasureType type, String codeNumber, int parts, int points,
            ETreasureRarity rarity, String description) {
        this.name = name;
        this.idCode = region + "_" + type.getCode() + "_" + codeNumber;
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
