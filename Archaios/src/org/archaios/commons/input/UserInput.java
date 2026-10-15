package org.archaios.commons.input;

import java.io.Console;
import java.io.IOError;

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


    public static String getUserString(){
        return console.readLine();
    }
}
