package org.archaios.menu.archeologist;

import org.archaios.commons.input.UserInput;
import org.archaios.commons.operations.IOperation;
import org.archaios.commons.statistics.Stats;
import org.archaios.menu.MenuGenerator;
import org.archaios.people.Archeologist;
/**
 * Menú para licenciar arqueólogos.
 * @see org.archaios.menu.archeologist.ArcheologistMenu
 * @author Miguel González Obenza
 */
public class LicenseArcheologist implements IOperation {
    
    /**
     * Devuelve el nombre del menú
     * @return el nombre de la operación.
     * @see org.archaios.commons.operations.IOperation#getName()
     */
    @Override
    public String getName() {
        return "Licenciar";
    }

    /**
     * Pregunta al usuario si quiere licenciar a un arqueólogo y cual.
     * Si el usuario solo tiene un arqueólogo, no se permite licenciarlo.
     * @see org.archaios.commons.operations.IOperation#operation()
     */
    @Override
    public void operation() {
        System.out.println("Cuando licencias un arqueólogo te deshaces de él a cambio de puntos.");
        if(Stats.getArcheologistCollectionCount() > 1){
            System.out.println(MenuGenerator.generateTitle("Arqueólogos", '-', 20));
            for (int i = 0; i < Stats.getArcheologistCollectionCount(); i++)
            {
                Archeologist archeologist = Stats.getArcheologistCollection().get(i);
                System.out.println((i + 1) + ". " + archeologist.getName());
                System.out.println("\t Puntos: " +archeologist.getDismissPoints());
            }
            System.out.println("0. Volver");
            System.out.print("Selecciona un arqueólogo para licenciar: ");
            int userInput = UserInput.getUserIntegerInRange(0, Stats.getArcheologistCollectionCount(), "Opción no válida");

            if(userInput != 0){
                Archeologist selectedArcheologist = Stats.getArcheologistCollection().get(userInput - 1);
                System.out.println("Seguro que quieres licenciar a " + selectedArcheologist.getName() + " por " + selectedArcheologist.getDismissPoints() + " puntos?");
                System.out.println("0. No");
                System.out.println("0. Sí");
                int userConfirmation = UserInput.getUserIntegerInRange(0, 2, "Opción no válida");
                if(userConfirmation == 1){
                    Stats.addPoints(selectedArcheologist.getDismissPoints());
                    Stats.removeArcheologist(selectedArcheologist);
                    System.out.println("Arqueólogo licenciado.");
                }
            }
        } else {
            System.out.println("No puedes licenciar tu último arqueólogo.");
        }
    }
    
}
