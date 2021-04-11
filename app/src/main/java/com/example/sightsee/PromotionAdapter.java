package com.example.sightsee;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.sightsee.Models.Comment;
import com.example.sightsee.Models.Promotion;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class PromotionAdapter extends ArrayAdapter<Promotion> {

    Context _context;

    public PromotionAdapter(Context context, ArrayList<Promotion> comments) {
        super(context, 0, comments);
        _context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Activity activity = (Activity) _context;

        Promotion promotion = getItem(position);

        if (convertView == null) {
            convertView =
                    LayoutInflater
                    .from(getContext())
                    .inflate(R.layout.promotion_list, parent, false);
        }

        TextView tv_title = convertView.findViewById(R.id.tv_promoTitle);
        TextView tv_details = convertView.findViewById(R.id.tv_promoDetails);

        tv_title.setText(promotion.getPromotionTitle());
        tv_details.setText(promotion.getPromotionDetail());

        return convertView;
    }
}
