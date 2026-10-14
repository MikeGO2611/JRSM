
import java.util.Scanner;

/**
 * La clase que contiene el main, el menú y la lógica general del sistema Archaios.
 */
public class Archaios {

    // Atributos base indicados en el documento
    private String userCode;
    private String groupCode;
    private String gameName;
    // private Stats stats; // Comentado hasta que se implemente la clase Stats

    private Scanner scanner;
    private boolean isRunning;

    public Archaios() {
        this.scanner = new Scanner(System.in);
        this.isRunning = true;
    }

    /**
     * Método principal que arranca la aplicación.
     */
    public static void main(String[] args) {
        Archaios app = new Archaios();
        app.preInit();
        app.init();
        
        while (app.isRunning) {
            app.menu();
        }
        
        app.end();
    }

    /**
     * Método que realiza los registros de los elementos y la inicialización 
     * de las dependencias del sistema antes de que este arranque.
     */
    private void preInit() {
        System.out.println("Inicializando repositorios y registrando elementos...");
        // Aquí se registrarían regiones, habilidades y tesoros en sus repositorios estáticos.
    }

    /**
     * Método que inicializa todo el sistema. Verifica partidas guardadas, 
     * pide datos iniciales si es nueva, o carga los datos del fichero.
     */
    private void init() {
        System.out.println("--- Bienvenido a Archaios ---");
        // Simulación de creación de partida
        this.userCode = "USR_001";
        this.groupCode = "GRP_A";
        this.gameName = "Partida de Prueba";
        System.out.println("Partida '" + gameName + "' cargada correctamente.\n");
    }

    /**
     * Método que muestra el texto del menú principal y gestiona la entrada del usuario.
     */
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
            case "1":
                opcionArqueologos();
                break;
            case "2":
                opcionHabilidades();
                break;
            case "3":
                opcionLicencias();
                break;
            case "4":
                opcionAtlas();
                break;
            case "5":
                opcionCatalogo();
                break;
            case "6":
                opcionExcavar();
                break;
            case "7":
                opcionEstadisticas();
                break;
            case "0":
                this.isRunning = false;
                break;
            default:
                System.out.println("Opción no válida. Inténtalo de nuevo.");
        }
    }

    /**
     * Método que cierra todo el sistema, liberando los recursos.
     */
    private void end() {
        save();
        System.out.println("Cerrando el sistema Archaios. ¡Hasta pronto!");
        scanner.close();
    }

    /**
     * Método que guarda todo el sistema (Simulado).
     */
    private void save() {
        System.out.println("Guardando el estado de la partida...");
        // Aquí iría la lógica con GSON para guardar en archivo JSON.
    }

    // --- Métodos de operaciones para cada opción del menú ---

    private void opcionArqueologos() {
        System.out.println("\n-- Menú de Arqueólogos --");
        System.out.println("(Implementación pendiente)");
    }

    private void opcionHabilidades() {
        System.out.println("\n-- Menú de Habilidades --");
        System.out.println("(Implementación pendiente)");
    }

    private void opcionLicencias() {
        System.out.println("\n-- Menú de Licencias --");
        System.out.println("(Implementación pendiente)");
    }

    private void opcionAtlas() {
        System.out.println("\n-- Atlas de Regiones --");
        System.out.println("(Implementación pendiente)");
    }

    private void opcionCatalogo() {
        System.out.println("\n-- Catálogo de Tesoros --");
        System.out.println("(Implementación pendiente)");
    }

    /**
     * Método encargado de inicializar y lanzar el proceso de excavación.
     */
    private void opcionExcavar() {
        System.out.println("\n-- Preparando Excavación --");
        
        // Creación de objetos simulados (Mocks) para probar la clase Excavation
        Region mockRegion = new Region();
        Archeologist mockArcheologist = new Archeologist();
        
        // Terreno de prueba: Pequeño y Cuadrado (5x5), garantizando 1 tesoro común mínimo
        Terrain mockTerrain = new Terrain(
            TerrainSize.SMALL, 
            TerrainShape.SQUARE, 
            5, 5, 
            1, 0, 0
        );

        // Se instancia la clase principal del motor de excavación
        Excavation excavationGame = new Excavation();
        
        System.out.println("Iniciando excavación en terreno " + mockTerrain.getSize() + "...");
        
        // Lanzamos la excavación
        excavationGame.start(mockRegion, mockArcheologist, mockTerrain);
        
        // Al terminar, el control vuelve aquí automáticamente
        System.out.println("Excavación finalizada. Volviendo al menú principal.");
    }

    private void opcionEstadisticas() {
        System.out.println("\n-- Estadísticas --");
        System.out.println("(Implementación pendiente)");
    }
}