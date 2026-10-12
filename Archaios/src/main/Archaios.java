package main;
import commons.input.UserInput;
import menu.Menu;

public class Archaios {
    private static String idUser;
    private static String idGroup;
    private static String playName;
    //private static Stats statistics;
    
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
        
    }

    private static void init(){

        // Comprobación de partidas guardadas, de momento será false siempre
        if(true){
            System.out.print("Introduce tu código personal único: ");
            idUser = UserInput.getUserString();


            System.out.print("Introduce el nombre de la partida: ");
            playName = UserInput.getUserString();


            System.out.print("Introduce el nombre del arqueólogo: ");
            String archeologistName = UserInput.getUserString();
        }
        
    }

    private static void end(){
        
    }

    private static void save(){
        
    }
}
