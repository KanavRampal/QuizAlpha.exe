package com.example.k.quizmaster;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

//    LAYOUT OF MAIN ACTIVITY CHANGED, I THINK IT WOULD BE BETTER COMMITTED

    GridView mGridView;
    QuizGridViewAdapter quizGridViewAdapter;
    ArrayList<String> quizNumberArrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGridView = (GridView)findViewById(R.id.quizGridView);
        for(int i=1;i<=5;i++){
            quizNumberArrayList.add(i+"");
        }

        quizGridViewAdapter = new QuizGridViewAdapter(this, quizNumberArrayList);
        mGridView.setAdapter(quizGridViewAdapter);

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, QuestionPageActivity.class);
                startActivity(intent);
            }
        });

    }

    //  MENU CREATED AND COMMITTED

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.item1){
            Intent i = new Intent();
            //  ABOUT US ACTIVITY COMPLETELY CREATED AND COMMITTED
            i.setClass(MainActivity.this, AboutUsActivity.class);
            startActivity(i);
        }else if(item.getItemId()==R.id.item2){
            Intent i = new Intent (Intent.ACTION_SENDTO);
            i.setData(Uri.parse("mailto:phiz99@gmail.com"));
            i.putExtra(Intent.EXTRA_SUBJECT, "subject");
            if (i.resolveActivity(getPackageManager()) != null) {
                startActivity(i);
            }
        }else if(item.getItemId()==R.id.item3){
            Intent i = new Intent(Intent.ACTION_DIAL);
            i.setData(Uri.parse("tel:"+ "7838668869"));
            if (i.resolveActivity(getPackageManager()) != null) {
                startActivity(i);
            }
        }else if(item.getItemId()==R.id.item4){
            Intent i = new Intent (Intent.ACTION_SENDTO);
            i.setData(Uri.parse("mailto:phiz99@gmail.com"));
            i.putExtra(Intent.EXTRA_SUBJECT, "subject");
            if (i.resolveActivity(getPackageManager()) != null) {
                startActivity(i);
            }
        }else{
            Intent i = new Intent (Intent.ACTION_SENDTO);
            i.setData(Uri.parse("mailto:phiz99@gmail.com"));
            i.putExtra(Intent.EXTRA_SUBJECT, "subject");
            if (i.resolveActivity(getPackageManager()) != null) {
                startActivity(i);
            }
        }
        return true;
    }
}
