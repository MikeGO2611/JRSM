package people;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import skills.Skill;
import skills.SkillService;
import skills.ESkillType;
import excavation.ETerrainShape;
import treasures.ETreasureRarity;

/**
 * Representa a un arqueólogo en el juego, gestionando su progreso,
 * experiencia, nivel, terrenos disponibles y bonificaciones acumuladas.
 * 
 * @author Javier Búa Martínez
 */
public class Archeologist {
    private String name = null;
    private int exp = 0;
    private int maxExp = 50;
    private int level = 0;
    private String specialization = null;
    private String rarity;
    private List<Skill> skillCollection = new ArrayList<>();
    private List<ETerrainShape> terrainShapes = new ArrayList<>();
    private int extraActions = 0;
    private int extraRandomTreasure = 0;
    private int extraCTreasure = 0;
    private int extraITreasure = 0;
    private int extraRTreasure = 0;

    /**
     * Construye un nuevo arqueólogo con su nombre y rareza.
     * Inicializa la forma de terreno básica cuadrada.
     * 
     * @param name Nombre del arqueólogo.
     * @param rarity Rareza inicial del arqueólogo.
     */
    public Archeologist(String name, String rarity) {
        this.name = name;
        this.rarity = rarity;
        this.terrainShapes.add(ETerrainShape.SQUARE);
    }

    /**
     * Imprime por consola la ficha detallada del arqueólogo con su nivel,
     * experiencia, especialización, tamaños de terreno y habilidades aprendidas.
     */
    public void archeologistInformation() {
        StringBuilder sb = new StringBuilder();
        sb.append("--------------------").append(this.name).append("--------------------\n");
        sb.append("Nivel: ").append(this.level).append("\n");

        if (this.level == 5) {
            if (this.specialization != null) {
                sb.append("Especialización: ").append(this.specialization).append("\n");
            }
            sb.append("Exp: ").append(this.exp).append("\n");
        } else {
            sb.append("Exp: ").append(this.exp).append("/").append(this.maxExp).append("\n");
        }

        sb.append("Terrenos:\n");
        sb.append("  - Pequeños\n");
        if (this.level >= 2) {
            sb.append("  - Mediano\n");
        }
        if (this.level >= 4) {
            sb.append("  - Grande\n");
        }

        sb.append("Habilidades:\n");
        for (Skill skill : this.skillCollection) {
            sb.append("  ").append(skill.getName()).append("\n");
            sb.append("   ").append(skill.getDescription()).append("\n");
        }

        System.out.print(sb.toString());
    }

    /**
     * Calcula los puntos de compensación al licenciar al arqueólogo según su nivel y rareza.
     * 
     * @return Puntos obtenidos tras el despido.
     */
    public int getDismissPoints() {
        int points = this.level * 10;
        if ("infrecuente".equalsIgnoreCase(this.rarity)) {
            points += 10;
        } else if ("raro".equalsIgnoreCase(this.rarity)) {
            points += 20;
        }
        return points;
    }

    /**
     * Obtiene el nombre del arqueólogo.
     * 
     * @return Nombre del arqueólogo.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Establece el nombre del arqueólogo.
     * 
     * @param name Nuevo nombre.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtiene el nivel actual del arqueólogo.
     * 
     * @return Nivel (entre 0 y 5).
     */
    public int getLevel() {
        return this.level;
    }

    /**
     * Obtiene la experiencia acumulada actual.
     * 
     * @return Puntos de experiencia.
     */
    public int getExp() {
        return this.exp;
    }

    /**
     * Obtiene la experiencia requerida para el siguiente nivel.
     * 
     * @return Límite de experiencia del nivel actual.
     */
    public int getMaxExp() {
        return this.maxExp;
    }

    /**
     * Obtiene la rareza del arqueólogo.
     * 
     * @return Rareza del arqueólogo.
     */
    public String getRarity() {
        return this.rarity;
    }

    /**
     * Modifica la rareza del arqueólogo.
     * 
     * @param rarity Nueva rareza.
     */
    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    /**
     * Obtiene la región de especialización del arqueólogo.
     * 
     * @return Nombre o identificador de la especialización.
     */
    public String getSpecialization() {
        return this.specialization;
    }

    /**
     * Asigna la especialización al alcanzar nivel 5.
     * 
     * @param specialization Nombre de la región asignada.
     */
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    /**
     * Devuelve la lista de habilidades aprendidas.
     * 
     * @return Lista de habilidades.
     */
    public List<Skill> getSkillList() {
        return this.skillCollection;
    }

    /**
     * Devuelve las formas de terreno desbloqueadas.
     * 
     * @return Lista de formas de terreno disponibles.
     */
    public List<ETerrainShape> getTerrainShapes() {
        return this.terrainShapes;
    }

    /**
     * Añade una nueva forma de terreno a la colección si aún no ha sido desbloqueada.
     * 
     * @param shape Forma de terreno a añadir.
     */
    public void addTerrainShape(ETerrainShape shape) {
        if (!this.terrainShapes.contains(shape)) {
            this.terrainShapes.add(shape);
        }
    }

    /**
     * Asigna una habilidad al arqueólogo y desbloquea las formas de terreno asociadas.
     * 
     * @param skill Habilidad a aprender.
     */
    public void addSkill(Skill skill) {
        if (skill != null && !this.skillCollection.contains(skill)) {
            this.skillCollection.add(skill);
            for (ETerrainShape shape : skill.getBonusTerrainShapes()) {
                addTerrainShape(shape);
            }
        }
    }

    /**
     * Presenta tres opciones válidas al usuario y permite seleccionar por consola
     * la habilidad que el arqueólogo aprenderá al subir de nivel.
     * 
     * @param allSkills Colección completa de habilidades del juego.
     */
    public void addSkill(List<Skill> allSkills) {
        List<Skill> options = SkillService.selectThreeSkills(this, allSkills);
        if (options.isEmpty()) {
            return;
        }

        Scanner scn = new Scanner(System.in);
        System.out.println("El arqueólogo " + this.name + " ha subido al nivel " + this.level);
        System.out.println("Puede aprender una de estas habilidades:");

        for (int i = 0; i < options.size(); i++) {
            Skill s = options.get(i);
            System.out.println((i + 1) + ". " + s.getName() + " - " + s.getDescription());
        }

        // Validación de la entrada del usuario en consola
        int choice = -1;
        while (choice < 1 || choice > options.size()) {
            System.out.print("Habilidad a aprender (1-" + options.size() + "): ");
            if (scn.hasNextInt()) {
                choice = scn.nextInt();
            } else {
                scn.next();
            }
        }

        Skill selectedSkill = options.get(choice - 1);
        addSkill(selectedSkill);
    }

    /**
     * Recalcula las bonificaciones pasivas otorgadas por el excedente de experiencia
     * tras haber alcanzado el nivel 5.
     */
    public void extrasExpSpecialization() {
        this.extraActions = this.exp / 25;
        this.extraRandomTreasure = this.exp / 50;
        this.extraCTreasure = this.exp / 50;
        this.extraITreasure = this.exp / 100;
        this.extraRTreasure = this.exp / 200;
    }

    /**
     * Incrementa la experiencia del arqueólogo, procesa las subidas de nivel pertinentes
     * e inicia la selección de habilidades por cada nivel ganado.
     * 
     * @param expGained Puntos de experiencia ganados.
     * @param allSkills Catálogo total de habilidades disponibles.
     */
    public void addExp(int expGained, List<Skill> allSkills) {
        int levelInicial = this.level;
        this.exp += expGained;

        // Comprobación de subida de nivel iterativa
        while (this.level < 5 && this.exp >= this.maxExp) {
            this.exp -= this.maxExp;
            this.level++;
            this.maxExp += 50;
            if (this.level == 5) {
                break;
            }
        }

        // Si ya es nivel 5, la experiencia sobrante computa para bonos de especialización
        if (this.level == 5) {
            extrasExpSpecialization();
        }

        int levelsGained = this.level - levelInicial;
        for (int i = 0; i < levelsGained; i++) {
            addSkill(allSkills);
        }
    }

    /**
     * @return Acciones extra otorgadas por especialización.
     */
    public int getExtraActions() {
        return this.extraActions;
    }

    /**
     * @return Tesoros aleatorios extra otorgados por especialización.
     */
    public int getExtraRandomTreasure() {
        return this.extraRandomTreasure;
    }

    /**
     * @return Tesoros comunes garantizados otorgados por especialización.
     */
    public int getExtraCTreasure() {
        return this.extraCTreasure;
    }

    /**
     * @return Tesoros infrecuentes garantizados otorgados por especialización.
     */
    public int getExtraITreasure() {
        return this.extraITreasure;
    }

    /**
     * @return Tesoros raros garantizados otorgados por especialización.
     */
    public int getExtraRTreasure() {
        return this.extraRTreasure;
    }

    /**
     * @return Valor genérico de bono de habilidad.
     */
    public int getSkillBonus() {
        return -1;
    }

    /**
     * Calcula la suma total de acciones base y bonificaciones de habilidades y especialización.
     * 
     * @return Total de acciones extra acumuladas.
     */
    public int getTotalBonusActions() {
        int total = this.extraActions;
        for (Skill s : this.skillCollection) {
            total += s.getBonusActions();
        }
        return total;
    }

    /**
     * Calcula el multiplicador acumulado de acciones derivado de las habilidades aprendidas.
     * 
     * @return Multiplicador final.
     */
    public float getTotalActionsMultiplier() {
        float multiplier = 1.0f;
        for (Skill s : this.skillCollection) {
            multiplier *= s.getBonusActionsPercentage();
        }
        return multiplier;
    }

    /**
     * Calcula la cantidad total de tesoros aleatorios adicionales.
     * 
     * @return Total de tesoros aleatorios extra.
     */
    public int getTotalRandomTreasures() {
        int total = this.extraRandomTreasure;
        for (Skill s : this.skillCollection) {
            total += s.getBonusRandomTreasures();
        }
        return total;
    }

    /**
     * Acumula y clasifica los tesoros garantizados por rareza obtenidos tanto por
     * especialización de nivel 5 como por habilidades.
     * 
     * @return Mapa con la cantidad mínima de tesoros agrupada por rareza.
     */
    public Map<ETreasureRarity, Integer> getTotalBonusTreasures() {
        Map<ETreasureRarity, Integer> totals = new HashMap<>();
        totals.put(ETreasureRarity.COMMON, this.extraCTreasure);
        totals.put(ETreasureRarity.UNCOMMON, this.extraITreasure);
        totals.put(ETreasureRarity.RARE, this.extraRTreasure);

        for (Skill s : this.skillCollection) {
            Map<ETreasureRarity, Integer> bonus = s.getBonusTreasures();
            for (ETreasureRarity rarity : bonus.keySet()) {
                int cantHabilidad = bonus.get(rarity);
                int cantActual = totals.getOrDefault(rarity, 0);
                totals.put(rarity, cantActual + cantHabilidad);
            }
        }
        return totals;
    }

    /**
     * Suma las casillas vacías reveladas aportadas por las habilidades de revelación.
     * 
     * @return Cantidad de casillas vacías que se descubren al inicio.
     */
    public int getRevelationEmpty() {
        int total = 0;
        for (Skill s : this.skillCollection) {
            total += s.getRevelationEmpty();
        }
        return total;
    }

    /**
     * Suma las pistas de interrogación o pistas de tesoro aportadas por habilidades.
     * 
     * @return Cantidad de pistas otorgadas.
     */
    public int getRevelationTreasure() {
        int total = 0;
        for (Skill s : this.skillCollection) {
            total += s.getRevelationTreasure();
        }
        return total;
    }
}