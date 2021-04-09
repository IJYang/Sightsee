package com.example.sightsee;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.sightsee.Models.Comment;
import com.example.sightsee.Models.CommentUpload;
import com.example.sightsee.Models.Promotion;
import com.example.sightsee.PromotionActivity;
import com.example.sightsee.Models.Site;
import com.example.sightsee.Models.Upload;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class SiteDetailActivity extends AppCompatActivity implements OnMapReadyCallback{
    private DatabaseReference mDatabaseRef;
    private DatabaseReference databaseCases;
    private Button moreCommentsBtn;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expanded_site_details);

        // GOOGLE MAPS
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        //

        String name = (String) getIntent().getExtras().get("name");
        String address = (String) getIntent().getExtras().get("address");
        String imageUrl = (String) getIntent().getExtras().get("imageUrl");
        int position = (Integer) getIntent().getExtras().get("position");
        String type = (String) getIntent().getExtras().get("site_type");
        String price = (String) getIntent().getExtras().get("price");

        ScrollView expanded_site_detail = findViewById(R.id.expanded_site_background);
        if (position % 2 == 0) {
            expanded_site_detail.setBackgroundColor(Color.parseColor("#CB0093D7"));
        }
        else {
            expanded_site_detail.setBackgroundColor(Color.parseColor("#9C24C82A"));
        }

        ImageView photo = findViewById(R.id.siteImage);
        //Glide.with(getApplicationContext()).load(imageUrl).into(photo);

        Picasso.get()
                .load(imageUrl)
                .into(photo);

        //photo.setImageResource(R.drawable.capilano);
//        photo.setContentDescription(food.toString());

        // Display product information
        TextView nametv = findViewById(R.id.siteName);
        nametv.setText(name);

        TextView addresstv = findViewById(R.id.siteAddress);
        TextView typetv = findViewById(R.id.siteType);
        TextView pricetv = findViewById(R.id.sitePrice);

        addresstv.setText("Address: " + address);
        typetv.setText("Type: " + type);
        pricetv.setText("Price: " + price);

        loadComments();
        loadPromotions();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        ScrollView expanded_site_detail = findViewById(R.id.expanded_site_background);
        mMap = googleMap;
        String queried_address = (String) getIntent().getExtras().get("address");
        // Add a marker in Sydney and move the camera
        LatLng selected_site = new LatLng(49.246292, -123.116226);
        mMap.addMarker(new MarkerOptions()
                .position(selected_site)
                .title("Site"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(selected_site, 10.0f));
        expanded_site_detail.smoothScrollTo(0, 0);
    }

    public void loadComments() {
        ArrayList<Comment> first_comment = new ArrayList<Comment>();
        String site_id = (String) getIntent().getExtras().get("site_id");
        ListView lvCommentList = findViewById(R.id.lv_singleComment);
        databaseCases = FirebaseDatabase.getInstance().getReference();
        databaseCases.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                first_comment.clear();
                for (DataSnapshot caseSnapshot: dataSnapshot.child("comments").getChildren()) {
                    Comment comment = caseSnapshot.getValue(Comment.class);
                    if (comment.getSite_id().equals(site_id)) {
                        first_comment.add(comment);
                        break;
                    }
                }
                CommentAdapter adapter = new CommentAdapter(SiteDetailActivity.this, first_comment);
                lvCommentList.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });

        CommentAdapter adapter = new CommentAdapter(SiteDetailActivity.this, first_comment);
        lvCommentList.setEmptyView(findViewById(android.R.id.empty));
        lvCommentList.setAdapter(adapter);
    }


    public void loadPromotions() {
//        List<Promotion> fullPromoList = Promotion.get_test_promotions().stream()
//                //.filter(promotion -> promotion.getSiteId() == siteId)
//                .collect(Collectors.toList());
//
//        if (fullPromoList.size() > 0) {
//            ListView lvPromoList = findViewById(R.id.lv_singlePromotion);
//            ArrayList<Promotion> onlyPromotion = new ArrayList<Promotion>(fullPromoList.subList(0, 1));
//            PromotionAdapter promotionAdapter = new PromotionAdapter(SiteDetailActivity.this, onlyPromotion);
//            lvPromoList.setAdapter(promotionAdapter);
//
//        } else {
//            // If no promos
//        }
    }


    public void moreComments(View view) {
        String site_id = (String) getIntent().getExtras().get("site_id");
        Intent intent = new Intent(SiteDetailActivity.this, CommentsActivity.class);
        intent.putExtra("site_id", site_id);
        startActivity(intent);
    }

    public void morePromotions(View view) {
        String site_id = (String) getIntent().getExtras().get("site_id");
        Intent intent = new Intent(SiteDetailActivity.this, PromotionActivity.class);
        intent.putExtra("site_id", String.valueOf(site_id));
        startActivity(intent);
    }

    public void addPromotion(View view) {
        String siteId = (String) getIntent().getExtras().get("site_id");
        Intent intent = new Intent(SiteDetailActivity.this, AddPromotionActivity.class);
        intent.putExtra("siteId", String.valueOf(siteId));
        startActivity(intent);
    }

    public void submitComment(View view) {
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("comments");
        EditText comment_edit_text = findViewById(R.id.comment_edit_text);
        String user_id = (String) getIntent().getExtras().get("user_id");
        String site_id = (String) getIntent().getExtras().get("site_id");
        String comment_text = comment_edit_text.getText().toString().trim();
        String capitalized_user_id = user_id.substring(0, 1).toUpperCase() + user_id.substring(1);

        if (comment_text.trim().equals("")) {
            Toast.makeText(SiteDetailActivity.this, "No comment provided", Toast.LENGTH_SHORT).show();
        }
        else {
            CommentUpload comment_upload = new CommentUpload(capitalized_user_id.trim(),
                    site_id.trim(),
                    comment_text.trim());
            String uploadId = mDatabaseRef.push().getKey();
            mDatabaseRef.child(uploadId).setValue(comment_upload);
            Toast.makeText(SiteDetailActivity.this, "Comment added!", Toast.LENGTH_SHORT).show();
            comment_edit_text.setText("");
        }
    }

    public LatLng determineLatLngFromAddress(Context appContext, String strAddress) {
        LatLng latLng = null;
        Geocoder geocoder = new Geocoder(appContext, Locale.getDefault());
        List<Address> geoResults = null;

        try {
            geoResults = geocoder.getFromLocationName(strAddress, 1);
            while (geoResults.size()==0) {
                geoResults = geocoder.getFromLocationName(strAddress, 1);
            }
            if (geoResults.size()>0) {
                Address addr = geoResults.get(0);
                latLng = new LatLng(addr.getLatitude(),addr.getLongitude());
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }

        return latLng; //LatLng value of address
    }

}
