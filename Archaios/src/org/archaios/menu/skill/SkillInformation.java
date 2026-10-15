package org.archaios.menu.skill;

import java.util.List;

import org.archaios.commons.operations.IOperation;
import org.archaios.menu.MenuGenerator;

public class SkillInformation  implements IOperation {

    String name;
    String description;
    List<SkillInformation> dependencyCollection = null;
    List<SkillInformation> incompatibilityCollection = null;

    public SkillInformation(String name, String description){
        this.name = name;
        this.description = description;
    }

    public SkillInformation(String name, String description, List<SkillInformation> dependencyCollection, List<SkillInformation> incompatibilityCollection){
        this(name, description);

        if (dependencyCollection != null){
            this.dependencyCollection = dependencyCollection;
        }
        
        if (incompatibilityCollection != null){
            this.incompatibilityCollection = incompatibilityCollection;
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void operation() {
        System.out.println(MenuGenerator.generateTitle(getName(), '-', 20));
        System.out.println(description);

        if (!dependencyCollection.isEmpty() && dependencyCollection != null){
            System.out.println(MenuGenerator.generateDivisor('-', 20));
            System.out.println("Dependencias: ");
            dependencyCollection.forEach( (dependency) ->
                System.out.println(dependency.getName())
            );
        }

        if(!incompatibilityCollection.isEmpty() && incompatibilityCollection != null){
            System.out.println(MenuGenerator.generateDivisor('-', 20));
            System.out.println("Incompatibilidades: ");
            incompatibilityCollection.forEach( (incompatibility) ->
                System.out.println(incompatibility.getName())
            );
        }
    }

}
