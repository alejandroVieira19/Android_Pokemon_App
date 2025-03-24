package com.pokemon_app.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.List;

/**
 * Classe que representa uma entidade Pokémon no banco de dados.
 * Esta classe é usada para armazenar informações sobre os Pokémons, como nome, tipo, altura, peso,
 * ataques, defesa, HP, velocidade, e imagens, além de marcar um Pokémon como favorito.
 *
 * A classe é anotada com @Entity para ser reconhecida como uma tabela do Room Database.
 * Cada instância dessa classe corresponde a uma linha na tabela 'poke_table'.
 */
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

    /**
     * Construtor para criar um objeto [PokemonEntity] com os atributos necessários.
     *
     * @param pokemonId ID único do Pokémon.
     * @param pokemonName Nome do Pokémon.
     * @param pokemonHeight Altura do Pokémon.
     * @param pokemonWeight Peso do Pokémon.
     * @param pokemonType Lista de tipos do Pokémon (ex: "Fogo", "Água").
     * @param pokemonDetailImageUrlBackground URL para a imagem de fundo detalhada do Pokémon.
     * @param pokemonImageUrlCard URL para a imagem do cartão do Pokémon.
     * @param pokemonMovesList Lista de movimentos do Pokémon.
     * @param isPokemonFavorite Indica se o Pokémon é favorito (verdadeiro ou falso).
     * @param pokemonAttack Valor do ataque do Pokémon.
     * @param pokemonDefense Valor da defesa do Pokémon.
     * @param pokemonHP Valor do HP (pontos de vida) do Pokémon.
     * @param pokemonSpeed Valor da velocidade do Pokémon.
     */
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

    // Getters e Setters

    /**
     * Retorna o valor do atributo de ataque do Pokémon.
     *
     * @return O valor do ataque do Pokémon.
     */
    public int getPokemonAttack() {
        return pokemonAttack;
    }

    /**
     * Define o valor do ataque do Pokémon.
     *
     * @param pokemonAttack O valor do ataque a ser definido.
     */
    public void setPokemonAttack(int pokemonAttack) {
        this.pokemonAttack = pokemonAttack;
    }

    /**
     * Retorna o valor da defesa do Pokémon.
     *
     * @return O valor da defesa do Pokémon.
     */
    public int getPokemonDefense() {
        return pokemonDefense;
    }

    /**
     * Define o valor da defesa do Pokémon.
     *
     * @param pokemonDefense O valor da defesa a ser definido.
     */
    public void setPokemonDefense(int pokemonDefense) {
        this.pokemonDefense = pokemonDefense;
    }

    /**
     * Retorna o valor do HP (pontos de vida) do Pokémon.
     *
     * @return O valor do HP do Pokémon.
     */
    public int getPokemonHP() {
        return pokemonHP;
    }

    /**
     * Define o valor do HP do Pokémon.
     *
     * @param pokemonHP O valor do HP a ser definido.
     */
    public void setPokemonHP(int pokemonHP) {
        this.pokemonHP = pokemonHP;
    }

    /**
     * Retorna o valor da velocidade do Pokémon.
     *
     * @return O valor da velocidade do Pokémon.
     */
    public int getPokemonSpeed() {
        return pokemonSpeed;
    }

    /**
     * Define o valor da velocidade do Pokémon.
     *
     * @param pokemonSpeed O valor da velocidade a ser definido.
     */
    public void setPokemonSpeed(int pokemonSpeed) {
        this.pokemonSpeed = pokemonSpeed;
    }

    /**
     * Retorna se o Pokémon é favorito.
     *
     * @return Verdadeiro se o Pokémon é favorito, falso caso contrário.
     */
    public boolean isPokemonFavorite() {
        return isPokemonFavorite;
    }

    /**
     * Define se o Pokémon é favorito.
     *
     * @param pokemonFavorite Indica se o Pokémon é favorito (verdadeiro ou falso).
     */
    public void setPokemonFavorite(boolean pokemonFavorite) {
        isPokemonFavorite = pokemonFavorite;
    }

    /**
     * Retorna a URL da imagem de fundo detalhada do Pokémon.
     *
     * @return A URL da imagem de fundo detalhada do Pokémon.
     */
    public String getPokemonDetailImageUrlBackground() {
        return pokemonDetailImageUrlBackground;
    }

    /**
     * Define a URL da imagem de fundo detalhada do Pokémon.
     *
     * @param pokemonDetailImageUrlBackground A URL da imagem de fundo a ser definida.
     */
    public void setPokemonDetailImageUrlBackground(String pokemonDetailImageUrlBackground) {
        this.pokemonDetailImageUrlBackground = pokemonDetailImageUrlBackground;
    }

    /**
     * Retorna a altura do Pokémon.
     *
     * @return A altura do Pokémon.
     */
    public double getPokemonHeight() {
        return pokemonHeight;
    }

    /**
     * Define a altura do Pokémon.
     *
     * @param pokemonHeight A altura a ser definida.
     */
    public void setPokemonHeight(double pokemonHeight) {
        this.pokemonHeight = pokemonHeight;
    }

    /**
     * Retorna o ID único do Pokémon.
     *
     * @return O ID do Pokémon.
     */
    public int getPokemonId() {
        return pokemonId;
    }

    /**
     * Define o ID único do Pokémon.
     *
     * @param pokemonId O ID do Pokémon a ser definido.
     */
    public void setPokemonId(int pokemonId) {
        this.pokemonId = pokemonId;
    }

    /**
     * Retorna a URL da imagem do cartão do Pokémon.
     *
     * @return A URL da imagem do cartão do Pokémon.
     */
    public String getPokemonImageUrlCard() {
        return pokemonImageUrlCard;
    }

    /**
     * Define a URL da imagem do cartão do Pokémon.
     *
     * @param pokemonImageUrlCard A URL da imagem do cartão a ser definida.
     */
    public void setPokemonImageUrlCard(String pokemonImageUrlCard) {
        this.pokemonImageUrlCard = pokemonImageUrlCard;
    }

    /**
     * Retorna a lista de movimentos do Pokémon.
     *
     * @return A lista de movimentos do Pokémon.
     */
    public List<String> getPokemonMovesList() {
        return pokemonMovesList;
    }

    /**
     * Define a lista de movimentos do Pokémon.
     *
     * @param pokemonMovesList A lista de movimentos a ser definida.
     */
    public void setPokemonMovesList(List<String> pokemonMovesList) {
        this.pokemonMovesList = pokemonMovesList;
    }

    /**
     * Retorna o nome do Pokémon.
     *
     * @return O nome do Pokémon.
     */
    public String getPokemonName() {
        return pokemonName;
    }

    /**
     * Define o nome do Pokémon.
     *
     * @param pokemonName O nome do Pokémon a ser definido.
     */
    public void setPokemonName(String pokemonName) {
        this.pokemonName = pokemonName;
    }

    /**
     * Retorna a lista de tipos do Pokémon.
     *
     * @return A lista de tipos do Pokémon.
     */
    public List<String> getPokemonType() {
        return pokemonType;
    }

    /**
     * Define a lista de tipos do Pokémon.
     *
     * @param pokemonType A lista de tipos a ser definida.
     */
    public void setPokemonType(List<String> pokemonType) {
        this.pokemonType = pokemonType;
    }

    /**
     * Retorna o peso do Pokémon.
     *
     * @return O peso do Pokémon.
     */
    public double getPokemonWeight() {
        return pokemonWeight;
    }

    /**
     * Define o peso do Pokémon.
     *
     * @param pokemonWeight O peso a ser definido.
     */
    public void setPokemonWeight(double pokemonWeight) {
        this.pokemonWeight = pokemonWeight;
    }
}
