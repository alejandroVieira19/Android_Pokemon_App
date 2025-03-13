package com.pokemon_app;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.pokemon_app.data.network.PokeApiService;
import com.pokemon_app.data.network.RetrofitInstance;
import com.pokemon_app.data.repository.PokemonRepository;
import com.pokemon_app.domain.model.Pokemon;
import com.pokemon_app.domain.repository.IPokemonRepository;
import com.pokemon_app.presentation.ui.view.DetailPokemonFragment;
import com.pokemon_app.presentation.ui.view.ListPokemonFragment;
import com.pokemon_app.presentation.ui.view.PokemonIntroductionScreen;
import com.pokemon_app.presentation.viewmodel.PokemonViewModel;
import com.pokemon_app.presentation.viewmodel.PokemonViewModelFactory;
import com.pokemon_app.utils.ActionBarHelper;
import com.pokemon_app.utils.FragmentHelper;
import com.pokemon_app.utils.PokemonService;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import retrofit2.Retrofit;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity implements ListPokemonFragment.OnButtonClicked {
    PokemonViewModel pokemonViewModel;
    FragmentManager fragmentManager;
    ActionBarHelper actionBarHelper;
    Fragment detailPokemonFragment, listPokemonFragment, pokemonIntroScreen;

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

        actionBarHelper = new ActionBarHelper(this);
        fragmentManager = getSupportFragmentManager();
        //pokemonViewModel =  new ViewModelProvider(this).get(PokemonViewModel.class);
        fragmentHelper = new FragmentHelper(fragmentManager);

        listPokemonFragment = new ListPokemonFragment();
        detailPokemonFragment = new DetailPokemonFragment();
        pokemonIntroScreen = new PokemonIntroductionScreen();





    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        actionBarHelper.changeActionBarTitleAndShowArrowBack("PokeExplorer App", false);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onButtonClickedToChangeFragment() {
        fragmentHelper.replaceFragment(R.id.mainFrag, detailPokemonFragment, true, "DetailScreen");
    }

    // Tratando o clique no botão de voltar (ActionBar)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
           actionBarHelper.changeActionBarTitleAndPopStackBack("PokeExplorer App", fragmentManager);
        }
        return super.onOptionsItemSelected(item);
    }
}
