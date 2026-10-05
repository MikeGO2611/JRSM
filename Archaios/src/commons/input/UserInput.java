package commons.input;

import java.io.Console;

public class UserInput {
    private static Console console = System.console();

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

        while (userInput < min || userInput >= max){
            System.out.println(errorMessage);
            userInput = getUserInteger();
        }

        return userInput;

    }
}
