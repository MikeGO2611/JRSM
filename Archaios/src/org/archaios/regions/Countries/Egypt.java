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
                TreasureJSON.loadTreasures("EGP.json"));
    }

    /**
     * Porcentaje de conseguir x terreno.
     * 
     * @param symbol Símbolo del tipo de terreno.
     * @return float del porcentaje (entre 0 y 1) de que salga dado caracter
     */
    @Override
    public float getTerrainPercent(String symbol) {
        switch (symbol) {
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
    /**
     * @return Nombre de la expansión a la que pertenece la región.
     */
    public String getExpansion() {
        return "Imperio de las arenas.";
    }
}
