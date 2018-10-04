/* This is the home page
* Part of this file is from Lonely Twitter by Joshua Campbell
*/
package com.example.liuxiaohui.xiaohui1_feelsbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // initialize the file saving history info
    private static final String FILENAME = "emotionCounts.sav";

    // initialize all emotions
    public Emotion Love = new Emotion("Love");
    public Emotion Joy = new Emotion("Joy");
    public Emotion Surprise = new Emotion("Surprise");
    public Emotion Anger = new Emotion("Anger");
    public Emotion Sadness = new Emotion("Sadness");
    public Emotion Fear = new Emotion("Fear");
    // create array of all 6 emotions in order
    private ArrayList<Emotion> Emotions =
            new ArrayList<>(Arrays.asList(Love, Joy, Surprise, Anger, Sadness, Fear));

    // initialize all buttons
    public Button ButLove;
    public Button ButJoy;
    public Button ButSurprise;
    public Button ButAnger;
    public Button ButSadness;
    public Button ButFear;
    public Button ButHistory;
    // create empty array of buttons
    private List<Button> Buttons = new ArrayList<>();

    public void init(){

        ButLove = (Button)findViewById(R.id.ButLove);
        ButJoy = (Button)findViewById(R.id.ButJoy);
        ButSurprise = (Button)findViewById(R.id.ButSurprise);
        ButAnger = (Button)findViewById(R.id.ButAnger);
        ButSadness = (Button)findViewById(R.id.ButSadness);
        ButFear = (Button)findViewById(R.id.ButFear);
        ButHistory = (Button)findViewById(R.id.ButHistory);
        // add all emotion buttons to array
        this.Buttons = Arrays.asList(ButLove, ButJoy, ButSurprise, ButAnger, ButSadness, ButFear);

        ButLove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Love.increaseCount();  // increment counter
                // refresh the button's content with new counter
                ButLove.setText("LOVE: " + Integer.toString(Love.getCount()));
                saveInFile();
                Intent intent = new Intent(MainActivity.this, RecordEmotion.class);
                intent.putExtra("emotionName","Love");  // tell next activity what button is pressed
                startActivity(intent);
            }
        });

        ButJoy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Joy.increaseCount();
                ButJoy.setText("JOY: " + Integer.toString(Joy.getCount()));
                saveInFile();
                Intent intent = new Intent(MainActivity.this, RecordEmotion.class);
                intent.putExtra("emotionName","Joy");
                startActivity(intent);
            }
        });

        ButSurprise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Surprise.increaseCount();
                ButSurprise.setText("SURPRISE: " + Integer.toString(Surprise.getCount()));
                saveInFile();
                Intent intent = new Intent(MainActivity.this, RecordEmotion.class);
                intent.putExtra("emotionName","Surprise");
                startActivity(intent);
            }
        });

        ButAnger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Anger.increaseCount();
                ButAnger.setText("ANGER: " + Integer.toString(Anger.getCount()));
                Intent intent = new Intent(MainActivity.this, RecordEmotion.class);
                intent.putExtra("emotionName","Anger");
                startActivity(intent);
            }
        });

        ButSadness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Sadness.increaseCount();
                ButSadness.setText("SADNESS: " + Integer.toString(Sadness.getCount()));
                saveInFile();
                Intent intent = new Intent(MainActivity.this, RecordEmotion.class);
                intent.putExtra("emotionName","Sadness");
                startActivity(intent);
            }
        });

        ButFear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Fear.increaseCount();
                ButFear.setText("FEAR: " + Integer.toString(Fear.getCount()));
                saveInFile();
                Intent intent = new Intent(MainActivity.this, RecordEmotion.class);
                intent.putExtra("emotionName","Fear");
                startActivity(intent);
            }
        });

        ButHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, History.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    protected void onStart() {
        super.onStart();
        loadFromFile();

    }
/*
    protected void loadCount(String emotionName) {
        switch(emotionName){
            case "Love":
                break;
            case "Joy":
                break;
            case "Surprise":
                break;
            case "Anger":
                break;
            case "Sadness":
                break;
            case "Fear":
                break;
        }
    }*/

    // imitated from lab 3 code
    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader reader = new BufferedReader(isr);

            Gson gson = new Gson();
            Type listEmotionsType = new TypeToken<ArrayList<Emotion>>(){}.getType();
            gson.fromJson(reader, listEmotionsType);

            fis.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            Emotions = new ArrayList<Emotion>();
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME, 0);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter writer = new BufferedWriter(osw);

            Gson gson= new Gson();
            gson.toJson(Emotions, writer);

            writer.flush();
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
