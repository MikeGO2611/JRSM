package org.archaios.main;
import org.archaios.commons.input.UserInput;
import org.archaios.commons.repositories.Skills;
import org.archaios.commons.statistics.Stats;
import org.archaios.menu.Menu;
import org.archaios.people.Archeologist;

public class Archaios {
    private static String idUser;
    private static String idGroup = "2DAM2627";
    private static String playName;

    public static Skills skillRepository;
    
    public static void main(String[] args) {
        preInit();
        init();
        menu();
        end();
    }

    private static void menu(){
        Menu mainMenu = new Menu();
        mainMenu.start();
    }
    
    private static void preInit(){
        skillRepository = new Skills();
    }

    private static void init(){

        // Comprobación de partidas guardadas, de momento será true siempre
        if(true){
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

    private static void end(){
        
    }

    private static void save(){
        
    }
}
