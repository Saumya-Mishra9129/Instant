package com.example.instant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ShowProfile extends AppCompatActivity {

    ImageView image;
    TextView name,email,id,locate;
    TextView mobile_number,address,guardian_name,guardian_mobilenumber,guardian_email,y_profession,y_age;

    GoogleSignInClient mGoogleSignInClient;
    DatabaseReference reff;

    Member member;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_profile);
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions
                .Builder()
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this,googleSignInOptions);
        image=findViewById(R.id.imageprofile);
        name= findViewById(R.id.username);
        email=findViewById(R.id.emailid);
        locate=findViewById(R.id.location);
        mobile_number=findViewById(R.id.s_y_num);
        address=findViewById(R.id.s_address);
        guardian_name=findViewById(R.id.s_guardianName);
        guardian_mobilenumber=findViewById(R.id.s_guardianNumber);
        guardian_email=findViewById(R.id.s_guardianEmail);
        y_profession=findViewById(R.id.s_profession);

        y_age=findViewById(R.id.s_age);


        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);

        if (acct != null) {
            String personName = acct.getDisplayName();

            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();


            Glide.with(this).load(String.valueOf(personPhoto)).into(image);
            name.setText(personName);
            email.setText(personEmail);


        }


        member=new Member();

        reff = FirebaseDatabase.getInstance().getReference().child("Member").child("1");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                y_age.setText((String)dataSnapshot.child("your_age").getValue().toString());
                guardian_name.setText((String)dataSnapshot.child("guar_name").getValue().toString());
                guardian_email.setText((String)dataSnapshot.child("guar_email").getValue().toString());
                guardian_mobilenumber.setText((String)dataSnapshot.child("guar_mobile").getValue().toString());
                mobile_number.setText((String)dataSnapshot.child("your_number").getValue().toString());
                address.setText((String)dataSnapshot.child("current_address").getValue().toString());
                y_profession.setText((String)dataSnapshot.child("profession").getValue().toString());



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }





}
