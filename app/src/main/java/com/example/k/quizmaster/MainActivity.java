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
    QuestionsClass[] sample;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_page);
        sample=new QuestionsClass[1];
        sample[0]=new QuestionsClass(1,"it is a question","yes","no","may be","maybe not");
        Retrofit retrofit=new Retrofit.Builder().baseUrl("https://sheetsu.com/apis/").addConverterFactory(GsonConverterFactory.create()).build();
        SheetApi sheetApi=retrofit.create(SheetApi.class);
        Call<QuestionClassResponseClass>call =sheetApi.getQuestions();
        call.enqueue(new Callback<QuestionClassResponseClass>() {
            @Override
            public void onResponse(Call<QuestionClassResponseClass> call, Response<QuestionClassResponseClass> response) {
                Toast.makeText(getApplicationContext(),response.body().getQuestionClassResponseClassObject().size(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<QuestionClassResponseClass> call, Throwable t) {

            }
        });
        listView= (ListView) findViewById(R.id.listview);
        CustomAdapter adapter  =new CustomAdapter(this,sample);
        listView.setAdapter(adapter);
    }
}
