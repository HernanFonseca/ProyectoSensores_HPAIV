package com.example.proyecto_sensores;

import android.app.Activity;
import androidx.biometric.BiometricPrompt;;
import android.content.Context;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import java.util.concurrent.Executor;

public class SensorHuella {
    public BiometricPrompt biometricPrompt;
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void usar(Executor executor, final Context context, final FragmentActivity fragmentActivity){
        //Executor executor = ContextCompat.getMainExecutor(this);
        biometricPrompt = new BiometricPrompt(fragmentActivity,
                executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode,
                                              @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                Toast.makeText(context,
                        "Authentication error: " + errString, Toast.LENGTH_SHORT)
                        .show();
            }

            @Override
            public void onAuthenticationSucceeded(
                    @NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Toast.makeText(context,
                        "Todo Bien", Toast.LENGTH_SHORT).show();
                //startActivity(new Intent(getApplicationContext(), TipoPedido.class));
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Toast.makeText(context, "Todo Mal :(",
                        Toast.LENGTH_SHORT)
                        .show();

            }
        });

        BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Captación de datos biométricos")
                .setSubtitle("Log in using your biometric credential")
                .setNegativeButtonText("Use account password")
                .build();

        // Prompt appears when user clicks "Log in".
        // Consider integrating with the keystore to unlock cryptographic operations,
        // if needed by your app.
        biometricPrompt.authenticate(promptInfo);
        return;
    }

}
