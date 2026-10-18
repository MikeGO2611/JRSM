package org.archaios.skills;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.archaios.excavation.ETerrainShape;

/**
 * Gestor encargado de inicializar, instanciar y configurar el catálogo
 * completo de habilidades del juego con sus dependencias e incompatibilidades.
 * 
 * @author Javier Búa Martínez
 */
public class SkillManager {
    /** Colección estática que almacena todas las habilidades disponibles del sistema. */
    private static List<Skill> skillCollection = new ArrayList<>();

    /**
     * Construye un nuevo gestor de habilidades.
     */
    public SkillManager() {
    }

    /**
     * Inicializa y devuelve el catálogo completo de habilidades del juego.
     * Si ya han sido cargadas previamente, devuelve la lista existente.
     * 
     * @return Lista con todas las habilidades instanciadas y relacionadas.
     */
    public static List<Skill> initSkills() {
        if (!skillCollection.isEmpty()) {
            return skillCollection;
        }

        // Habilidades de obtención de tesoros
        Skill extraI = new TreasureSkill("Extra I", "Añade 3 tesoros aleatorios a la excavación.", 3, 0, 0, 0);
        Skill extraII = new TreasureSkill("Extra II", "Añade 7 tesoros aleatorios a la excavación.", 7, 0, 0, 0);
        Skill extraIII = new TreasureSkill("Extra III", "Añade 10 tesoros aleatorios a la excavación.", 10, 0, 0, 0);

        extraII.addDependency(extraI);
        extraIII.addDependency(extraII);

        Skill aprendiz = new TreasureSkill("Aprendiz", "Asegura al menos 5 tesoros de rareza común en la excavación.", 0, 5, 0, 0);
        Skill experto = new TreasureSkill("Experto", "Asegura al menos 5 tesoros comunes y 5 infrecuentes en la excavación.", 0, 5, 5, 0);
        Skill maestro = new TreasureSkill("Maestro", "Asegura al menos 5 tesoros infrecuentes y 2 raros en la excavación.", 0, 0, 5, 2);

        experto.addDependency(aprendiz);
        maestro.addDependency(experto);

        Skill especialistaU = new TreasureSkill("Especialista U", "Aumenta en 10% la probabilidad de tesoros infrecuentes.", 0, 0, 0, 0);
        Skill especialistaR = new TreasureSkill("Especialista R", "Aumenta en 10% la probabilidad de tesoros raros.", 0, 0, 0, 0);
        Skill especialistaM = new TreasureSkill("Especialista M", "Aumenta en 5% la probabilidad de tesoros infrecuentes y 5% de raros.", 0, 0, 0, 0);

        especialistaU.addIncompatibility(especialistaR);
        especialistaU.addIncompatibility(especialistaM);
        especialistaR.addIncompatibility(especialistaU);
        especialistaR.addIncompatibility(especialistaM);
        especialistaM.addIncompatibility(especialistaU);
        especialistaM.addIncompatibility(especialistaR);

        // Habilidades de excavación
        Skill excavacionI = new ExcavationSkill("Excavación I", "Aumenta en 5 el número de acciones de excavación.", 5, 1.0f);
        Skill excavacionII = new ExcavationSkill("Excavación II", "Aumenta en 10 el número de acciones de excavación.", 10, 1.0f);
        Skill excavacionIII = new ExcavationSkill("Excavación III", "Aumenta en 15 el número de acciones de excavación.", 15, 1.0f);

        excavacionII.addDependency(excavacionI);
        excavacionIII.addDependency(excavacionII);

        Skill experienciaI = new ExcavationSkill("Experiencia I", "Aumenta las acciones de excavación en un 20%.", 0, 1.20f);
        Skill experienciaII = new ExcavationSkill("Experiencia II", "Aumenta las acciones de excavación en un 30%.", 0, 1.30f);

        experienciaII.addDependency(experienciaI);

        // Habilidades de revelación
        Skill limpiezaI = new RevelationSkill("Limpieza I", "Elimina 5 casillas sin tesoro de forma aleatoria al inicio.", 5, 0);
        Skill limpiezaII = new RevelationSkill("Limpieza II", "Elimina 10 casillas sin tesoro de forma aleatoria al inicio.", 10, 0);

        limpiezaII.addDependency(limpiezaI);

        Skill escaneoA = new RevelationSkill("Escaneo A", "Pone una interrogación en 3 posiciones aleatorias, revelando un tesoro.", 0, 3);
        Skill escaneoB = new RevelationSkill("Escaneo B", "Pone una interrogación en 3 posiciones aleatorias, revelando un tesoro.", 0, 3);
        Skill escaneoC = new RevelationSkill("Escaneo C", "Pone una interrogación en 3 posiciones aleatorias, revelando un tesoro.", 0, 3);
        Skill escaneoD = new RevelationSkill("Escaneo D", "Pone una interrogación en 3 posiciones aleatorias, revelando un tesoro.", 0, 3);

        Skill investigacion = new RevelationSkill("Investigación", "Desglosa la cantidad de tesoros de cada rareza en la rejilla.", 0, 0);

        // Habilidades de terreno
        Skill licenciaA = new TerrainSkill("Licencia A", "Permite excavar en formas de Fila y Columna.", Arrays.asList(ETerrainShape.ROW, ETerrainShape.COLUMN));
        Skill licenciaB = new TerrainSkill("Licencia B", "Permite excavar en formas Horizontal y Vertical.", Arrays.asList(ETerrainShape.HORIZONTAL, ETerrainShape.VERTICAL));

        licenciaA.addIncompatibility(licenciaB);
        licenciaB.addIncompatibility(licenciaA);

        // Habilidades de especialistas
        Skill espDesierto = new ExpertSkill("Especialista en desierto", "Establece el coste de excavar arena en 1 acción.", 1, "Arena", 0, null);
        Skill espAgua = new ExpertSkill("Especialista en agua", "Establece el coste de excavar agua en 2 acciones.", 2, "Agua", 0, null);
        Skill espSubmarinismo = new ExpertSkill("Especialista en submarinismo", "Establece el coste de excavar agua profunda en 2 acciones.", 2, "Agua profunda", 0, null);
        Skill espMarI = new ExpertSkill("Especialista en mar abierto I", "Establece el coste de excavar océano en 3 acciones.", 3, "Océano", 0, null);
        Skill espMarII = new ExpertSkill("Especialista en mar abierto II", "Establece el coste de excavar océano en 2 acciones.", 2, "Océano", 0, null);

        espMarII.addDependency(espMarI);

        Skill espDemolicionesA = new ExpertSkill("Especialista en demoliciones A", "Elimina 5 casillas de terreno de piedra al iniciar.", 0, null, 5, "Piedra");

        // Almacenamiento en la colección
        skillCollection.add(extraI);
        skillCollection.add(extraII);
        skillCollection.add(extraIII);
        skillCollection.add(aprendiz);
        skillCollection.add(experto);
        skillCollection.add(maestro);
        skillCollection.add(especialistaU);
        skillCollection.add(especialistaR);
        skillCollection.add(especialistaM);

        skillCollection.add(excavacionI);
        skillCollection.add(excavacionII);
        skillCollection.add(excavacionIII);
        skillCollection.add(experienciaI);
        skillCollection.add(experienciaII);
        skillCollection.add(limpiezaI);
        skillCollection.add(limpiezaII);
        skillCollection.add(escaneoA);
        skillCollection.add(escaneoB);
        skillCollection.add(escaneoC);
        skillCollection.add(escaneoD);
        skillCollection.add(investigacion);

        skillCollection.add(licenciaA);
        skillCollection.add(licenciaB);

        skillCollection.add(espDesierto);
        skillCollection.add(espAgua);
        skillCollection.add(espSubmarinismo);
        skillCollection.add(espMarI);
        skillCollection.add(espMarII);
        skillCollection.add(espDemolicionesA);

        return skillCollection;
    }

    /**
     * Obtiene la colección completa de habilidades del sistema.
     * 
     * @return Lista de habilidades.
     */
    public static List<Skill> getSkillCollection() {
        if (skillCollection.isEmpty()) {
            return initSkills();
        }
        return skillCollection;
    }
}