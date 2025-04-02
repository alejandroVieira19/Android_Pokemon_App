package com.pokemon_app.presentation.ui.view.composable.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.pokemon_app.R
import com.pokemon_app.interactions.PokemonTabList
import com.pokemon_app.presentation.ui.view.composable.manager.ComposableProvider


sealed class PokemonDetailComposable : ComposableProvider {
    @Composable
    abstract override fun ProvideComposableContent()
}


data class PokemonImage(val pokemonImageUrl: String) : PokemonDetailComposable() {
    @Composable
    override fun ProvideComposableContent() {
        PokemonImageDetail(pokemonImageUrl)
    }
}

data class FavoritePokemonIcon(val iconResId: Int) : PokemonDetailComposable() {
    @Composable
    override fun ProvideComposableContent() {
        PokemonDetailDrawableImage(iconResId)
    }
}

data class PokemonDetailTaBar(val tabBarProps: PokemonDetailTabBarProps) : PokemonDetailComposable() {
    @Composable
    override fun ProvideComposableContent() {
        PokemonDetailTabBar(tabBarProps)
    }
}

@Composable
fun PokemonImageDetail(pokemonImageUrl: String) {
    AsyncImage(
        model = pokemonImageUrl,
        contentDescription = stringResource(R.string.content_description),
        modifier = Modifier.fillMaxWidth().
        height(375.dp)
            .padding(horizontal = 20.dp, vertical = 20.dp),
        contentScale = ContentScale.Fit,
        error = painterResource(R.drawable.error)
    )
}

@Composable
fun PokemonDetailDrawableImage(icon: Int, height: Int? = null, width: Int? = null) {
    Image(
        painter = painterResource(icon),
        contentDescription = stringResource(R.string.content_description),
        contentScale = ContentScale.Fit,
        modifier = Modifier
            .height(height?.dp ?: 40.dp) // Define altura padrão se `height` for nulo
            .width(width?.dp ?: 40.dp)   // Define largura padrão se `width` for nulo
            .padding(top = 10.dp)        // Corrigido o erro de sintaxe
    )
}

data class PokemonDetailTabBarProps(
    val tabBarList: List<PokemonTabList>,
    val selectedTab: PokemonTabList,
    val textColor: Int,
    val onTabClicked: (PokemonTabList) -> Unit,
)

@Composable
fun PokemonDetailTabBar(pokemonDetailTabProps: PokemonDetailTabBarProps) {

    val selectedTab = remember { mutableStateOf(pokemonDetailTabProps.selectedTab) }

    Row(modifier = Modifier.fillMaxWidth()) {
        pokemonDetailTabProps.tabBarList.forEach { tabTitle ->
            Box(
                modifier = Modifier
                    .weight(1f)
                    .background(
                        color = if (tabTitle == selectedTab.value) colorResource(R.color.blur) else colorResource(R.color.transparent),
                        shape = RoundedCornerShape(24.dp),

                    )
                    .clickable (
                        interactionSource = remember { MutableInteractionSource() } ,
                        indication = rememberRipple(color = colorResource(R.color.white)),
                        onClick = {selectedTab.value = tabTitle
                            pokemonDetailTabProps.onTabClicked(tabTitle)}
                    )
                    .padding(16.dp)
            ) {
                Text(
                    text = tabTitle.toString(),
                    color = colorResource(pokemonDetailTabProps.textColor),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}





//-----------------------------------------------------------------------------------------------------------------------------------------
@Preview
@Composable
fun PreviewPokemonImageDetail() {
    PokemonImageDetail("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png")
}

@Preview
@Composable
fun PreviewPokemonFavoriteIcon() {
    PokemonDetailDrawableImage(android.R.drawable.btn_star_big_on)
}

@Preview(showBackground = true)
@Composable
fun PreviewPokemonDetailTabBar() {
    val tabList = PokemonTabList.values().toList()
    val selectedTab = "Moves"
    val textColor = R.color.black
    Column(modifier = Modifier.background(colorResource(R.color.pikachu_yellow_gold))) {
        PokemonDetailTabBar(
            PokemonDetailTabBarProps(
                tabBarList = tabList, selectedTab = tabList.get(0),
                onTabClicked = {}, textColor = textColor
            ))
    }
}