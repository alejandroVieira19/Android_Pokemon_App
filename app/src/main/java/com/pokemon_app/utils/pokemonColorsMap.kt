
@file:JvmName("PokemonUtils")
package com.pokemon_app.utils
import android.graphics.Color
import android.util.Log
import com.pokemon_app.R

val colours = mapOf(
    "normal" to "#A8A77A",
    "fire" to "#EE8130",
    "water" to "#6390F0",
    "electric" to "#F7D02C",
    "grass" to "#7AC74C",
    "ice" to "#96D9D6",
    "fighting" to "#C22E28",
    "poison" to "#A33EA1",
    "ground" to "#E2BF65",
    "flying" to "#A98FF3",
    "psychic" to "#F95587",
    "bug" to "#A6B91A",
    "rock" to "#B6A136",
    "ghost" to "#735797",
    "dragon" to "#6F35FC",
    "dark" to "#705746",
    "steel" to "#B7B7CE",
    "fairy" to "#D685AD"
)

val typeIcons = mapOf(
    "normal" to R.drawable.battrio_normal_type,
    "fire" to R.drawable.battrio_fire_type,
    "water" to R.drawable.battrio_water_type,
    "electric" to R.drawable.battrio_electric_type,
    "grass" to R.drawable.battrio_grass_type,
    "ice" to R.drawable.battrio_ice_type,
    "fighting" to R.drawable.battrio_fighting_type,
    "poison" to R.drawable.battrio_poison_type,
    "ground" to R.drawable.battrio_ground_type,
    "flying" to R.drawable.battrio_flying_type,
    "psychic" to R.drawable.battrio_psychic_type,
    "bug" to R.drawable.battrio_bug_type,
    "rock" to R.drawable.battrio_rock_type,
    "ghost" to R.drawable.battrio_ghost_type,
    "dragon" to R.drawable.battrio_dragon_type,
    "dark" to R.drawable.battrio_dark_type,
    "steel" to R.drawable.battrio_steel_type,
    "fairy" to R.drawable.fairy_icon_sleep
)


fun getColorForPokemonByType(type: String) : String {
    return colours[type.lowercase()] ?: "#777"
}

fun getPokemonDetailTypeImage(types:List<String>) : List<Int> {
    return types.mapNotNull { type -> typeIcons[type.lowercase()] }
}

 fun getTextColorByPokemonTypeColor(backgroundColor: String?): Int {
     return when(backgroundColor) {
         colours["ice"], colours["electric"] -> R.color.black_1
         else -> R.color.white
     }
 }