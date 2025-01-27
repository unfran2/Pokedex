package com.example.pokedex;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class Login extends AppCompatActivity {
    /**
     * Este método se llama cuando la actividad es creada.
     * Aquí configuro el estado inicial de la actividad y verifico si el usuario ya está autenticado.
     *
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

}

    /**
     * Inicio el flujo de inicio de sesión usando FirebaseUI.
     * Permito el inicio de sesión con correo electrónico.
     */
    private void startSignIn() {
        List<AuthUI.IdpConfig> providers = Arrays.asList(
          new AuthUI.IdpConfig.EmailBuilder().build());
        // Creo el intent de inicio de sesión con FirebaseUI
        Intent signInIntent = AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .setLogo(R.drawable.logopokemon)
                        .setTheme(R.style.Theme_Pokedex)
                        .build();
        // Lanzo el intent para iniciar sesión
        signInLauncher.launch(signInIntent);
    }

    /**
     * Este lanzador maneja los resultados del intento de inicio de sesión con FirebaseUI.
     */
    private final ActivityResultLauncher<Intent> signInLauncher = registerForActivityResult(
            new FirebaseAuthUIActivityResultContract(),
            new ActivityResultCallback<FirebaseAuthUIAuthenticationResult>() {
                @Override
                public void onActivityResult(FirebaseAuthUIAuthenticationResult result) {
                    onSignInResult(result);
                }
            }
    );

    /**
     * Manejo los resultados del inicio de sesión.
     * Si el inicio de sesión es exitoso, redirijo al usuario a la actividad principal.
     * Si falla, muestro un mensaje de error.
     *
     * @param result el resultado de la autenticación.
     */
    private void onSignInResult(FirebaseAuthUIAuthenticationResult result) {
        IdpResponse response = result.getIdpResponse();
        if (result.getResultCode() == RESULT_OK) {
            // Successfully signed in
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            goToMainActivity();
        } else {
            Toast.makeText(this, "No se ha podido abrir sesión", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Este método se llama cuando la actividad está a punto de mostrarse al usuario.
     * Verifico si el usuario ya está autenticado y actúo en consecuencia.
     */
    @Override
    protected void onStart() {
        super.onStart();
        // Obtengo el usuario autenticado actual, si lo hay
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if(user != null){
            goToMainActivity();
        }else{
            startSignIn();
        }
    }

    /**
     * Redirijo al usuario a la actividad principal y finalizo esta actividad.
     */
    private void goToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}