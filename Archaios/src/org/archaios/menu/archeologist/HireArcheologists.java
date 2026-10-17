package org.archaios.menu.archeologist;

import org.archaios.commons.operations.IOperation;
import org.archaios.commons.statistics.Stats;

public class HireArcheologists implements IOperation{

    @Override
    public String getName() {
        return "Contratar";
    }

    @Override
    public void operation() {
        System.out.println("Puntos disponibles: " + Stats.getPoints());
        System.out.println("Cada arqueólogo cuesta 50 puntos.");
        if(Stats.getPoints() >= 50){
            throw new UnsupportedOperationException("Unimplemented method 'operation'");
        } else {
            System.out.println("No tienes puntos suficientes para contratar un nuevo arqueólogo.");
        }
    }

}
