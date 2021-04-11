package com.example.sightsee;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sightsee.Models.Site;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SiteAdapter extends ArrayAdapter<Site> {
    Context _context;
    public SiteAdapter(Context context, ArrayList<Site> sites) {
        super(context, 0, sites);
        _context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Activity activity = (Activity) _context;
        // Get the data item for this position
        Site site = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.condensed_site_details, parent, false);
        }

        // Lookup view for data population
        LinearLayout site_background = convertView.findViewById(R.id.site_background);
        if (position % 2 == 0) {


            site_background.setBackgroundColor(Color.parseColor("#BF008BF8"));
        }
        else {
            site_background.setBackgroundColor(Color.parseColor("#68B684"));
        }

        ImageView site_image = convertView.findViewById(R.id.site_image);
        TextView site_name = convertView.findViewById(R.id.site_name);
        TextView site_type = convertView.findViewById(R.id.site_type);
        TextView site_price = convertView.findViewById(R.id.site_price);

        // Populate the data into the template view using the data object


        //Glide.with(_context).load(site.getImageUrl()).into(site_image);
        Picasso.get()
                .load(site.getImageUrl())
                .into(site_image);
        //site_image.setImageResource(site.getImageUrl());
        site_name.setText(site.getName());
        site_type.setText("Type: " + site.getType());
        site_price.setText("Cost: " + site.getPrice());

        // Return the completed view to render on screen
        return convertView;
    }
}
