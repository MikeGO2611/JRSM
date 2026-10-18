package org.archaios.commons.statistics;

import java.util.ArrayList;
import java.util.List;

import org.archaios.people.Archeologist;

/**
 * Clase que contiene las estadísticas y los datos del jugador.
 * Contiene los puntos y los arqueólogos.
 * @author Miguel González Obenza
 */
public class Stats {
    /** Puntos del jugador. */
    private static int points = 0;
    /** Lista de arqueólogos del jugador. */
    private static List<Archeologist> archeologistCollection = new ArrayList<Archeologist>();

    public static void getPlaceStats(){
        // TODO implementar 
        throw new UnsupportedOperationException("No implementado");
    }

    /**
     * Añade un arqueólogo a la lista del jugador,
     * @param archeologist Archeologist para añadir.
     */
    public static void addArcheologist(Archeologist archeologist){
        archeologistCollection.add(archeologist);
    }

    /**
     * Elimina el arqueólogo de la lista del jugador,
     * @param archeologist Archeologist para eliminar.
     */
    public static void removeArcheologist(Archeologist archeologist){
        archeologistCollection.remove(archeologist);
    }

    /**
     * Devuelve la lista de arqueólogos del jugador.
     * @return List<Archeologist> del jugador.
     */
    public static List<Archeologist> getArcheologistCollection(){
        return archeologistCollection;
    }

    /**
     * Devuelve el número de arqueólogos del jugador.
     * @return int cantidad de arqueólogos que posee el jugador.
     */
    public static int getArcheologistCollectionCount(){
        return archeologistCollection.size();
    }

    /**
     * Devuelve los puntos del jugador.
     * @return int puntos del jugador.
     */
    public static int getPoints(){
        return points;
    }

    /**
     * Setea los puntos del jugador al valor introducido.
     * @param points int para setear los puntos del jugador.
     */
    public static void setPoints(int points){
        Stats.points = points;
    }

    /**
     * Añade puntos al jugador.
     * @param points int puntos a añadir.
     */
    public static void addPoints(int points){
        Stats.points += points;
    }
}
