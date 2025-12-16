package com.example.myapplication.models;

import java.util.Date;

public class Chat {
    private String id;
    private String userId;
    private String userName;
    private String userPhotoUrl;
    private String lastMessage;
    private Date timestamp;
    private int unreadCount;

    public Chat() {}

    public Chat(String id, String userId, String userName, String userPhotoUrl,
                String lastMessage, Date timestamp, int unreadCount) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.userPhotoUrl = userPhotoUrl;
        this.lastMessage = lastMessage;
        this.timestamp = timestamp;
        this.unreadCount = unreadCount;
    }

    public Chat(String chat3, String juanPérez, String s, String ayer, boolean b, int i) {
    }

    // Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getUserPhotoUrl() { return userPhotoUrl; }
    public void setUserPhotoUrl(String userPhotoUrl) { this.userPhotoUrl = userPhotoUrl; }

    public String getLastMessage() { return lastMessage; }
    public void setLastMessage(String lastMessage) { this.lastMessage = lastMessage; }

    public Date getTimestamp() { return timestamp; }
    public void setTimestamp(Date timestamp) { this.timestamp = timestamp; }

    public int getUnreadCount() { return unreadCount; }
    public void setUnreadCount(int unreadCount) { this.unreadCount = unreadCount; }

    // Método getTime() corregido - retorna la hora del timestamp en formato HH:mm
    public String getTime() {
        if (timestamp != null) {
            // Formato: HH:mm (ejemplo: 14:30)
            return String.format("%02d:%02d",
                    timestamp.getHours(),
                    timestamp.getMinutes());
        }
        return ""; // Retorna string vacío si no hay timestamp
    }
}