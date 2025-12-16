package com.example.myapplication.passenger;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;  // ← AÑADIDO
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.myapplication.R;
import com.example.myapplication.adapters.TripHistoryAdapter;
import com.example.myapplication.models.Trip;
import java.util.ArrayList;
import java.util.List;

public class PassengerHomeActivity extends AppCompatActivity {

    private CardView cardActiveTrip;
    private RecyclerView rvRecentTrips;
    private TripHistoryAdapter tripHistoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_home);

        initViews();
        setupBottomNavigation();
        loadRecentTrips();
        checkActiveTrip();
    }

    private void initViews() {
        cardActiveTrip = findViewById(R.id.card_active_trip);
        rvRecentTrips = findViewById(R.id.rv_recent_trips);

        Button btnRequestTrip = findViewById(R.id.btn_request_trip);
        btnRequestTrip.setOnClickListener(v -> {
            Intent intent = new Intent(this, RequestTripActivity.class);
            startActivity(intent);
        });

        // Configurar RecyclerView - CORREGIDO
        rvRecentTrips.setLayoutManager(new LinearLayoutManager(this));
        tripHistoryAdapter = new TripHistoryAdapter(new ArrayList<>(), new TripHistoryAdapter.OnTripClickListener() {
            @Override
            public void onTripClick(Trip trip) {
                // Manejar clic en viaje
                Intent intent = new Intent(PassengerHomeActivity.this, TripDetailActivity.class);
                intent.putExtra("TRIP_ID", trip.getId());
                startActivity(intent);
            }
        });
        rvRecentTrips.setAdapter(tripHistoryAdapter);

        // Click en viaje activo
        cardActiveTrip.setOnClickListener(v -> {
            Intent intent = new Intent(this, TripInProgressActivity.class);
            startActivity(intent);
        });
    }

    private void setupBottomNavigation() {
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.nav_home) {
                // Ya estamos en home
                return true;
            } else if (itemId == R.id.nav_history) {
                Intent intent = new Intent(this, TripHistoryActivity.class);
                startActivity(intent);
                return true;
            } else if (itemId == R.id.nav_map) {
                // Abrir mapa (implementar después)
                Toast.makeText(this, "Funcionalidad de mapa en desarrollo", Toast.LENGTH_SHORT).show();
                return true;
            } else if (itemId == R.id.nav_chat) {
                Intent intent = new Intent(this, ChatListActivity.class);
                startActivity(intent);
                return true;
            } else if (itemId == R.id.nav_profile) {
                Intent intent = new Intent(this, PassengerProfileActivity.class);
                startActivity(intent);
                return true;
            }
            return false;
        });
    }

    private void loadRecentTrips() {
        // Datos de ejemplo - CORREGIDO: Usar constructor correcto
        List<Trip> recentTrips = new ArrayList<>();
        recentTrips.add(new Trip(
                "1",  // id
                "Carlos López",  // driverName
                "Av. Insurgentes Sur 1602",  // origin
                "Centro Comercial Perisur",  // destination
                120.00,  // price (double, no String)
                4.5f,  // rating
                "15/03/2024"  // date
        ));
        recentTrips.add(new Trip(
                "2",  // id
                "María González",  // driverName
                "Casa",  // origin
                "Aeropuerto Terminal 1",  // destination
                350.00,  // price (double, no String)
                5.0f,  // rating
                "14/03/2024"  // date
        ));

        tripHistoryAdapter.updateTrips(recentTrips);
    }

    private void checkActiveTrip() {
        // Verificar si hay viaje activo (esto vendría de tu backend)
        boolean hasActiveTrip = false; // Cambiar según lógica

        if (hasActiveTrip) {
            cardActiveTrip.setVisibility(View.VISIBLE);
            // Actualizar información del viaje activo
        } else {
            cardActiveTrip.setVisibility(View.GONE);
        }
    }
}