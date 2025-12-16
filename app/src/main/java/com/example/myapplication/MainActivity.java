package com.example.myapplication;



import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.passenger.PassengerHomeActivity;

public class MainActivity extends AppCompatActivity {

    private static final int SPLASH_DELAY = 2000; // 2 segundos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ocultar ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Simular carga inicial y luego redirigir
        new Handler().postDelayed(() -> {
            // Verificar si el usuario ya está logueado
            checkUserSession();
        }, SPLASH_DELAY);
    }

    private void checkUserSession() {
        // Aquí deberías verificar si el usuario tiene sesión activa
        // Por ahora, asumimos que el usuario está logueado y es pasajero

        boolean isLoggedIn = true; // Esto vendría de SharedPreferences
        String userType = "passenger"; // Esto vendría de la base de datos

        if (isLoggedIn) {
            if ("passenger".equals(userType)) {
                startActivity(new Intent(MainActivity.this, PassengerHomeActivity.class));
            } else if ("driver".equals(userType)) {
                // startActivity(new Intent(MainActivity.this, DriverHomeActivity.class));
                // Por ahora redirigimos a pasajero
                startActivity(new Intent(MainActivity.this, PassengerHomeActivity.class));
            }
        } else {
            // Si no está logueado, ir a LoginActivity
            // startActivity(new Intent(MainActivity.this, LoginActivity.class));
            // Por ahora redirigimos directo al home
            startActivity(new Intent(MainActivity.this, PassengerHomeActivity.class));
        }

        finish(); // Cerrar esta actividad para que no se pueda volver
    }

    @Override
    public void onBackPressed() {
        // Deshabilitar back press en splash
        // super.onBackPressed();
    }
}