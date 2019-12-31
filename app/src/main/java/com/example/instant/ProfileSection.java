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

public class ProfileSection extends AppCompatActivity {
    private FusedLocationProviderClient fusedLocationClient;
    ImageView image;
    TextView name,email,id,locate;
    EditText mobile_number,address,guardian_name,guardian_mobilenumber,guardian_email,y_profession,y_age;
    Button update,show;
    GoogleSignInClient mGoogleSignInClient;
    DatabaseReference reff;
    long maxid=0;
    Member member;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_section);
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
        mobile_number=findViewById(R.id.mobilenumber);
        address=findViewById(R.id.current_address);
        guardian_name=findViewById(R.id.guar_name);
        guardian_mobilenumber=findViewById(R.id.guar_number);
        guardian_email=findViewById(R.id.guar_email);
        y_profession=findViewById(R.id.profession);
        update=findViewById(R.id.update_profile);
        y_age=findViewById(R.id.age);
        show=findViewById(R.id.show_profile);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        if (acct != null) {
            String personName = acct.getDisplayName();

            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();


            Glide.with(this).load(String.valueOf(personPhoto)).into(image);
            name.setText(personName);
            email.setText(personEmail);


        }

        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            Toast.makeText(ProfileSection.this,"Fetched location",Toast.LENGTH_SHORT).show();

                        }
                    }
                });
        member=new Member();

        reff = FirebaseDatabase.getInstance().getReference().child("Member");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                    maxid=(dataSnapshot.getChildrenCount());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int yourage=Integer.parseInt(y_age.getText().toString().trim());
                Long yourphn=Long.parseLong(mobile_number.getText().toString().trim());
                Long guarphn=Long.parseLong(guardian_mobilenumber.getText().toString().trim());
                member.setCurrent_address(address.getText().toString().trim());
                member.setGuar_email(guardian_email.getText().toString().trim());
                member.setGuar_name(guardian_name.getText().toString().trim());
                member.setYour_name(name.getText().toString().trim());
                member.setYour_email(email.getText().toString().trim());
                member.setProfession(y_profession.getText().toString().trim());
                member.setGuar_mobile(guarphn);
                member.setYour_number(yourphn);
                member.setYour_age(yourage);
                reff.child(String.valueOf(maxid+1)).setValue(member);
                Toast.makeText(ProfileSection.this,"Your Profile Updated Successfully",Toast.LENGTH_SHORT).show();

            }
        });
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ProfileSection.this, ShowProfile.class);
                startActivity(intent);
                Toast.makeText(ProfileSection.this,"Showing Profile Information",Toast.LENGTH_SHORT).show();

            }
        });
    }





}
