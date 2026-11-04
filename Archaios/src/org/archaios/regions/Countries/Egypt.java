package org.archaios.regions.Countries;

import org.archaios.regions.Region;
import org.archaios.treasures.ETreasureRarity;
import org.archaios.treasures.ETreasureType;
import org.archaios.treasures.Treasure;

/**
 * Clase Egypt
 * Contiene los tesoros de la región, así como el código de la misma y métodos de los diferentes terrenos posibles.
 * @author Rubén Hernández
 */
public class Egypt extends Region {
    /**
     * Código de la región.
     */
    public static final String CODE = "EGP";

    /**
     * Constructor de Egypt.
     * Al crearse crea todos los tesoros de la región y los añade a la lista de la clase padre. 
     */ 
    public Egypt() {
        super("EGP", "Egipto");
        this.treasures.add(new Treasure("Deben de cobre", "EGP", ETreasureType.COIN, "001", 1, 1, ETreasureRarity.COMMON, "Pieza de cobre con un peso estandarizado para el trueque."));
        this.treasures.add(new Treasure("Deben de plata", "EGP", ETreasureType.COIN, "002", 1, 1, ETreasureRarity.COMMON, "Pieza de plata con un peso estandarizado para el trueque."));
        this.treasures.add(new Treasure("Cuenta de lapislázuli", "EGP", ETreasureType.BEAD, "001", 1, 1, ETreasureRarity.COMMON, "Lapislázuli tallado como cuenta para un collar o decoración."));
        this.treasures.add(new Treasure("Anj", "EGP", ETreasureType.JEWELRY, "001", 2, 2, ETreasureRarity.UNCOMMON, "Símbolo de la vida ampliamente utilizado como talismán protector."));
        this.treasures.add(new Treasure("Pluma de Ma'at", "EGP", ETreasureType.JEWELRY, "002", 3, 5, ETreasureRarity.RARE, "Símbolo protector de la verdad y la justicia."));
        this.treasures.add(new Treasure("Pilar Dyed", "EGP", ETreasureType.JEWELRY, "003", 3, 2, ETreasureRarity.UNCOMMON, "Símbolo de estabilidad utilizado como amuleto protector."));
        this.treasures.add(new Treasure("Udyat", "EGP", ETreasureType.JEWELRY, "004", 5, 3, ETreasureRarity.UNCOMMON, "El Ojo de Horus, símbolo solar de orden y completitud."));
        this.treasures.add(new Treasure("Ureo", "EGP", ETreasureType.JEWELRY, "005", 6, 6, ETreasureRarity.RARE, "Emblema protector de los faraones con forma de cobra erguida."));
        this.treasures.add(new Treasure("Escarabeo", "EGP", ETreasureType.JEWELRY, "006", 2, 2, ETreasureRarity.UNCOMMON, "Símbolo de resurrección, es el amuleto más común del Antiguo Egipto."));
        this.treasures.add(new Treasure("Wadj", "EGP", ETreasureType.JEWELRY, "007", 3, 2, ETreasureRarity.UNCOMMON, "Amuleto con forma de pilar de papiro que representaba la juventud."));
        this.treasures.add(new Treasure("Ánfora de vino", "EGP", ETreasureType.CERAMIC, "001", 14, 1, ETreasureRarity.COMMON, "Ánfora con forma puntiaguda para guardar el vino."));
        this.treasures.add(new Treasure("Vasija del Nilo", "EGP", ETreasureType.CERAMIC, "002", 14, 1, ETreasureRarity.COMMON, "Vasija creada con arcilla roja y limo negro del Nilo."));
        this.treasures.add(new Treasure("Vasija de marga", "EGP", ETreasureType.CERAMIC, "003", 12, 3, ETreasureRarity.UNCOMMON, "Vasija creada con marga, dándole un acabado arenoso."));
        this.treasures.add(new Treasure("Vasija de fondo blanco", "EGP", ETreasureType.CERAMIC, "004", 18, 1, ETreasureRarity.UNCOMMON, "Cerámica fina pintada de blanco sobre la que dibujan figuras y símbolos."));
        this.treasures.add(new Treasure("Ushebti", "EGP", ETreasureType.CERAMIC, "005", 12, 5, ETreasureRarity.RARE, "Pequeña estatua dejada como ofrenda en las tumbas para hacer de sirviente en la otra vida."));
        this.treasures.add(new Treasure("Khopesh", "EGP", ETreasureType.EQUIP, "001", 18, 4, ETreasureRarity.UNCOMMON, "Espada de hoja curva con forma de C."));
        this.treasures.add(new Treasure("Shenti", "EGP", ETreasureType.EQUIP, "002", 15, 3, ETreasureRarity.UNCOMMON, "Falda de lino utilizada como prenda básica por los hombres."));
        this.treasures.add(new Treasure("Kalasiris", "EGP", ETreasureType.EQUIP, "003", 29, 3, ETreasureRarity.UNCOMMON, "Prenda de lino usada por las mujeres de todas las clases sociales."));
        this.treasures.add(new Treasure("Cetro Uas", "EGP", ETreasureType.EQUIP, "004", 13, 6, ETreasureRarity.RARE, "Cetro símbolo de poder con una cabeza de animal en la parte superior y una horquilla en la inferior."));
        this.treasures.add(new Treasure("Heka y Nejej", "EGP", ETreasureType.EQUIP, "005", 30, 6, ETreasureRarity.RARE, "El cayado y el mayal, símbolos del poder real y la fertilidad de la tierra."));
        this.treasures.add(new Treasure("Estela de Ra", "EGP", ETreasureType.STELE, "001", 94, 1, ETreasureRarity.COMMON, "Dios del sol y de la vida con cabeza de halcón peregrino."));
        this.treasures.add(new Treasure("Estela de Isis", "EGP", ETreasureType.STELE, "002", 96, 1, ETreasureRarity.COMMON, "Diosa suprema de la magia, la sabiduría y la realeza."));
        this.treasures.add(new Treasure("Estela de Osiris", "EGP", ETreasureType.STELE, "003", 94, 1, ETreasureRarity.COMMON, "Dios de la fertilidad y Juez de los Muertos."));
        this.treasures.add(new Treasure("Estela de Seth", "EGP", ETreasureType.STELE, "004", 94, 1, ETreasureRarity.COMMON, "Dios del caos y el inframundo con cabeza animal de orejas rectangulares y hocico curvo."));
        this.treasures.add(new Treasure("Estela de Horus", "EGP", ETreasureType.STELE, "005", 94, 1, ETreasureRarity.COMMON, "Dios del cielo con cabeza de halcón."));
        this.treasures.add(new Treasure("Estela de Ptah", "EGP", ETreasureType.STELE, "006", 94, 1, ETreasureRarity.COMMON, "Dios creador de la artesanía y la arquitectura."));
        this.treasures.add(new Treasure("Estela de Bastet", "EGP", ETreasureType.STELE, "007", 94, 1, ETreasureRarity.COMMON, "Diosa protectora del hogar, el amor y la harmonía con cabeza de gato."));
        this.treasures.add(new Treasure("Estela de Anubis", "EGP", ETreasureType.STELE, "008", 94, 1, ETreasureRarity.COMMON, "Dios de la muerte con cabeza de chacal."));
        this.treasures.add(new Treasure("Estela de Hathor", "EGP", ETreasureType.STELE, "009", 94, 1, ETreasureRarity.COMMON, "Diosa de la maternidad, la música y las artes, pero también la venganza."));
        this.treasures.add(new Treasure("Estela de Ma'at", "EGP", ETreasureType.STELE, "010", 96, 1, ETreasureRarity.COMMON, "Diosa alada del orden, la verdad y la justicia."));
        this.treasures.add(new Treasure("Estela de Thot", "EGP", ETreasureType.STELE, "011", 94, 1, ETreasureRarity.COMMON, "Dios de la sabiduría, la ciencia y la escritura con cabeza de ibis."));
        this.treasures.add(new Treasure("Estela de Amón", "EGP", ETreasureType.STELE, "012", 94, 1, ETreasureRarity.COMMON, "Dios celeste de la creación."));
        this.treasures.add(new Treasure("Estela de Khnum", "EGP", ETreasureType.STELE, "013", 94, 1, ETreasureRarity.COMMON, "Dios alfarero y de la creación con cabeza de carnero. Creaba a los humanos con lodo del Nilo."));
        this.treasures.add(new Treasure("Estela de Sobek", "EGP", ETreasureType.STELE, "014", 94, 1, ETreasureRarity.COMMON, "Dios de la fertilidad, la vegetación y creador del Nilo con cabeza de cocodrilo"));
        this.treasures.add(new Treasure("Estela de Jonsu", "EGP", ETreasureType.STELE, "015", 94, 1, ETreasureRarity.COMMON, "Dios niño de la luna y su luz."));
    }

    /**
     * Porcentaje de conseguir x terreno.
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
}
