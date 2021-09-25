package com.example.jsonparse;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

        Button parseButton;
        TextView txtName,txtAge,txtCar;
        String SAWAN_URL = "https://gist.githubusercontent.com/drewhystereo/849260d157baaaeb1793ea3c7ba52664/raw/796e62fe768cb621a29afb828ebc0d7fd52f397f/sawan.json";
        RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        parseButton =findViewById(R.id.button);
        txtAge = findViewById(R.id.txt_age);
        txtCar = findViewById(R.id.txt_car);
        txtName = findViewById(R.id.txt_name);


        requestQueue = Volley.newRequestQueue(this);
        parseButton.setOnClickListener(v -> {
            sawanParse();
        });


    }

    private void sawanParse() {

        JsonObjectRequest sawanRequest = new JsonObjectRequest(Request.Method.GET, SAWAN_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                  String name = response.getString("name");
                  String age = response.getString("age");
                  String car = response.getString("car");
                    txtName.setText(name);
                    txtAge.setText(age);
                    txtCar.setText(car);
                    Toast.makeText(getApplicationContext(), "parsed successfully", Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(sawanRequest);
    }
}