package regions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import regions.Countries.Greece;

public class Atlas {
    private static Map<String, Region> regionCollection = new HashMap<>();

    static {
        regionCollection.put("Greece", new Greece());
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
