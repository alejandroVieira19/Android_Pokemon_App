package com.pokemon_app.database;

import androidx.room.TypeConverter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;


class PokemonTypeConverter {

    private static final Gson gson = new Gson();
    @TypeConverter
    public static String fromPokemonModel(List<String> value){
       return  gson.toJson(value);
    }

    // Represents a generic type T.
    // Java doesn't yet provide a way to represent generic types, so this class does.
    // Forces clients to create a subclass of this class which enables retrieval the
    // type information even at runtime.
    @TypeConverter
    public static List<String> toPokemonModel(String value) {
        return gson.fromJson(value, new TypeToken<List<String>>(){}.getType());
    }
}