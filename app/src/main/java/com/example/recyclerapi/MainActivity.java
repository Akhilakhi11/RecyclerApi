package com.example.recyclerapi;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.name)
    TextView name;
    @Nullable
   @BindView(R.id.listrv)
     RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);


        Interface_Api methods = RetrofitClient.getRetrofitInstance().create(Interface_Api.class);
        Call<ProductListresponse>call = methods.getAllData();
        call.enqueue(new Callback<ProductListresponse>() {
            @Override
            public void onResponse(Call<ProductListresponse> call, Response<ProductListresponse> response) {
                if (response.isSuccessful()){
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerView.setLayoutManager(linearLayoutManager);
                    MyAdapter myAdapter = new MyAdapter(MainActivity.this,response.body().getData() );
                    recyclerView.setAdapter(myAdapter);
                    Log.i("response", String.valueOf(response.body().getData()));


                }
            }

            @Override
            public void onFailure(Call<ProductListresponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}