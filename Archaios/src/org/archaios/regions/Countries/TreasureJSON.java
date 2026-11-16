package org.archaios.regions.Countries;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.archaios.treasures.ETreasureRarity;
import org.archaios.treasures.ETreasureType;
import org.archaios.treasures.Treasure;

/**
 * Clase TreasureJSON.
 * Contiene lo necesario para leer los archivos JSON
 * que contienen los tesoros de cada región.
 *
 * @author Rubén Hernández
 */
public class TreasureJSON {

    /**
     * Variables a leer del JSON.
     */
    private static class TreasureData {
        private String code;
        private String name;
        private String description;
        private String rarity;
        private int parts;
        private int points;
        private String type;
    }

    private static ETreasureType getTreasureType(String code) {
        for (ETreasureType type : ETreasureType.values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        return null;
    }

    private static ETreasureRarity getTreasureRarity(String code) {
        for (ETreasureRarity rarity : ETreasureRarity.values()) {
            if (rarity.getCode().equals(code)) {
                return rarity;
            }
        }
        return null;
    }

    /**
     * Lee los datos recibidos y los guarda en una lista.
     *
     * @param filePath Ruta relativa al paquete Countries.
     * @return Lista de tesoros de la región.
     */
    public static List<Treasure> loadTreasures(String filePath) {

        Gson gson = new Gson();

        try {
            Path classPath = Paths.get(
                    TreasureJSON.class.getProtectionDomain()
                            .getCodeSource()
                            .getLocation()
                            .toURI());

            // Obtener la raíz dev_Ruben
            Path projectPath = classPath.getParent();

            // Entrar en Archaios/src
            Path basePath = projectPath.resolve(
                    "Archaios/src/org/archaios/regions/Countries").normalize();

            Path jsonPath = basePath.resolve(filePath).normalize();

            try (Reader reader = new FileReader(jsonPath.toFile())) {

                Type listType = new TypeToken<List<TreasureData>>() {
                }.getType();

                List<TreasureData> dataList = gson.fromJson(reader, listType);

                List<Treasure> treasures = new ArrayList<>();

                for (TreasureData data : dataList) {
                    treasures.add(new Treasure(
                            data.name,
                            "EGP",
                            getTreasureType(data.type),
                            data.code,
                            data.parts,
                            data.points,
                            getTreasureRarity(data.rarity),
                            data.description));
                }

                return treasures;
            }

        } catch (Exception e) {
            System.err.println("Error al cargar el archivo JSON: " + filePath);
            e.printStackTrace();
            return List.of();
        }
    }
}