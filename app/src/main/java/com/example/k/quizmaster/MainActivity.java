package com.example.k.quizmaster;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
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
    ArrayList<QuestionsClass> questionsClassObj;
    ArrayList<QuestionsClass> sample;
    Button button;
    TextView mtimerTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_page);

        mtimerTextView = (TextView)findViewById(R.id.timerTextView);

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

                //QUESTIONS ARE COMING PERFECTLY  COMMITTED
                questionsClassObj.addAll(response.body());
                CustomAdapter adapter  =new CustomAdapter(getApplicationContext(),questionsClassObj);
                listView.setAdapter(adapter);

                // ADDED TIMER IN QUIZ  COMMITTED
                new CountDownTimer(10000,1000){
                    public void onTick(long millisUntilFinished) {
                        mtimerTextView.setText("seconds remaining: " + millisUntilFinished / 1000);
                    }

                    public void onFinish() {
                        mtimerTextView.setText("OVER!");
                        Toast.makeText(MainActivity.this, "QUIZ OVER", Toast.LENGTH_LONG).show();
                    }
                }.start();

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
