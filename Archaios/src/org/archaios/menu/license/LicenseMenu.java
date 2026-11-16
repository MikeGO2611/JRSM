package org.archaios.menu.license;

import java.util.ArrayList;
import java.util.List;

import org.archaios.commons.input.UserInput;
import org.archaios.commons.operations.IOperation;
import org.archaios.commons.statistics.Stats;
import org.archaios.main.Archaios;
import org.archaios.menu.MenuGenerator;
import org.archaios.menu.archeologist.ArcheologistMenuUtils;
import org.archaios.people.Archeologist;
import org.archaios.regions.Region;
import org.archaios.regions.Countries.ITerDif;

/**
 * Menú con las opciones para las licencias.
 * @author Javier Búa Martínez
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

        List<Region> availableRegions = new ArrayList<>();
        for (Region region : Archaios.regionsRepository.getAllElements()) {
            if (region instanceof ITerDif && !selectedArcheologist.hasLicense(region.getCode())) {
                availableRegions.add(region);
            }
        }

        if (availableRegions.isEmpty()) {
            System.out.println("Este arqueólogo ya dispone de todas las licencias disponibles.");
            return;
        }

        System.out.println(MenuGenerator.generateTitle("Regiones disponibles", ':', 20));
        for (int i = 0; i < availableRegions.size(); i++) {
            Region reg = availableRegions.get(i);
            System.out.println((i + 1) + ". " + reg.getName() + " [" + reg.getCode() + "]");
        }
        System.out.println("0. Cancelar");

        int choice = UserInput.getUserIntegerInRange(0, availableRegions.size(), "Opción no válida.");

        if (choice > 0) {
            Region chosenRegion = availableRegions.get(choice - 1);

            Stats.addPoints(-licenseCost);
            selectedArcheologist.addLicense(chosenRegion.getCode());

            System.out.println("Licencia para " + chosenRegion.getName() + " adquirida con éxito para " + selectedArcheologist.getName() + ".");
        }
    }
}