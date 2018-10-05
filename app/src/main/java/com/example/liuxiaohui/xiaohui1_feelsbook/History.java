// This is the history page

package com.example.liuxiaohui.xiaohui1_feelsbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class History extends AppCompatActivity {
    private static final String FILENAME = "history.sav";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        ListView lv = (ListView)findViewById(R.id.historyView);
        String[] records = loadRecords();
        ArrayAdapter<String> adap = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, records);
        lv.setAdapter(adap);
    }

    protected void onStart() {
        super.onStart();
    }

    protected String[] loadRecords() {
        ArrayList<String> records = new ArrayList<>();
        try {
            FileInputStream fis = openFileInput(FILENAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader reader = new BufferedReader(isr);

            String record = reader.readLine();
            while (record != null) {
                records.add(record);
                record = reader.readLine();
            }

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // get size of records for later returning
        int arraySize = records.size();
        return records.toArray(new String[arraySize]);
    }

}
