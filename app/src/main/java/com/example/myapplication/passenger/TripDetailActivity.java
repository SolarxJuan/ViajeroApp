package com.example.myapplication.passenger;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;
import com.example.myapplication.models.Trip;
import com.example.myapplication.utils.RatingUtils;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class TripDetailActivity extends AppCompatActivity {

    private Trip trip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_detail);

        String tripId = getIntent().getStringExtra("TRIP_ID");
        loadTripDetails(tripId);
        initViews();
        setupRatingStars();
    }

    private void loadTripDetails(String tripId) {
        // En una app real, esto vendría de una base de datos o API
        // Aquí creamos datos de ejemplo
        trip = new Trip(
                tripId,
                "Centro Comercial",
                "Av. Insurgentes Sur 1602, CDMX",
                "Centro Comercial Perisur, CDMX",
                120.50,
                4.5f,
                "Carlos Mendoza",
                "completed",
                8.3,
                25,
                "2023-03-15 14:00"
        );
    }

    private void initViews() {
        // Configurar datos del viaje
        TextView tvTripTitle = findViewById(R.id.tv_trip_title);
        TextView tvTripDate = findViewById(R.id.tv_trip_date);
        TextView tvOrigin = findViewById(R.id.tv_origin);
        TextView tvDestination = findViewById(R.id.tv_destination);
        TextView tvDistance = findViewById(R.id.tv_distance);
        TextView tvDuration = findViewById(R.id.tv_duration);
        TextView tvCost = findViewById(R.id.tv_cost);
        TextView tvPaymentMethod = findViewById(R.id.tv_payment_method);
        TextView tvDriverName = findViewById(R.id.tv_driver_name);
        TextView tvDriverRating = findViewById(R.id.tv_driver_rating);
        TextView tvVehicleInfo = findViewById(R.id.tv_vehicle_info);

        tvTripTitle.setText("Viaje a " + trip.getDestination());

        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
            SimpleDateFormat outputFormat = new SimpleDateFormat("dd 'de' MMMM, yyyy - HH:mm", new Locale("es", "ES"));
            String formattedDate = outputFormat.format(inputFormat.parse(trip.getDate()));
            tvTripDate.setText(formattedDate);
        } catch (Exception e) {
            tvTripDate.setText(trip.getDate());
        }

        tvOrigin.setText(trip.getOrigin());
        tvDestination.setText(trip.getDestination());
        tvDistance.setText(String.format("%.1f km", trip.getDistance()));
        tvDuration.setText(String.format("%d min", trip.getDuration()));
        tvCost.setText(String.format("$%.2f MXN", trip.getPrice()));
        tvPaymentMethod.setText("Tarjeta ****4582");
        tvDriverName.setText(trip.getDriverName());
        tvDriverRating.setText(String.format("%.1f", trip.getRating()));
        tvVehicleInfo.setText("Toyota Corolla 2019 - ABC1234");

        // Configurar botones
        Button btnCall = findViewById(R.id.btn_call);
        Button btnMessage = findViewById(R.id.btn_message);
        Button btnViewReceipt = findViewById(R.id.btn_view_receipt);
        Button btnEmailReceipt = findViewById(R.id.btn_email_receipt);
        Button btnReport = findViewById(R.id.btn_report);

        btnCall.setOnClickListener(v -> makeCall());
        btnMessage.setOnClickListener(v -> openChat());
        btnViewReceipt.setOnClickListener(v -> viewReceipt());
        btnEmailReceipt.setOnClickListener(v -> emailReceipt());
        btnReport.setOnClickListener(v -> reportProblem());

        // Configurar calificación del usuario
        setupUserRating();
    }

    private void setupRatingStars() {
        LinearLayout ratingContainer = findViewById(R.id.rating_container);
        RatingUtils.setRatingStars(ratingContainer, trip.getRating());
    }

    private void setupUserRating() {
        LinearLayout userRatingContainer = findViewById(R.id.user_rating_container);
        Button btnSubmitRating = findViewById(R.id.btn_submit_rating);

        // Si el viaje ya fue calificado, mostrar calificación
        if (trip.getUserRating() > 0) {
            RatingUtils.setRatingStars(userRatingContainer, trip.getUserRating());
            btnSubmitRating.setVisibility(View.GONE);
        } else {
            // Configurar estrellas interactivas
            for (int i = 0; i < userRatingContainer.getChildCount(); i++) {
                ImageView star = (ImageView) userRatingContainer.getChildAt(i);
                final int rating = i + 1;

                star.setOnClickListener(v -> {
                    setUserRating(rating);
                    RatingUtils.setRatingStars(userRatingContainer, rating);
                });
            }

            btnSubmitRating.setOnClickListener(v -> submitRating());
        }
    }

    private void setUserRating(int rating) {
        trip.setUserRating(rating);
    }

    private void submitRating() {
        if (trip.getUserRating() > 0) {
            // Enviar calificación al servidor
            Toast.makeText(this, "¡Gracias por calificar!", Toast.LENGTH_SHORT).show();
            findViewById(R.id.btn_submit_rating).setVisibility(View.GONE);
        }
    }

    private void makeCall() {
        // Lógica para llamar al conductor
        Toast.makeText(this, "Llamando al conductor...", Toast.LENGTH_SHORT).show();
    }

    private void openChat() {
        Intent intent = new Intent(this, ChatActivity.class);
        intent.putExtra("DRIVER_ID", trip.getDriverId());
        intent.putExtra("DRIVER_NAME", trip.getDriverName());
        startActivity(intent);
    }

    private void viewReceipt() {
        // Mostrar recibo completo
        Toast.makeText(this, "Mostrando recibo...", Toast.LENGTH_SHORT).show();
    }

    private void emailReceipt() {
        // Enviar recibo por email
        Toast.makeText(this, "Enviando recibo por email...", Toast.LENGTH_SHORT).show();
    }

    private void reportProblem() {
        // Reportar problema
        Toast.makeText(this, "Reportando problema...", Toast.LENGTH_SHORT).show();
    }
}
