package com.example.k.quizmaster;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

//    LAYOUT OF MAIN ACTIVITY CHANGED, I THINK IT WOULD BE BETTER COMMITTED

    GridView mGridView;
    QuizGridViewAdapter quizGridViewAdapter;
    ArrayList<String> quizNumberArrayList = new ArrayList<>();
    Button muserDetailsSaveButton, muserDetailsEditButton, mliveOrFestQuizButton;
    SharedPreferences sharedPreferences;

    public static final String USER_NAME = "username", COLLEGE = "college", CONTACT = "0000000000";
    public static final String mypreference = "mypref";

    TextInputEditText muserName, mCollege, mContact;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        muserName = (TextInputEditText)findViewById(R.id.userNameTextInputEditText);
        mCollege = (TextInputEditText)findViewById(R.id.collegeTextInputEditText);
        mContact = (TextInputEditText)findViewById(R.id.contactTextInputEditText);

        mliveOrFestQuizButton = (Button)findViewById(R.id.liveOrFestQuizButton);
        muserDetailsSaveButton = (Button)findViewById(R.id.userDetailsSaveButton);
        muserDetailsEditButton = (Button)findViewById(R.id.userDetailsEditButton);

        mGridView = (GridView)findViewById(R.id.quizGridView);

//      USER DETAILS ARE NOW BEING SAVED COMMITTED
        sharedPreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);

        if (sharedPreferences.contains(USER_NAME)) {
            muserName.setText(sharedPreferences.getString(USER_NAME, ""));
            muserName.setEnabled(false);
            muserDetailsSaveButton.setEnabled(false);
        }
        if (sharedPreferences.contains(COLLEGE)) {
            mCollege.setText(sharedPreferences.getString(COLLEGE, ""));
            mCollege.setEnabled(false);
            muserDetailsSaveButton.setEnabled(false);
        }
        if (sharedPreferences.contains(CONTACT)) {
            mContact.setText(sharedPreferences.getString(CONTACT, ""));
            mContact.setEnabled(false);
            muserDetailsSaveButton.setEnabled(false);
        }
        muserDetailsSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(USER_NAME, muserName.getText().toString());
                editor.putString(COLLEGE, mCollege.getText().toString());
                editor.putString(CONTACT, mContact.getText().toString());
                editor.commit();
                muserName.setEnabled(false);
                mCollege.setEnabled(false);
                mContact.setEnabled(false);
                muserDetailsSaveButton.setEnabled(false);
                muserDetailsEditButton.setEnabled(true);
            }
        });

        muserDetailsEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                muserName.setEnabled(true);
                mCollege.setEnabled(true);
                mContact.setEnabled(true);
                muserDetailsSaveButton.setEnabled(true);
                muserDetailsEditButton.setEnabled(false);
            }
        });


        for(int i=1;i<=5;i++){
            quizNumberArrayList.add(i+"");
        }

        quizGridViewAdapter = new QuizGridViewAdapter(this, quizNumberArrayList);
        mGridView.setAdapter(quizGridViewAdapter);

//        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent intent = new Intent();
//                intent.putExtra("userName", muserName.getText().toString());
//                intent.putExtra("college", mCollege.getText().toString());
//                intent.putExtra("contact", mContact.getText().toString());
//                intent.setClass(MainActivity.this, QuestionPageActivity.class);
//                startActivity(intent);
//            }
//        });

        mliveOrFestQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("userName", muserName.getText().toString());
                intent.putExtra("college", mCollege.getText().toString());
                intent.putExtra("contact", mContact.getText().toString());
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
