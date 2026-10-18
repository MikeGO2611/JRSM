package org.archaios.menu.excavation;

import java.util.List;

import org.archaios.commons.input.UserInput;
import org.archaios.commons.operations.IOperation;
import org.archaios.excavation.ETerrainSize;
import org.archaios.excavation.Excavation;
import org.archaios.menu.MenuGenerator;
import org.archaios.menu.archeologist.ArcheologistMenuUtils;
import org.archaios.people.Archeologist;
import org.archaios.regions.Countries.Greece;
import org.archaios.treasures.Treasure;

/**
 * Menú con las opciones de excavación.
 * 
 * @author Miguel González Obenza
 */
public class ExcavateMenu implements IOperation {

    @Override
    public String getName() {
        return "Excavación";
    }

    @Override
    public void operation() {
        System.out.println(MenuGenerator.generateTitle(getName(), ':', 20));
        Archeologist selectedArcheologist = ArcheologistMenuUtils.selectArcheologist();
        if (selectedArcheologist == null) {
            return;
        }

        // Elegir region
        // Elegir tamaño

        ETerrainSize terrain = ETerrainSize.SMALL;
        Excavation excavation = new Excavation(selectedArcheologist, new Greece(), terrain);
        System.out.println("Excavación iniciada. Puedes introducir varias coordenadas separadas por espacios, por ejemplo 1A 2A 3A.");

        while (!excavation.isFinished()) {
            excavation.printExcavationState();

            System.out.print("Coordenadas: ");
            String[] coordinates = UserInput.getUserString().trim().split("\\s+");
            boolean dugAtLeastOne = false;

            for (String input : coordinates) {
                int[] coordinate = parseCoordinate(input, terrain);
                if (coordinate == null) {
                    System.out.println("Coordenada no válida: " + input + ". Usa el formato fila+columna, por ejemplo 1A.");
                    continue;
                }

                int foundBeforeDig = excavation.getFoundTreasures().size();
                excavation.digPosition(coordinate[0], coordinate[1]);
                dugAtLeastOne = true;

                if (excavation.getFoundTreasures().size() > foundBeforeDig) {
                    Treasure treasure = excavation.getFoundTreasures()
                            .get(excavation.getFoundTreasures().size() - 1);
                    System.out.println("¡Has encontrado: " + treasure.getName() + "!");
                } else {
                    System.out.println("No había ningún tesoro en esa casilla.");
                }

                if (excavation.isFinished()) {
                    break;
                }
            }

            if (!dugAtLeastOne) {
                System.out.println("Introduce al menos una coordenada válida.");
            }
        }

        excavation.printExcavationState();
        printResults(excavation.getFoundTreasures());
    }

    private int[] parseCoordinate(String value, ETerrainSize terrain) {
        String input = value.toUpperCase();
        int letterStart = 0;
        while (letterStart < input.length()
                && Character.isDigit(input.charAt(letterStart))) {
            letterStart++;
        }

        if (letterStart == 0 || letterStart == input.length()) {
            return null;
        }

        try {
            int row = Integer.parseInt(input.substring(0, letterStart));
            String letters = input.substring(letterStart);
            int column = 0;
            boolean valid = row >= 1 && row <= terrain.getRows();

            for (int i = 0; valid && i < letters.length(); i++) {
                char letter = letters.charAt(i);
                if (letter < 'A' || letter > 'Z') {
                    valid = false;
                    break;
                }

                int digit = letter - 'A' + 1;
                if (column > (terrain.getCols() - digit) / 26) {
                    valid = false;
                    break;
                }
                column = column * 26 + digit;
            }

            if (valid && column >= 1 && column <= terrain.getCols()) {
                return new int[] { row - 1, column - 1 };
            }
        } catch (NumberFormatException e) {
            return null;
        }

        return null;
    }

    private void printResults(List<Treasure> foundTreasures) {
        System.out.println("La excavación ha terminado.");
        if (foundTreasures.isEmpty()) {
            System.out.println("No has encontrado ningún tesoro.");
            return;
        }

        System.out.println("Tesoros encontrados:");
        for (Treasure treasure : foundTreasures) {
            System.out.println("- " + treasure.getName());
        }
    }

}
