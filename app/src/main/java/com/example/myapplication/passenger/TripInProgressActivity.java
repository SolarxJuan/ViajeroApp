package com.example.myapplication.passenger;



import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;
import com.example.myapplication.utils.RatingUtils;
import java.util.Locale;

public class TripInProgressActivity extends AppCompatActivity {

    private TextView tvArrivalTime, tvDistanceRemaining, tvNextInstruction;
    private TextView tvDriverName, tvDriverRating;
    private TextView tvPickupPoint, tvDestination;
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_in_progress);

        initViews();
        setupDriverInfo();
        startTripTimer();
        setupActions();
    }

    private void initViews() {
        tvArrivalTime = findViewById(R.id.tv_arrival_time);
        tvDistanceRemaining = findViewById(R.id.tv_distance_remaining);
        tvNextInstruction = findViewById(R.id.tv_next_instruction);
        tvDriverName = findViewById(R.id.tv_driver_name);
        tvDriverRating = findViewById(R.id.tv_driver_rating);
        tvPickupPoint = findViewById(R.id.tv_pickup_point);
        tvDestination = findViewById(R.id.tv_destination);

        LinearLayout ratingContainer = findViewById(R.id.rating_container);
        RatingUtils.setRatingStars(ratingContainer, 4.8f);
    }

    private void setupDriverInfo() {
        // Datos de ejemplo
        tvDriverName.setText("Carlos Mendoza");
        tvDriverRating.setText("4.8");
        tvPickupPoint.setText("Av. Universidad 3000, Coyoacán");
        tvDestination.setText("Plaza Satélite, Naucalpan");
    }

    private void startTripTimer() {
        // Simular cuenta regresiva de 5 minutos
        countDownTimer = new CountDownTimer(5 * 60 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long minutes = millisUntilFinished / 1000 / 60;
                long seconds = (millisUntilFinished / 1000) % 60;

                tvArrivalTime.setText(String.format(Locale.getDefault(),
                        "Llegada en %d min", minutes + 1));

                // Actualizar distancia (simulación)
                double distance = 2.3 - (millisUntilFinished / (5 * 60 * 1000.0)) * 2.3;
                tvDistanceRemaining.setText(String.format("%.1f km restantes", distance));
            }

            @Override
            public void onFinish() {
                tvArrivalTime.setText("¡Has llegado!");
                tvDistanceRemaining.setText("0 km restantes");
            }
        }.start();
    }

    private void setupActions() {
        Button btnArrived = findViewById(R.id.btn_arrived);
        Button btnCall = findViewById(R.id.btn_call_driver);
        Button btnMessage = findViewById(R.id.btn_message_driver);
        Button btnCancel = findViewById(R.id.btn_cancel_trip);

        btnArrived.setOnClickListener(v -> {
            // Confirmar llegada al punto de recogida
            btnArrived.setEnabled(false);
            btnArrived.setText("Esperando conductor");
            Toast.makeText(this, "Conductor notificado", Toast.LENGTH_SHORT).show();
        });

        btnCall.setOnClickListener(v -> {
            // Llamar al conductor
            Toast.makeText(this, "Llamando al conductor...", Toast.LENGTH_SHORT).show();
        });

        btnMessage.setOnClickListener(v -> {
            // Abrir chat con conductor
            Intent intent = new Intent(this, ChatActivity.class);
            intent.putExtra("DRIVER_ID", "driver_123");
            intent.putExtra("DRIVER_NAME", "Carlos Mendoza");
            startActivity(intent);
        });

        btnCancel.setOnClickListener(v -> {
            // Cancelar viaje
            showCancelConfirmation();
        });
    }

    private void showCancelConfirmation() {
        new AlertDialog.Builder(this)
                .setTitle("Cancelar Viaje")
                .setMessage("¿Estás seguro de que quieres cancelar este viaje?")
                .setPositiveButton("Sí, cancelar", (dialog, which) -> {
                    cancelTrip();
                })
                .setNegativeButton("No", null)
                .show();
    }

    private void cancelTrip() {
        countDownTimer.cancel();
        Toast.makeText(this, "Viaje cancelado", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}
