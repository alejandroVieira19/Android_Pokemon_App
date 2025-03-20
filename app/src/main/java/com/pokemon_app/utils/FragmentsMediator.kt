package com.pokemon_app.utils

import android.os.Bundle

interface FragmentsMediator {
    fun sendDataToFragment(fragmentTag:String, data: Bundle)
}