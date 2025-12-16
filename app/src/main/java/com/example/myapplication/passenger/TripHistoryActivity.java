package com.example.myapplication.passenger;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.myapplication.R;
import com.example.myapplication.adapters.TripHistoryAdapter;
import com.example.myapplication.models.Trip;
import java.util.ArrayList;
import java.util.List;

public class TripHistoryActivity extends AppCompatActivity {

    private RecyclerView rvTripHistory;
    private TripHistoryAdapter tripHistoryAdapter;
    private Button btnCompleted, btnCancelled;
    private TextView tvTotalEarnings, tvTotalTrips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_history);

        initViews();
        setupBottomNavigation();
        loadTripHistory("completed");
        setupFilterButtons();
    }

    private void initViews() {
        rvTripHistory = findViewById(R.id.rv_trip_history);
        btnCompleted = findViewById(R.id.btn_completed);
        btnCancelled = findViewById(R.id.btn_cancelled);
        tvTotalEarnings = findViewById(R.id.tv_total_earnings);
        tvTotalTrips = findViewById(R.id.tv_total_trips);

        // Configurar RecyclerView
        rvTripHistory.setLayoutManager(new LinearLayoutManager(this));
        tripHistoryAdapter = new TripHistoryAdapter(new ArrayList<>(), trip -> {
            // Abrir detalles del viaje
            openTripDetail(trip);
        });
        rvTripHistory.setAdapter(tripHistoryAdapter);
    }

    private void setupFilterButtons() {
        btnCompleted.setOnClickListener(v -> {
            btnCompleted.setBackgroundResource(R.drawable.button_tab_active);
            btnCancelled.setBackgroundResource(R.drawable.button_tab_inactive);
            btnCompleted.setTextColor(getResources().getColor(R.color.text_light));
            btnCancelled.setTextColor(getResources().getColor(R.color.text_primary));
            loadTripHistory("completed");
        });

        btnCancelled.setOnClickListener(v -> {
            btnCancelled.setBackgroundResource(R.drawable.button_tab_active);
            btnCompleted.setBackgroundResource(R.drawable.button_tab_inactive);
            btnCancelled.setTextColor(getResources().getColor(R.color.text_light));
            btnCompleted.setTextColor(getResources().getColor(R.color.text_primary));
            loadTripHistory("cancelled");
        });
    }

    private void loadTripHistory(String filter) {
        List<Trip> trips = new ArrayList<>();

        if ("completed".equals(filter)) {
            // Viajes completados
            trips.add(new Trip(
                    "1",
                    "Centro Comercial",
                    "Av. Insurgentes Sur 1602",
                    "Centro Comercial Perisur",
                    120.50,
                    4.5f,
                    "Carlos Mendoza",
                    "completed",
                    8.3,
                    25,
                    "2023-03-15 14:00"
            ));
            trips.add(new Trip(
                    "2",
                    "Aeropuerto",
                    "Casa",
                    "Aeropuerto Terminal 1",
                    350.00,
                    5.0f,
                    "María González",
                    "completed",
                    22.5,
                    45,
                    "2023-03-14 08:30"
            ));
        } else {
            // Viajes cancelados
            trips.add(new Trip(
                    "3",
                    "Oficina",
                    "Casa",
                    "Reforma 222",
                    85.00,
                    0,
                    "Juan Pérez",
                    "cancelled",
                    4.5,
                    12,
                    "2023-03-13 09:15"
            ));
        }

        tripHistoryAdapter.updateTrips(trips);
        updateStats(trips);
    }

    private void updateStats(List<Trip> trips) {
        double total = 0;
        int completedCount = 0;

        for (Trip trip : trips) {
            if ("completed".equals(trip.getStatus())) {
                total += trip.getPrice();
                completedCount++;
            }
        }

        tvTotalEarnings.setText(String.format("$%.2f", total));
        tvTotalTrips.setText(String.valueOf(completedCount));
    }

    private void openTripDetail(Trip trip) {
        Intent intent = new Intent(this, TripDetailActivity.class);
        intent.putExtra("TRIP_ID", trip.getId());
        startActivity(intent);
    }

    private void setupBottomNavigation() {
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setSelectedItemId(R.id.nav_history);
        bottomNav.setOnNavigationItemSelectedListener(item -> {
            navigateFromBottomNav(item.getItemId());
            return true;
        });
    }

    private void navigateFromBottomNav(int itemId) {
        Intent intent = null;

        if (itemId == R.id.nav_home) {
            intent = new Intent(this, PassengerHomeActivity.class);
        } else if (itemId == R.id.nav_history) {
            return; // Ya estamos aquí
        } else if (itemId == R.id.nav_map) {
            // Implementar mapa
            return;
        } else if (itemId == R.id.nav_chat) {
            intent = new Intent(this, ChatListActivity.class);
        } else if (itemId == R.id.nav_profile) {
            intent = new Intent(this, PassengerProfileActivity.class);
        }

        if (intent != null) {
            startActivity(intent);
            finish();
        }
    }
}