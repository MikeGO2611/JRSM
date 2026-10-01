package main;
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
    }

    private static void menu(){
        Menu mainMenu = new Menu();
        mainMenu.start();
    }
    
    private static void preInit(){
        
    }

    private static void init(){
        
    }

    private static void end(){
        
    }

    private static void save(){
        
    }
}
