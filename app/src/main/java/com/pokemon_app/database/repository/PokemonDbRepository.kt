package com.pokemon_app.database.repository

import androidx.lifecycle.LiveData
import com.pokemon_app.database.PokemonEntity
import com.pokemon_app.database.room.PokemonDB
import com.pokemon_app.domain.repository.IPokemonDbRepository
import javax.inject.Inject

/**
 * Repositório para interação com o banco de dados de Pokémons.
 * Este repositório implementa a interface [IPokemonDbRepository] para fornecer métodos
 * que permitem a inserção, exclusão e obtenção de informações sobre Pokémons no banco de dados.
 *
 * @param pokemonDb Instância do banco de dados Pokémon (PokemonDB).
 */
class PokemonDbRepository @Inject constructor(private val pokemonDb: PokemonDB) : IPokemonDbRepository {

    /**
     * Insere um Pokémon no banco de dados, caso ele ainda não exista.
     *
     * @param pokemon A instância de [PokemonEntity] que representa o Pokémon a ser inserido.
     * @return O ID do Pokémon inserido no banco de dados.
     * @throws IllegalStateException Caso o Pokémon já exista no banco de dados.
     */
    override suspend fun insertPokemon(pokemon: PokemonEntity): Long {
        return when (checkIfPokemonExists(pokemon.pokemonId)) {
            0 -> insert(pokemon)
            else -> throw IllegalStateException("O Pokémon ${pokemon.pokemonName} já está no banco de dados.")
        }
    }

    /**
     * Realiza a inserção do Pokémon no banco de dados.
     * Marca o Pokémon como favorito antes de inserir.
     *
     * @param pokemon A instância de [PokemonEntity] a ser inserida.
     * @return O ID do Pokémon inserido.
     */
    suspend fun insert(pokemon: PokemonEntity): Long {
        pokemon.isPokemonFavorite = true
        return pokemonDb.pokemonDao().insertPokemon(pokemon)
    }

    /**
     * Obtém um Pokémon do banco de dados com base no seu ID.
     *
     * @param pokeId O ID do Pokémon a ser obtido.
     * @return A instância de [PokemonEntity] representando o Pokémon encontrado.
     */
    override suspend fun getPokemon(pokeId: Int): PokemonEntity {
        return pokemonDb.pokemonDao().getPokemon(pokeId)
    }

    /**
     * Deleta um Pokémon do banco de dados.
     *
     * @param pokemon A instância de [PokemonEntity] a ser deletada.
     * @return O número de registros excluídos.
     */
    override suspend fun deletePokemon(pokemon: PokemonEntity): Int {
        return pokemonDb.pokemonDao().deletePokemon(pokemon)
    }

    /**
     * Obtém todos os Pokémons armazenados no banco de dados.
     *
     * @return Uma lista de [PokemonEntity] com todos os Pokémons presentes no banco de dados.
     */
    override suspend fun getAllPokemon(): List<PokemonEntity> {
        return pokemonDb.pokemonDao().getAllPokemon()
    }

    /**
     * Verifica se um Pokémon já existe no banco de dados com base no seu ID.
     *
     * @param pokeId O ID do Pokémon a ser verificado.
     * @return O número de registros encontrados (0 se não existir, >0 caso contrário).
     */
    override suspend fun checkIfPokemonExists(pokeId: Int): Int {
        return pokemonDb.pokemonDao().checkIfPokemonExists(pokeId)
    }
}
