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

    @ColumnInfo(name = "pokeAttack")
    private int pokemonAttack;
    @ColumnInfo(name = "pokeDefense")
    private int pokemonDefense;
    @ColumnInfo(name = "pokeHP")
    private int pokemonHP;
    @ColumnInfo(name = "pokeSpeed")
    private int pokemonSpeed;

    // Construtor com novos atributos
    public PokemonEntity(int pokemonId, String pokemonName, double pokemonHeight, double pokemonWeight,
                         List<String> pokemonType, String pokemonDetailImageUrlBackground,
                         String pokemonImageUrlCard, List<String> pokemonMovesList, boolean isPokemonFavorite,
                         int pokemonAttack, int pokemonDefense, int pokemonHP, int pokemonSpeed) {
        this.pokemonId = pokemonId;
        this.pokemonName = pokemonName;
        this.pokemonHeight = pokemonHeight;
        this.pokemonWeight = pokemonWeight;
        this.pokemonType = pokemonType;
        this.pokemonDetailImageUrlBackground = pokemonDetailImageUrlBackground;
        this.pokemonImageUrlCard = pokemonImageUrlCard;
        this.pokemonMovesList = pokemonMovesList;
        this.isPokemonFavorite = isPokemonFavorite;
        this.pokemonAttack = pokemonAttack;
        this.pokemonDefense = pokemonDefense;
        this.pokemonHP = pokemonHP;
        this.pokemonSpeed = pokemonSpeed;
    }

    // Getters e Setters para os novos atributos
    public int getPokemonAttack() {
        return pokemonAttack;
    }

    public void setPokemonAttack(int pokemonAttack) {
        this.pokemonAttack = pokemonAttack;
    }

    public int getPokemonDefense() {
        return pokemonDefense;
    }

    public void setPokemonDefense(int pokemonDefense) {
        this.pokemonDefense = pokemonDefense;
    }

    public int getPokemonHP() {
        return pokemonHP;
    }

    public void setPokemonHP(int pokemonHP) {
        this.pokemonHP = pokemonHP;
    }

    public int getPokemonSpeed() {
        return pokemonSpeed;
    }

    public void setPokemonSpeed(int pokemonSpeed) {
        this.pokemonSpeed = pokemonSpeed;
    }

    public boolean isPokemonFavorite() {
        return isPokemonFavorite;
    }

    public void setPokemonFavorite(boolean pokemonFavorite) {
        isPokemonFavorite = pokemonFavorite;
    }

    public String getPokemonDetailImageUrlBackground() {
        return pokemonDetailImageUrlBackground;
    }

    public void setPokemonDetailImageUrlBackground(String pokemonDetailImageUrlBackground) {
        this.pokemonDetailImageUrlBackground = pokemonDetailImageUrlBackground;
    }

    public double getPokemonHeight() {
        return pokemonHeight;
    }

    public void setPokemonHeight(double pokemonHeight) {
        this.pokemonHeight = pokemonHeight;
    }

    public int getPokemonId() {
        return pokemonId;
    }

    public void setPokemonId(int pokemonId) {
        this.pokemonId = pokemonId;
    }

    public String getPokemonImageUrlCard() {
        return pokemonImageUrlCard;
    }

    public void setPokemonImageUrlCard(String pokemonImageUrlCard) {
        this.pokemonImageUrlCard = pokemonImageUrlCard;
    }

    public List<String> getPokemonMovesList() {
        return pokemonMovesList;
    }

    public void setPokemonMovesList(List<String> pokemonMovesList) {
        this.pokemonMovesList = pokemonMovesList;
    }

    public String getPokemonName() {
        return pokemonName;
    }

    public void setPokemonName(String pokemonName) {
        this.pokemonName = pokemonName;
    }

    public List<String> getPokemonType() {
        return pokemonType;
    }

    public void setPokemonType(List<String> pokemonType) {
        this.pokemonType = pokemonType;
    }

    public double getPokemonWeight() {
        return pokemonWeight;
    }

    public void setPokemonWeight(double pokemonWeight) {
        this.pokemonWeight = pokemonWeight;
    }
}
