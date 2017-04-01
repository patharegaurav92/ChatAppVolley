package edu.student.android.chatappvolley;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

/**
 * Created by Gaurav on 31-03-2017.
 */

public class Register extends AppCompatActivity {
    EditText username, password,phone,email;
    Button registerButton;
    String useremail, userpass;
    TextView login;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        phone = (EditText) findViewById(R.id.phone_number);
        email = (EditText) findViewById(R.id.email);
        registerButton = (Button)findViewById(R.id.registerButton);
        login = (TextView)findViewById(R.id.login);

        Firebase.setAndroidContext(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this,Login.class));
            }
        });
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 useremail = EncodeString(email.getText().toString());
                userpass = password.getText().toString();
                final ProgressDialog pd = new ProgressDialog(Register.this);
                pd.setMessage("Loading...");
                pd.show();



                StringRequest request = new StringRequest(Request.Method.GET, FireObjects.url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Firebase ref = FireObjects.ref;
                        if(response.equals("null")) {
                            ref.child(useremail).child("password").setValue(userpass);
                            ref.child(useremail).child("phone").setValue(phone.getText().toString());
                            ref.child(useremail).child("name").setValue(username.getText().toString());
                            Toast.makeText(Register.this, "registration successful", Toast.LENGTH_LONG).show();
                        }
                        else {
                            try {
                                JSONObject obj = new JSONObject(response);

                                if (!obj.has(useremail)) {
                                    ref.child(useremail).child("password").setValue(userpass);
                                    ref.child(useremail).child("phone").setValue(phone.getText().toString());
                                    ref.child(useremail).child("name").setValue(username.getText().toString());
                                    Toast.makeText(Register.this, "registration successful", Toast.LENGTH_SHORT).show();
                                    finish();
                                } else {
                                    Toast.makeText(Register.this, "username already exists", Toast.LENGTH_LONG).show();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        pd.dismiss();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("" + error );
                        pd.dismiss();
                    }
                });
                RequestQueue rQueue = Volley.newRequestQueue(Register.this);
                rQueue.add(request);
            }
        });

    }
    public static String EncodeString(String string) {
        return string.replace(".", ",");
    }
    public static String DecodeString(String string) {
        return string.replace(",", ".");
    }

}
