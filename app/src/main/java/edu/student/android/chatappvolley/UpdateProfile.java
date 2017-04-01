package edu.student.android.chatappvolley;

import android.content.Intent;
import android.os.Bundle;
import android.service.voice.VoiceInteractionSession;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.firebase.client.Firebase;

import org.json.JSONException;
import org.json.JSONObject;

import static android.R.attr.name;
import static android.R.id.edit;
import static edu.student.android.chatappvolley.FireObjects.url;

/**
 * Created by Gaurav on 31-03-2017.
 */

public class UpdateProfile  extends AppCompatActivity {

    EditText username, password,phone;
    Button update;
    String email, userpass;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);


        username = (EditText)findViewById(R.id.newusername);
        password = (EditText) findViewById(R.id.newpassword);
        phone = (EditText) findViewById(R.id.new_phone_number);
        update = (Button) findViewById(R.id.registerButton);
        email = UserDetails.useremail;
        Firebase.setAndroidContext(this);

        //To Populate the fields

        StringRequest request = new StringRequest(Request.Method.GET, FireObjects.url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Firebase ref = FireObjects.ref;
                if (response.equals(null)) {
                    Toast.makeText(UpdateProfile.this, "user not found", Toast.LENGTH_LONG).show();
                } else {
                    try {
                        JSONObject obj = new JSONObject(response);

                        if(obj.has(email)){

                            username.setText(obj.getJSONObject(email).getString("name"));
                            phone.setText(obj.getJSONObject(email).getString("phone"));
                            password.setText(obj.getJSONObject(email).getString("password"));
                        }else{
                            Toast.makeText(UpdateProfile.this, "user not found", Toast.LENGTH_LONG).show();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue rQueue = Volley.newRequestQueue(UpdateProfile.this);
        rQueue.add(request);





        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = UserDetails.useremail;



                StringRequest request = new StringRequest(Request.Method.GET, FireObjects.url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Firebase ref = FireObjects.ref;
                        if (response.equals(null)) {
                            Toast.makeText(UpdateProfile.this, "user not found", Toast.LENGTH_LONG).show();
                        } else {
                            try {
                                String uname = username.getText().toString(),pass = password.getText().toString(),ph=phone.getText().toString();
                                JSONObject obj = new JSONObject(response);
                                System.out.println(email);
                                if(obj.has(email)){
                                    System.out.println("We are in!");
                                            updateUserDetails(uname,pass,ph);
                                    ref.child(email).child("name").setValue(uname);
                                    ref.child(email).child("password").setValue(pass);
                                    ref.child(email).child("phone").setValue(ph);
                                    Toast.makeText(UpdateProfile.this, "Updated Profile", Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(UpdateProfile.this,ViewProfile.class));
                                    finish();
                                }else{
                                    Toast.makeText(UpdateProfile.this, "user not found", Toast.LENGTH_LONG).show();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }


                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                RequestQueue rQueue = Volley.newRequestQueue(UpdateProfile.this);
                rQueue.add(request);
            }
        });


    }

    private void updateUserDetails(String uname, String pass, String ph) {
        UserDetails.username=uname;
        UserDetails.password=pass;
    }
}
