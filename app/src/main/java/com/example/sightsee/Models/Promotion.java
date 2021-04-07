package com.example.sightsee.Models;

import com.example.sightsee.R;

import java.util.ArrayList;

public class Promotion {
    private String promotionId;
    private int siteId;
    private String promotionTitle;
    private String image_url;

    public String getPromotionId() { return this.promotionId; }
    public void setPromotionId(String promotionId) { this.promotionId = promotionId; }

    public int getSiteId() { return siteId; }
    public void setSiteId(int siteId) { this.siteId = siteId; }

    public String getPromotionTitle() { return this.promotionTitle; }
    public void setPromotionTitle(String promotionTitle) { this.promotionTitle = promotionTitle; }

    public String getImage_url() { return this.image_url; }
    public void setImage_url(String image_url) { this.image_url = image_url; }

    public Promotion(String promotionId, int siteId, String promotionTitle, String image_url) {
        this.promotionId = promotionId;
        this.siteId = siteId;
        this.promotionTitle = promotionTitle;
        this.image_url = image_url;
    }

    public static ArrayList<Promotion> get_test_promotions() {
        ArrayList<Promotion> promo_list = new ArrayList<Promotion>();
//        Promotion promo1 = new Promotion("1", 0, "Science world promo 1", R.drawable.qr_code);
//        promo_list.add(promo1);
//
//        Promotion promo2 = new Promotion("2", 0, "Science world promo 2", R.drawable.qr_code2);
//        promo_list.add(promo2);
//
//        Promotion promo3 = new Promotion("3", 1, "Aquarium promo 1", R.drawable.qr_code3);
//        promo_list.add(promo3);
//
//        Promotion promo4 = new Promotion("4", 1, "Aquarium promo 2", R.drawable.qr_code4);
//        promo_list.add(promo4);
//
//        Promotion promo5 = new Promotion("5", 2, "Capilano promo 1", R.drawable.qr_code5);
//        promo_list.add(promo5);
//
//        Promotion promo6 = new Promotion("6", 3, "Stanley Park promo 1", R.drawable.qr_code6);
//        promo_list.add(promo6);
//
//        Promotion promo7 = new Promotion("7", 3, "Stanley Park promo 2", R.drawable.qr_code7);
//        promo_list.add(promo7);
//
//        Promotion promo8 = new Promotion("8", 4, "Sea to Sky promo 1", R.drawable.qr_code8);
//        promo_list.add(promo8);

        return promo_list;
    }

}
