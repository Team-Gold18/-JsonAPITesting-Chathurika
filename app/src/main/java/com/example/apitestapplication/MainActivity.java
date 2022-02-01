package com.example.apitestapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt = findViewById(R.id.t_view);

        //Retrofit Builder
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://run.mocky.io/v3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //instance for instance
        APICall apiCall = retrofit.create(APICall.class);
        Call<DataModel> call = apiCall.getDate();

        call.enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {

                //Checking for the response
                if (response.code() !=200){
                    txt.setText("Check the connection");
                    return;
                }

                //Get data into textView
                String jsony = "";

                jsony = "ID" + response.body().getId() +
                        "\n userId= "+response.body().getUserId() +
                        "\ntitle= " +response.body().getTitle() +
                        "\nCompleted= " +response.body().isCompleted();

                txt.append(jsony);

            }

            @Override
            public void onFailure(Call<DataModel> call, Throwable t) {

            }
        });

    }
}