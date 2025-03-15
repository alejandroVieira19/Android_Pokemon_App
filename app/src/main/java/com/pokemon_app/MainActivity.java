package com.pokemon_app;

import static com.pokemon_app.utils.Config.POKEMON_NAME_KEY;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.pokemon_app.presentation.adapter.PokeCardAdapter;
import com.pokemon_app.presentation.ui.view.DetailPokemonFragment;
import com.pokemon_app.presentation.ui.view.ListPokemonFragment;
import com.pokemon_app.presentation.ui.view.PokemonIntroductionScreen;
import com.pokemon_app.utils.ActionBarHelper;
import com.pokemon_app.utils.FragmentHelper;
import com.pokemon_app.utils.FragmentsTags;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    FragmentManager fragmentManager;
    ActionBarHelper actionBarHelper;
    Fragment detailPokemonFragment, listPokemonFragment, pokemonIntroScreen;
    Bundle bundle;
    FragmentHelper fragmentHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            // Ajustando o padding para a área segura da tela
            v.setPadding(insets.getInsets(WindowInsetsCompat.Type.systemBars()).left,
                    insets.getInsets(WindowInsetsCompat.Type.systemBars()).top,
                    insets.getInsets(WindowInsetsCompat.Type.systemBars()).right,
                    insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom);
            return insets;
        });

        initializateMainMenu();
    }
    private void initializateMainMenu() {
        actionBarHelper = new ActionBarHelper(this);
        fragmentManager = getSupportFragmentManager();
        fragmentHelper = new FragmentHelper(fragmentManager);
        bundle = new Bundle();

        listPokemonFragment = new ListPokemonFragment();
        detailPokemonFragment = new DetailPokemonFragment();
        pokemonIntroScreen = new PokemonIntroductionScreen();
        fragmentHelper.replaceFragment(R.id.mainFrag, pokemonIntroScreen, false,FragmentsTags.TAG_FRAGMENTS_INTRO);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        actionBarHelper.changeActionBarTitleAndShowArrowBack("PokeExplorer App", false);
        return super.onCreateOptionsMenu(menu);
    }



    // Tratando o clique no botão de voltar (ActionBar)
    // TODO -----> TRATAR DO ERRO DO LIST
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if(fragmentManager.findFragmentById(R.id.mainFrag) instanceof ListPokemonFragment) {
                actionBarHelper.changeActionBarTitleAndPopStackBack("PokeExplorer App", fragmentManager);
            } else {
                fragmentManager.popBackStack();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
