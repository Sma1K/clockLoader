package com.example.smaik.loaderproject;

import android.content.Intent;
import android.os.AsyncTask;
import android.provider.AlarmClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.ma_image_iv);
        progressBar = findViewById(R.id.ma_progress_pb);
        imageView.setOnClickListener(view -> new LongTask().execute());
    }

    class LongTask extends AsyncTask<Void, Void, String> {


        //метод выполняющийсе перед созданием и выполнением рабочего потока
        @Override
        protected void onPreExecute() {
            imageView.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
        }

        //метод выполняющийся в отдельном(рабочем) потоке
        @Override
        protected String doInBackground(Void... voids) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Log.e(MainActivity.class.getSimpleName(), e.getMessage());
            }
            return "Какой-то ответ";
        }


        //метод выполняющийся после работы раб потока
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressBar.setVisibility(View.GONE);
            Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
            if (intent.resolveActivity(getPackageManager()) != null)
                startActivity(intent);
//            loadFinishedTv.setVisibility(View.VISIBLE);
//            loadFinishedTv.setText(s);

        }
    }
}
