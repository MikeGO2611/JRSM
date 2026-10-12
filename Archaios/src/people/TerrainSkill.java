package people;
import java.util.List;

public class TerrainSkill extends Skill {
    private List<ETerrainShape> shapes;
    
    public TerrainSkill(String name, String description, List<ETerrainShape> shapes) {
        super(name, description, "terreno");
        this.shapes = shapes;
    }

    @Override
    public List<ETerrainShape> getBonusTerrainShapes() {
        return shapes;
    }
}