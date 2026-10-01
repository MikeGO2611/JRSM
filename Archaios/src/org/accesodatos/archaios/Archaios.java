import regions.*;
import regions.Countries.*;
import commons.*;
import treasures.*;

public class Archaios {
    public static void main(String[] args) {
        // pruebas de funcionalidad
        Greece greece = new Greece();
        System.out.println(greece.getRandomTreasure().getName());
        System.out.println(greece.getRandomRarityTreasure(ETreasureRarity.RARE).getName());
    }
}
