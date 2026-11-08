package org.archaios.commons.json;

import java.util.List;

import org.archaios.people.Archeologist;

import com.google.gson.annotations.SerializedName;

public class SaveJObject {
    @SerializedName("UID")
    private String uid;
    @SerializedName("Partida")
    private  String playName;
    @SerializedName("Arqueólogos")
    private List<Archeologist> archeologistCollection;
    @SerializedName("Puntos")
    private int points;
    
    public SaveJObject(String uID, String partida, List<Archeologist> arqueólogos, int puntos) {
        this.uid = uID;
        this.playName = partida;
        this.archeologistCollection = arqueólogos;
        this.points = puntos;
    }

}
