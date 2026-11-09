package org.archaios.regions.Countries;

import org.archaios.regions.Region;

/**
 * Clase Rome
 * Contiene los tesoros de la región, así como el código de la misma.
 * @author Rubén Hernández
 */

public class Rome extends Region {
    /**
     * Código de la región.
     */
    public static final String CODE = "ROm";

    /**
     * Constructor de Greece.
     * Obtiene todos los tesoros del JSON de la región
     */ 
    public Rome() {
        super("ROM", "Roma");
        this.treasures.addAll(
                TreasureJSON.loadTreasures("./treasures/ROM.json"));
    }

    /**
     * @return código necesario para Region.
     */
    public String getCode() {
        return CODE;
    }
}
