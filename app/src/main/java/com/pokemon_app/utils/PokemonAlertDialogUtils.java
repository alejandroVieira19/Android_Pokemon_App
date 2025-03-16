package com.pokemon_app.utils;

import android.app.AlertDialog;
import android.content.Context;

public class PokemonAlertDialogUtils {

    Context context;

    ConfirmationCallback callback;

    public PokemonAlertDialogUtils(Context context,  ConfirmationCallback callback) {
        this.callback = callback;
        this.context = context;
    }

    // Interface para o Callback de confirmação
    public interface ConfirmationCallback {
        void onConfirm(); // Método chamado quando o "Yes" é clicado
    }

    // Método que exibe o AlertDialog
    public void showAlertDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle(Config.POKE_EXPLORER_APP)
                .setMessage(message)
                .setPositiveButton(Config.POKEMON_DIALOG_CONFIRM, (dialog, which) -> {
                    callback.onConfirm();
                })
                .setNegativeButton(Config.POKEMON_DIALOG_NEGATIVE, (dialog, which) -> {
                    dialog.dismiss(); // Fecha o diálogo se o usuário clicar em "No"
                })
                .create().show();
    }
}
