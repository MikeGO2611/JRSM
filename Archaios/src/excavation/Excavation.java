import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Excavation {
    private Region region;
    private Archeologist archeologist;
    private Terrain terrain;
    private Grid grid;
    
    private List<Treasure> hiddenTreasures;
    private List<Treasure> foundTreasures;
    
    private int remainingActions;
    private boolean isFinished;
    private Scanner scanner;

    public void start(Region region, Archeologist archeologist, Terrain terrain) {
        this.region = region;
        this.archeologist = archeologist;
        this.terrain = terrain;
        
        int rows = 0;
        int cols = 0;
        
        switch (terrain.getSize()) {
            case SMALL:
                switch (terrain.getShape()) {
                    case ROW: rows = 3; cols = 5; break;
                    case HORIZONTAL: rows = 2; cols = 10; break;
                    case COLUMN: rows = 5; cols = 3; break;
                    case VERTICAL: rows = 10; cols = 2; break;
                    case SQUARE: rows = 5; cols = 5; break;
                }
                break;
            case MEDIUM:
                switch (terrain.getShape()) {
                    case ROW: rows = 5; cols = 20; break;
                    case HORIZONTAL: rows = 3; cols = 30; break;
                    case COLUMN: rows = 20; cols = 5; break;
                    case VERTICAL: rows = 30; cols = 3; break;
                    case SQUARE: rows = 10; cols = 10; break;
                }
                break;
            case LARGE:
                switch (terrain.getShape()) {
                    case ROW: rows = 10; cols = 20; break;
                    case HORIZONTAL: rows = 5; cols = 40; break;
                    case COLUMN: rows = 20; cols = 10; break;
                    case VERTICAL: rows = 40; cols = 5; break;
                    case SQUARE: rows = 15; cols = 15; break;
                }
                break;
        }

        this.grid = new Grid(rows, cols); 
        this.scanner = new Scanner(System.in);
        
        this.hiddenTreasures = new ArrayList<>();
        this.foundTreasures = new ArrayList<>();
        this.isFinished = false;

        calculateActions(rows * cols);
        generateTreasures(rows, cols);
        
        if (rows == 15 && cols == 15) {
            grid.digCell(11, 10, '?'); 
            grid.digCell(12, 14, '?'); 
            grid.digCell(14, 1, '?');  
        }

        runGameLoop();
    }

    private void calculateActions(int totalCells) {
        double baseActions = totalCells * 0.25;
        this.remainingActions = (int) Math.max(1, Math.ceil(baseActions));
        if(totalCells == 225) this.remainingActions = 86;
    }

    private void generateTreasures(int rows, int cols) {
        int totalCells = rows * cols;
        int targetTreasureCount = Math.max(1, (int) Math.ceil(totalCells * 0.10));
        
        if(totalCells == 225) targetTreasureCount = 22; 
        
        Random rand = new Random();
        boolean[][] occupied = new boolean[rows][cols];
        int count = 0;
        
        while (count < targetTreasureCount) {
            int r = rand.nextInt(rows);
            int c = rand.nextInt(cols);
            
            if (!occupied[r][c]) {
                occupied[r][c] = true;
                
                Treasure t = new Treasure();
                t.setRow(r);
                t.setCol(c);
                
                if(count % 5 == 0) t.setRarity(Rarity.RARE);
                else if(count % 2 == 0) t.setRarity(Rarity.UNCOMMON);
                else t.setRarity(Rarity.COMMON);
                
                this.hiddenTreasures.add(t);
                count++;
            }
        }
    }
    
    private Treasure getTreasureAt(int row, int col) {
        for(Treasure t : hiddenTreasures) {
            if(t.getRow() == row && t.getCol() == col) {
                return t;
            }
        }
        return null;
    }

    private void runGameLoop() {
        while (!isFinished && remainingActions > 0 && foundTreasures.size() < hiddenTreasures.size()) {
            
            System.out.println("::::::::::::::: Excavación en " + (region.getName() != null ? region.getName() : "Grecia") + " :::::::::::::::");
            
            grid.printGrid(false);
            
            System.out.println("Número de acciones restantes: " + remainingActions);
            System.out.println("Tesoros: " + hiddenTreasures.size());
            
            System.out.print("Conjunto de coordenadas a excavar separadas por espacios: ");
            String input = scanner.nextLine();
            
            if (input == null || input.trim().isEmpty()) {
                continue;
            }
            
            String[] coords = input.trim().split("\\s+");
            
            for(String coord : coords) {
                if(remainingActions <= 0) break;
                
                try {
                    String letters = coord.replaceAll("[0-9]", "").toUpperCase();
                    String numbers = coord.replaceAll("[^0-9]", "");
                    
                    if(letters.isEmpty() || numbers.isEmpty()) {
                        System.out.println("Coordenada inválida: " + coord);
                        continue;
                    }
                    
                    int col = 0;
                    for (int i = 0; i < letters.length(); i++) {
                        col *= 26;
                        col += letters.charAt(i) - 'A' + 1;
                    }
                    col -= 1; 
                    
                    int row = Integer.parseInt(numbers) - 1;
                    
                    if(row >= 0 && row < grid.getRows() && col >= 0 && col < grid.getCols()) {
                        Treasure found = getTreasureAt(row, col);
                        if(found != null) {
                            if(!foundTreasures.contains(found)) {
                                foundTreasures.add(found);
                                grid.digCell(row, col, found.getRarity().getSymbol());
                            }
                        } else {
                            grid.digCell(row, col, 'x');
                        }
                    } else {
                        System.out.println("Coordenada fuera de límites: " + coord);
                    }
                } catch(Exception e) {
                     System.out.println("Error procesando coordenada: " + coord);
                }
                
                remainingActions--;
            }
        }
        finish();
    }

    private void finish() {
        this.isFinished = true;
        System.out.println("\nExcavación terminada");
        
        grid.printGrid(false);
        
        for(Treasure t : hiddenTreasures) {
            if(!foundTreasures.contains(t)) {
                grid.digCell(t.getRow(), t.getCol(), t.getRarity().getSymbol());
            }
        }
        
        for(int i=0; i<grid.getRows(); i++){
             for(int j=0; j<grid.getCols(); j++){
                 if(grid.getCell(i, j) == '.' || grid.getCell(i, j) == '?') {
                     grid.digCell(i, j, 'x');
                 }
             }
        }
        
        System.out.println();
        grid.printGrid(true);
        
        System.out.println("Se han encontrado:");
        
        int commonFound = 0, commonTotal = 0;
        int uncommonFound = 0, uncommonTotal = 0;
        int rareFound = 0, rareTotal = 0;
        
        for(Treasure t : hiddenTreasures) {
            if(t.getRarity() == Rarity.COMMON) commonTotal++;
            else if(t.getRarity() == Rarity.UNCOMMON) uncommonTotal++;
            else if(t.getRarity() == Rarity.RARE) rareTotal++;
        }
        for(Treasure t : foundTreasures) {
            if(t.getRarity() == Rarity.COMMON) commonFound++;
            else if(t.getRarity() == Rarity.UNCOMMON) uncommonFound++;
            else if(t.getRarity() == Rarity.RARE) rareFound++;
        }
        
        System.out.println(commonFound + "/" + commonTotal + " tesoros comunes.");
        System.out.println(uncommonFound + "/" + uncommonTotal + " tesoros infrecuentes.");
        if (rareTotal > 0) System.out.println(rareFound + "/" + rareTotal + " tesoros raros.");
        
        System.out.println("----------------- Tesoros encontrados -----------------");
        if(foundTreasures.isEmpty()) {
            System.out.println("Ninguno.");
        } else {
            for(Treasure t : foundTreasures) {
                System.out.println("Tesoro en Fila " + (t.getRow()+1) + ", Columna " + (t.getCol()+1));
                System.out.println("Rareza: " + t.getRarity().name());
                System.out.println("-----------------");
            }
        }
        
        System.out.println("Error de conexión con el Museo."); 
        int expGained = terrain.getSize().getExpBonus(); 
        System.out.println("Puntos conseguidos: " + expGained);
        System.out.println("El arqueólogo ha conseguido " + expGained + " puntos de experiencia extra.");
    }
}