package com.pokemon_app.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.List;

@Entity(tableName = "poke_table")
public class PokemonEntity {
    @PrimaryKey
    private int pokemonId;
    @ColumnInfo(name = "pokeName")
    private String pokemonName;
    @ColumnInfo(name = "pokeHeight")
    private double pokemonHeight;
    @ColumnInfo(name = "pokeWeight")
    private double pokemonWeight;
    @ColumnInfo(name = "pokeType")
    @TypeConverters(PokemonTypeConverter.class)
    private List<String> pokemonType;
    @ColumnInfo(name = "pokeUrlBackgroundImage")
    private String pokemonDetailImageUrlBackground;
    @ColumnInfo(name = "pokeUrlCardImage")
    private String pokemonImageUrlCard;
    @ColumnInfo(name = "pokeMoves")
    @TypeConverters(PokemonTypeConverter.class)
    private List<String> pokemonMovesList;
    @ColumnInfo(name = "pokeFavorite")
    private boolean isPokemonFavorite;


    public PokemonEntity(int pokemonId, String pokemonName, double pokemonHeight, double pokemonWeight,
                         List<String> pokemonType, String pokemonDetailImageUrlBackground,
                         String pokemonImageUrlCard, List<String> pokemonMovesList, boolean isPokemonFavorite) {
        this.pokemonId = pokemonId;
        this.pokemonName = pokemonName;
        this.pokemonHeight = pokemonHeight;
        this.pokemonWeight = pokemonWeight;
        this.pokemonType = pokemonType;
        this.pokemonDetailImageUrlBackground = pokemonDetailImageUrlBackground;
        this.pokemonImageUrlCard = pokemonImageUrlCard;
        this.pokemonMovesList = pokemonMovesList;
        this.isPokemonFavorite = isPokemonFavorite;
    }
}
