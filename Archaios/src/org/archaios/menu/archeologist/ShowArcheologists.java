package org.archaios.menu.archeologist;

import org.archaios.commons.operations.IOperation;

public class ShowArcheologists implements IOperation{

    @Override
    public String getName() {
        return "Ver Arqueólogos";
    }

    @Override
    public void operation() {
        ListArcheologists.selectArcheologist();
    }

}
