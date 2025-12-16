package com.example.myapplication.models;

import java.util.Date;

public class Message {
    private String id;
    private String senderId;
    private String senderName;
    private String content;
    private Date timestamp;
    private String type; // "sent" o "received"

    // Constructor VACÍO (requerido para Firebase)
    public Message() {}

    // Constructor con TODOS los parámetros (6)
    public Message(String id, String senderId, String senderName,
                   String content, Date timestamp, String type) {
        this.id = id;
        this.senderId = senderId;
        this.senderName = senderName;
        this.content = content;
        this.timestamp = timestamp;
        this.type = type;
    }

    // AÑADE ESTE CONSTRUCTOR (5 parámetros) para que funcione con ChatActivity
    public Message(String id, String senderId, Date timestamp,
                   String content, String type) {
        this.id = id;
        this.senderId = senderId;
        this.timestamp = timestamp;
        this.content = content;
        this.type = type;
        this.senderName = ""; // Valor por defecto
    }

    // Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getSenderId() { return senderId; }
    public void setSenderId(String senderId) { this.senderId = senderId; }

    public String getSenderName() { return senderName; }
    public void setSenderName(String senderName) { this.senderName = senderName; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public Date getTimestamp() { return timestamp; }
    public void setTimestamp(Date timestamp) { this.timestamp = timestamp; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
}