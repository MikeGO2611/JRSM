package org.archaios.regions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.archaios.regions.Countries.Egypt;
import org.archaios.regions.Countries.Greece;
import org.archaios.regions.Countries.Petra;
import org.archaios.regions.Countries.Sumeria;

public class Atlas {
    private static Map<String, Region> regionCollection = new HashMap<>();

    static {
        regionCollection.put("Greece", new Greece());
        regionCollection.put("Egypt", new Egypt());
        regionCollection.put("Sumeria", new Sumeria());
        regionCollection.put("Petra", new Petra());
    }

    public static Region getRegion(String name){
        return regionCollection.get(name);
    }

    public static List<Region> getRegionCollection() {
        List<Region> regionList = new ArrayList<>();
        
        regionCollection.forEach((name, region) -> {
            regionList.add(region);
        });

        return regionList;
    }
}
