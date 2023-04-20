package com.example.rateconverter.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.example.rateconverter.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}