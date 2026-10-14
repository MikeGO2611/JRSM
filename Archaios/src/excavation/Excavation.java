
import java.util.ArrayList;
import java.util.List;

/**
 * Clase principal que gestiona la lógica del proceso de excavación.
 */
public class Excavation {
    private Region region;
    private Archeologist archeologist;
    private Terrain terrain;
    private Grid grid;
    
    private List<Treasure> hiddenTreasures;
    private List<Treasure> foundTreasures;
    
    private int remainingActions;
    private boolean isFinished;

    /**
     * Inicia un nuevo proceso de excavación.
     *
     * @param region       Región seleccionada.
     * @param archeologist Arqueólogo asignado a la excavación.
     * @param terrain      Terreno donde se realizará la excavación.
     */
    public void start(Region region, Archeologist archeologist, Terrain terrain) {
        this.region = region;
        this.archeologist = archeologist;
        this.terrain = terrain;
        this.grid = new Grid(terrain.getRows(), terrain.getCols()); 
        
        this.hiddenTreasures = new ArrayList<>();
        this.foundTreasures = new ArrayList<>();
        this.isFinished = false;

        calculateActions();
        generateTreasures();
        
        runGameLoop();
    }

    private void calculateActions() {
        int totalSize = grid.getTotalCells();
        double baseActions = totalSize * 0.25;
        
        int extraFromArcheologist = 0; 
        double multiplierFromSkills = 1.0;
        
        this.remainingActions = (int) ((baseActions + extraFromArcheologist) * multiplierFromSkills);
    }

    private void generateTreasures() {
        int totalCells = grid.getTotalCells();
        int targetTreasureCount = Math.max(1, (int) Math.ceil(totalCells * 0.10));
        
        // Implementación de la generación de tesoros
    }

    private void runGameLoop() {
        while (!isFinished && remainingActions > 0 && foundTreasures.size() < hiddenTreasures.size()) {
            grid.printGrid(false);
            
            // Implementación del procesamiento de entrada y excavación
            
            remainingActions--;
        }
        finish();
    }

    private void finish() {
        this.isFinished = true;
        
        grid.printGrid(true);
        
        int expGained = terrain.getSize().getExpBonus();
        System.out.println("El arqueólogo ha conseguido " + expGained + " puntos de experiencia.");
    }
}
