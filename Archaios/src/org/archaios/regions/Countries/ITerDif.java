package org.archaios.regions.Countries;

/**
 * Interfaz que marca las regiones difíciles y define su expansión y distribución de terrenos.
 */
public interface ITerDif {
    /**
     * @return Nombre de la expansión a la que pertenece la región.
     */
    String getExpansion();

    /**
     * @param symbol Símbolo del tipo de terreno.
     * @return Probabilidad de aparición del terreno entre 0 y 1.
     */
    float getTerrainPercent(String symbol);
}