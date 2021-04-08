package com.example.sightsee.Models;

import com.example.sightsee.R;
import java.util.ArrayList;

public class Site {
    private String name;
    private String type;
    private String imageUrl;
    private String address;
    private String price;

    public Site() {
        // required empty constructor
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) {this.imageUrl = imageUrl;}

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPrice() { return price; }
    public void setPrice(String price) { this.price = price; }

    public Site(String name, String type, String imgResource, String address, String price) {
        this.name = name;
        this.type = type;
        this.imageUrl = imgResource;
        this.address = address;
        this.price = price;
    }
}
