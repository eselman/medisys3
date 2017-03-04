package com.eselman.medisys.helpers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.lang.reflect.Type;

/**
 * Created by eselman on 11/02/2017.
 */
public class LocalDateTimeConverter implements JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {
    private final static String PATTERN = "yyyy-MM-dd";
    final DateTimeFormatter fmt = DateTimeFormat.forPattern(PATTERN);
    @Override
    public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject dateJsonObject = json.getAsJsonObject();
        String dateStr = dateJsonObject.get("year") + "-" + dateJsonObject.get("monthValue") + "-" + dateJsonObject.get("dayOfMonth");
        return fmt.parseLocalDateTime(dateStr);
    }

    @Override
    public JsonElement serialize(LocalDateTime src, Type typeOfSrc, JsonSerializationContext context) {
        String retVal = fmt.print(src);
        return new JsonPrimitive(retVal);
    }
}
