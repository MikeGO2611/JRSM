package org.archaios.regions.Countries;

import org.archaios.regions.Region;
import org.archaios.treasures.ETreasureRarity;
import org.archaios.treasures.ETreasureType;
import org.archaios.treasures.Treasure;

/**
 * Clase Petra
 * Contiene los tesoros de la región, así como el código de la misma y métodos de los diferentes terrenos posibles.
 * @author Rubén Hernández
 */
public class Petra extends Region  {
    /**
     * Código de la región.
     */
    public static final String CODE = "PTR";

    /**
     * Constructor de Petra.
     * Al crearse crea todos los tesoros de la región y los añade a la lista de la clase padre. 
     */ 

    public Petra() {
        super("PTR", "Petra");
        this.treasures.add(new Treasure("Séquel", "PTR", ETreasureType.COIN, "001", 1, 1, ETreasureRarity.COMMON, "Moneda de bronce acuñada por los nabateos durante el reinado de Areta IV y Shaqilat."));
        this.treasures.add(new Treasure("Sela", "PTR", ETreasureType.COIN, "002", 1, 1, ETreasureRarity.COMMON, "Moneda de plata nabatea acuñada por el general Syllaeus que representaba 1/4 de Séquel."));
        this.treasures.add(new Treasure("Ma'ah", "PTR", ETreasureType.COIN, "003", 1, 1, ETreasureRarity.COMMON, "Moneda de plata nabatea acuñada por el rey Obodas II que representaba 1/24 de Séquel."));
        this.treasures.add(new Treasure("Cuenta de cristal", "PTR", ETreasureType.BEAD, "001", 1, 1, ETreasureRarity.COMMON, "Cuentas de cristal transparente."));
        this.treasures.add(new Treasure("Cuenta de turquesa", "PTR", ETreasureType.BEAD, "002", 1, 1, ETreasureRarity.COMMON, "Fragmentos de turquesa usados para decorar joyas."));
        this.treasures.add(new Treasure("Collar de Jamila", "PTR", ETreasureType.JEWELRY, "001", 10, 5, ETreasureRarity.RARE, "Collar hecho con cuentas de de nácar, piedra caliza roja, turquesas y hematita."));
        this.treasures.add(new Treasure("Plato decorado", "PTR", ETreasureType.CERAMIC, "001", 20, 1, ETreasureRarity.COMMON, "Plato cerámico adornado al estilo nabateo."));
        this.treasures.add(new Treasure("Cuenco decorado", "PTR", ETreasureType.CERAMIC, "002", 14, 1, ETreasureRarity.COMMON, "Cuenco cerámico adornado con dibujos al estilo nabateo."));
        this.treasures.add(new Treasure("Olla dos asas", "PTR", ETreasureType.CERAMIC, "003", 22, 5, ETreasureRarity.RARE, "Jarra nabatea con dos asas procedente de la ciudad de Petra."));
        this.treasures.add(new Treasure("Tubería de agua", "PTR", ETreasureType.CERAMIC, "004", 23, 3, ETreasureRarity.UNCOMMON, "Tubería cerámica utilizada para transportar el agua largas distancias."));
        this.treasures.add(new Treasure("Arco compuesto", "PTR", ETreasureType.EQUIP, "001", 15, 3, ETreasureRarity.UNCOMMON, "Arco utilizado por los nabateos para disparar desde camellos."));
        this.treasures.add(new Treasure("Escudo nabateo", "PTR", ETreasureType.EQUIP, "002", 35, 6, ETreasureRarity.RARE, "Escudo de bronce utilizado por la infantería nabatea."));
        this.treasures.add(new Treasure("Spatha", "PTR", ETreasureType.EQUIP, "003", 25, 5, ETreasureRarity.RARE, "Espada utilizada durante el periodo romano de nabatea."));
        this.treasures.add(new Treasure("Azagaya", "PTR", ETreasureType.EQUIP, "004", 20, 3, ETreasureRarity.UNCOMMON, "Lanza pequeña que se arrojaba mediante un palo propulsor."));
        this.treasures.add(new Treasure("Betilo Hayyan", "PTR", ETreasureType.STELE, "001", 24, 1, ETreasureRarity.COMMON, "Piedra sagrada rectangular tallada con rasgos humanos representando a la diosa Hayyan."));
        this.treasures.add(new Treasure("Betilo Al-Uzza", "PTR", ETreasureType.STELE, "002", 24, 1, ETreasureRarity.COMMON, "Betilo con ojos estrellados representando a una diosa celeste."));
        this.treasures.add(new Treasure("Inscripción de Namara", "PTR", ETreasureType.STELE, "003", 48, 1, ETreasureRarity.COMMON, "Inscripción nabatea en piedra, posiblemente una lápida."));
        this.treasures.add(new Treasure("Reloj de sol", "PTR", ETreasureType.FRAGMENT, "001", 20, 3, ETreasureRarity.UNCOMMON, "Reloj de sol tallado de un bloque de arenisca."));
        this.treasures.add(new Treasure("Mosaico Maqueronte", "PTR", ETreasureType.TILE, "001", 625, 1, ETreasureRarity.COMMON, "Mosaico inspirado en el de los baños de la fortaleza de Maqueronte."));
    }

    /**
     * Porcentaje de conseguir x terreno.
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
}