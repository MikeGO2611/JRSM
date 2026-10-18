package org.archaios.excavation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.archaios.commons.input.UserInput;
import org.archaios.commons.statistics.Stats;
import org.archaios.main.Archaios;
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

    private ETerrainShape terrainShape;

    /**
     * Número de acciones (intentos) restantes que el jugador puede realizar.
     */
    private int remainingAttempts;

    /**
     * Lista de tesoros que han sido desenterrados exitosamente en la excavación actual.
     */
    private List<Treasure> foundTreasures;

    private boolean completionRewardsApplied;

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

        ETerrainShape[] shapes = ETerrainShape.values();
        this.terrainShape = shapes[new Random().nextInt(shapes.length)];
        this.grid = new Grid(terrain.getRows(), terrain.getCols(), terrainShape);
        
        calculateAttempts();
        distributeTreasures();
        grid.revealSkillClues(
                archeologist.getRevelationEmpty(),
                archeologist.getRevelationTreasure());
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
        return grid.hasHiddenTreasure(row, col);
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

    public void excavate() {
        System.out.println("Excavación iniciada. Puedes introducir varias coordenadas separadas por espacios, por ejemplo 1A 2A 3A.");
        System.out.println("Forma del terreno: " + terrainShape.getName());
        if (archeologist.getRevelationEmpty() > 0 || archeologist.getRevelationTreasure() > 0) {
            System.out.println("Pistas de habilidades: E = casilla vacía revelada; ? = tesoro localizado.");
        }

        while (!isFinished()) {
            printExcavationState();

            System.out.print("Coordenadas: ");
            String[] coordinates = UserInput.getUserString().trim().split("\\s+");
            boolean dugAtLeastOne = false;

            for (String input : coordinates) {
                int[] coordinate = parseCoordinate(input);
                if (coordinate == null) {
                    System.out.println("Coordenada no válida: " + input + ". Usa el formato fila+columna, por ejemplo 1A.");
                    continue;
                }

                int foundBeforeDig = foundTreasures.size();
                digPosition(coordinate[0], coordinate[1]);
                dugAtLeastOne = true;

                if (foundTreasures.size() > foundBeforeDig) {
                    Treasure treasure = foundTreasures.get(foundTreasures.size() - 1);
                    System.out.println("¡Has encontrado: " + treasure.getName() + "!");
                } else {
                    System.out.println("No había ningún tesoro en esa casilla.");
                }

                if (isFinished()) {
                    break;
                }
            }

            if (!dugAtLeastOne) {
                System.out.println("Introduce al menos una coordenada válida.");
            }
        }

        printExcavationState();
        printResults();
    }

    private int[] parseCoordinate(String value) {
        String input = value.toUpperCase();
        int letterStart = 0;
        while (letterStart < input.length()
                && Character.isDigit(input.charAt(letterStart))) {
            letterStart++;
        }

        if (letterStart == 0 || letterStart == input.length()) {
            return null;
        }

        try {
            int row = Integer.parseInt(input.substring(0, letterStart));
            String letters = input.substring(letterStart);
            int column = 0;
            boolean valid = row >= 1 && row <= grid.getRows();

            for (int i = 0; valid && i < letters.length(); i++) {
                char letter = letters.charAt(i);
                if (letter < 'A' || letter > 'Z') {
                    valid = false;
                    break;
                }

                int digit = letter - 'A' + 1;
                if (column > (grid.getCols() - digit) / 26) {
                    valid = false;
                    break;
                }
                column = column * 26 + digit;
            }

            if (valid && column >= 1 && column <= grid.getCols()) {
                return new int[] { row - 1, column - 1 };
            }
        } catch (NumberFormatException e) {
            return null;
        }

        return null;
    }

    private void printResults() {
        System.out.println("Excavación terminada (" + terrainShape.getName() + ")");
        grid.printGrid();
        grid.printFullyRevealedGrid();

        System.out.println("Se han encontrado:");
        for (ETreasureRarity rarity : ETreasureRarity.values()) {
            long foundCount = foundTreasures.stream()
                    .filter(treasure -> treasure.getRarity() == rarity)
                    .count();
            System.out.println(foundCount + "/" + grid.countTreasures(rarity)
                    + " tesoros " + rarity + ".");
        }

        System.out.println("-------------------- Tesoros encontrados --------------------");
        int pointsEarned = 0;
        for (Treasure treasure : foundTreasures) {
            System.out.println(treasure.getName());
            System.out.println(treasure.getDescription());
            System.out.println("Código: " + treasure.getCode());
            System.out.println(treasure.getType() + " " + treasure.getRarity());
            System.out.println("----------------------------------------");
            pointsEarned += treasure.getPoints();
        }

        int experienceEarned = terrain.getExpBonus() + pointsEarned;
        if (!completionRewardsApplied) {
            Stats.addPoints(pointsEarned);
            archeologist.addExp(experienceEarned, Archaios.skillRepository.getAllElements());
            completionRewardsApplied = true;
        }

        System.out.println("Puntos conseguidos: " + pointsEarned);
        System.out.println("El arqueólogo " + archeologist.getName() + " ha conseguido "
                + experienceEarned + " puntos de experiencia.");
    }
}