package com.pokemon_app.database;

import androidx.room.TypeConverter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por converter tipos personalizados para o Room Database e vice-versa.
 * Especificamente, esta classe converte uma lista de strings (List<String>) em uma string JSON e
 * uma string JSON de volta em uma lista de strings.
 *
 * O Room não lida diretamente com tipos complexos como listas, por isso é necessário usar
 * TypeConverters para realizar a conversão. Esta classe utiliza a biblioteca Gson para a
 * serialização e desserialização dos dados.
 */
public class PokemonTypeConverter {

    // Instância do Gson para realizar a conversão de objetos para JSON e vice-versa.
    private static final Gson gson = new Gson();

    /**
     * Converte uma lista de strings para uma representação JSON (String).
     * Este método é utilizado pelo Room para salvar listas de strings no banco de dados.
     *
     * @param value A lista de strings que será convertida em uma string JSON.
     * @return Uma string JSON representando a lista de strings fornecida.
     */
    @TypeConverter
    public static String fromPokemonModel(List<String> value) {
        return gson.toJson(value);
    }

    /**
     * Converte uma string JSON para uma lista de strings.
     * Este método é utilizado pelo Room para ler dados JSON e transformá-los em uma lista de strings.
     *
     * @param value A string JSON que representa uma lista de strings.
     * @return Uma lista de strings obtida a partir da string JSON fornecida.
     */
    @TypeConverter
    public static List<String> toPokemonModel(String value) {
        return gson.fromJson(value, new TypeToken<List<String>>(){}.getType());
    }
}
