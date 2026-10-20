package org.archaios.commons.repositories;
import org.archaios.regions.Region;
import org.archaios.regions.Countries.Greece;

/**
 * Clase que extiende de Repository para usar solo con regiones.
 * @author Rubén Hernández
 */
public class Regions extends Repository<Region> {

    public Regions() {
        Greece grecia = new Greece();
        
        super.addElement(grecia);
    }

}