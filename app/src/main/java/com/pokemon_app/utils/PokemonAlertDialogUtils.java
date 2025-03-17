package com.pokemon_app.utils;

import android.app.AlertDialog;
import android.content.Context;

public class PokemonAlertDialogUtils {

    Context context;

    ConfirmationCallback callback;



    // Interface para o Callback de confirmação
    public interface ConfirmationCallback {
        void onConfirm(); // Método chamado quando o "Yes" é clicado
    }

    // Método que exibe o AlertDialog
    public static void showAlertDialog(String message, ConfirmationCallback callback, Context context ) {
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

    public static void showMessageAlert(Context context, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle(Config.POKE_EXPLORER_APP)
                .setMessage(message)
                .setPositiveButton(Config.POKEMON_DIALOG_OK, (dialog, which) -> {
                    dialog.dismiss();
                })
                .create().show();
    }
}
