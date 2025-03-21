package com.pokemon_app.presentation.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment

abstract class GenericFragment : Fragment() {
    open fun onFragmentDataReceive(data:Bundle) {}
}