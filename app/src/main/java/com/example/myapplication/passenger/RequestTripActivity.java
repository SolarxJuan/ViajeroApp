package com.example.myapplication.passenger;


import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputLayout;
import com.example.myapplication.R;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class RequestTripActivity extends AppCompatActivity {

    private EditText etOrigin, etDestination, etNotes;
    private TextView tvDateTime, tvSeats, tvPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_trip);

        initViews();
        setupDateTime();
    }

    private void initViews() {
        etOrigin = findViewById(R.id.et_origin);
        etDestination = findViewById(R.id.et_destination);
        etNotes = findViewById(R.id.et_notes);
        tvDateTime = findViewById(R.id.tv_date_time);
        tvSeats = findViewById(R.id.tv_seats);
        tvPrice = findViewById(R.id.tv_price);

        Button btnMinus = findViewById(R.id.btn_minus);
        Button btnPlus = findViewById(R.id.btn_plus);
        Button btnRequest = findViewById(R.id.btn_request_trip);

        btnMinus.setOnClickListener(v -> {
            int seats = Integer.parseInt(tvSeats.getText().toString());
            if (seats > 1) {
                seats--;
                tvSeats.setText(String.valueOf(seats));
                updatePrice(seats);
            }
        });

        btnPlus.setOnClickListener(v -> {
            int seats = Integer.parseInt(tvSeats.getText().toString());
            if (seats < 4) {
                seats++;
                tvSeats.setText(String.valueOf(seats));
                updatePrice(seats);
            }
        });

        btnRequest.setOnClickListener(v -> {
            // Lógica para solicitar viaje
            requestTrip();
        });
    }

    private void setupDateTime() {
        String currentDateTime = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
                .format(new Date());
        tvDateTime.setText(currentDateTime);
    }

    private void updatePrice(int seats) {
        double basePrice = 50.00;
        double total = basePrice * seats;
        tvPrice.setText(String.format("$%.2f MXN", total));
    }

    private void requestTrip() {
        String origin = etOrigin.getText().toString().trim();
        String destination = etDestination.getText().toString().trim();
        String notes = etNotes.getText().toString().trim();
        int seats = Integer.parseInt(tvSeats.getText().toString());

        // Validaciones
        if (origin.isEmpty() || destination.isEmpty()) {
            // Mostrar error
            return;
        }

        // Aquí llamarías a tu API para solicitar el viaje
        // Después de éxito, regresar al home o mostrar confirmación
        finish();
    }
}
