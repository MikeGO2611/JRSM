package org.archaios.regions.Countries;

import org.archaios.regions.Region;

/**
 * Clase Iberia
 * Contiene los tesoros de la región, así como el código de la misma.
 * @author Rubén Hernández
 */

public class Iberia extends Region {
    /**
     * Código de la región.
     */
    public static final String CODE = "IBR";

    /**
     * Constructor de Greece.
     * Obtiene todos los tesoros del JSON de la región
     */ 
    public Iberia() {
        super("IBR", "Iberia");
        this.treasures.addAll(
                TreasureJSON.loadTreasures("./treasures/IBR.json"));
    }

    /**
     * @return código necesario para Region.
     */
    public String getCode() {
        return CODE;
    }
}
