package com.example.liuxiaohui.xiaohui1_feelsbook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class RecordEmotion extends AppCompatActivity {
    private Button saveBut;
    private EditText commentText;
    private ArrayList<Record> Records = new ArrayList<Record>();
    private String date;
    private Record newRecord;
    private String comment;
    private String emotionName;

    private static final String FILENAME = "history.sav";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_emotion);

        Date tempDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss", Locale.CANADA);
        this.date = dateFormat.format(tempDate);

        // get the emotion name passed in from main activity and make a record
        Intent intent = getIntent();
        this.emotionName = intent.getStringExtra("emotionName");


        // this is the "save" button
        saveBut = (Button)findViewById(R.id.ButSave);
        saveBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                // get user's input comment text
                commentText = findViewById(R.id.commentText);
                // transform it to string for better readbility
                comment = commentText.getText().toString();
                switch(emotionName) {
                    case "Love":
                        newRecord = new Record("Love", comment, date);
                        break;
                    case "Joy":
                        newRecord = new Record("Joy", comment, date);
                        break;
                    case "Surprise":
                        newRecord = new Record("Surprise", comment, date);
                        break;
                    case "Anger":
                        newRecord = new Record("Anger", comment, date);
                        break;
                    case "Sadness":
                        newRecord = new Record("Sadness", comment, date);
                        break;
                    case "Fear":
                        newRecord = new Record("Fear", comment, date);
                        break;
                }
                saveRecord(newRecord);  // once save button is clicked, we save the new record
                Intent intent = new Intent(RecordEmotion.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }



    protected void saveRecord(Record someRecord) {
        try {
            String newRecordString = someRecord.toString();  // transform record to more readable form
            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_APPEND);
            fos.write(newRecordString.getBytes());

            fos.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
