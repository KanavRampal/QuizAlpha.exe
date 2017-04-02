package com.example.k.quizmaster;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity {

    String score="";
    TextView mresultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        score = getIntent().getStringExtra("score");
        mresultTextView = (TextView)findViewById(R.id.resultTextView);
        mresultTextView.setText("Your final Score is "+score+".");
    }
}
