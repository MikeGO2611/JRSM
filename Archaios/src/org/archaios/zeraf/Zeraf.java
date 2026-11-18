package org.archaios.zeraf;

import java.util.List;

import org.archaios.treasures.Treasure;

/**
 * Clase encargada de la comunicación y el envío de datos con la librería Terra.
 * @author Miguel González Obenza
 */
public class Zeraf {

    /**
     * Método factoría que crea una instancia de Zeraf.
     * @return Zeraf instancia.
     */
    public static Zeraf createZeraf(){
        Zeraf zeraf = new Zeraf();

        return zeraf;
    }

    /**
     * Envío de los datos y tesoros a la librería.
     */
    public void sendData(List<Treasure> treasureCollection){
        // De momento siempre falla
        if(false){
            
        } else{
            System.err.println("No se ha podido establecer conexión con la librería Terra.");
        }
    }
}
