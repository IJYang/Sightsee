package com.example.sightsee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity {

    public DatabaseReference mDatabase;
    public String uid, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_profile);

        mDatabase = FirebaseDatabase.getInstance().getReference();


        TextView tv_email = (TextView) findViewById(R.id.tv_email);
        tv_email.setText("Loading...");

        TextView tv_userType = (TextView) findViewById(R.id.tv_userType);
        tv_userType.setText("Loading...");

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            email = user.getEmail();
            tv_email.setText(email);

            uid = user.getUid();
        } else {
            tv_userType.setText("Error");
            tv_email.setText("Error");

        }

        mDatabase.child("users").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
                    DataSnapshot t = task.getResult();
                    HashMap<String, String> users = (HashMap) t.getValue();
                    for (Map.Entry mapElement : users.entrySet()) {
                        String key = (String)mapElement.getKey();

                        HashMap<String, String> u = (HashMap) mapElement.getValue();
                        String user_email = (String) u.get("user_email");
                        String user_type = (String) u.get("user_type");

                        if (user_email.equals(email)){
                            tv_userType.setText(user_type);
                        }
                    }
                }
            }
        });
    }
}