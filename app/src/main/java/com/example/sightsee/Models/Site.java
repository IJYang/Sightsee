package com.example.sightsee.Models;

import com.example.sightsee.R;
import java.util.ArrayList;

public class Site {
    private int id;
    private String name;
    private String type;
    private int imageResourceId;
    private String address;

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

    public Site(int id, String name, String type, int imgResource, String address) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.imageResourceId = imgResource;
        this.address = address;
    }

    public static ArrayList<Site> get_test_sites() {
            ArrayList<Site> site_list = new ArrayList<Site>();
            Site sw = new Site(0, "Science World", "Museum", R.mipmap.science_world, "1455 Quebec St, Vancouver BC");
            Site va =new Site(1, "Vancouver Aquarium", "Museum", R.mipmap.vancouver_aquarium, "845 Avison Way, Vancouver BC");
            Site csp =new Site(2, "Capilano Suspension Park", "Hiking", R.mipmap.capilano, "3735 Capilano Rd, North Vancouver BC");
            Site sp =new Site(3, "Stanley Park", "Trails", R.mipmap.seawall, "Vancouver BC, V6G 1Z4");
            Site sts =new Site(4, "Sea to Sky", "Hiking", R.mipmap.sea_to_sky, "36800 BC-99, Squamish BC");
            site_list.add(sw);
            site_list.add(va);
            site_list.add(csp);
            site_list.add(sp);
            site_list.add(sts);
            return site_list;
    }
}
