package org.archaios.menu.archeologist;

import org.archaios.people.Archeologist;

import java.util.List;

import org.archaios.commons.input.UserInput;
import org.archaios.commons.statistics.Stats;
import org.archaios.menu.MenuGenerator;

/**
 * Utilidades para gestionar los arqueólogos.
 * @author Miguel González Obenza
 */
public class ArcheologistMenuUtils {
    /**
     * Pide al jugador que elija un arqueólogo, y lo devuelve.
     * @return Archeologist arqueólogo elegido por el jugador.
     */
    public static Archeologist selectArcheologist(){
        Archeologist selectedArcheologist = null;
        List<Archeologist> archeologistCollection = Stats.getArcheologistCollection();

        System.out.println(MenuGenerator.generateTitle("Arqueólogos", '-', 20));
        for(int i = 0; i < archeologistCollection.size(); i++){
            System.out.println((i + 1) + ". " + archeologistCollection.get(i).getName());
        }

        System.out.println("0. Volver");

        int userInput = UserInput.getUserIntegerInRange(0, archeologistCollection.size(), "Operación no válida.");

        if(userInput != 0){
            selectedArcheologist = archeologistCollection.get(userInput - 1);
        }

        return selectedArcheologist;
    }
}
