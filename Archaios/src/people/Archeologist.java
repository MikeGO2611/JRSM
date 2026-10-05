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
    private List<Skill> skillCollection = new ArrayList<>();
    private int extraActions = 0;
    private int extraRandomTreasure = 0;
    private int extraCTreasure = 0;
    private int extraITreasure = 0;
    private int extraRTreasure = 0;

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
        extraActions = exp/25;
        extraRandomTreasure = exp/50;
        extraCTreasure = exp/50;
        extraITreasure = exp/100;
        extraRTreasure = exp/200;
    }

    public void addExp(int exp){
        int levelInicial = level;
        this.exp += exp;
        if(level != 5){
            if(exp > maxExp){
                exp /= 50;
                maxExp += 50;
                level++;
            }
            if(level == 5){
                setSpecialization();
            }
        }
        if(level == 5){extrasExpSpecialization();};
        //Cambiar el addSkill para hacer q dea una Skill aleatoria.
        if(levelInicial != level){addSkill();}
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
}
