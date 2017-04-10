package com.example.k.quizmaster;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity {

    String score="";
    TextView mresultTextView;
    TextView mcorrectAnswersTextView;
    TextView mincorrectAnswersTextView;

    int mCorrectAnswers=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        score = getIntent().getStringExtra("score");
        mCorrectAnswers = Integer.parseInt(score)/10;

        mresultTextView = (TextView)findViewById(R.id.resultTextView);
        mcorrectAnswersTextView = (TextView)findViewById(R.id.correctQuestionsTextView);
        mincorrectAnswersTextView = (TextView)findViewById(R.id.incorrectQuestionsTextView);

//      SCORING IS WORKING PERFECTLY COMMITTED
        mresultTextView.setText("Your final Score is "+score+".");
        mcorrectAnswersTextView.setText("Correct :"+mCorrectAnswers);
        mincorrectAnswersTextView.setText("Incorrect :"+(10-mCorrectAnswers));
    }
}
