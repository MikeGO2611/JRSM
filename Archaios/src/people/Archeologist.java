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

    public Archeologist(String name, String rarity) {
        this.name = name;
        this.rarity = rarity;
        this.terrainShapes.add(ETerrainShape.SQUARE);
    }

    public void archeologistInformation(){
        StringBuilder sb = new StringBuilder();
        sb.append("--------------------").append(this.name).append("--------------------\n");
        sb.append("Nivel: ").append(this.level).append("\n");

        if (this.level == 5){
            if (this.specialization != null){
                sb.append("Especialización: ").append(this.specialization).append("\n");
            }
            sb.append("Exp: ").append(this.exp).append("\n");
        }else {
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

    public int getDismissPoints() {
        int points = this.level * 10;
        if ("infrecuente".equalsIgnoreCase(this.rarity)) {
            points += 10;
        } else if ("raro".equalsIgnoreCase(this.rarity)) {
            points += 20;
        }
        return points;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return this.level;
    }

    public int getExp() {
        return this.exp;
    }

    public int getMaxExp() {
        return this.maxExp;
    }

    public String getRarity() {
        return this.rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public String getSpecialization() {
        return this.specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public List<Skill> getSkillList() {
        return this.skillCollection;
    }

    public List<ETerrainShape> getTerrainShapes() {
        return this.terrainShapes;
    }

    public void addTerrainShape(ETerrainShape shape) {
        if (!this.terrainShapes.contains(shape)) {
            this.terrainShapes.add(shape);
        }
    }

    public void addSkill(Skill skill) {
        if (skill != null && !this.skillCollection.contains(skill)) {
            this.skillCollection.add(skill);
            for (ETerrainShape shape : skill.getBonusTerrainShapes()) {
                addTerrainShape(shape);
            }
        }
    }

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

    public void extrasExpSpecialization() {
        this.extraActions = this.exp / 25;
        this.extraRandomTreasure = this.exp / 50;
        this.extraCTreasure = this.exp / 50;
        this.extraITreasure = this.exp / 100;
        this.extraRTreasure = this.exp / 200;
    }

    public void addExp(int expGained, List<Skill> allSkills) {
        int levelInicial = this.level;
        this.exp += expGained;

        while (this.level < 5 && this.exp >= this.maxExp) {
            this.exp -= this.maxExp;
            this.level++;
            this.maxExp += 50;
            if (this.level == 5) {
                break;
            }
        }

        if (this.level == 5) {
            extrasExpSpecialization();
        }

        int levelsGained = this.level - levelInicial;
        for (int i = 0; i < levelsGained; i++) {
            addSkill(allSkills);
        }
    }

    public int getExtraActions() {
        return this.extraActions;
    }

    public int getExtraRandomTreasure() {
        return this.extraRandomTreasure;
    }

    public int getExtraCTreasure() {
        return this.extraCTreasure;
    }

    public int getExtraITreasure() {
        return this.extraITreasure;
    }

    public int getExtraRTreasure() {
        return this.extraRTreasure;
    }

    public int getSkillBonus() {
        return -1;
    }

    public int getTotalBonusActions() {
        int total = this.extraActions;
        for (Skill s : this.skillCollection) {
            total += s.getBonusActions();
        }
        return total;
    }

    public float getTotalActionsMultiplier() {
        float multiplier = 1.0f;
        for (Skill s : this.skillCollection) {
            multiplier *= s.getBonusActionsPercentage();
        }
        return multiplier;
    }

    public int getTotalRandomTreasures() {
        int total = this.extraRandomTreasure;
        for (Skill s : this.skillCollection) {
            total += s.getBonusRandomTreasures();
        }
        return total;
    }

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

    public int getRevelationEmpty() {
        int total = 0;
        for (Skill s : this.skillCollection) {
            total += s.getRevelationEmpty();
        }
        return total;
    }

    public int getRevelationTreasure() {
        int total = 0;
        for (Skill s : this.skillCollection) {
            total += s.getRevelationTreasure();
        }
        return total;
    }
}