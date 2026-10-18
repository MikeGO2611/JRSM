package org.archaios.commons.repositories;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.archaios.excavation.ETerrainShape;
import org.archaios.main.Archaios;
import org.archaios.people.Archeologist;
import org.archaios.skills.ExcavationSkill;
import org.archaios.skills.ExpertSkill;
import org.archaios.skills.RevelationSkill;
import org.archaios.skills.Skill;
import org.archaios.skills.SkillService;
import org.archaios.skills.TerrainSkill;
import org.archaios.skills.TreasureSkill;

/**
 * Clase que extiende de Repository para usar solo con habilidades.
 */
public class Skills extends Repository<Skill> {

    public Skills() {
        super();

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

        super.addElement(extraI);
        super.addElement(extraII);
        super.addElement(extraIII);

        super.addElement(aprendiz);
        super.addElement(experto);
        super.addElement(maestro);

        super.addElement(especialistaU);
        super.addElement(especialistaR);
        super.addElement(especialistaM);

        super.addElement(excavacionI);
        super.addElement(excavacionII);
        super.addElement(excavacionIII);

        super.addElement(experienciaI);
        super.addElement(experienciaII);

        super.addElement(limpiezaI);
        super.addElement(limpiezaII);

        super.addElement(escaneoA);
        super.addElement(escaneoB);
        super.addElement(escaneoC);
        super.addElement(escaneoD);

        super.addElement(investigacion);

        super.addElement(licenciaA);
        super.addElement(licenciaB);

        super.addElement(espDesierto);
        super.addElement(espAgua);
        super.addElement(espSubmarinismo);
        super.addElement(espMarI);
        super.addElement(espMarII);
        super.addElement(espDemolicionesA);
    }

    public List<Skill> getRandomCompatibleSkills(Archeologist archeologist, int count){
        List<Skill> skillList = SkillService.selectThreeSkills(archeologist, getAllElements());
        while (skillList.size() > count) {
            skillList.removeLast();
        }


        return skillList;
    }

}