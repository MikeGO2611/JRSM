package org.archaios.menu.archeologist;

import org.archaios.commons.operations.IOperation;
import org.archaios.commons.statistics.Stats;

public class LicenseArcheologist implements IOperation {

    @Override
    public String getName() {
        return "Licenciar";
    }

    @Override
    public void operation() {
        System.out.println("Cuando licencias un arqueólogo te deshaces de él a cambio de puntos.");
        if(Stats.getArcheologistCollectionCount() > 1){
            throw new UnsupportedOperationException("Unimplemented method 'operation'");
        } else {
            System.out.println("No puedes licenciar tu último arqueólogo.");
        }
    }
    
}
