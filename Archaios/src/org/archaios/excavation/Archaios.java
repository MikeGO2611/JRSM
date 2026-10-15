package org.archaios.excavation;

import java.util.Scanner;
import java.util.Random;

public class Archaios {

    private String userCode;
    private String groupCode;
    private String gameName;

    private Scanner scanner;
    private boolean isRunning;
    private Random random;

    public Archaios() {
        this.scanner = new Scanner(System.in);
        this.isRunning = true;
        this.random = new Random();
    }

    public static void main(String[] args) {
        Archaios app = new Archaios();
        app.init();
        
        while (app.isRunning) {
            app.menu();
        }
        
        app.end();
    }

    private void init() {
        System.out.println("--- Bienvenido a Archaios ---");
        this.userCode = "USR_001";
        this.groupCode = "GRP_A";
        this.gameName = "Partida de Prueba";
    }

    private void menu() {
        System.out.println("\n=== MENÚ PRINCIPAL ===");
        System.out.println("1. Arqueólogos");
        System.out.println("2. Habilidades");
        System.out.println("3. Licencias");
        System.out.println("4. Atlas");
        System.out.println("5. Catálogo de tesoros");
        System.out.println("6. Excavar");
        System.out.println("7. Estadísticas");
        System.out.println("0. Salir");
        System.out.print("Selecciona una opción: ");

        String option = scanner.nextLine();

        switch (option) {
            case "6":
                opcionExcavar();
                break;
            case "0":
                this.isRunning = false;
                break;
            default:
                System.out.println("Opción no implementada en este demo.");
        }
    }

    private void end() {
        System.out.println("Cerrando el sistema Archaios.");
        scanner.close();
    }

    private void opcionExcavar() {
        System.out.println("2.- Iberia");
        System.out.println("3.- Roma");
        System.out.println("0.- Volver");
        System.out.print("Región en la que realizar la excavación: ");
        String regionOpt = scanner.nextLine();
        
        if (regionOpt.equals("0")) return; 

        System.out.println("-------------------- Terreno --------------------");
        System.out.println("1.- Pequeño");
        System.out.println("2.- Mediano");
        System.out.println("3.- Grande");
        System.out.println("0.- Volver");
        System.out.print("Tamaño del terreno a excavar: ");
        String terrenoOpt = scanner.nextLine();
        
        if (terrenoOpt.equals("0")) return;

        ETerrainSize size = ETerrainSize.LARGE; 
        if(terrenoOpt.equals("1")) size = ETerrainSize.SMALL; 
        else if(terrenoOpt.equals("2")) size = ETerrainSize.MEDIUM;
        
        // --- Generación Aleatoria de la Forma (TerrainShape) ---
        ETerrainShape[] shapes = ETerrainShape.values();
        ETerrainShape randomShape = shapes[random.nextInt(shapes.length)];
        
        System.out.println("\nGenerando cuadrícula con forma aleatoria: " + randomShape.name());

        // La clase Excavation ahora calcula las filas y columnas basadas en size y shape
        Terrain mockTerrain = new Terrain(
            size, 
            randomShape, 
            0, 0, // Ignorados, ahora los define Excavation según las reglas
            5, 3, 1 
        );

        Region mockRegion = new Region(); 
        mockRegion.setName("Grecia"); 
        Archeologist mockArcheologist = new Archeologist();

        Excavation excavationGame = new Excavation();
        excavationGame.start(mockRegion, mockArcheologist, mockTerrain);
    }
}