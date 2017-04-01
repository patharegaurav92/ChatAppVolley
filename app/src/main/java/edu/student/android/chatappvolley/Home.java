package edu.student.android.chatappvolley;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.firebase.client.Firebase;

/**
 * Created by Gaurav on 31-03-2017.
 */

public class Home extends AppCompatActivity {
TextView chat,updateProfile;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        chat = (TextView) findViewById(R.id.chat);
        updateProfile = (TextView) findViewById(R.id.updateprofile);
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this,Users.class));
            }
        });
        updateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this,ViewProfile.class));
            }
        });

    }
}
