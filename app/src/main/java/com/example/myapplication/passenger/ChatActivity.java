package com.example.myapplication.passenger;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;  // ← AÑADIDO ESTE IMPORT
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.R;
import com.example.myapplication.adapters.MessageAdapter;
import com.example.myapplication.models.Message;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private RecyclerView rvMessages;
    private MessageAdapter messageAdapter;
    private EditText etMessage;
    private ImageButton btnSend;
    private TextView tvUserName;
    private String chatId;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        // Obtener datos del intent
        chatId = getIntent().getStringExtra("CHAT_ID");
        userName = getIntent().getStringExtra("USER_NAME");

        if (chatId == null) {
            chatId = "default_chat";
        }
        if (userName == null) {
            userName = "Conductor";
        }

        initViews();
        loadMessages();
    }

    @SuppressLint("WrongViewCast")
    private void initViews() {
        tvUserName = findViewById(R.id.tv_user_name);
        rvMessages = findViewById(R.id.rv_messages);
        etMessage = findViewById(R.id.et_message);
        btnSend = findViewById(R.id.btn_send);

        tvUserName.setText(userName);

        // Configurar RecyclerView
        rvMessages.setLayoutManager(new LinearLayoutManager(this));
        messageAdapter = new MessageAdapter(new ArrayList<>());
        rvMessages.setAdapter(messageAdapter);

        // Configurar botón de enviar
        btnSend.setOnClickListener(v -> sendMessage());

        // Configurar botón de retroceso
        ImageButton btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(v -> finish());

        // Configurar botón de llamada
        @SuppressLint("WrongViewCast") ImageButton btnCall = findViewById(R.id.btn_call);
        btnCall.setOnClickListener(v -> {
            // Lógica para llamar
            Toast.makeText(this, "Llamando a " + userName, Toast.LENGTH_SHORT).show();
        });
    }

    private void loadMessages() {
        List<Message> messages = new ArrayList<>();

        // Datos de ejemplo - CORREGIDO: Usar constructor de 5 parámetros
        messages.add(new Message(
                "msg_1",
                "Hola " + userName + ", estoy llegando al punto de encuentro. Estoy en un auto blanco.",
                new Date(System.currentTimeMillis() - 3600000), // 1 hora atrás
                "sent",
                chatId
        ));

        messages.add(new Message(
                "msg_2",
                "Hola, perfecto. Estoy esperando en la esquina con una chaqueta roja.",
                new Date(System.currentTimeMillis() - 3500000),
                "received",
                chatId
        ));

        messages.add(new Message(
                "msg_3",
                "Te veo justo ahora.",
                new Date(System.currentTimeMillis() - 3400000),
                "sent",
                chatId
        ));

        messages.add(new Message(
                "msg_4",
                "¡Genial, ya te vi! Voy para allá.",
                new Date(System.currentTimeMillis() - 3300000),
                "received",
                chatId
        ));

        messages.add(new Message(
                "msg_5",
                "¡Todo bien con el viaje hasta ahora!",
                new Date(System.currentTimeMillis() - 1000000),
                "sent",
                chatId
        ));

        messages.add(new Message(
                "msg_6",
                "El viaje perfecto. Gracias por preguntar. ¿Cuánto tiempo estimado tenemos hasta llegar?",
                new Date(System.currentTimeMillis() - 500000),
                "received",
                chatId
        ));

        messages.add(new Message(
                "msg_7",
                "Sí, como 15 minutos más. El tráfico está ligero.",
                new Date(System.currentTimeMillis() - 10000),
                "sent",
                chatId
        ));

        messageAdapter.updateMessages(messages);
        scrollToBottom();
    }

    private void sendMessage() {
        String messageText = etMessage.getText().toString().trim();

        if (!TextUtils.isEmpty(messageText)) {
            // Crear nuevo mensaje
            Message newMessage = new Message(
                    "msg_" + System.currentTimeMillis(),
                    messageText,
                    new Date(),
                    "sent",
                    chatId
            );

            // Agregar a la lista
            messageAdapter.addMessage(newMessage);

            // Limpiar campo de texto
            etMessage.setText("");

            // Desplazar al final
            scrollToBottom();

            // Aquí enviarías el mensaje a tu backend/servidor
            // sendMessageToServer(newMessage);

            // Simular respuesta automática después de 2 segundos
            simulateReply(messageText);
        }
    }

    private void simulateReply(String userMessage) {
        // Simular una respuesta automática después de 2 segundos
        new android.os.Handler().postDelayed(() -> {
            String reply = getAutoReply(userMessage);

            Message replyMessage = new Message(
                    "msg_reply_" + System.currentTimeMillis(),
                    reply,
                    new Date(),
                    "received",
                    chatId
            );

            messageAdapter.addMessage(replyMessage);
            scrollToBottom();
        }, 2000);
    }

    private String getAutoReply(String userMessage) {
        // Lógica simple de respuesta automática
        userMessage = userMessage.toLowerCase();

        if (userMessage.contains("hola") || userMessage.contains("buenos")) {
            return "¡Hola! ¿En qué puedo ayudarte?";
        } else if (userMessage.contains("gracias")) {
            return "¡De nada!";
        } else if (userMessage.contains("llegar") || userMessage.contains("tiempo")) {
            return "Aproximadamente 10-15 minutos más";
        } else if (userMessage.contains("precio") || userMessage.contains("costo")) {
            return "El precio estimado es de $120 MXN";
        } else {
            return "Entendido, gracias por tu mensaje.";
        }
    }

    private void scrollToBottom() {
        rvMessages.post(() -> {
            if (messageAdapter.getItemCount() > 0) {
                rvMessages.smoothScrollToPosition(messageAdapter.getItemCount() - 1);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Aquí podrías conectarte a un WebSocket para mensajes en tiempo real
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Desconectar de WebSocket si es necesario
    }
}