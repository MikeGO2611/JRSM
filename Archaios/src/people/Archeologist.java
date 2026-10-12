package people;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
        this.terrainShapes.add(ETerrainShape.CUADRADO);
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

    public void addSkill() {
        Scanner scn = new Scanner(System.in);
        System.out.println("El arqueólogo " + this.name + " ha subido al nivel " + this.level);
        System.out.println("Puede aprender una de estas tres habilidades:");
        System.out.println("Habilidad 1");
        System.out.println("Habilidad 2");
        System.out.println("Habilidad 3");
        System.out.print("Habilidad a aprender: ");
        String skill = scn.nextLine();
    }

    public void extrasExpSpecialization() {
        this.extraActions = this.exp / 25;
        this.extraRandomTreasure = this.exp / 50;
        this.extraCTreasure = this.exp / 50;
        this.extraITreasure = this.exp / 100;
        this.extraRTreasure = this.exp / 200;
    }

    public void addExp(int expGained) {
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
            addSkill();
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
}