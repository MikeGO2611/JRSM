package org.archaios.commons.repositories;
import org.archaios.regions.Region;
import org.archaios.regions.Countries.Egypt;
import org.archaios.regions.Countries.Greece;
import org.archaios.regions.Countries.Iberia;
import org.archaios.regions.Countries.Petra;
import org.archaios.regions.Countries.Rome;
import org.archaios.regions.Countries.Sumeria;

/**
 * Clase que extiende de Repository para usar solo con regiones.
 * @author Rubén Hernández
 */
public class Regions extends Repository<Region> {

    public Regions() {
        Greece grecia = new Greece();
        Egypt egypt = new Egypt();
        Iberia iberia = new Iberia();
        Petra petra = new Petra();
        Rome rome = new Rome();
        Sumeria sumeria = new Sumeria();

        super.addElement(grecia);
        super.addElement(egypt);
        super.addElement(iberia);
        super.addElement(petra);
        super.addElement(rome);
        super.addElement(sumeria);
    }

}