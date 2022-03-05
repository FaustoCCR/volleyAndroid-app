package com.faustoc.consumoapi2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.faustoc.consumoapi2.model.StoreProducts;
import com.faustoc.consumoapi2.view.CustomAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    List<StoreProducts> lst;
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        getData();

    }

/*    private List<StoreProducts> getDatas() {
        lst = new ArrayList<>();
        StoreProducts sp = new StoreProducts();
        sp.setId(1);
        sp.setTitle("T-shirt");
        sp.setPrice(12);
        sp.setDescription("Blue T-shirt");
        sp.setCategory("Casual Clothes");
        sp.setImage(R.drawable.box_icon);
        lst.add(sp);

        return lst;
    }*/
    

    private void getData(){

        String url = "https://fakestoreapi.com/products";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                //como manejo los datos recibidos del Json
                lst = streamJson(response);
                adapter = new CustomAdapter(getApplicationContext(),lst);
                listView.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                System.out.println("Error: " + error.getMessage());
            }
        });
        Volley.newRequestQueue(this).add(jsonArrayRequest);
    }

    private List<StoreProducts>streamJson(JSONArray array){

        lst = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {

            StoreProducts products = new StoreProducts();
            JSONObject jsonObject = null;

            try {
                jsonObject = array.getJSONObject(i);
                products.setId(jsonObject.getInt("id"));
                products.setTitle(jsonObject.getString("title"));
                products.setPrice(jsonObject.getDouble("price"));
                products.setCategory(jsonObject.getString("category"));
                products.setImage(R.drawable.box_icon);

                lst.add(products);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return lst;
        //adapter.notifyDataSetChanged();
    }
}