package com.example.k.quizmaster;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ExpandedMenuView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<QuizClass>obj;
    ArrayList<QuizClass>sample;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=(ListView)findViewById(R.id.listview);
        obj=new ArrayList<>();
        sample=new ArrayList<>();
        QuizClass sampleObj=new QuizClass(1,"QuizName",1);
        sample.add(0,sampleObj);
        Retrofit retrofit=new Retrofit.Builder().baseUrl("https://sheetsu.com/apis/").addConverterFactory(GsonConverterFactory.create()).build();
        SheetApi sheetApi=retrofit.create(SheetApi.class);
        Call<ArrayList<QuizClass>> call =sheetApi.getQuizes();
        call.enqueue(new Callback<ArrayList<QuizClass>>() {
            @Override
            public void onResponse(Call<ArrayList<QuizClass>> call, Response<ArrayList<QuizClass>> response) {
                obj.addAll(response.body());
                QuizAdapter quizAdapter=new QuizAdapter(getApplicationContext(),obj);
                listView.setAdapter(quizAdapter);

            }

            @Override
            public void onFailure(Call<ArrayList<QuizClass>> call, Throwable t) {
                QuizAdapter quizAdapter=new QuizAdapter(getApplicationContext(),sample);
                listView.setAdapter(quizAdapter);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               Toast.makeText(getApplicationContext(),"GG",Toast.LENGTH_LONG).show();
            }
        });

    }
}
