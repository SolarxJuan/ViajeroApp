package com.example.myapplication.passenger;



import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.myapplication.R;
import com.example.myapplication.adapters.ChatListAdapter;
import com.example.myapplication.models.Chat;
import java.util.ArrayList;
import java.util.List;

public class ChatListActivity extends AppCompatActivity {

    private RecyclerView rvChatList;
    private ChatListAdapter chatListAdapter;
    private EditText etSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);

        initViews();
        setupBottomNavigation();
        loadChats();
    }

    private void initViews() {
        rvChatList = findViewById(R.id.rv_chat_list);
        etSearch = findViewById(R.id.et_search);
        ImageView btnSearch = findViewById(R.id.btn_search);

        // Configurar RecyclerView
        rvChatList.setLayoutManager(new LinearLayoutManager(this));
        chatListAdapter = new ChatListAdapter(new ArrayList<>(), chat -> {
            openChat(chat);
        });
        rvChatList.setAdapter(chatListAdapter);

        // Configurar búsqueda
        btnSearch.setOnClickListener(v -> {
            String query = etSearch.getText().toString().trim();
            searchChats(query);
        });
    }

    private void loadChats() {
        List<Chat> chats = new ArrayList<>();

        // Datos de ejemplo
        chats.add(new Chat(
                "chat_1",
                "Carlos Mendoza",
                "Gracias por el viaje, fue muy puntual.",
                "10:30",
                true,
                2
        ));

        chats.add(new Chat(
                "chat_2",
                "María González",
                "¿A qué hora pasarás a recogerme mañana?",
                "09:15",
                false,
                0
        ));

        chats.add(new Chat(
                "chat_3",
                "Juan Pérez",
                "Perfecto, nos vemos el viernes entonces.",
                "Ayer",
                false,
                0
        ));

        chats.add(new Chat(
                "chat_4",
                "Ana García",
                "Yo estoy en el punto de encuentro",
                "Ayer",
                false,
                1
        ));

        chats.add(new Chat(
                "chat_5",
                "Laura Martínez",
                "¿Tienes disponibilidad para un viaje semanal?",
                "15/03",
                false,
                0
        ));

        chatListAdapter.updateChats(chats);
    }

    private void searchChats(String query) {
        if (query.isEmpty()) {
            loadChats();
        } else {
            // Filtrar chats por nombre
            List<Chat> filteredChats = new ArrayList<>();
            // Lógica de filtrado
            chatListAdapter.updateChats(filteredChats);
        }
    }

    private void openChat(Chat chat) {
        Intent intent = new Intent(this, ChatActivity.class);
        intent.putExtra("CHAT_ID", chat.getId());
        intent.putExtra("USER_NAME", chat.getUserName());
        startActivity(intent);
    }

    private void setupBottomNavigation() {
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setSelectedItemId(R.id.nav_chat);
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
            return; // Ya estamos aquí
        } else if (itemId == R.id.nav_profile) {
            intent = new Intent(this, PassengerProfileActivity.class);
        }

        if (intent != null) {
            startActivity(intent);
            finish();
        }
    }
}
