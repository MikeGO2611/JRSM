package org.archaios.menu.license;

import org.archaios.commons.operations.IOperation;
import org.archaios.commons.statistics.Stats;
import org.archaios.menu.MenuGenerator;
import org.archaios.menu.archeologist.ArcheologistMenuUtils;
import org.archaios.people.Archeologist;

/**
 * Menú con las opciones para las licencias.
 * @author Miguel González Obenza
 */
public class LicenseMenu implements IOperation{

    /**
     * Devuelve el nombre del menú.
     * @return el nombre de la operación.
     * @see org.archaios.commons.operations.IOperation#getName()
     */
    @Override
    public String getName() {
        return "Licencias";
    }

    /**
     * Abre el menú de obtener licencias para un arqueólogo.
     * @see org.archaios.commons.operations.IOperation#operation()
     */
    @Override
    public void operation() {
        System.out.println(MenuGenerator.generateTitle(getName(), ':', 20));

        boolean userInMenu = true;

        while (userInMenu) {
            Archeologist selectedArcheologist = ArcheologistMenuUtils.selectArcheologist();

            if(selectedArcheologist != null){
                System.out.println("Puntos disponibles: " + Stats.getPoints());
                System.out.println("Las licencias cuestan 20 puntos.");

                if(Stats.getPoints() >= 20){
                    // TODO No me quedó claro que hacer.
                    System.err.println("Aún no implementado.");
                } else {
                    System.out.println("No tienes suficientes puntos para comprar una licencia a este arqueólogo.");
                }
            } else {
                userInMenu = false;
            }
        }
    }

}
