package com.example.k.quizmaster;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AboutUsActivity extends AppCompatActivity {
    Button mgithubButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        mgithubButton = (Button)findViewById(R.id.githubButton);
        mgithubButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setAction(Intent.ACTION_VIEW);
                Uri a = Uri.parse("https://github.com/KanavRampal/QuizAlpha.exe");
                i.setData(a);
                startActivity(i);
            }
        });
    }
}
