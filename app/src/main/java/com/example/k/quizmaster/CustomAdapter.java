package com.example.k.quizmaster;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by k on 2/23/2017.
 */

public class CustomAdapter extends ArrayAdapter<QuestionsClass> {

    ArrayList<QuestionsClass> mData;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        View v=convertView;
        if(v==null){
            v = LayoutInflater.from(this.context).inflate(R.layout.question_page, parent, false);
        }
        QuestionsClass currentQuestion=mData.get(position);
        TextView question=(TextView)v.findViewById(R.id.question);
        Button answer1=(Button) v.findViewById(R.id.answer1);
        Button answer2=(Button)v.findViewById(R.id.answer2);
        Button answer3=(Button) v.findViewById(R.id.answer3);
        Button answer4=(Button)v.findViewById(R.id.answer4);
        question.setText("Q"+/*i+*/" "+currentQuestion.getQuestion());
        answer1.setText("A"+" "+currentQuestion.getAnswer1());
        answer2.setText("B"+" "+currentQuestion.getAnswer2());
        answer3.setText("C"+" "+currentQuestion.getAnswer3());
        answer4.setText("D"+" "+currentQuestion.getAnswer4());
//        i++;
        return v;
    }
}
