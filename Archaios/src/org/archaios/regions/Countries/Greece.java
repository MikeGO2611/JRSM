package org.archaios.regions.Countries;

import org.archaios.regions.Region;

/**
 * Clase Greece
 * Contiene los tesoros de la región, así como el código de la misma.
 * @author Rubén Hernández
 */

public class Greece extends Region {
    /**
     * Código de la región.
     */
    public static final String CODE = "GRC";

    /**
     * Constructor de Greece.
     * Obtiene todos los tesoros del JSON de la región
     */ 
    public Greece() {
        super("GRC", "Grecia");
        this.treasures.addAll(
                TreasureJSON.loadTreasures("./treasures/GRC.json"));
    }

    /**
     * @return código necesario para Region.
     */
    public String getCode() {
        return CODE;
    }
}
