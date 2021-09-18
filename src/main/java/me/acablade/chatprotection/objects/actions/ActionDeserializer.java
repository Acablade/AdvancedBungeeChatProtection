package me.acablade.chatprotection.objects.actions;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.regex.Pattern;

public class ActionDeserializer<T extends Action> implements JsonDeserializer<T> {

    private static final String CLASSNAME = "actionType";

    @Override
    public T deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        final JsonObject jsonObject = json.getAsJsonObject();
        final JsonPrimitive prim = (JsonPrimitive) jsonObject.get(CLASSNAME);
        final String className = prim.getAsString();
        final Class<T> clazz = getClassInstance("me.acablade.chatprotection.objects.actions.impl."+className);
        JsonElement regex = jsonObject.get("regex");
        if(regex!=null){
            String regexString = regex.getAsString();
            jsonObject.remove("regex");
            jsonObject.add("regex",new Gson().toJsonTree(Pattern.compile(regexString,Pattern.CASE_INSENSITIVE)));
        }
        return context.deserialize(jsonObject, clazz);
    }

    @SuppressWarnings("unchecked")
    public Class<T> getClassInstance(String className) {
        try {
            return (Class<T>) Class.forName(className);
        } catch (ClassNotFoundException cnfe) {
            throw new JsonParseException(cnfe.getMessage());
        }
    }
}