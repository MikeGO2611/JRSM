package org.archaios.commons.input;

import java.io.Console;
import java.io.IOError;
import java.util.ArrayList;
import java.util.List;

import org.archaios.commons.statistics.Stats;
import org.archaios.people.Archeologist;

/**
 * Utilidad para gestionar el input del usuario.
 * @author Miguel González Obenza
 */
public class UserInput {
    private static Console console = System.console();

    /**
     * Pidea al usuario un número entero.
     * Si el usuario no proporciona un número entero, se le vuelve a pedir hasta que lo introduzca.
     * @return el número entero
     */
    public static int getUserInteger(){
        int userInput = Integer.MIN_VALUE;
        boolean correctInput = false;
        while (!correctInput) {
            try{
                String line = console.readLine();
                userInput = Integer.parseInt(line);
                correctInput = true;
            } catch (NumberFormatException e){
                System.out.println("\nEl input no es un número entero válido.\n");
            } catch(IOError e){
                System.err.println("Error de input o output.");
            }
        }

        return userInput;
    }

    /**
     * Pide al usuario un número entero y comprueba que está entre los valores definidos.
     * Si no está imprime el mensaje de error y lo vuelve a pedir.
     * @param min el número mínimo permitido inclusive
     * @param max el número máximo permitido exclusive
     * @param errorMessage el mensaje para imprimir si el número está fuera del rango
     * @return el número dentro del rango
     */
    public static int getUserIntegerInRange(int min, int max, String errorMessage){
        int userInput = getUserInteger();

        while (userInput < min || userInput > max){
            System.out.println(errorMessage);
            userInput = getUserInteger();
        }

        return userInput;

    }

    /**
     * Pide al usuario un texto String
     * @return String pedido al usuario.
     */
    public static String getUserString(){
        return console.readLine();
    }

    /**
     * Pide al usuario un nombre de arqueólogo y comprueba si es único.
     * @return String con el nombre único
     */
    public static String getUserUniqueArcheologistName(){
        String archeologistName = getUserString();

        List<String> archeologistNameCollection = new ArrayList<>();

        for (Archeologist archeologist : Stats.getArcheologistCollection()) {
            archeologistNameCollection.add(archeologist.getName().trim());
        }

        while (archeologistNameCollection.contains(archeologistName.trim())) {
            System.out.print("El nombre ya existe, elige otro: ");
            archeologistName = getUserString();
        }

        return archeologistName;
    }
}
