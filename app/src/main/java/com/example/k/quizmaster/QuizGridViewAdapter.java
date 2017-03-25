package com.example.k.quizmaster;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ashukaushik on 24/03/17.
 */

public class QuizGridViewAdapter extends ArrayAdapter<String> {
    //    ADAPTER FOR THE GRIDVIEW LAYOUT ON MAIN ACTIVITY COMMITTED

    Context context;
    ArrayList<String> mData;

    public QuizGridViewAdapter(Context context, ArrayList<String> objects) {
        super(context,0, objects);
        mData=objects;
        this.context=context;
    }

    @Override
    public boolean isEnabled(int position) {
        if(position < 2)
            return true;
        else
            return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v=convertView;
        if(v==null){
            v= LayoutInflater.from(context).inflate(R.layout.quiz_grid_view_adapter_layout,parent,false);
        }
        TextView quizName=(TextView)v.findViewById(R.id.quizNumberTextView);

        quizName.setText(getItem(position).toString());
        return v;
    }
}
