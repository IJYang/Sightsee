package com.example.sightsee;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sightsee.Models.Promotion;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PromotionAdapter extends ArrayAdapter<Promotion> {

    Context _context;

    public PromotionAdapter(Context context, ArrayList<Promotion> promotions) {
        super(context, 0, promotions);
        _context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Activity activity = (Activity) _context;
        Promotion promotion = getItem(position);

        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.promotion_list, parent, false);

        // Inflate the promo list
//        tv_promoTitle, tv_promoPicture
        TextView tv_promoTitle = convertView.findViewById(R.id.tv_promoTitle);
        ImageView iv_promoPicture = convertView.findViewById(R.id.iv_promoPicture);

        tv_promoTitle.setText(promotion.getPromotionTitle());
        iv_promoPicture.setImageResource(promotion.getImageResourceId());

        return convertView;
    }


}
