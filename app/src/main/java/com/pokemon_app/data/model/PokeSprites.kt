package com.pokemon_app.data.model

data class PokeSprites(
    val back_default: String?,
    val back_female: String?,
    val back_shiny: String?,
    val back_shiny_female: String?,
    val front_default: String?,
    val front_female: String?,
    val front_shiny: String?,
    val front_shiny_female: String?,
    val other: OtherSprites,
    val versions: Versions
)

data class OtherSprites(
    val dream_world: DreamWorldSprites,
    val home: HomeSprites,
    val official_artwork: OfficialArtworkSprites,
    val showdown: ShowdownSprites
)

data class DreamWorldSprites(
    val front_default: String?,
    val front_female: String?
)

data class HomeSprites(
    val front_default: String?,
    val front_female: String?,
    val front_shiny: String?,
    val front_shiny_female: String?
)

data class OfficialArtworkSprites(
    val front_default: String?,
    val front_shiny: String?
)

data class ShowdownSprites(
    val back_default: String?,
    val back_female: String?,
    val back_shiny: String?,
    val back_shiny_female: String?,
    val front_default: String?,
    val front_female: String?,
    val front_shiny: String?,
    val front_shiny_female: String?
)

data class Versions(
    val generation_i: GenerationISprites,
    val generation_ii: GenerationIISprites,
    val generation_iii: GenerationIIISprites,
    val generation_iv: GenerationIVSprites,
    val generation_v: GenerationVSprites,
    val generation_vi: GenerationVISprites,
    val generation_vii: GenerationVIISprites,
    val generation_viii: GenerationVIIISprites
)

data class GenerationISprites(
    val red_blue: RedBlueSprites,
    val yellow: YellowSprites
)

data class RedBlueSprites(
    val back_default: String?,
    val back_gray: String?,
    val back_transparent: String?,
    val front_default: String?,
    val front_gray: String?,
    val front_transparent: String?
)

data class YellowSprites(
    val back_default: String?,
    val back_gray: String?,
    val back_transparent: String?,
    val front_default: String?,
    val front_gray: String?,
    val front_transparent: String?
)

data class GenerationIISprites(
    val crystal: CrystalSprites,
    val gold: GoldSprites,
    val silver: SilverSprites
)

data class CrystalSprites(
    val back_default: String?,
    val back_shiny: String?,
    val back_shiny_transparent: String?,
    val back_transparent: String?,
    val front_default: String?,
    val front_shiny: String?,
    val front_shiny_transparent: String?,
    val front_transparent: String?
)

data class GoldSprites(
    val back_default: String?,
    val back_shiny: String?,
    val front_default: String?,
    val front_shiny: String?,
    val front_transparent: String?
)

data class SilverSprites(
    val back_default: String?,
    val back_shiny: String?,
    val front_default: String?,
    val front_shiny: String?,
    val front_transparent: String?
)

data class GenerationIIISprites(
    val emerald: EmeraldSprites,
    val firered_leafgreen: FireredLeafgreenSprites,
    val ruby_sapphire: RubySapphireSprites
)

data class EmeraldSprites(
    val front_default: String?,
    val front_shiny: String?
)

data class FireredLeafgreenSprites(
    val back_default: String?,
    val back_shiny: String?,
    val front_default: String?,
    val front_shiny: String?
)

data class RubySapphireSprites(
    val back_default: String?,
    val back_shiny: String?,
    val front_default: String?,
    val front_shiny: String?
)

data class GenerationIVSprites(
    val diamond_pearl: DiamondPearlSprites,
    val heartgold_soulsilver: HeartgoldSoulsilverSprites,
    val platinum: PlatinumSprites
)

data class DiamondPearlSprites(
    val back_default: String?,
    val back_female: String?,
    val back_shiny: String?,
    val back_shiny_female: String?,
    val front_default: String?,
    val front_female: String?,
    val front_shiny: String?,
    val front_shiny_female: String?
)

data class HeartgoldSoulsilverSprites(
    val back_default: String?,
    val back_female: String?,
    val back_shiny: String?,
    val back_shiny_female: String?,
    val front_default: String?,
    val front_female: String?,
    val front_shiny: String?,
    val front_shiny_female: String?
)

data class PlatinumSprites(
    val back_default: String?,
    val back_female: String?,
    val back_shiny: String?,
    val back_shiny_female: String?,
    val front_default: String?,
    val front_female: String?,
    val front_shiny: String?,
    val front_shiny_female: String?
)

data class GenerationVSprites(
    val black_white: BlackWhiteSprites
)

data class BlackWhiteSprites(
    val animated: AnimatedSprites,
    val back_default: String?,
    val back_female: String?,
    val back_shiny: String?,
    val back_shiny_female: String?,
    val front_default: String?,
    val front_female: String?,
    val front_shiny: String?,
    val front_shiny_female: String?
)

data class AnimatedSprites(
    val back_default: String?,
    val back_female: String?,
    val back_shiny: String?,
    val back_shiny_female: String?,
    val front_default: String?,
    val front_female: String?,
    val front_shiny: String?,
    val front_shiny_female: String?
)

data class GenerationVISprites(
    val omegaruby_alphasapphire: OmegarubyAlphasapphireSprites,
    val x_y: XYSprites
)

data class OmegarubyAlphasapphireSprites(
    val front_default: String?,
    val front_female: String?,
    val front_shiny: String?,
    val front_shiny_female: String?
)

data class XYSprites(
    val front_default: String?,
    val front_female: String?,
    val front_shiny: String?,
    val front_shiny_female: String?
)

data class GenerationVIISprites(
    val icons: IconsSprites,
    val ultra_sun_ultra_moon: UltraSunUltraMoonSprites
)

data class IconsSprites(
    val front_default: String?,
    val front_female: String?
)

data class UltraSunUltraMoonSprites(
    val front_default: String?,
    val front_female: String?,
    val front_shiny: String?,
    val front_shiny_female: String?
)

data class GenerationVIIISprites(
    val icons: IconsSprites
)

