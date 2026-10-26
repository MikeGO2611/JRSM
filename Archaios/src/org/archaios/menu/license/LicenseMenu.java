package org.archaios.menu.license;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.archaios.commons.input.UserInput;
import org.archaios.commons.operations.IOperation;
import org.archaios.commons.statistics.Stats;
import org.archaios.menu.MenuGenerator;
import org.archaios.menu.archeologist.ArcheologistMenuUtils;
import org.archaios.people.Archeologist;

/**
 * Menú con las opciones para las licencias.
 * @author Miguel González Obenza
 */
public class LicenseMenu implements IOperation {

    @Override
    public String getName() {
        return "Licencias";
    }

    @Override
    public void operation() {
        System.out.println(MenuGenerator.generateTitle(getName(), ':', 20));

        Archeologist selectedArcheologist = ArcheologistMenuUtils.selectArcheologist();
        if (selectedArcheologist == null) {
            return;
        }

        int licenseCost = selectedArcheologist.getNextLicenseCost();
        System.out.println("Puntos disponibles: " + Stats.getPoints());
        System.out.println("Coste de la siguiente licencia para " + selectedArcheologist.getName() + ": " + licenseCost + " puntos.");

        if (Stats.getPoints() < licenseCost) {
            System.out.println("No tienes suficientes puntos para comprar una licencia a este arqueólogo.");
            return;
        }
        
        // TODO: Sustituir por Archaios.regionRepository.getDifficultRegions() cuando este implementado el repositorio
        String[] codes = {"EGP", "PTR", "SUM"};
        String[] names = {"Egipto", "Petra", "Sumeria"};

        List<String> availableCodes = new ArrayList<>();
        List<String> availableNames = new ArrayList<>();

        for (int i = 0; i < codes.length; i++) {
            if (!selectedArcheologist.hasLicense(codes[i])) {
                availableCodes.add(codes[i]);
                availableNames.add(names[i]);
            }
        }

        if (availableCodes.isEmpty()) {
            System.out.println("Este arqueólogo ya dispone de todas las licencias disponibles.");
            return;
        }

        System.out.println(MenuGenerator.generateTitle("Regiones disponibles", ':', 20));
        for (int i = 0; i < availableNames.size(); i++) {
            System.out.println((i + 1) + ". " + availableNames.get(i) + " [" + availableCodes.get(i) + "]");
        }
        System.out.println("0. Cancelar");

        int choice = UserInput.getUserIntegerInRange(0, availableCodes.size(), "Opción no válida.");

        if (choice > 0) {
            String chosenCode = availableCodes.get(choice - 1);
            String chosenName = availableNames.get(choice - 1);

            Stats.addPoints(-licenseCost);
            selectedArcheologist.addLicense(chosenCode);

            System.out.println("Licencia para " + chosenName + " adquirida con éxito para " + selectedArcheologist.getName() + ".");
        }
    }
}