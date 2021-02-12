package com.example.sightsee.Models;

public class Site {
    private String name;
    private String type;
    private int imageResourceId;
    private String address;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public int getImageResourceId() { return imageResourceId; }
    public void setImageResourceId(int imageResourceId) {this.imageResourceId = imageResourceId;}

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public Site(String name, String type, int imgResource, String address) {
        this.name = name;
        this.type = type;
        this.imageResourceId = imgResource;
        this.address = address;
    }
}
