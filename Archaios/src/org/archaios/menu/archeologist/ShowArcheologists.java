package org.archaios.menu.archeologist;

import org.archaios.commons.operations.IOperation;
import org.archaios.people.Archeologist;

public class ShowArcheologists implements IOperation{

    @Override
    public String getName() {
        return "Ver Arqueólogos";
    }

    @Override
    public void operation() {
        Archeologist selectedArcheologist = ArcheologistMenuUtils.selectArcheologist();

        if(selectedArcheologist != null){
            selectedArcheologist.archeologistInformation();
        }
    }

}
