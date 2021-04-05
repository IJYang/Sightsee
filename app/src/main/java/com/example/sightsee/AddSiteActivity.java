package com.example.sightsee;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class AddSiteActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private EditText mEditTextSiteName;
    private EditText mEditTextSiteType;
    private EditText mEditTextSiteAddress;
    private EditText mEditTextSitePrice;
    private Button mButtonChooseImage;
    private Button mButtonAddNewSite;
    private ImageView mImageViewSiteImage;
    private Uri mImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_site);

        mEditTextSiteName = findViewById(R.id.site_name);
        mEditTextSiteType = findViewById(R.id.site_type);
        mEditTextSiteAddress = findViewById(R.id.site_address);
        mEditTextSitePrice = findViewById(R.id.site_price);

        mButtonChooseImage = findViewById(R.id.add_image_button);
        mButtonAddNewSite = findViewById(R.id.add_new_site_button);

        mImageViewSiteImage = findViewById(R.id.site_image_display);

        mButtonChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();

            }
        });

        mButtonAddNewSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            mImageUri = data.getData();
            mImageViewSiteImage.setImageURI(mImageUri);
        }
    }
}