//package com.example.k.quizmaster;
//
//import android.content.Context;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.TextView;
//
//import java.util.ArrayList;
//
///**
// * Created by k on 3/26/2017.
// */
//
//public class QuizAdapter extends ArrayAdapter<QuizClass>{
//    ArrayList<QuizClass> mData;
//    Context context;
//    public QuizAdapter(Context context, ArrayList<QuizClass> objects){
//        super(context,0,objects);
//        mData=objects;
//        this.context=context;
//    }
//
//    @Override
//    public int getCount() {
//        return mData.size();
//    }
//
//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        View v=convertView;
//        if(v==null){
//            v = LayoutInflater.from(this.context).inflate(R.layout.quiz_layout, parent, false);
//        }
//        QuizClass currentQuiz=mData.get(position);
//        TextView quizName=(TextView) v.findViewById(R.id.quizName);
//        quizName.setText(currentQuiz.getQuizName());
//        return v;
//    }
//
//}
