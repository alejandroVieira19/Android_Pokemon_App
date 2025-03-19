package com.pokemon_app.utils

import android.content.Context

object StringUtils {

    private var _builder : StringBuilder = StringBuilder()

    fun getString(context: Context, stringResId: Int): String {
        return context.getString(stringResId)
    }

    fun buildMessage(firstMessage: String, secondMessage: String = "", thirdMessage: String = ""): String {
        return StringBuilder()
            .append(firstMessage)
            .apply {
                if (secondMessage.isNotEmpty()) append(" ").append(secondMessage)
                if (thirdMessage.isNotEmpty()) append(" ").append(thirdMessage)
            }
            .toString()
    }

}