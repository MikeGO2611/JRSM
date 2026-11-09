package org.archaios.commons.json;

import java.lang.reflect.Type;

import org.archaios.commons.repositories.Skills;
import org.archaios.people.Archeologist;
import org.archaios.skills.Skill;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class SkillAdapter implements JsonSerializer<Skill>, JsonDeserializer<Skill> {

    @Override
    public Skill deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
                String skillName = json.getAsString();
                Skills skillCollection = new Skills();
                Skill skill = null;
                for(Skill probableSkill : skillCollection.getAllElements()){
                    if(probableSkill.getName().equals(skillName)){
                        skill = probableSkill;
                        break;
                    }
                }

                return skill;
    }

    @Override
    public JsonElement serialize(Skill src, Type typeOfSrc, JsonSerializationContext context) {
        return context.serialize(src.getName());
    }

}
