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
                TreasureJSON.loadTreasures("PTR.json"));
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
            case "^":
                return 0.75F;
            case "~":
                return 0.25F;
        }

        return 0.0F;
    }
    /**
     * @return Nombre de la expansión a la que pertenece la región.
     */
    public String getExpansion() {
        return "Imperio de las arenas.";
    }
}