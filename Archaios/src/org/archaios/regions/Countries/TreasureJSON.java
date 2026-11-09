package org.archaios.regions.Countries;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.archaios.treasures.ETreasureRarity;
import org.archaios.treasures.ETreasureType;
import org.archaios.treasures.Treasure;

public class TreasureJSON {

    private static class TreasureData {
        private String code;
        private String name;
        private String description;
        private String rarity;
        private int parts;
        private int points;
        private String type;
    }

    public static List<Treasure> loadTreasures(String filePath) {

        Gson gson = new Gson();

        try (Reader reader = new FileReader(filePath)) {

            Type listType = new TypeToken<List<TreasureData>>() {}.getType();

            List<TreasureData> dataList = gson.fromJson(reader, listType);

            List<Treasure> treasures = new ArrayList<>();

            for (TreasureData data : dataList) {

                treasures.add(new Treasure(
                    data.name,
                    "EGP",
                    ETreasureType.getByCode(data.type),
                    data.code,
                    data.parts,
                    data.points,
                    ETreasureRarity.getByCode(data.rarity),
                    data.description
                ));
            }

            return treasures;

        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }
}