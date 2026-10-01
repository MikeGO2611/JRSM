import regions.Countries.*;

import regions.Region;

import commons.repositories.*;
import treasures.*;

public class Archaios {
    public static void main(String[] args) {
        // pruebas de funcionalidad
        Greece greece = new Greece();
        
        Repository<Region> repositorio = new Repository<Region>();
        repositorio.addElement(greece);

        System.out.println(greece.getRandomTreasure().getName());
        System.out.println(greece.getRandomRarityTreasure(ETreasureRarity.RARE).getName());
    }
}
