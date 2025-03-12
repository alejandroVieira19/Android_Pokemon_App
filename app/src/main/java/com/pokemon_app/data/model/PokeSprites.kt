import com.google.gson.annotations.SerializedName

data class PokeSprites(
    @SerializedName("back_default") val backDefault: String?,
    @SerializedName("back_female") val backFemale: String?,
    @SerializedName("back_shiny") val backShiny: String?,
    @SerializedName("back_shiny_female") val backShinyFemale: String?,
    @SerializedName("front_default") val frontDefault: String?,
    @SerializedName("front_female") val frontFemale: String?,
    @SerializedName("front_shiny") val frontShiny: String?,
    @SerializedName("front_shiny_female") val frontShinyFemale: String?,
    @SerializedName("other") val other: OtherSprites?,
    @SerializedName("versions") val versions: Versions?
)

data class OtherSprites(
    @SerializedName("dream_world") val dreamWorld: DreamWorld?,
    @SerializedName("home") val home: Home?,
    @SerializedName("official-artwork") val officialArtwork: OfficialArtwork?,
    @SerializedName("showdown") val showdown: ShowdownSprites?
)

data class DreamWorld(
    @SerializedName("front_default") val frontDefault: String?,
    @SerializedName("front_female") val frontFemale: String?
)

data class Home(
    @SerializedName("front_default") val frontDefault: String?,
    @SerializedName("front_female") val frontFemale: String?,
    @SerializedName("front_shiny") val frontShiny: String?,
    @SerializedName("front_shiny_female") val frontShinyFemale: String?
)

data class OfficialArtwork(
    @SerializedName("front_default") val frontDefault: String?,
    @SerializedName("front_shiny") val frontShiny: String?
)

data class ShowdownSprites(
    @SerializedName("back_default") val backDefault: String?,
    @SerializedName("back_female") val backFemale: String?,
    @SerializedName("back_shiny") val backShiny: String?,
    @SerializedName("back_shiny_female") val backShinyFemale: String?,
    @SerializedName("front_default") val frontDefault: String?,
    @SerializedName("front_female") val frontFemale: String?,
    @SerializedName("front_shiny") val frontShiny: String?,
    @SerializedName("front_shiny_female") val frontShinyFemale: String?
)

data class Versions(
    @SerializedName("generation-i") val generationI: GenerationI?,
    @SerializedName("generation-ii") val generationII: GenerationII?,
    @SerializedName("generation-iii") val generationIII: GenerationIII?,
    @SerializedName("generation-iv") val generationIV: GenerationIV?,
    @SerializedName("generation-v") val generationV: GenerationV?,
    @SerializedName("generation-vi") val generationVI: GenerationVI?,
    @SerializedName("generation-vii") val generationVII: GenerationVII?,
    @SerializedName("generation-viii") val generationVIII: GenerationVIII?
)

data class GenerationI(
    @SerializedName("red-blue") val redBlue: RedBlue?,
    @SerializedName("yellow") val yellow: Yellow?
)

data class RedBlue(
    @SerializedName("back_default") val backDefault: String?,
    @SerializedName("back_gray") val backGray: String?,
    @SerializedName("back_transparent") val backTransparent: String?,
    @SerializedName("front_default") val frontDefault: String?,
    @SerializedName("front_gray") val frontGray: String?,
    @SerializedName("front_transparent") val frontTransparent: String?
)

data class Yellow(
    @SerializedName("back_default") val backDefault: String?,
    @SerializedName("back_gray") val backGray: String?,
    @SerializedName("back_transparent") val backTransparent: String?,
    @SerializedName("front_default") val frontDefault: String?,
    @SerializedName("front_gray") val frontGray: String?,
    @SerializedName("front_transparent") val frontTransparent: String?
)

data class GenerationII(
    @SerializedName("crystal") val crystal: Crystal?,
    @SerializedName("gold") val gold: Gold?,
    @SerializedName("silver") val silver: Silver?
)

data class Crystal(
    @SerializedName("back_default") val backDefault: String?,
    @SerializedName("back_shiny") val backShiny: String?,
    @SerializedName("back_shiny_transparent") val backShinyTransparent: String?,
    @SerializedName("back_transparent") val backTransparent: String?,
    @SerializedName("front_default") val frontDefault: String?,
    @SerializedName("front_shiny") val frontShiny: String?,
    @SerializedName("front_shiny_transparent") val frontShinyTransparent: String?,
    @SerializedName("front_transparent") val frontTransparent: String?
)

data class Gold(
    @SerializedName("back_default") val backDefault: String?,
    @SerializedName("back_shiny") val backShiny: String?,
    @SerializedName("front_default") val frontDefault: String?,
    @SerializedName("front_shiny") val frontShiny: String?,
    @SerializedName("front_transparent") val frontTransparent: String?
)

data class Silver(
    @SerializedName("back_default") val backDefault: String?,
    @SerializedName("back_shiny") val backShiny: String?,
    @SerializedName("front_default") val frontDefault: String?,
    @SerializedName("front_shiny") val frontShiny: String?,
    @SerializedName("front_transparent") val frontTransparent: String?
)

data class GenerationIII(
    @SerializedName("emerald") val emerald: Emerald?,
    @SerializedName("firered-leafgreen") val fireredLeafgreen: FireredLeafgreen?,
    @SerializedName("ruby-sapphire") val rubySapphire: RubySapphire?
)

data class Emerald(
    @SerializedName("front_default") val frontDefault: String?,
    @SerializedName("front_shiny") val frontShiny: String?
)

data class FireredLeafgreen(
    @SerializedName("back_default") val backDefault: String?,
    @SerializedName("back_shiny") val backShiny: String?,
    @SerializedName("front_default") val frontDefault: String?,
    @SerializedName("front_shiny") val frontShiny: String?
)

data class RubySapphire(
    @SerializedName("back_default") val backDefault: String?,
    @SerializedName("back_shiny") val backShiny: String?,
    @SerializedName("front_default") val frontDefault: String?,
    @SerializedName("front_shiny") val frontShiny: String?
)

data class GenerationIV(
    @SerializedName("diamond-pearl") val diamondPearl: DiamondPearl?,
    @SerializedName("heartgold-soulsilver") val heartgoldSoulsilver: HeartgoldSoulsilver?,
    @SerializedName("platinum") val platinum: Platinum?
)

data class DiamondPearl(
    @SerializedName("back_default") val backDefault: String?,
    @SerializedName("back_female") val backFemale: String?,
    @SerializedName("back_shiny") val backShiny: String?,
    @SerializedName("back_shiny_female") val backShinyFemale: String?,
    @SerializedName("front_default") val frontDefault: String?,
    @SerializedName("front_female") val frontFemale: String?,
    @SerializedName("front_shiny") val frontShiny: String?,
    @SerializedName("front_shiny_female") val frontShinyFemale: String?
)

data class HeartgoldSoulsilver(
    @SerializedName("back_default") val backDefault: String?,
    @SerializedName("back_female") val backFemale: String?,
    @SerializedName("back_shiny") val backShiny: String?,
    @SerializedName("back_shiny_female") val backShinyFemale: String?,
    @SerializedName("front_default") val frontDefault: String?,
    @SerializedName("front_female") val frontFemale: String?,
    @SerializedName("front_shiny") val frontShiny: String?,
    @SerializedName("front_shiny_female") val frontShinyFemale: String?
)

data class Platinum(
    @SerializedName("back_default") val backDefault: String?,
    @SerializedName("back_female") val backFemale: String?,
    @SerializedName("back_shiny") val backShiny: String?,
    @SerializedName("back_shiny_female") val backShinyFemale: String?,
    @SerializedName("front_default") val frontDefault: String?,
    @SerializedName("front_female") val frontFemale: String?,
    @SerializedName("front_shiny") val frontShiny: String?,
    @SerializedName("front_shiny_female") val frontShinyFemale: String?
)

data class GenerationV(
    @SerializedName("black-white") val blackWhite: BlackWhite?
)

data class BlackWhite(
    @SerializedName("animated") val animated: AnimatedSprites?,
    @SerializedName("back_default") val backDefault: String?,
    @SerializedName("back_female") val backFemale: String?,
    @SerializedName("back_shiny") val backShiny: String?,
    @SerializedName("back_shiny_female") val backShinyFemale: String?,
    @SerializedName("front_default") val frontDefault: String?,
    @SerializedName("front_female") val frontFemale: String?,
    @SerializedName("front_shiny") val frontShiny: String?,
    @SerializedName("front_shiny_female") val frontShinyFemale: String?
)

data class AnimatedSprites(
    @SerializedName("back_default") val backDefault: String?,
    @SerializedName("back_female") val backFemale: String?,
    @SerializedName("back_shiny") val backShiny: String?,
    @SerializedName("back_shiny_female") val backShinyFemale: String?,
    @SerializedName("front_default") val frontDefault: String?,
    @SerializedName("front_female") val frontFemale: String?,
    @SerializedName("front_shiny") val frontShiny: String?,
    @SerializedName("front_shiny_female") val frontShinyFemale: String?
)

data class GenerationVI(
    @SerializedName("omegaruby-alphasapphire") val omegarubyAlphasapphire: OmegaRubyAlphaSapphire?,
    @SerializedName("x-y") val xY: XY?
)

data class OmegaRubyAlphaSapphire(
    @SerializedName("front_default") val frontDefault: String?,
    @SerializedName("front_female") val frontFemale: String?,
    @SerializedName("front_shiny") val frontShiny: String?,
    @SerializedName("front_shiny_female") val frontShinyFemale: String?
)

data class XY(
    @SerializedName("front_default") val frontDefault: String?,
    @SerializedName("front_female") val frontFemale: String?,
    @SerializedName("front_shiny") val frontShiny: String?,
    @SerializedName("front_shiny_female") val frontShinyFemale: String?
)

data class GenerationVII(
    @SerializedName("icons") val icons: Icons?,
    @SerializedName("ultra-sun-ultra-moon") val ultraSunUltraMoon: UltraSunUltraMoon?
)

data class Icons(
    @SerializedName("front_default") val frontDefault: String?,
    @SerializedName("front_female") val frontFemale: String?
)

data class UltraSunUltraMoon(
    @SerializedName("front_default") val frontDefault: String?,
    @SerializedName("front_female") val frontFemale: String?,
    @SerializedName("front_shiny") val frontShiny: String?,
    @SerializedName("front_shiny_female") val frontShinyFemale: String?
)

data class GenerationVIII(
    @SerializedName("icons") val icons: Icons?
)
