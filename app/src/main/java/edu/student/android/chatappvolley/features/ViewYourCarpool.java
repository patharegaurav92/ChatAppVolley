package edu.student.android.chatappvolley.features;

import android.app.Activity;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import edu.student.android.chatappvolley.Chat;
import edu.student.android.chatappvolley.FireObjects;
import edu.student.android.chatappvolley.R;
import edu.student.android.chatappvolley.Register;
import edu.student.android.chatappvolley.UserDetails;

public class ViewYourCarpool extends AppCompatActivity {
    Geocoder gc;
    FirebaseDatabase firebaseDatabase;
    List<Address> addresses= null;
    String date,slocation,dlocation;
    ArrayList<Carpool> carpool = new ArrayList<>();
    CarpoolDetailsAdapter carpoolDetailsAdapter;
    ListView listView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carpool_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listView = (ListView) findViewById(R.id.list);
        final String email = UserDetails.useremail;
        gc = new Geocoder(this, Locale.US);

        /*firebaseDatabase.getInstance().getReference().child("own").child(email).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                  CarpoolDetails carpoolDetails = snapshot.getValue(CarpoolDetails.class);
                    date = carpoolDetails.getDate();
                    double slat = carpoolDetails.getSlatitude();
                    double slon = carpoolDetails.getSlongitude();
                   slocation = getLocation(slat,slon);
                    double dlat = carpoolDetails.getDlatitude();
                    double dlon = carpoolDetails.getDlongitude();
                    dlocation = getLocation(dlat,dlon);
                    //System.out.println(date);
                    addToArrayList();

                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/
        String viewCarpool = "https://carpoolapp-2ec11.firebaseio.com/own/"+email+".json";
        StringRequest request = new StringRequest(Request.Method.GET, viewCarpool, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                doThis(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue rQueue = Volley.newRequestQueue(ViewYourCarpool.this);
        rQueue.add(request);



    }

    private void doThis(String response) {
        if(!response.equals("null")){
            try {
                JSONObject obj = new JSONObject(response);
                Iterator<String> iter = obj.keys();
                while (iter.hasNext()) {
                    String key = iter.next();
                    try {
                        JSONObject value = (JSONObject) obj.get(key);
                        date = value.getString("date");
                        double slat = value.getDouble("slatitude");
                        double slon = value.getDouble("slongitude");
                        slocation = getLocation(slat,slon);
                        double dlat = value.getDouble("dlatitude");
                        double dlon = value.getDouble("dlongitude");
                        dlocation = getLocation(dlat,dlon);
                        System.out.println(slocation+" / "+dlocation+" / "+date);
                        carpool.add(new Carpool(slocation,dlocation,date));
                    } catch (JSONException e) {
                        // Something went wrong!
                    }

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else
        {
            Toast.makeText(ViewYourCarpool.this, "No Rides", Toast.LENGTH_SHORT).show();
        }

       carpoolDetailsAdapter = new CarpoolDetailsAdapter(getApplicationContext(),carpool);
        listView.setAdapter(carpoolDetailsAdapter);
    }


    public String getLocation(double lat, double lon){


        String s= null;
        try {

            addresses = gc.getFromLocation(lat,lon,15);
          s= addresses.get(0).getAddressLine(0);


        } catch (IOException e) {
            e.printStackTrace();
        }

        return s;
    }
    public static String EncodeString(String string) {
        return string.replace(".", ",");
    }
    public static String DecodeString(String string) {
        return string.replace(",", ".");
    }


}
