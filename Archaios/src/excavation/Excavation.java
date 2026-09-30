package org.accesodatos.archaios.Excavation;

import java.util.ArrayList;
import java.util.Map;


public class Excavation {

    
    private Map<ETerrainShape, Pair<Integer, Integer>> smallShapes;
    private Map<ETerrainShape, Pair<Integer, Integer>> mediumShapes;
    private Map<ETerrainShape, Pair<Integer, Integer>> bigShapes;
    private ArrayList<Treasure> foundTreasures;
    private ArrayList<ArrayList<Treasure>> grid;
    String aux;
    
    public void generateGrid() {
      
    }

    public void excavateIn(Pair<Integer, Integer> coordinates) {
        // TODO: Implementar lógica
    }

    public void excavationFinalization() {
        // TODO: Implementar lógica
    }
}
