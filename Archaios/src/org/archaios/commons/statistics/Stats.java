package org.archaios.commons.statistics;

import java.util.ArrayList;
import java.util.List;

import org.archaios.people.Archeologist;

public class Stats {
    private static int points = 0;
    private static List<Archeologist> archeologistCollection = new ArrayList<Archeologist>();

    public static void getPlaceStats(){
        // TODO implementar 
        throw new UnsupportedOperationException("No implementado");
    }

    public static void addArcheologist(Archeologist archeologist){
        archeologistCollection.add(archeologist);
    }

    public static List<Archeologist> getArcheologistCollection(){
        return archeologistCollection;
    }

    public static int getArcheologistCollectionCount(){
        return archeologistCollection.size();
    }

    public static int getPoints(){
        return points;
    }

    public static void setPoints(int points){
        Stats.points = points;
    }
}
