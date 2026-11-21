package org.archaios.commons.statistics;

import java.util.ArrayList;
import java.util.List;

import org.archaios.regions.Region;
import org.archaios.treasures.Treasure;

/**
 * Estadística de un terreno, conteniendo el número de visitas, el número de tesoros excavados.
 */
public class TerrainStatEntry {
    /** Región del terreno */
    private Region region;
    /** Número de visitas al terreno */
    private int visits = 1;
    /** Lista de tesoros encontrados en este terreno */
    private List<Treasure> foundTreasureCollection = new ArrayList<>();
    /** Número de veces que se ha excavado un tesoro */
    private int treasureDigCount = 0;

    /**
     * Construtor mediante una región.
     * @param region Region
     */
    public TerrainStatEntry(Region region){
        this.region = region;
        visits = 1;
        treasureDigCount = 0;
    }
    
    /**
     * Construtor mediante una región y el número de veces que se ha excavado.
     * @param region Region
     * @param treasureDigCount int
     */
    public TerrainStatEntry(Region region, int treasureDigCount){
        this(region);
        this.treasureDigCount = treasureDigCount;
    }

    /**
     * Construtor mediante una región, lista de tesoros encontrados y el número de veces que se ha excavado.
     * @param region Region
     * @param foundTreasureCollection {@code List<Treasure>}
     * @param treasureDigCount int
     */
    public TerrainStatEntry(Region region, List<Treasure> foundTreasureCollection, int treasureDigCount){
        this(region, treasureDigCount);
        this.foundTreasureCollection = foundTreasureCollection;
    }

    /**
     * Obtiene la región del TerrainStat
     * @return Region
     */
    public Region getRegion(){
        return this.region;
    }

    /**
     * Añade tesoros a la lista de tesoros encontrados.
     * @param foundTreasures Lista de tesoros encontrados en la excavación.
     */
    public void addTreasures(List<Treasure> foundTreasures){
        visits++;
        if(!foundTreasures.isEmpty()){
            treasureDigCount += foundTreasures.size();

            for (Treasure treasure : foundTreasures) {
                if(!foundTreasureCollection.contains(treasure)) {
                    foundTreasureCollection.add(treasure);
                }
            }

            sortTreasures();
        }
    }

    /**
     * Añade un tesoro cargado desde archivo guardado sin incrementar el contador de visitas.
     * @param treasure Tesoro recuperado de la partida guardada.
     * @author Javier Búa
     */
    public void addLoadedTreasure(Treasure treasure) {
        if (treasure != null) {
            treasureDigCount++;
            if (!foundTreasureCollection.contains(treasure)) {
                foundTreasureCollection.add(treasure);
            }
            sortTreasures();
        }
    }

    /**
     * Obtiene la visitas al terreno
     * @return int
     */
    public int getVisits(){
        return visits;
    }

    /**
     * Setea las visitas al terreno
     * @param visits int
     */
    public void setVisits(int visits){
        this.visits = visits;
    }

    /**
     * Obtiene el número de veces donde se excavó un tesoro en este territorio.
     * @return int
     */
    public int getTreasureDigCount(){
        return treasureDigCount;
    }

    /**
     * Obtiene la lista de todos los tesoros excavados en este territorio.
     * @return {@code List<Treasure>}
     */
    public List<Treasure> getTreasures(){
        return foundTreasureCollection;
    }

    /**
     * Ordena los tesoros.
     */
    private void sortTreasures(){
        foundTreasureCollection.sort(
            (Treasure t1, Treasure t2) -> {
                if(!t1.getName().equals(t2.getName())){
                    return t1.getName().compareTo(t2.getName());
                } else {
                    return t1.getCode().compareTo(t2.getCode());
                }
            }
        );
    }
}
