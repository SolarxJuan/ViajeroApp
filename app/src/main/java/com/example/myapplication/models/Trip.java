package com.example.myapplication.models;

public class Trip {
    private String id;
    private String driverName;
    private String origin;
    private String destination;
    private double price;
    private float rating;
    private String date;
    private double distance; // Añadido
    private int duration;    // Añadido

    // CAMPOS ADICIONALES
    private String status;
    private String driverId;
    private String driverPhoto;
    private int seats;

    // Constructor Vacío (necesario para algunas librerías como Firebase)
    public Trip() {}

    // Constructor de 11 argumentos (el que usa tu código en las actividades, ahora funcional)
    public Trip(String id, String driverName, String origin, String destination,
                double price, float rating, String date, String status, double distance,
                int duration, String driverId) {
        this.id = id;
        this.driverName = driverName;
        this.origin = origin;
        this.destination = destination;
        this.price = price;
        this.rating = rating;
        this.date = date;
        this.status = status;
        this.distance = distance;
        this.duration = duration;
        this.driverId = driverId;

        // Asignar valores por defecto para los no incluidos
        this.driverPhoto = "";
        this.seats = 0;
    }

    // Constructor SIMPLIFICADO (7 argumentos)
    public Trip(String id, String driverName, String origin, String destination,
                double price, float rating, String date) {
        this.id = id;
        this.driverName = driverName;
        this.origin = origin;
        this.destination = destination;
        this.price = price;
        this.rating = rating;
        this.date = date;
        this.distance = 0;
        this.duration = 0;
        this.status = "PENDING";
        this.driverId = null;
        this.driverPhoto = "";
        this.seats = 0;
    }

    // Constructor COMPLETO (9 argumentos)
    public Trip(String id, String driverName, String origin, String destination,
                double price, float rating, String date, double distance, int duration) {
        this.id = id;
        this.driverName = driverName;
        this.origin = origin;
        this.destination = destination;
        this.price = price;
        this.rating = rating;
        this.date = date;
        this.distance = distance;
        this.duration = duration;
        this.status = "COMPLETED";
        this.driverId = null;
        this.driverPhoto = "";
        this.seats = 0;
    }

    // Constructor SUPER COMPLETO con todos los campos (13 argumentos)
    public Trip(String id, String driverName, String origin, String destination,
                double price, float rating, String date, double distance, int duration,
                String status, String driverId, String driverPhoto, int seats) {
        this.id = id;
        this.driverName = driverName;
        this.origin = origin;
        this.destination = destination;
        this.price = price;
        this.rating = rating;
        this.date = date;
        this.distance = distance;
        this.duration = duration;
        this.status = status;
        this.driverId = driverId;
        this.driverPhoto = driverPhoto;
        this.seats = seats;
    }

    // Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getDriverName() { return driverName; }
    public void setDriverName(String driverName) { this.driverName = driverName; }

    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public float getRating() { return rating; }
    public void setRating(float rating) { this.rating = rating; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public double getDistance() { return distance; }
    public void setDistance(double distance) { this.distance = distance; }

    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }

    // Campos Adicionales
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getDriverId() { return driverId; }
    public void setDriverId(String driverId) { this.driverId = driverId; }

    public String getDriverPhoto() { return driverPhoto; }
    public void setDriverPhoto(String driverPhoto) { this.driverPhoto = driverPhoto; }

    public int getSeats() { return seats; }
    public void setSeats(int seats) { this.seats = seats; }

    // Método que parece ser usado por tu código (User Rating)
    public float getUserRating() { return rating; }
    public void setUserRating(float rating) { this.rating = rating; }
}