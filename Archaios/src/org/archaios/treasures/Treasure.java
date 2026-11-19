package org.archaios.treasures;

import java.util.Random;

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
    private String codeNumber;
    private int part;
    private ETreasureType type;
    private ETreasureRarity rarity;
    private int parts;
    private int points;

    /**
     * Constructor que genera el código del tesoro a partir de region, type y
     * codeNumber.
     */

    public Treasure(String name, String region, ETreasureType type, String codeNumber, int parts, int part, int points,
            ETreasureRarity rarity, String description) {
        this.name = name;
        this.type = type;
        this.codeNumber = codeNumber;
        this.part = part;
        this.rarity = rarity;
        this.parts = parts;
        this.points = points;
        this.description = description;
    }

    /**
     * Asignar el número concreto de la parte del tesoro
     * @param part int número de la parte
     * @author Miguel González Obenza
     */
    public void setPartNumber(int part){
        this.part = part;
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
     * Genera el código en base a los atributos
     * @return String código del tesoro
     * @author Miguel González Obenza
     */
    public String getCode() {
        if (part < 10) {
            return codeNumber + "_000"+ part;
        }else if (part < 100) {
           return codeNumber + "_00"+ part;
        }else if (part < 1000) {
            return codeNumber + "_0"+ part;
        }else{
            return codeNumber + "_"+ part;
        }
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

    /**
     * @return
     */
    public void generateRandomPart(){
        Random random = new Random();
        this.part = random.nextInt(1, parts+1);
    }

}
