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

    public static ArrayList<Site> get_test_sites() {
            ArrayList<Site> site_list = new ArrayList<Site>();
            Site sw = new Site("Science World", "Museum","Science World", "1455 Quebec St, Vancouver BC", "$17-25");
            Site va =new Site("Vancouver Aquarium", "Museum", "t", "845 Avison Way, Vancouver BC", "$24-29");
            Site csp =new Site("Capilano Suspension Park", "Hiking", "t", "3735 Capilano Rd, North Vancouver BC", "$21-25");
            Site sp =new Site("Stanley Park", "Trails", "t", "Vancouver BC, V6G 1Z4", "Free");
            Site sts =new Site( "Sea to Sky", "Hiking", "t", "36800 BC-99, Squamish BC", "$39");
            Site gi =new Site( "Granville Island", "Shopping", "t", "1689 Johnston St, Vancouver, BC V6H 3R9", "Free");
            site_list.add(sw);
            site_list.add(va);
            site_list.add(csp);
            site_list.add(sp);
            site_list.add(sts);
            site_list.add(gi);
            return site_list;
    }
}
