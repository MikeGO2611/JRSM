package org.archaios.menu.archeologist;

import org.archaios.people.Archeologist;

import org.archaios.menu.MenuGenerator;

public class ListArcheologists {
    public static Archeologist selectArcheologist(){
        Archeologist selectedArcheologist = null;

        MenuGenerator.generateTitle("Arqueólogos", '-', 20);
        
        System.out.println("0. Volver");

        return selectedArcheologist;
    }
}
