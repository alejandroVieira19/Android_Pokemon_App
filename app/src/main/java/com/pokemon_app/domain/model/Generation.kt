package com.pokemon_app.domain.model

/**
 * Enumeração que representa as gerações dos Pokémon.
 *
 * Esta enumeração define as diferentes gerações de Pokémon, com um título descritivo e um número
 * associado a cada geração. Ela é usada para classificar os Pokémon de acordo com sua geração de lançamento.
 *
 * @property title O título descritivo da geração.
 * @property number O número da geração, representando a ordem cronológica das gerações.
 */
enum class Generation(val title: String, val number: Int) {
    FIRST("1º Generation", 1),  // Primeira geração
    SECOND("2º Generation", 2), // Segunda geração
    THIRD("3º Generation", 3),  // Terceira geração
    FOURTH("4º Generation", 4), // Quarta geração
    FIFTH("5º Generation", 5),  // Quinta geração
    SIXTH("6º Generation", 6),  // Sexta geração
    SEVENTH("7º Generation", 7),// Sétima geração
    EIGHTH("8º Generation", 8); // Oitava geração
}
