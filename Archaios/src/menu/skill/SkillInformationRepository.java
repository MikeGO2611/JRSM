package menu.skill;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SkillInformationRepository {
    public static Map<String, SkillInformation> skillMap = new HashMap<>();

    static {
        skillMap.put("Aprendíz", new SkillInformation(
            "Aprendiz", 
            "Asegura que al menos se generen 5 tesoros de rareza común en la excavación."));

        skillMap.put("Experto", new SkillInformation(
            "Experto", 
            "Asegura que al menos se generen 5 tesoros de rareza común y 5 de rareza infrecuente en la excavación.",
            Arrays.asList(skillMap.get("Aprendiz")),
            null
        ));

        skillMap.put("Maestro", new SkillInformation(
            "Maestro", 
            "Asegura que al menos se generen 5 tesoros de rareza infrecuente y 5 raros en la excavación.",
            Arrays.asList(skillMap.get("Aprendiz"), skillMap.get("Experto")),
            null
        ));
    }
}
