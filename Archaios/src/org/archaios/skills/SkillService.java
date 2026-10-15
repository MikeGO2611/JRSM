package org.archaios.skills;

import java.util.ArrayList;
import java.util.List;

import org.archaios.helpers.UtilsHelper;
import org.archaios.people.Archeologist;

/**
 * Servicio encargado de la lógica de selección y filtrado de habilidades
 * disponibles para los arqueólogos según dependencias e incompatibilidades.
 * 
 * @author Javier Búa Martínez
 */
public class SkillService {

    /**
     * Selecciona hasta tres habilidades válidas para ofrecer al arqueólogo al subir de nivel.
     * Prioriza una habilidad que sea mejora directa de otra ya aprendida y completa el resto al azar.
     * 
     * @param arch Arqueólogo que va a aprender la habilidad.
     * @param allSkills Catálogo completo de habilidades del juego.
     * @return Lista con un máximo de tres habilidades elegibles.
     */
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

        // Prioridad de la primera: si hay mejoras directas disponibles, se añade una al azar
        if (!upgradeSkills.isEmpty()) {
            List<Integer> idx = UtilsHelper.genRandomIdxs(1, upgradeSkills.size());
            Skill upgradeChoice = upgradeSkills.get(idx.get(0));
            options.add(upgradeChoice);
            validSkills.remove(upgradeChoice);
        }

        // Rellena los huecos restantes hasta 3 con habilidades válidas aleatorias
        while (options.size() < 3 && !validSkills.isEmpty()) {
            List<Integer> idx = UtilsHelper.genRandomIdxs(1, validSkills.size());
            Skill randomChoice = validSkills.get(idx.get(0));
            options.add(randomChoice);
            validSkills.remove(randomChoice);
        }

        return options;
    }
}