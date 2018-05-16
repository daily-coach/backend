package br.com.rodrigocardoso.utils;

import com.google.gson.*;
import spark.ResponseTransformer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

/**
 * Created by rodri on 26/04/2018.
 */
public class JsonUtils {
    public static String toJson(Object obj) {
        Gson gson = new Gson();
        return gson.toJson(obj);
    }

    public static <T> T fromJson(String jsonString, Class<T> clazz) {
        Gson gson = LocalDateTimeGson();
        return clazz.cast(gson.fromJson(jsonString, clazz));
    }

    public static ResponseTransformer json() {
        return JsonUtils::toJson;
    }

    private static Gson LocalDateTimeGson() {
        GsonBuilder gson = new GsonBuilder();
        gson
                .registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>) (json, typeOfT, context) ->
                    ZonedDateTime.parse(json.getAsJsonPrimitive().getAsString()).toLocalDateTime())
                .registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>) (json, typeofT, context) ->
                    ZonedDateTime.parse(json.getAsJsonPrimitive().getAsString()).toLocalDate());
        return gson.create();
    }
}
