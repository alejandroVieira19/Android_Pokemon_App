package com.pokemon_app.domain.model

data class Generation(val generation:String, val generationNumber: Int)

object GenerationRepository {
    val generations = listOf(
        Generation("1º Generation", 1),
        Generation("2º Generation", 2),
        Generation("3+ Generation", 3),
        Generation("4º Generation", 4),
        Generation("5º Generation", 5),
        Generation("6º Generation", 6),
        Generation("7º Generation", 7),
        Generation("8º Generation", 8)
    )
}

