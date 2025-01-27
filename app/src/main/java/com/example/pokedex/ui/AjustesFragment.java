package com.example.pokedex.ui;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.example.pokedex.Login;
import com.example.pokedex.R;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.Locale;
import java.util.Objects;

public class AjustesFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {


    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.settings, rootKey);

        // Configuración de la preferencia para cerrar sesión
        Preference logoutPreference = findPreference("logout");
        if (logoutPreference != null) {
            logoutPreference.setOnPreferenceClickListener(preference -> {
                logout();
                return true;
            });
        }
        // Configuración de la preferencia "Acerca de"
        Preference aboutPreference = findPreference("about");
        if (aboutPreference != null) {
            aboutPreference.setOnPreferenceClickListener(preference -> {
                about();
                return true;
            });
        }


    }
    /**
     * Escucha los cambios en las preferencias compartidas.
     *
     * @param sharedPreferences Preferencias compartidas.
     * @param key               Clave de la preferencia cambiada.
     */
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals("language_preference")) {
            boolean isEnglish = sharedPreferences.getBoolean("language_preference", false);
            setLocale(isEnglish ? "en" : "es");
        }
    }

    /**
     * Cierra la sesión del usuario y lo redirige a la pantalla de inicio de sesión.
     */
    private void logout() {
        AuthUI.getInstance()
                .signOut(getContext())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getContext(), "Sesión cerrada con éxito", Toast.LENGTH_SHORT).show();
                    }
                });
        Intent intent = new Intent(getActivity(), Login.class);
        startActivity(intent);
        requireActivity().finish();
    }
    /**
     * Cambia el idioma de la aplicación.
     *
     * @param languageCode Código del idioma deseado (por ejemplo, "en" o "es").
     */
    private void setLocale(String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);

        android.content.res.Configuration config = getResources().getConfiguration();
        config.setLocale(locale);

        getResources().updateConfiguration(config, getResources().getDisplayMetrics());

        // Recarga la actividad para aplicar el cambio
        requireActivity().recreate();
    }

    /**
     * Muestra un cuadro de diálogo "Acerca de".
     */
    private void about(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(getString(R.string.about));
        builder.setMessage(getString(R.string.about_description));
        builder.setPositiveButton("Ok", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    @Override
    public void onResume() {
        super.onResume();
        Objects.requireNonNull(getPreferenceManager().getSharedPreferences())
                .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        Objects.requireNonNull(getPreferenceManager().getSharedPreferences())
                .unregisterOnSharedPreferenceChangeListener(this);
    }
}
