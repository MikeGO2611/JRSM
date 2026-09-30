import java.util.ArrayList;

public class Archeologist {
    public String name;
    public int exp;
    public String specialization = null; 
    public List<Skill> skillCollection = new ArrayList<>();

    public void setName(String name){
        this.name = name;
    }

    public int getLevel(){

    }

    public int getSkillBonus(){

    }

    public void setSpecialization(String region){
        this.specialization = region;
    }

    public void addSkill(Skill s){
        this.skillCollection.add(s);
    }
}
