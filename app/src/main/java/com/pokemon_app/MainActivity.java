package com.pokemon_app;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.pokemon_app.presentation.ui.view.GenericFragment;
import com.pokemon_app.presentation.ui.view.list.ListPokemonFragment;
import com.pokemon_app.presentation.ui.view.intro.PokemonIntroductionScreen;
import com.pokemon_app.utils.ActionBarHelper;
import com.pokemon_app.utils.FragmentHelper;
import com.pokemon_app.utils.FragmentsMediator;
import com.pokemon_app.utils.FragmentsTags;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity implements FragmentsMediator {
    FragmentManager fragmentManager;
    ActionBarHelper actionBarHelper;
    Fragment listPokemonFragment, pokemonIntroScreen;
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
        pokemonIntroScreen = new PokemonIntroductionScreen();
        fragmentHelper.replaceFragment(R.id.mainFrag, pokemonIntroScreen, false, FragmentsTags.TAG_FRAGMENTS_INTRO);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Verifique se o fragmento é o desejado e só então exibe o botão de voltar
        if (fragmentManager.findFragmentById(R.id.mainFrag) instanceof PokemonIntroductionScreen) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }else {
          getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        return super.onCreateOptionsMenu(menu);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        fragmentManager.popBackStack();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void sendDataToFragment(@NonNull String fragmentTag, @NonNull Bundle data) {

        Log.d("HELLO", "HELLO");

      Fragment fragment = fragmentManager.findFragmentByTag(fragmentTag);

      if(fragment instanceof GenericFragment) {
          Log.d("HELLO", "HELLO1");
          ((GenericFragment) fragment).onFragmentDataReceive(bundle);
      }
    }
}
