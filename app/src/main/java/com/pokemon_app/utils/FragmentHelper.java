package com.pokemon_app.utils;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class FragmentHelper {
    private final FragmentManager fragmentManager;

    // Construtor recebendo o FragmentManager (da Activity ou Fragment)
    public FragmentHelper(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    /**
     * Método genérico para substituir fragmentos.
     */
    public void replaceFragment(int containerViewId, Fragment fragment, boolean addToBackStack, String tag) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(containerViewId, fragment, tag);

        if (addToBackStack) {
            transaction.addToBackStack(tag);
        }

        transaction.commit();
    }

    public void popStackBack(String fragmentTag) {
        fragmentManager.popBackStackImmediate(fragmentTag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    /**
     * Método genérico para adicionar um fragmento.
     */
    public void addFragment(int containerViewId, Fragment fragment, boolean addToBackStack, String tag) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(containerViewId, fragment, tag);

        if (addToBackStack) {
            transaction.addToBackStack(tag);
        }

        transaction.commit();
    }

    /**
     * Mostrar um fragmento e esconder outro (útil para tabs e navegação por visibilidade).
     */
    public void showHideFragment(Fragment fragmentToShow, Fragment fragmentToHide, boolean addToBackStack) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.hide(fragmentToHide);
        transaction.show(fragmentToShow);

        if (addToBackStack) {
            transaction.addToBackStack(null);
        }

        transaction.commit();
    }

    /**
     * Mostrar um fragmento (assumindo que ele já foi adicionado).
     */
    public void showFragment(Fragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.show(fragment);
        transaction.commit();
    }

    /**
     * Esconder um fragmento.
     */
    public void hideFragment(Fragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.hide(fragment);
        transaction.commit();
    }

    /**
     * Método para adicionar animações personalizadas na transação (opcional).
     */
    public void replaceFragmentWithAnimation(int containerViewId, Fragment fragment, boolean addToBackStack, String tag,
                                             int enterAnim, int exitAnim, int popEnterAnim, int popExitAnim) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.setCustomAnimations(enterAnim, exitAnim, popEnterAnim, popExitAnim);
        transaction.replace(containerViewId, fragment, tag);

        if (addToBackStack) {
            transaction.addToBackStack(tag);
        }

        transaction.commit();
    }
}
