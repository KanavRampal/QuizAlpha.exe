package com.example.k.quizmaster;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
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
     ArrayList<QuestionsClass> questionsClassObj;
    ArrayList<QuestionsClass> sample;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_page);
        sample=new ArrayList<>();
        questionsClassObj=new ArrayList<>();
        QuestionsClass obj= new QuestionsClass(1,"it is a question","yes","no","may be","maybe not");
        sample.add(0,obj);
        Retrofit retrofit=new Retrofit.Builder().baseUrl("https://sheetsu.com/apis/").addConverterFactory(GsonConverterFactory.create()).build();
        SheetApi sheetApi=retrofit.create(SheetApi.class);
        Call<ArrayList<QuestionsClass>>call =sheetApi.getQuestions();
        call.enqueue(new Callback<ArrayList<QuestionsClass>>() {
            @Override
            public void onResponse(Call<ArrayList<QuestionsClass>> call, Response<ArrayList<QuestionsClass>> response) {
                questionsClassObj.addAll(response.body());
                CustomAdapter adapter  =new CustomAdapter(getApplicationContext(),questionsClassObj);
                listView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ArrayList<QuestionsClass>> call, Throwable t) {
                CustomAdapter adapter  =new CustomAdapter(getApplicationContext(),sample);
                listView.setAdapter(adapter);
                Toast.makeText(getApplicationContext(),"Network Not Working",Toast.LENGTH_SHORT).show();
            }
        });
        listView= (ListView) findViewById(R.id.listview);



    }
}
