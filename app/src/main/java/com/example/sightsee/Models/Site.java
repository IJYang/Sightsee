package com.example.sightsee.Models;

import com.example.sightsee.R;
import java.util.ArrayList;

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

    public static ArrayList<Site> get_test_sites() {
            ArrayList<Site> site_list = new ArrayList<Site>();
            Site sw = new Site("Science World", "Museum", R.drawable.science_world, "1455 Quebec St, Vancouver BC");
            Site va =new Site("Vancouver Aquarium", "Museum", R.drawable.vancouver_aquarium, "845 Avison Way, Vancouver BC");
            Site csp =new Site("Capilano Suspension Park", "Hiking", R.drawable.capilano, "3735 Capilano Rd, North Vancouver BC");
            Site sp =new Site("Stanley Park", "Trails", R.drawable.seawall, "Vancouver BC, V6G 1Z4");
            Site sts =new Site("Sea to Sky", "Hiking", R.drawable.sea_to_sky, "36800 BC-99, Squamish BC");
            site_list.add(sw);
            site_list.add(va);
            site_list.add(csp);
            site_list.add(sp);
            site_list.add(sts);
            return site_list;
    }
}
