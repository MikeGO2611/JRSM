package org.archaios.regions.Countries;

import org.archaios.regions.Region;

/**
 * Clase Egypt
 * Contiene los tesoros de la región, así como el código de la misma y métodos
 * de los diferentes terrenos posibles.
 * 
 * @author Rubén Hernández
 */
public class Egypt extends Region implements ITerDif {
    /**
     * Código de la región.
     */
    public static final String CODE = "EGP";

    /**
     * Constructor de Egypt.
     * Obtiene todos los tesoros del JSON de la región
     */
    public Egypt() {
        super("EGP", "Egipto");
        this.treasures.addAll(
                TreasureJSON.loadTreasures("./treasures/EGP.json"));
    }

    /**
     * Porcentaje de conseguir x terreno.
     * 
     * @param charac caracter del terreno
     * @return float del porcentaje (entre 0 y 1) de que salga dado caracter
     */
    public float getTerrainPercent(String charac) {
        switch (charac) {
            case "-":
                return 0.1F;
            case ".":
                return 0.35F;
            case "~":
                return 0.55F;
        }

        return 0.0F;
    }

    /**
     * @return código necesario para Region.
     */
    public String getCode() {
        return CODE;
    }

    public String getExpansion() {
        return "Imperio de las arenas.";
    }
}
