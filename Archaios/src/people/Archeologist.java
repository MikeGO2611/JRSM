package people;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Archeologist {
    private String name = null;
    private int exp = 0;
    private int level= 0;
    private String specialization = null; 
    boolean haveSpecialization = false;
    private List<Skill> skillCollection = new ArrayList<>();
    private int extraActions = 0;
    private int extraRandomTreasura = 0;
    private int extraCTreasure = 0;
    private int extraITreasure = 0;
    private int extraRTreasure = 0;

    public void setName(String name){
        this.name = name;
    }

    public void extrasExpSpecialization(){
        extraActions = exp/25;
        extraRandomTreasura = exp/50;
        extraCTreasure = exp/50;
        extraITreasure = exp/100;
        extraRTreasure = exp/200;
    }

    public void addExp(int exp){
        int levelInicial = level;
        this.exp += exp;
        if(level != 5){
            if(exp >= 50){
                if(exp < 100){
                    level = 1;
                }else if(exp < 150){
                    level = 2;
                }else if(exp < 200){
                    level = 3;
                }else if(exp < 250){
                    level = 4;
                }else{
                    level = 5;
                    setSpecialization();
                    haveSpecialization = true;
                }
            }
        }
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
}
