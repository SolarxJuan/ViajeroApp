package com.example.myapplication.passenger;



import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.myapplication.R;
import com.example.myapplication.models.User;
import java.io.IOException;

public class EditProfileActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private EditText etName, etPhone, etEmail, etVehicleModel, etVehicleYear, etVehicleColor, etVehiclePlate;
    private ImageView ivProfilePhoto, ivVehiclePhoto;
    private User currentUser;
    private Uri profileImageUri, vehicleImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        loadUserData();
        initViews();
        setupBottomNavigation();
    }

    private void loadUserData() {
        // Cargar datos actuales del usuario
        currentUser = new User();
        currentUser.setName("Alejandro Martínez");
        currentUser.setPhone("+52 555 123 4567");
        currentUser.setEmail("alejandro.martinez@email.com");
        currentUser.setVehicleModel("Toyota Corolla");
        currentUser.setVehicleYear("2019");
        currentUser.setVehicleColor("Gris Plata");
        currentUser.setVehiclePlate("1234 ABC");
    }

    private void initViews() {
        // Referencias a las vistas
        etName = findViewById(R.id.et_name);
        etPhone = findViewById(R.id.et_phone);
        etEmail = findViewById(R.id.et_email);
        etVehicleModel = findViewById(R.id.et_vehicle_model);
        etVehicleYear = findViewById(R.id.et_vehicle_year);
        etVehicleColor = findViewById(R.id.et_vehicle_color);
        etVehiclePlate = findViewById(R.id.et_vehicle_plate);

        ivProfilePhoto = findViewById(R.id.iv_profile_photo);
        ivVehiclePhoto = findViewById(R.id.iv_vehicle_photo);

        // Cargar datos actuales
        etName.setText(currentUser.getName());
        etPhone.setText(currentUser.getPhone());
        etEmail.setText(currentUser.getEmail());
        etVehicleModel.setText(currentUser.getVehicleModel());
        etVehicleYear.setText(currentUser.getVehicleYear());
        etVehicleColor.setText(currentUser.getVehicleColor());
        etVehiclePlate.setText(currentUser.getVehiclePlate());

        // Configurar botones de foto
        Button btnChangeProfilePhoto = findViewById(R.id.btn_change_profile_photo);
        Button btnChangeVehiclePhoto = findViewById(R.id.btn_change_vehicle_photo);
        Button btnSave = findViewById(R.id.btn_save);
        Button btnCancel = findViewById(R.id.btn_cancel);

        btnChangeProfilePhoto.setOnClickListener(v -> openImagePicker("profile"));
        btnChangeVehiclePhoto.setOnClickListener(v -> openImagePicker("vehicle"));

        btnSave.setOnClickListener(v -> saveProfile());
        btnCancel.setOnClickListener(v -> finish());
    }

    private void openImagePicker(String type) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);

        // Guardar el tipo para usar en onActivityResult
        if (type.equals("profile")) {
            startActivityForResult(Intent.createChooser(intent, "Selecciona foto de perfil"), PICK_IMAGE_REQUEST);
        } else {
            startActivityForResult(Intent.createChooser(intent, "Selecciona foto del vehículo"), PICK_IMAGE_REQUEST + 1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);

                if (requestCode == PICK_IMAGE_REQUEST) {
                    profileImageUri = imageUri;
                    ivProfilePhoto.setImageBitmap(bitmap);
                } else if (requestCode == PICK_IMAGE_REQUEST + 1) {
                    vehicleImageUri = imageUri;
                    ivVehiclePhoto.setImageBitmap(bitmap);
                }
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Error al cargar la imagen", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void saveProfile() {
        // Validar campos
        String name = etName.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String vehicleModel = etVehicleModel.getText().toString().trim();
        String vehicleYear = etVehicleYear.getText().toString().trim();
        String vehicleColor = etVehicleColor.getText().toString().trim();
        String vehiclePlate = etVehiclePlate.getText().toString().trim();

        if (name.isEmpty() || phone.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Por favor completa los campos obligatorios", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!isValidEmail(email)) {
            Toast.makeText(this, "Ingresa un email válido", Toast.LENGTH_SHORT).show();
            return;
        }

        // Aquí guardarías los datos en tu backend/database
        // Por ahora solo mostramos un mensaje
        currentUser.setName(name);
        currentUser.setPhone(phone);
        currentUser.setEmail(email);
        currentUser.setVehicleModel(vehicleModel);
        currentUser.setVehicleYear(vehicleYear);
        currentUser.setVehicleColor(vehicleColor);
        currentUser.setVehiclePlate(vehiclePlate);

        // Subir imágenes si fueron seleccionadas
        if (profileImageUri != null) {
            // uploadImage(profileImageUri, "profile");
        }

        if (vehicleImageUri != null) {
            // uploadImage(vehicleImageUri, "vehicle");
        }

        Toast.makeText(this, "Perfil actualizado correctamente", Toast.LENGTH_SHORT).show();
        finish();
    }

    private boolean isValidEmail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void setupBottomNavigation() {
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setSelectedItemId(R.id.nav_profile);
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
            intent = new Intent(this, TripHistoryActivity.class);
        } else if (itemId == R.id.nav_map) {
            // Implementar mapa
            return;
        } else if (itemId == R.id.nav_chat) {
            intent = new Intent(this, ChatListActivity.class);
        } else if (itemId == R.id.nav_profile) {
            return; // Ya estamos en perfil
        }

        if (intent != null) {
            startActivity(intent);
            finish();
        }
    }
}
