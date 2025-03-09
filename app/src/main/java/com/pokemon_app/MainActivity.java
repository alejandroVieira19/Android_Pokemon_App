package com.pokemon_app;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.pokemon_app.ui.view.DetailPokemonFragment;
import com.pokemon_app.ui.view.ListPokemonFragment;

public class MainActivity extends AppCompatActivity implements ListPokemonFragment.OnButtonClicked {

    FragmentManager fragmentManager;
    Fragment detailPokemonFragment, listPokemonFragment;

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

        fragmentManager = getSupportFragmentManager();
        listPokemonFragment = new ListPokemonFragment();
        detailPokemonFragment = new DetailPokemonFragment();

        pokeExplorerSetup();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        changeActionBarTitle("PokeExplorer App", false);
        return super.onCreateOptionsMenu(menu);
    }

    private void pokeExplorerSetup() {
        // Adicionando o fragmento da lista de pokémons ao início
        replaceFragment(R.id.main, listPokemonFragment, false);
    }

    private void changeActionBarTitle(String newTile, boolean isArrowBackToShow) {
        getSupportActionBar().setTitle(newTile);
        if (isArrowBackToShow) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }


    @Override
    public void onButtonClickedToChangeFragment() {
        // Atualizando a visibilidade dos fragmentos com hide e show
        showFragment(detailPokemonFragment, listPokemonFragment);
        replaceFragment(R.id.mainFrag, detailPokemonFragment, true);
        changeActionBarTitle("Pokemon Detail", true);
    }

    private void replaceFragment(int containerViewId, Fragment fragment, boolean addToBackStack) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(containerViewId, fragment);

        if (addToBackStack) {
            transaction.addToBackStack(null); // Adiciona à pilha de volta
        }

        transaction.commit();
    }

    // Função para gerenciar a visibilidade dos fragmentos usando hide/show
    private void showFragment(Fragment fragmentToShow, Fragment fragmentToHide) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        // Esconde o fragmento atual
        transaction.hide(fragmentToHide);

        // Exibe o fragmento desejado
        transaction.show(fragmentToShow);

        // Commit da transação
        transaction.commit();
    }

    // Tratando o clique no botão de voltar (ActionBar)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            fragmentManager.popBackStack(); // Volta para o fragmento anterior

            // Atualizando a visibilidade ao voltar
            showFragment(listPokemonFragment, detailPokemonFragment);

            changeActionBarTitle("PokeExplorer App", false);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
