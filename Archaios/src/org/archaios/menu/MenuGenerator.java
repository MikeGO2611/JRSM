package org.archaios.menu;

import java.util.Arrays;
import java.util.List;

import org.archaios.commons.input.UserInput;
import org.archaios.commons.operations.IOperation;
import org.archaios.commons.statistics.Stats;
import org.archaios.main.Archaios;
import org.archaios.people.Archeologist;

/**
 * Utilidad para generar menús en base a entradas.
 * Clase estática, no se puede instanciar ni heredar.
 * @author Miguel González Obenza
 */
final public class MenuGenerator {

    // Definiendo el constructor como un método privado se impide la instanciación de la clase sin necesidad de hacerla abstracta.
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

    public static void askUserMenuOptionCheats(String title, Character ch, List<IOperation> entryCollection, String exitOption, String errorMessage){
        boolean userExit = false;
        while (!userExit) {
            // Para ocultar el estado de la consola anterior:
            //System.out.print("\033[H\033[2J");

            printMenu(title, ch, entryCollection, exitOption);
            String userInput = UserInput.getUserString();

            if(userInput.matches("[0-9]+")){
                int userInputInt = Integer.parseInt(userInput);
                if (userInputInt >= 0 && userInputInt <= entryCollection.size()){
                    if(userInputInt != 0) entryCollection.get(userInputInt - 1).operation();
                    else userExit = true;
                } else {
                    System.out.println("Operación no válida.");
                }
            } else {
                if(userInput.contains("add_archeologist")){
                        List<String> command = Arrays.asList(userInput.split("\s"));
                        if(command.size() > 1){
                            StringBuilder sb = new StringBuilder();
                            for(int i = 1; i < command.size(); i++){
                                sb.append(command.get(i) + " ");
                            }
                            Archeologist testArcheologist = new Archeologist(sb.toString().trim());
                            Stats.addArcheologist(testArcheologist);
                            System.out.println("Añadido " + testArcheologist.getName());
                        }

                } 
                else if(userInput.contains("add_points")){
                    List<String> command = Arrays.asList(userInput.split("\s"));
                    if(command.size() == 2){
                        int points = Integer.parseInt(command.get(1));
                        Stats.addPoints(points);
                        System.out.println(points + " puntos añadidos.");
                    }
                }
                else if(userInput.contains("add_exp")){
                    List<String> command = Arrays.asList(userInput.split("\s"));
                    if(command.size() > 2){
                        int exp = Integer.parseInt(command.get(1));
                        StringBuilder sb = new StringBuilder();
                        for(int i = 2; i < command.size(); i++){
                            sb.append(command.get(i) + " ");
                        }
                        String archeologistName = sb.toString().trim();

                        for(Archeologist archeologist : Stats.getArcheologistCollection()){
                            if(archeologist.getName().contains(archeologistName)){
                                archeologist.addExp(exp, Archaios.skillRepository.getAllElements());
                                System.out.println("Añadidos " + exp + " puntos de experiencia a " + archeologist.getName());
                            }
                        }
                    }
                }
            }
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
     * @return String el menú generado
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
     * @return String el título generado
     */
    public static String generateTitle(String title, Character ch, int repetition){
        StringBuilder sb = new StringBuilder();

        sb.append(generateDivisor(ch, repetition));
        
        sb.append(" " + title + " ");

        sb.append(generateDivisor(ch, repetition));

        return sb.toString();
    }

    /**
     * Genera un divisor sin título, solo con caracteres.
     * @param ch Character empleado para decorar el divisor
     * @param repetition int veces que se repite el caracter
     * @return String el divisor
     */
    public static String generateDivisor(Character ch, int repetition){

        StringBuilder sb = new StringBuilder();

        sb.repeat(ch, repetition);

        return sb.toString();
    }
}
