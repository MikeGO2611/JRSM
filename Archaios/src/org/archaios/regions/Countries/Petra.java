package org.archaios.regions.Countries;

import org.archaios.regions.Region;

/**
 * Clase Petra
 * Contiene los tesoros de la región, así como el código de la misma y métodos
 * de los diferentes terrenos posibles.
 * 
 * @author Rubén Hernández
 */
public class Petra extends Region implements ITerDif {
    /**
     * Código de la región.
     */
    public static final String CODE = "PTR";

    /**
     * Constructor de Petra.
     * Obtiene todos los tesoros del JSON de la región
     */ 

    public Petra() {
        super("PTR", "Petra");
        this.treasures.addAll(
                TreasureJSON.loadTreasures("./treasures/PTR.json"));
    }

    /**
     * Porcentaje de conseguir x terreno.
     * 
     * @param charac caracter del terreno
     * @return float del porcentaje (entre 0 y 1) de que salga dado caracter
     */
    public float getTerrainPercent(String icon) {
        switch (icon) {
            case "^":
                return 0.75F;
            case "~":
                return 0.25F;
        }

        return 0.0F;
    }

    public String getExpansion() {
        return "Imperio de las arenas.";
    }
}