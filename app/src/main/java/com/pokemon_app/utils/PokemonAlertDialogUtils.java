package com.pokemon_app.utils;

import android.app.AlertDialog;
import android.content.Context;

import com.pokemon_app.R;

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

        builder.setTitle(context.getString(R.string.poke_explorer_app))
                .setMessage(message)
                .setPositiveButton(context.getString(R.string.pokemon_dialog_confirm), (dialog, which) -> {
                    callback.onConfirm();
                })
                .setNegativeButton(context.getString(R.string.pokemon_dialog_negative), (dialog, which) -> {
                    dialog.dismiss();
                })
                .create().show();
    }

    public static void showMessageAlert(Context context, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle(context.getString(R.string.poke_explorer_app))
                .setMessage(message)
                .setPositiveButton(context.getString(R.string.pokemon_dialog_ok), (dialog, which) -> {
                    dialog.dismiss();
                })
                .create().show();
    }
}
