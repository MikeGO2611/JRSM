package org.archaios.commons.json;

import java.lang.reflect.Type;

import org.archaios.commons.repositories.Skills;
import org.archaios.main.Archaios;
import org.archaios.people.Archeologist;
import org.archaios.skills.Skill;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * Adaptador de la clase <code>Skill</code> para serializar y deserializar.
 */
public class SkillAdapter implements JsonSerializer<Skill>, JsonDeserializer<Skill> {

    /**
     * Deserializar de JSON a <code>Skill</code>
     * @see com.google.gson.JsonDeserializer#deserialize(com.google.gson.JsonElement, java.lang.reflect.Type, com.google.gson.JsonDeserializationContext)
     */
    @Override
    public Skill deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
                String skillName = json.getAsString();
                Skill skill = null;
                for(Skill probableSkill : Archaios.skillRepository.getAllElements()){
                    if(probableSkill.getName().equals(skillName)){
                        skill = probableSkill;
                        break;
                    }
                }

                return skill;
    }

    /**
     * Serializar de <code>Skill</code> a JSON
     * @see com.google.gson.JsonSerializer#serialize(java.lang.Object, java.lang.reflect.Type, com.google.gson.JsonSerializationContext)
     */
    @Override
    public JsonElement serialize(Skill src, Type typeOfSrc, JsonSerializationContext context) {
        return context.serialize(src.getName());
    }

}
