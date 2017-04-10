package com.example.k.quizmaster;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by k on 2/23/2017.
 */

public class CustomAdapter extends ArrayAdapter<QuestionsClass> {

    ArrayList<QuestionsClass> mData;
    ArrayList<String> manswerArrayListCheck = Singleton.Singleton().getList();
    Context context;
    public CustomAdapter(Context context,ArrayList<QuestionsClass> objects){
        super(context,0,objects);
        mData=objects;
        this.context=context;
    }

//    int i=1;


    @Override
    public int getCount() {
        return mData.size();
    }
    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
//        View v=convertView;   NOW THIS IS WORKING FINE AND SCORING IS WORKING PERFECTLY COMMITTED
//        if(v==null){
          View  v = LayoutInflater.from(this.context).inflate(R.layout.question_page, parent, false);
//        }
        QuestionsClass currentQuestion=mData.get(position);
        TextView question=(TextView)v.findViewById(R.id.question);
        final Button answer1=(Button) v.findViewById(R.id.answer1);
        final Button answer2=(Button)v.findViewById(R.id.answer2);
        final Button answer3=(Button) v.findViewById(R.id.answer3);
        final Button answer4=(Button)v.findViewById(R.id.answer4);
        question.setText(currentQuestion.getQuestion());
        answer1.setText(currentQuestion.getAnswer1());
        answer2.setText(currentQuestion.getAnswer2());
        answer3.setText(currentQuestion.getAnswer3());
        answer4.setText(currentQuestion.getAnswer4());

        //  IMPLEMENTED ONCLICKS FOR SCORING OF THE QUIZ COMMITTED

        answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Singleton.Singleton().getList().add(position, answer1.getText().toString());
                answer2.setEnabled(false);
                answer3.setEnabled(false);
                answer4.setEnabled(false);
            }
        });

        answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Singleton.Singleton().getList().add(position, answer2.getText().toString());
                answer1.setEnabled(false);
                answer3.setEnabled(false);
                answer4.setEnabled(false);
            }
        });
        answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Singleton.Singleton().getList().add(position, answer3.getText().toString());
                answer2.setEnabled(false);
                answer1.setEnabled(false);
                answer4.setEnabled(false);
            }
        });
        answer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Singleton.Singleton().getList().add(position, answer4.getText().toString());
                answer2.setEnabled(false);
                answer3.setEnabled(false);
                answer1.setEnabled(false);
            }
        });
//        i++;
        return v;
    }

}
