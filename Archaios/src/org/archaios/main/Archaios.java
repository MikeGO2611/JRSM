package org.archaios.main;

import org.archaios.commons.input.UserInput;
import org.archaios.commons.repositories.Skills;
import org.archaios.commons.statistics.Stats;
import org.archaios.menu.Menu;
import org.archaios.people.Archeologist;

/**
 * Clase principal que sirve como entrada al programa.
 * 
 * @author Miguel González Obenza
 */
public class Archaios {
    /** El identificador del usuario. */
    private static String idUser;
    /** El identificador del grupo del trabajo. */
    private static final String idGroup = "2DAM2627";
    /** El nombre de la partida. */
    private static String playName;

    /** Repositorio de skills. */
    public static Skills skillRepository;

    /**
     * Método main que inicia la ejecución del programa
     * @param args String[] argumentos de ejecución. Son ignorados.
     */
    public static void main(String[] args) {
        preInit();
        init();
        menu();
        end();
    }

    /**
     * Inicializa y ejecuta el menú principal.
     */
    private static void menu() {
        Menu mainMenu = new Menu();
        mainMenu.start();
    }

    /**
     * Ejecuta la preinicialización.
     * Inicializa el repositorio de skills.
     */
    private static void preInit() {
        skillRepository = new Skills();
    }

    /**
     * Ejecuta la inicialización.
     * Pide al usuario el código persona, nombre de la partida y primer arqueólogo.
     */
    private static void init() {

        // Comprobación de partidas guardadas, de momento será true siempre
        if (true) {
            System.out.print("Introduce tu código personal único: ");
            idUser = UserInput.getUserString();

            System.out.print("Introduce el nombre de la partida: ");
            playName = UserInput.getUserString();

            System.out.print("Introduce el nombre del arqueólogo: ");
            String archeologistName = UserInput.getUserString();
            Archeologist firstArcheologist = new Archeologist(archeologistName);
            Stats.addArcheologist(firstArcheologist);
        }

    }

    /**
     * Hace limpieza final.
     */
    private static void end() {

    }

    /**
     * Guarda la partida.
     */
    private static void save() {

    }
}
