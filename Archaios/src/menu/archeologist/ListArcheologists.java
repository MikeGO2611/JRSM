package menu.archeologist;

import menu.MenuGenerator;
import people.Archeologist;

public class ListArcheologists {
    public static Archeologist selectArcheologist(){
        Archeologist selectedArcheologist = null;

        MenuGenerator.generateTitle("Arqueólogos", '-', 20);
        
        System.out.println("0. Volver");

        return selectedArcheologist;
    }
}
