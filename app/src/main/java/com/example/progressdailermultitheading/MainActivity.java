package com.example.progressdailermultitheading;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button btnProgressrdailer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        intializeView();
        initializeListners();
    }
    private void intializeView() {
        btnProgressrdailer = findViewById(R.id.btnProgressrdailer);
    }


    private void initializeListners() {
        btnProgressrdailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String [] fileurl ={
                        "file1",
                        "file2",
                        "file3",
                        "file4"
                };
                new Downloader().execute(fileurl);
            }
        });
    }

    class Downloader extends AsyncTask<String,Integer,Float>{
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("Downloading");
            progressDialog.setMessage("Files Downloading In process");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.show();
        }

        @Override
        protected Float doInBackground(String... fileUrls) {
            for (String url:fileUrls) {
                int i;
                for (i = 0; i < 100; i++) {
                    Log.e("tag", url);
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                Integer[] progress = new Integer[1];
                progress[0] = 1;
                publishProgress(1);
                progressDialog.setProgress(i);
            }
            return 0f;
        }

        @Override
        protected void onPostExecute(Float aFloat) {
            super.onPostExecute(aFloat);
            btnProgressrdailer.setText("Float"+ aFloat);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            btnProgressrdailer.setText("Progress" + values[0] + "%");
        }
    }


}