package com.example.k.quizmaster;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

//    LAYOUT OF MAIN ACTIVITY CHANGED, I THINK IT WOULD BE BETTER COMMITTED

    GridView mGridView;
    QuizGridViewAdapter quizGridViewAdapter;
    ArrayList<String> quizNumberArrayList = new ArrayList<>();
    Button muserDetailsSaveButton, mliveOrFestQuizButton;
    SharedPreferences sharedPreferences;
    ImageView muserImageView;
    GoogleApiClient mGoogleApiClient;
    public static final int RC_SIGN_IN = 100;

    String imageurl = "";
    public static final String USER_NAME = "username", COLLEGE = "college", CONTACT = "0000000000" ,IMAGE="";
    public static final String mypreference = "mypref";

    TextInputEditText mCollege, mContact;
    TextView muserName;

    SignInButton signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        muserName = (TextView) findViewById(R.id.userNameTextView);
        mCollege = (TextInputEditText)findViewById(R.id.collegeTextInputEditText);
        mContact = (TextInputEditText)findViewById(R.id.contactTextInputEditText);
        muserImageView = (ImageView)findViewById(R.id.userImageView);

        mliveOrFestQuizButton = (Button)findViewById(R.id.liveOrFestQuizButton);
        mliveOrFestQuizButton.setEnabled(false);
        muserDetailsSaveButton = (Button)findViewById(R.id.userDetailsSaveButton);

        mGridView = (GridView)findViewById(R.id.quizGridView);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setScopes(gso.getScopeArray());
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.sign_in_button:
                        signIn();
                        break;
                    // ...
                }
            }
        });


        if(muserName.getText().toString().compareTo("")==0){
            muserDetailsSaveButton.setEnabled(false);

        }else{
            muserDetailsSaveButton.setEnabled(true);
        }

//      USER DETAILS ARE NOW BEING SAVED COMMITTED
        sharedPreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);

        if (sharedPreferences.contains(USER_NAME) ||
                sharedPreferences.contains(COLLEGE) ||
                sharedPreferences.contains(CONTACT)) {
            muserName.setText(sharedPreferences.getString(USER_NAME, ""));
            muserName.setEnabled(false);
            mCollege.setText(sharedPreferences.getString(COLLEGE, ""));
            mCollege.setEnabled(false);
            mContact.setText(sharedPreferences.getString(CONTACT, ""));
            mContact.setEnabled(false);
            String temp = sharedPreferences.getString(IMAGE, "");
            Picasso.with(getApplicationContext()).load(temp).into(muserImageView);
            muserDetailsSaveButton.setVisibility(View.GONE);
            signInButton.setVisibility(View.GONE);
            mliveOrFestQuizButton.setEnabled(true);
        }

        muserDetailsSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder dialog=new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("ATTENTION");
                dialog.setMessage("Please check the entered details. Details ONCE entered cannot be changed !!");
                dialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sharedPreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(USER_NAME, muserName.getText().toString());
                        editor.putString(COLLEGE, mCollege.getText().toString());
                        editor.putString(CONTACT, mContact.getText().toString());
                        editor.putString(IMAGE, imageurl);
                        editor.commit();
                        muserName.setEnabled(false);
                        mCollege.setEnabled(false);
                        mContact.setEnabled(false);
                        muserDetailsSaveButton.setVisibility(View.GONE);
                        signInButton.setVisibility(View.GONE);
                        mliveOrFestQuizButton.setEnabled(true);

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

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                GoogleSignInAccount acct = result.getSignInAccount();
                muserName.setText(result.getSignInAccount().getDisplayName());
                imageurl = imageurl + result.getSignInAccount().getPhotoUrl();
                Picasso.with(getApplicationContext()).load(imageurl).into(muserImageView);
                Toast.makeText(getApplicationContext(),result.getSignInAccount().getDisplayName() + imageurl ,Toast.LENGTH_LONG).show();
                muserDetailsSaveButton.setEnabled(true);
            }
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

}
