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

//    LAYOUT OF MAIN ACTIVITY CHANGED, I THINK IT WOULD BE BETTER COMMITTED

    GridView mGridView;
    QuizGridViewAdapter quizGridViewAdapter;
    ArrayList<String> quizNumberArrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGridView = (GridView)findViewById(R.id.quizGridView);
        for(int i=1;i<=5;i++){
            quizNumberArrayList.add(i+"");
        }

        quizGridViewAdapter = new QuizGridViewAdapter(this, quizNumberArrayList);
        mGridView.setAdapter(quizGridViewAdapter);

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, QuestionPageActivity.class);
                startActivity(intent);
            }
        });

    }
}
