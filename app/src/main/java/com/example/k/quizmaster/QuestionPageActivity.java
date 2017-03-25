package com.example.k.quizmaster;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Console;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QuestionPageActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<QuestionsClass> questionsClassObj;
    ArrayList<QuestionsClass> sample;
    Button button,mcancelButton,msubmitButton;
    TextView mtimerTextView;
    Boolean aBoolean = true;
    ArrayList<String> manswerArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_page);

        listView = (ListView)findViewById(R.id.listview);
        mtimerTextView = (TextView)findViewById(R.id.timerTextView);
        mcancelButton = (Button)findViewById(R.id.cancelButton);
        msubmitButton = (Button)findViewById(R.id.submitButton);


        sample = new ArrayList<>();
        questionsClassObj = new ArrayList<>();
        QuestionsClass obj = new QuestionsClass(1,"it is a question","yes","no","may be","maybe not");
        sample.add(0,obj);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://sheetsu.com/apis/").addConverterFactory(GsonConverterFactory.create()).build();
        SheetApi sheetApi = retrofit.create(SheetApi.class);
        Call<ArrayList<QuestionsClass>> call = sheetApi.getQuestions();
        call.enqueue(new Callback<ArrayList<QuestionsClass>>() {
            @Override
            public void onResponse(Call<ArrayList<QuestionsClass>> call, Response<ArrayList<QuestionsClass>> response) {

                //QUESTIONS ARE COMING PERFECTLY  COMMITTED
                questionsClassObj.addAll(response.body());
                CustomAdapter adapter = new CustomAdapter(getApplicationContext(), questionsClassObj);
                listView.setAdapter(adapter);

                for(int i=0;i<questionsClassObj.size();i++){
                    manswerArrayList.add(questionsClassObj.get(i).answer);
                }

                // ADDED TIMER IN QUIZ  COMMITTED
                new CountDownTimer(60000,1000){
                    public void onTick(long millisUntilFinished) {
                        mtimerTextView.setText("TIME LEFT: " + millisUntilFinished / 1000 + " secs");
                    }

                    public void onFinish() {
                        Toast.makeText(QuestionPageActivity.this, "QUIZ OVER", Toast.LENGTH_LONG).show();
                        Intent i=new Intent();
                        i.setClass(QuestionPageActivity.this, ResultActivity.class);
                        startActivity(i);
                        finish();
                    }
                }.start();

            }

            @Override
            public void onFailure(Call<ArrayList<QuestionsClass>> call, Throwable t) {
                CustomAdapter adapter = new CustomAdapter(getApplicationContext(),sample);
                listView.setAdapter(adapter);
                Toast.makeText(getApplicationContext(),"Network Not Working",Toast.LENGTH_SHORT).show();
            }

        });



        msubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog=new AlertDialog.Builder(QuestionPageActivity.this);
                dialog.setTitle("SUBMIT");
                dialog.setMessage("Are you sure you want to submit?");
                dialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i=new Intent();
                        i.setClass(QuestionPageActivity.this, ResultActivity.class);
                        startActivity(i);
                        finish();
                    }
                });
                dialog.setNegativeButton("NO", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                dialog.create().show();
            }
        });

        mcancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog=new AlertDialog.Builder(QuestionPageActivity.this);
                dialog.setTitle("CANCEL");
                dialog.setMessage("Are you sure you want to cancel?");
                dialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

                dialog.setNegativeButton("NO", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                dialog.create().show();
            }
        });


    }
}
