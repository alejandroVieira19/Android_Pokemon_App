package com.pokemon_app.database.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.pokemon_app.database.PokemonEntity

/**
 * Interface DAO (Data Access Object) para realizar operações de banco de dados na tabela de Pokémons.
 * Esta interface define os métodos para inserir, obter, excluir e verificar a existência de Pokémons.
 */
@Dao
interface PokemonDbDao {

    /**
     * Insere um Pokémon na tabela do banco de dados.
     *
     * @param pokemon A instância de [PokemonEntity] que representa o Pokémon a ser inserido.
     * @return O ID do Pokémon inserido na tabela.
     */
    @Insert
    suspend fun insertPokemon(pokemon: PokemonEntity): Long

    /**
     * Obtém um Pokémon do banco de dados baseado no seu ID.
     *
     * @param pokeKey O ID do Pokémon a ser obtido.
     * @return A instância de [PokemonEntity] que representa o Pokémon com o ID fornecido.
     */
    @Query("SELECT * FROM poke_table WHERE pokemonId = :pokeKey")
    suspend fun getPokemon(pokeKey: Int): PokemonEntity

    /**
     * Exclui um Pokémon da tabela do banco de dados.
     *
     * @param pokemon A instância de [PokemonEntity] a ser excluída da tabela.
     * @return O número de registros excluídos (geralmente 1 ou 0, indicando se a exclusão foi bem-sucedida).
     */
    @Delete
    suspend fun deletePokemon(pokemon: PokemonEntity): Int

    /**
     * Obtém todos os Pokémons armazenados na tabela do banco de dados.
     *
     * @return Uma lista de [PokemonEntity] contendo todos os Pokémons presentes na tabela.
     */
    @Query("SELECT * FROM poke_table")
    fun getAllPokemon(): List<PokemonEntity>

    /**
     * Verifica se um Pokémon existe na tabela com base no seu ID.
     *
     * @param pokeId O ID do Pokémon a ser verificado.
     * @return O número de registros encontrados (0 se não existir, >0 caso contrário).
     */
    @Query("SELECT COUNT(*) FROM poke_table WHERE pokemonId = :pokeId")
    fun checkIfPokemonExists(pokeId: Int): Int
}
