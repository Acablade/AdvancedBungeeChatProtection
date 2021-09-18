package me.acablade.chatprotection.objects;

import com.google.gson.*;
import me.acablade.chatprotection.objects.actions.Action;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class RuleDeserializer implements JsonDeserializer<Rule> {

    @Override
    public Rule deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = json.getAsJsonObject();

        String regex = object.get("regex").getAsString();
        String ignore = object.get("ignore").getAsString();
        List<Action> actionList = new ArrayList<>();
        object.get("actionList").getAsJsonArray().forEach(jsonElement ->{
            actionList.add(context.deserialize(jsonElement,Action.class));
        });
        String perm = "";
        if(object.get("permission")!=null){
            perm = object.get("permission").getAsString();
        }

        return new Rule(regex,actionList,perm,ignore);
    }
}
