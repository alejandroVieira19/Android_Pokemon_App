package com.pokemon_app;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.pokemon_app.ui.view.DetailPokemonFragment;
import com.pokemon_app.ui.view.ListPokemonFragment;

public class MainActivity extends AppCompatActivity implements ListPokemonFragment.OnButtonClicked {

    FragmentManager fragmentManager;
    View detailPokemonFragment;
    View listPokemonFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        fragmentManager = getSupportFragmentManager();
        detailPokemonFragment = findViewById(R.id.detailPokemonFragment);
        listPokemonFragment = findViewById(R.id.listPokemonFragment);

       replaceFragment(R.id.listPokemonFragment, new ListPokemonFragment(), false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        getSupportActionBar().setTitle("Pokemon Search List");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onButtonClickedToChangeFragment() {
        // Atualizando a visibilidade
        showFragment(detailPokemonFragment, listPokemonFragment);

        replaceFragment(R.id.detailPokemonFragment, new DetailPokemonFragment(), true);

        getSupportActionBar().setTitle("Pokemon Detail");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void replaceFragment(int containerViewId, Fragment fragment, boolean addToBackStack) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.replace(containerViewId, fragment);

        if (addToBackStack) {transaction.addToBackStack(null);}

        // Executa a transação
        transaction.commit();
    }


    // Função para gerenciar a visibilidade dos fragmentos
    private void showFragment(View fragmentToShow, View fragmentToHide) {
        fragmentToShow.setVisibility(View.VISIBLE);
        fragmentToHide.setVisibility(View.GONE);
    }

    // Tratando o clique no botão de voltar (ActionBar)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            fragmentManager.popBackStack();

            // Atualizando a visibilidade
            showFragment(listPokemonFragment, detailPokemonFragment);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
