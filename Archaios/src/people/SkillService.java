package people;

import helpers.UtilsHelper;
import java.util.ArrayList;
import java.util.List;

public class SkillService {

    public static List<Skill> selectThreeSkills(Archeologist arch, List<Skill> allSkills) {
        List<Skill> validSkills = new ArrayList<>();
        List<Skill> upgradeSkills = new ArrayList<>();

        for (Skill s : allSkills) {
            if (arch.getSkillList().contains(s)) {
                continue;
            }

            boolean incompatible = false;
            for (Skill learned : arch.getSkillList()) {
                if (s.getIncompatibilities().contains(learned) || learned.getIncompatibilities().contains(s)) {
                    incompatible = true;
                    break;
                }
            }
            if (incompatible) {
                continue;
            }

            boolean hasAllDependencies = true;
            for (Skill dep : s.getDependencies()) {
                if (!arch.getSkillList().contains(dep)) {
                    hasAllDependencies = false;
                    break;
                }
            }

            if (hasAllDependencies) {
                validSkills.add(s);
                if (!s.getDependencies().isEmpty()) {
                    upgradeSkills.add(s);
                }
            }
        }
        List<Skill> options = new ArrayList<>();

        if (!upgradeSkills.isEmpty()) {
            List<Integer> idx = UtilsHelper.genRandomIdxs(1, upgradeSkills.size());
            Skill upgradeChoice = upgradeSkills.get(idx.get(0));
            options.add(upgradeChoice);
            validSkills.remove(upgradeChoice);
        }

        while (options.size() < 3 && !validSkills.isEmpty()) {
            List<Integer> idx = UtilsHelper.genRandomIdxs(1, validSkills.size());
            Skill randomChoice = validSkills.get(idx.get(0));
            options.add(randomChoice);
            validSkills.remove(randomChoice);
        }

        return options;
    }
}