package org.archaios.excavation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.archaios.people.Archeologist;
import org.archaios.regions.Region;
import org.archaios.treasures.ETreasureRarity;
import org.archaios.treasures.Treasure;

/**
 * Controla el proceso lógico de una excavación, gestionando el arqueólogo a cargo, 
 * el terreno, la distribución de los tesoros y los turnos/intentos restantes.
 */
public class Excavation {
    /**
     * La cuadrícula física en la que se desarrolla la excavación.
     */
    private Grid grid;

    /**
     * El arqueólogo asignado que está realizando la excavación.
     */
    private Archeologist archeologist;

    /**
     * La región en la que se desarrolla la excavación.
     */
    private Region region;

    /**
     * El tipo y tamaño del terreno configurado para esta excavación.
     */
    private ETerrainSize terrain;

    /**
     * Número de acciones (intentos) restantes que el jugador puede realizar.
     */
    private int remainingAttempts;

    /**
     * Lista de tesoros que han sido desenterrados exitosamente en la excavación actual.
     */
    private List<Treasure> foundTreasures;

    /**
     * Constructor de Excavation.
     * Inicializa los componentes, calcula el número de intentos permitidos 
     * y esconde los tesoros en la cuadrícula de forma aleatoria.
     *
     * @param archeologist El arqueólogo que liderará la excavación.
     * @param region       La región del mundo donde se excava.
     * @param terrain      El terreno seleccionado con su respectivo tamaño.
     */
    public Excavation(Archeologist archeologist, Region region, ETerrainSize terrain) {
        this.archeologist = archeologist;
        this.region = region;
        this.terrain = terrain;
        this.foundTreasures = new ArrayList<>();
        
        this.grid = new Grid(terrain.getRows(), terrain.getCols());
        
        calculateAttempts();
        distributeTreasures();
    }

    /**
     * Calcula el número de acciones iniciales basado en el tamaño de la cuadrícula
     * y los modificadores o bonificaciones del arqueólogo (habilidades).
     */
    private void calculateAttempts() {
        int totalCells = grid.getTotalCells();
        // Intentos base: 25% del tamaño de la rejilla
        double baseAttempts = totalCells * 0.25; 
        double attemptsWithMultiplier = baseAttempts * archeologist.getTotalActionsMultiplier();
        this.remainingAttempts = (int) Math.ceil(attemptsWithMultiplier)
                + archeologist.getTotalBonusActions();
    }

    /**
     * Distribuye los tesoros disponibles de la región en la cuadrícula de forma aleatoria.
     * Ocupa un 10% del total de las celdas y ajusta las probabilidades de rareza 
     * (60% Común, 30% Infrecuente, 10% Raro) considerando limitaciones del terreno.
     */
    private void distributeTreasures() {
        int totalCells = grid.getTotalCells();
        int totalTreasures = (int) Math.ceil(totalCells * 0.10);
        if (totalTreasures < 1) totalTreasures = 1;

        Random random = new Random();
        
        for (int i = 0; i < totalTreasures; i++) {
            int row, col;
            do {
                row = random.nextInt(grid.getRows());
                col = random.nextInt(grid.getCols());
            } while (isPositionOccupied(row, col)); // Evitar asignar 2 tesoros en la misma celda

            int chance = random.nextInt(100);
            ETreasureRarity rarity;
            
            if (chance < 60) {
                rarity = ETreasureRarity.COMMON;
            } else if (chance < 90) {
                rarity = ETreasureRarity.UNCOMMON;
            } else {
                if (grid.getTotalCells() <= 25) { 
                    rarity = ETreasureRarity.UNCOMMON;
                } else {
                    rarity = ETreasureRarity.RARE;
                }
            }

            List<Treasure> allTreasures = region.getAllTreasures();
            List<Treasure> possibleTreasures = new ArrayList<>();
            
            for (Treasure t : allTreasures) {
                if (t.getRarity() == rarity) {
                    possibleTreasures.add(t);
                }
            }

            if (!possibleTreasures.isEmpty()) {
                Treasure treasure = possibleTreasures.get(random.nextInt(possibleTreasures.size()));
                grid.setHiddenTreasure(row, col, treasure);
            }
        }
    }

    /**
     * Comprueba si una celda específica ya tiene un tesoro escondido durante
     * la fase de distribución.
     *
     * @param row La fila a comprobar.
     * @param col La columna a comprobar.
     * @return true si la posición ya tiene un tesoro, false en caso contrario.
     */
    private boolean isPositionOccupied(int row, int col) {
        Treasure temp = grid.dig(row, col);
        if (temp != null) {
            grid.setHiddenTreasure(row, col, temp); // Restaura el tesoro temporalmente excavado
            return true;
        }
        return false;
    }

    /**
     * Ejecuta un intento de excavación por parte del jugador en las coordenadas dadas.
     * Si encuentra un tesoro, lo añade a la lista de tesoros encontrados de la excavación.
     * Descuenta una acción del total disponible.
     *
     * @param row La fila donde el jugador quiere excavar.
     * @param col La columna donde el jugador quiere excavar.
     */
    public void digPosition(int row, int col) {
        if (remainingAttempts <= 0) return;

        Treasure found = grid.dig(row, col);
        if (found != null) {
            foundTreasures.add(found);
        }
        remainingAttempts--;
    }

    /**
     * Comprueba si la excavación ha concluido debido a que el jugador se quedó sin intentos.
     *
     * @return true si no quedan intentos disponibles, false en caso contrario.
     */
    public boolean isFinished() {
        return remainingAttempts <= 0;
    }

    /**
     * Imprime por pantalla el estado actual del proceso de excavación, mostrando
     * cuántas acciones quedan y dibujando la cuadrícula.
     */
    public void printExcavationState() {
        System.out.println("Acciones restantes: " + remainingAttempts);
        grid.printGrid();
    }

    /**
     * Obtiene la lista de tesoros que han sido desenterrados exitosamente hasta el momento.
     *
     * @return Lista de objetos Treasure.
     */
    public List<Treasure> getFoundTreasures() {
        return foundTreasures;
    }
}