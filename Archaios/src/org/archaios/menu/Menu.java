package org.archaios.menu;

import java.util.ArrayList;
import java.util.List;

import org.archaios.commons.operations.IOperation;
import org.archaios.menu.archeologist.ArcheologistMenu;
import org.archaios.menu.atlas.AtlasMenu;
import org.archaios.menu.excavation.ExcavateMenu;
import org.archaios.menu.license.LicenseMenu;
import org.archaios.menu.skill.SkillMenu;
import org.archaios.menu.stats.StatsMenu;
import org.archaios.menu.treasure.TreasureCatalogMenu;

/**
 * Menú que sirve como punto de entrada para la interfaz con el usuario.
 * @author Miguel González Obenza
 */
public class Menu {

    private List<IOperation> entryCollection = new ArrayList<>();


    /**
     * Inicializa el menú con sus entradas.
     */
    public Menu(){
        entryCollection.add(new ArcheologistMenu());
        entryCollection.add(new SkillMenu());
        entryCollection.add(new LicenseMenu());
        entryCollection.add(new AtlasMenu());
        entryCollection.add(new TreasureCatalogMenu());
        entryCollection.add(new ExcavateMenu());
        entryCollection.add(new StatsMenu());
    }

    /**
     * Inicio del menú principal.
     */
    public void start(){
        MenuGenerator.askUserMenuOption(
                "Archaios", 
                '=',
                entryCollection, 
                "0. Salir", 
                "Opción no aceptada.");
    }
}
