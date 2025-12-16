package com.example.myapplication.passenger;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.myapplication.R;
import com.example.myapplication.models.User;
import com.example.myapplication.utils.RatingUtils;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class PassengerProfileActivity extends AppCompatActivity {

    private User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_profile);

        loadUserData();
        initViews();
        // Llamada a la función de configuración (Línea 33)
        setupBottomNavigation();
    }

    // ... (Métodos loadUserData, initViews, showLogoutConfirmation, logout no modificados) ...

    private void loadUserData() {
        // En una app real, esto vendría de SharedPreferences o base de datos
        currentUser = new User();
        currentUser.setName("Alejandro Martínez");
        currentUser.setEmail("alejandro.martinez@email.com");
        currentUser.setPhone("+52 555 123 4567");
        currentUser.setUserType("passenger");
        currentUser.setRating(4.7f);
        currentUser.setTotalTrips(124);
        currentUser.setMemberSince("2022-05-15");
        currentUser.setPhotoUrl(""); // URL de foto de perfil
    }

    private void initViews() {
        // Configurar datos del usuario
        TextView tvUserName = findViewById(R.id.tv_user_name);
        TextView tvUserType = findViewById(R.id.tv_user_type);
        TextView tvUserStats = findViewById(R.id.tv_user_stats);
        TextView tvEmail = findViewById(R.id.tv_email);
        TextView tvPhone = findViewById(R.id.tv_phone);
        TextView tvMemberSince = findViewById(R.id.tv_member_since);
        TextView tvTotalTrips = findViewById(R.id.tv_total_trips);
        TextView tvAverageRating = findViewById(R.id.tv_average_rating);

        tvUserName.setText(currentUser.getName());
        tvUserType.setText("Pasajero");
        tvUserStats.setText(String.format("★ %.1f (%d viajes)",
                currentUser.getRating(), currentUser.getTotalTrips()));
        tvEmail.setText(currentUser.getEmail());
        tvPhone.setText(currentUser.getPhone());
        tvTotalTrips.setText(String.valueOf(currentUser.getTotalTrips()));
        tvAverageRating.setText(String.format("%.1f", currentUser.getRating()));

        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            SimpleDateFormat outputFormat = new SimpleDateFormat("MMMM yyyy", new Locale("es", "ES"));
            String formattedDate = outputFormat.format(inputFormat.parse(currentUser.getMemberSince()));
            tvMemberSince.setText("Miembro desde " + formattedDate);
        } catch (Exception e) {
            tvMemberSince.setText("Miembro desde " + currentUser.getMemberSince());
        }

        // Configurar rating con estrellas
        LinearLayout ratingContainer = findViewById(R.id.rating_container);
        RatingUtils.setRatingStars(ratingContainer, currentUser.getRating());

        // Configurar botones
        Button btnEditProfile = findViewById(R.id.btn_edit_profile);
        Button btnPaymentMethods = findViewById(R.id.btn_payment_methods);
        Button btnPreferences = findViewById(R.id.btn_preferences);
        Button btnHelp = findViewById(R.id.btn_help);
        Button btnLogout = findViewById(R.id.btn_logout);

        btnEditProfile.setOnClickListener(v -> {
            Intent intent = new Intent(this, EditProfileActivity.class);
            startActivity(intent);
        });

        btnPaymentMethods.setOnClickListener(v -> {
            // Abrir métodos de pago
            Toast.makeText(this, "Métodos de pago", Toast.LENGTH_SHORT).show();
        });

        btnPreferences.setOnClickListener(v -> {
            // Abrir preferencias
            Toast.makeText(this, "Preferencias", Toast.LENGTH_SHORT).show();
        });

        btnHelp.setOnClickListener(v -> {
            // Abrir ayuda
            Toast.makeText(this, "Centro de ayuda", Toast.LENGTH_SHORT).show();
        });

        btnLogout.setOnClickListener(v -> {
            showLogoutConfirmation();
        });

        // Configurar foto de perfil
        ImageView ivProfilePhoto = findViewById(R.id.iv_profile_photo);
        if (currentUser.getPhotoUrl() != null && !currentUser.getPhotoUrl().isEmpty()) {
            // Cargar imagen desde URL usando Glide o Picasso
            // Glide.with(this).load(currentUser.getPhotoUrl()).into(ivProfilePhoto);
        } else {
            ivProfilePhoto.setImageResource(R.drawable.ic_profile_default);
        }
    }

    private void showLogoutConfirmation() {
        new AlertDialog.Builder(this)
                .setTitle("Cerrar Sesión")
                .setMessage("¿Estás seguro de que quieres cerrar sesión?")
                .setPositiveButton("Sí, cerrar", (dialog, which) -> {
                    logout();
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }

    private void logout() {
        // Limpiar datos de sesión
        // SharedPreferences preferences = getSharedPreferences("app_prefs", MODE_PRIVATE);
        // preferences.edit().clear().apply();

        // Regresar a login
        Toast.makeText(this, "Sesión cerrada", Toast.LENGTH_SHORT).show();
        finish();
        // Intent intent = new Intent(this, LoginActivity.class);
        // startActivity(intent);
    }

    private void setupBottomNavigation() {
        // CORRECCIÓN: Usamos un ID más común para la vista de navegación inferior
        // (Reemplaza R.id.bottom_navigation por el ID real de tu XML, ej: R.id.bottom_navigation_view)
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);

        // Agregar verificación de nulidad (Good Practice)
        if (bottomNav != null) {
            bottomNav.setSelectedItemId(R.id.nav_profile);
            bottomNav.setOnNavigationItemSelectedListener(item -> {
                navigateFromBottomNav(item.getItemId());
                return true;
            });
        }
    }

    private void navigateFromBottomNav(int itemId) {
        Intent intent = null;

        if (itemId == R.id.nav_home) {
            intent = new Intent(this, PassengerHomeActivity.class);
        } else if (itemId == R.id.nav_history) {
            intent = new Intent(this, TripHistoryActivity.class);
        } else if (itemId == R.id.nav_map) {
            // Implementar mapa
            return;
        } else if (itemId == R.id.nav_chat) {
            intent = new Intent(this, ChatListActivity.class);
        } else if (itemId == R.id.nav_profile) {
            return; // Ya estamos aquí
        }

        if (intent != null) {
            startActivity(intent);
            finish();
        }
    }
}