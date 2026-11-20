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
    /** Inicializa el repositorio cargando todas las regiones disponibles. */
    public Regions() {
        Greece grecia = new Greece();
        Egypt egipto = new Egypt();
        Iberia iberia = new Iberia();
        Petra petra = new Petra();
        Rome roma = new Rome();
        Sumeria sumeria = new Sumeria();
        
        super.addElement(grecia);
        super.addElement(egipto);
        super.addElement(iberia);
        super.addElement(petra);
        super.addElement(roma);
        super.addElement(sumeria);
    }

}