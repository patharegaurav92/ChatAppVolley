package edu.student.android.chatappvolley;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import edu.student.android.chatappvolley.features.OwnACarpool;
import edu.student.android.chatappvolley.features.ViewYourCarpool;

/**
 * Created by Gaurav on 31-03-2017.
 */

public class Home extends AppCompatActivity {
TextView chat,updateProfile,own,view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        chat = (TextView) findViewById(R.id.chat);
        updateProfile = (TextView) findViewById(R.id.updateprofile);
        own = (TextView)  findViewById(R.id.ownacarpool);
        view = (TextView) findViewById(R.id.viewcarpool);
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this,Users.class));
            }
        });
        updateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this,ViewProfile.class);
                i.putExtra("status","0");
                startActivity(i);
            }
        });
        own.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, OwnACarpool.class));
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, ViewYourCarpool.class));
            }
        });
        //new kClosestLocations();

    }
}
