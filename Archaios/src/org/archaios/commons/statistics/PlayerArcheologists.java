package org.archaios.commons.statistics;

import java.util.ArrayList;
import java.util.List;

import org.archaios.people.Archeologist;

public class PlayerArcheologists {

    private static List<Archeologist> archeologistCollection = new ArrayList<Archeologist>();

    public static void addArcheologist(Archeologist archeologist){
        archeologistCollection.add(archeologist);
    }

    public static List<Archeologist> getArcheologistCollection(){
        return archeologistCollection;
    }

}
