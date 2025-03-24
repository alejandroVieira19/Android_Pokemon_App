package com.pokemon_app.domain.repository

import androidx.lifecycle.LiveData
import com.pokemon_app.database.PokemonEntity
import com.pokemon_app.domain.model.Pokemon

/**
 * Interface que define as operações para acessar e manipular os dados de Pokémon no banco de dados.
 *
 * Esta interface é responsável por fornecer os métodos necessários para realizar operações de
 * inserção, exclusão, recuperação e verificação de Pokémon no banco de dados. Ela define as funções
 * que devem ser implementadas para interagir com os dados persistidos de Pokémon, garantindo a
 * separação da lógica de acesso ao banco de dados.
 */
interface IPokemonDbRepository {

    /**
     * Insere um Pokémon no banco de dados.
     *
     * @param pokemon O objeto [PokemonEntity] que será inserido no banco de dados.
     * @return O ID do Pokémon inserido.
     * @throws IllegalStateException Se o Pokémon já existir no banco de dados.
     */
    suspend fun insertPokemon(pokemon: PokemonEntity): Long

    /**
     * Recupera um Pokémon do banco de dados com base no ID.
     *
     * @param pokeId O ID do Pokémon a ser recuperado.
     * @return O objeto [PokemonEntity] correspondente ao Pokémon recuperado.
     */
    suspend fun getPokemon(pokeId: Int): PokemonEntity

    /**
     * Deleta um Pokémon do banco de dados.
     *
     * @param pokemon O objeto [PokemonEntity] que será deletado.
     * @return O número de linhas afetadas pela operação de exclusão.
     */
    suspend fun deletePokemon(pokemon: PokemonEntity): Int

    /**
     * Recupera todos os Pokémon armazenados no banco de dados.
     *
     * @return Uma lista de objetos [PokemonEntity] representando todos os Pokémon no banco de dados.
     */
    suspend fun getAllPokemon(): List<PokemonEntity>

    /**
     * Verifica se um Pokémon já existe no banco de dados com base no ID.
     *
     * @param pokeId O ID do Pokémon a ser verificado.
     * @return O número de Pokémon encontrados com o ID fornecido. Se o valor for maior que 0, o Pokémon existe.
     */
    suspend fun checkIfPokemonExists(pokeId: Int): Int
}
