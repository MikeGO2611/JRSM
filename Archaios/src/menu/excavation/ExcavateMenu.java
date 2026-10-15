package menu.excavation;

import commons.operations.IOperation;
import menu.MenuGenerator;

/**
 * Menú con las opciones de excavación.
 * 
 * @author Miguel González Obenza
 */
public class ExcavateMenu implements IOperation {

    @Override
    public String getName() {
        return "Excavación";
    }

    @Override
    public void operation() {
        System.out.println(MenuGenerator.generateTitle(getName(), ':', 20));
    }

}
