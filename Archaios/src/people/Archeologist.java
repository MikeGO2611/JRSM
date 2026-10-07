package people;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Archeologist {
    private String name = null;
    private int exp = 0;
    private int maxExp = 50;
    private int level= 0;
    private String specialization = null; 
    private String rarity;
    private List<Skill> skillCollection = new ArrayList<>();
    private int extraActions = 0;
    private int extraRandomTreasure = 0;
    private int extraCTreasure = 0;
    private int extraITreasure = 0;
    private int extraRTreasure = 0;
    private List<ETerrainShape> terrainShapes = new ArrayList<>();

    public Archeologist(String name, String rarity) {
    this.name = name;
    this.rarity = rarity;
    this.terrainShapes.add(ETerrainShape.CUADRADO); // Asumiendo que el enum lo llama CUADRADO o similar
}
    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    /*public void archeologistInformation(){
        StringBuilder information = new StringBuilder();
        information.append("--------------------").append(this.name)
        .append("--------------------\n")
        .append("Nivel: ").append(this.level)
        .append("Exp: ").append(exp).append("/");
        if(level!=5) {information.append(maxExp);};
        information.append("\nTerrenos: \n").append("  -Pequeños\n");
        if(level >= 2){information.append("  -Mediano\n");};
        if(level >= 4){information.append("  -Grande\n");};
        information.append("Habilidades: ");

        System.out.println(information);
    }*/
    //Si tiene especializacion al escavar se le llamaria si tiene especializacion
    public void extrasExpSpecialization(){
        extraActions = this.exp/25;
        extraRandomTreasure = this.exp/50;
        extraCTreasure = this.exp/50;
        extraITreasure = this.exp/100;
        extraRTreasure = this.exp/200;
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

    public int getLevel(){
        return this.level;
    }

    public int getExp(){
        return exp;
    }

    public int getSkillBonus(){
        return -1;
    }

    public List<Skill> getSkillList(){
        return this.skillCollection;
    }

    //Region seria darle al usuario una lista de las regiones que puede escoger y segun 
    //la que escoja se devuelve el nombre.
    public void setSpecialization(){
        this.specialization = Region;
    }

    public void addSkill(){
        //Añadir las listas de habilidades y ver como sacar una Skill del Scanner.
        Scanner scn = new Scanner(System.in);
        System.out.println("El arqueólogo " + this.name + " ha subido al nivel " + this.level);
        System.out.println("Puede aprender una de estas tres habilidades:");
        System.out.println("Habilidad 1");
        System.out.println("Habilidad 2");
        System.out.println("Habilidad 3");
        System.out.print("Habilidad a aprender: ");
        String skill = scn.nextLine();

        this.skillCollection.add(skill);
    }

    public int getExtraActions() {
        return extraActions;
    }
    public int getExtraRandomTreasure() {
        return extraRandomTreasure;
    }
    public int getExtraCTreasure() {
        return extraCTreasure;
    }
    public int getExtraITreasure() {
        return extraITreasure;
    }
    public int getExtraRTreasure() {
        return extraRTreasure;
    }

    public void addTerrainShape(ETerrainShape shape) {
    if (!this.terrainShapes.contains(shape)) {
        this.terrainShapes.add(shape);
    }

    public String getRarity() {
    return this.rarity;
}

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public List<ETerrainShape> getTerrainShapes() {
        return this.terrainShapes;
    }
}
}
