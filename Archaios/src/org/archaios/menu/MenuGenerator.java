package org.archaios.menu;

import java.util.List;

import org.archaios.commons.input.UserInput;
import org.archaios.commons.operations.IOperation;

/**
 * Utilidad para generar menús en base a entradas.
 * Clase estática, no se puede instanciar ni heredar.
 * @author Miguel González Obenza
 */
final public class MenuGenerator {

    // Definiendo el constructor como un método privado se impide la instanciación de la clase.
    private MenuGenerator(){

    }

    /**
     * Imprime el menú en base al {@code entryCollection} y pide al usuario su selección.
     * @param title el título del menú
     * @param ch el decorador que rodea el título del menú
     * @param entryCollection la colección de entradas del menú
     * @param exitOption se mostrará para la opción de salida
     * @param errorMessage se mostrará en caso de que el usuario elija una opción inválida
     */
    public static void askUserMenuOption(String title, Character ch, List<IOperation> entryCollection, String exitOption, String errorMessage){
        boolean userExit = false;
        while (!userExit) {
            // Para ocultar el estado de la consola anterior:
            //System.out.print("\033[H\033[2J");

            printMenu(title, ch, entryCollection, exitOption);
            int userInput = UserInput.getUserIntegerInRange(0, entryCollection.size(), errorMessage);
            
            if(userInput != 0) entryCollection.get(userInput - 1).operation();
            else userExit = true;
        }
    }

    /**
     * Imprime un menú en base al {@code entryCollection}
     * @param title el título del menú
     * @param ch el decorador que rodea el título del menú
     * @param entryCollection la colección de entradas del menú
     * @param exitOption el texto para la opción de salida
     */
    private static void printMenu(String title, Character ch, List<IOperation> entryCollection, String exitOption) {
        System.out.println(MenuGenerator.generateMenu(title, ch, entryCollection));
        System.out.println(exitOption);
    }

    /**
     * Genera el {@code String} para el menú en base a {@code entryCollection}
     * @param title el título del menú
     * @param ch el decorador que rodea el título del menú
     * @param entryCollection la colección de entradas del menú
     * @return el menú generado
     */
    private static String generateMenu(String title, Character ch, List<IOperation> entryCollection){
        StringBuilder sb = new StringBuilder();

        sb.append(generateTitle(title, ch, 20) + '\n');

        for(int i = 0; i < entryCollection.size(); i++){
            sb.append((i + 1) + ". " + entryCollection.get(i).getName() + '\n');
        }

        sb.deleteCharAt(sb.length() - 1);

        return sb.toString();
    }

    /**
     * Genera un título decorándolo con caracteres.
     * @param title el título
     * @param ch el caracter de decoración
     * @param repetition el número de veces que aparece el caracter de cada lado
     * @return el título generado
     */
    public static String generateTitle(String title, Character ch, int repetition){
        StringBuilder sb = new StringBuilder();

        sb.append(generateDivisor(ch, repetition));
        
        sb.append(" " + title + " ");

        sb.append(generateDivisor(ch, repetition));

        return sb.toString();
    }

    public static String generateDivisor(Character ch, int repetition){

        StringBuilder sb = new StringBuilder();

        sb.repeat(ch, repetition);

        return sb.toString();
    }
}
