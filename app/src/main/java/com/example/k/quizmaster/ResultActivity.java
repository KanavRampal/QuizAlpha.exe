package com.example.k.quizmaster;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

public class ResultActivity extends AppCompatActivity {

    String score = "", username = "", contact = "", college = "";
    TextView mresultTextView, muserNameTextView;
    TextView mcorrectAnswersTextView;
    TextView mincorrectAnswersTextView;
    int mCorrectAnswers=0;
    ProgressDialog mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        score = getIntent().getStringExtra("score");
        username = getIntent().getStringExtra("user_name");
        college = getIntent().getStringExtra("col_lege");
        contact = getIntent().getStringExtra("con_tact");

        mCorrectAnswers = Integer.parseInt(score)/10;

        muserNameTextView = (TextView)findViewById(R.id.userNameTextView);
        mresultTextView = (TextView)findViewById(R.id.resultTextView);
        mcorrectAnswersTextView = (TextView)findViewById(R.id.correctQuestionsTextView);
        mincorrectAnswersTextView = (TextView)findViewById(R.id.incorrectQuestionsTextView);

//      SCORING IS WORKING PERFECTLY COMMITTED
        muserNameTextView.setText(username);
        mresultTextView.setText("Your final Score is "+score+".");
        mcorrectAnswersTextView.setText("Correct :"+mCorrectAnswers);
        mincorrectAnswersTextView.setText("Incorrect :"+(10-mCorrectAnswers));

        mProgress = new ProgressDialog(this);
        mProgress.setMessage("Storing Results wait ...");
        new SendRequest().execute();
    }


//   THE RESULTS ARE BEING STORED ONLINE COMMITTED

    public class SendRequest extends AsyncTask<String, Void, String> {
        protected void onPreExecute(){
            mProgress.show();
        }

        protected String doInBackground(String... arg0) {

            try{

                URL url = new URL("https://script.google.com/macros/s/AKfycbwg4-vfdpS4KXBLd5A5jyxW5ySbil3DWaYYRqZT9W3_a91Gt1qp/exec");

                JSONObject postDataParams = new JSONObject();

                String id= "1fjCZYggK4-b7RIy3wjY4feW6lFXE1EPZdHATJ16C7sY";

                postDataParams.put("name", username);
                postDataParams.put("college", college);
                postDataParams.put("score", score);
                postDataParams.put("contact", contact);
                postDataParams.put("id",id);

                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                conn.setReadTimeout(15000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(postDataParams));
                writer.flush();
                writer.close();
                os.close();

                int responseCode=conn.getResponseCode();

                if(responseCode == HttpsURLConnection.HTTP_OK){
                    BufferedReader in=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuffer sb = new StringBuffer("");
                    String line="";
                    while((line = in.readLine()) != null) {
                        sb.append(line);
                        break;
                    }
                    in.close();
                    return sb.toString();
                }
                else{
                    return new String("false : "+responseCode);
                }
            }
            catch(Exception e){
                return new String("Exception: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(String result) {
            mProgress.hide();
            Toast.makeText(getApplicationContext(), "Your result is saved successfully, you may now leave the app. :)", Toast.LENGTH_LONG).show();

        }

        public String getPostDataString(JSONObject params) throws Exception {
            StringBuilder result = new StringBuilder();
            boolean first = true;
            Iterator<String> itr = params.keys();
            while(itr.hasNext()){
                String key= itr.next();
                Object value = params.get(key);
                if (first){
                    first = false;
                }
                else{
                    result.append("&");
                }
                result.append(URLEncoder.encode(key, "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(value.toString(), "UTF-8"));
            }
            return result.toString();
        }
    }
}
