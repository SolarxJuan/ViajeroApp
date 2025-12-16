package com.example.myapplication.models;

public class User {
    private String id;
    private String name;
    private String email;
    private String phone;
    private String photoUrl;
    private String userType; // "passenger" o "driver"
    private float rating;
    private int totalTrips;
    private String memberSince; // CAMBIADO de Date a String
    private String vehicleModel;
    private String vehicleYear;
    private String vehicleColor;
    private String vehiclePlate;
    private String vehiclePhotoUrl;

    // Constructores
    public User() {}

    // Constructor con memberSince como String
    public User(String id, String name, String email, String phone,
                String photoUrl, String userType, float rating, int totalTrips,
                String memberSince) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.photoUrl = photoUrl;
        this.userType = userType;
        this.rating = rating;
        this.totalTrips = totalTrips;
        this.memberSince = memberSince;
    }

    // Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getPhotoUrl() { return photoUrl; }
    public void setPhotoUrl(String photoUrl) { this.photoUrl = photoUrl; }

    public String getUserType() { return userType; }
    public void setUserType(String userType) { this.userType = userType; }

    public float getRating() { return rating; }
    public void setRating(float rating) { this.rating = rating; }

    public int getTotalTrips() { return totalTrips; }
    public void setTotalTrips(int totalTrips) { this.totalTrips = totalTrips; }

    // CORREGIDO: Ahora getter y setter usan String
    public String getMemberSince() { return memberSince; }
    public void setMemberSince(String memberSince) { this.memberSince = memberSince; }

    public String getVehicleModel() { return vehicleModel; }
    public void setVehicleModel(String vehicleModel) { this.vehicleModel = vehicleModel; }

    public String getVehicleYear() { return vehicleYear; }
    public void setVehicleYear(String vehicleYear) { this.vehicleYear = vehicleYear; }

    public String getVehicleColor() { return vehicleColor; }
    public void setVehicleColor(String vehicleColor) { this.vehicleColor = vehicleColor; }

    public String getVehiclePlate() { return vehiclePlate; }
    public void setVehiclePlate(String vehiclePlate) { this.vehiclePlate = vehiclePlate; }

    public String getVehiclePhotoUrl() { return vehiclePhotoUrl; }
    public void setVehiclePhotoUrl(String vehiclePhotoUrl) { this.vehiclePhotoUrl = vehiclePhotoUrl; }
}