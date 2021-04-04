package com.example.sightsee.Models;

import com.example.sightsee.R;
import java.util.ArrayList;

public class Site {
    private int id;
    private String name;
    private String type;
    private int imageResourceId;
    private String address;
    private String price;

    public int getId() { return id; }
    public void setId(int id) { this.id = id;}

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public int getImageResourceId() { return imageResourceId; }
    public void setImageResourceId(int imageResourceId) {this.imageResourceId = imageResourceId;}

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPrice() { return price; }
    public void setPrice(String price) { this.price = price; }

    public Site(int id, String name, String type, int imgResource, String address, String price) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.imageResourceId = imgResource;
        this.address = address;
        this.price = price;
    }

    public static ArrayList<Site> get_test_sites() {
            ArrayList<Site> site_list = new ArrayList<Site>();
            Site sw = new Site(0, "Science World", "Museum", R.drawable.science_world, "1455 Quebec St, Vancouver BC", "$17-25");
            Site va =new Site(1, "Vancouver Aquarium", "Museum", R.drawable.vancouver_aquarium, "845 Avison Way, Vancouver BC", "$24-29");
            Site csp =new Site(2, "Capilano Suspension Park", "Hiking", R.drawable.capilano, "3735 Capilano Rd, North Vancouver BC", "$21-25");
            Site sp =new Site(3, "Stanley Park", "Trails", R.drawable.seawall, "Vancouver BC, V6G 1Z4", "Free");
            Site sts =new Site(4, "Sea to Sky", "Hiking", R.drawable.sea_to_sky, "36800 BC-99, Squamish BC", "$39");
            Site gi =new Site(5, "Granville Island", "Shopping", R.drawable.granville_island, "1689 Johnston St, Vancouver, BC V6H 3R9", "Free");
            site_list.add(sw);
            site_list.add(va);
            site_list.add(csp);
            site_list.add(sp);
            site_list.add(sts);
            site_list.add(gi);
            return site_list;
    }
}
