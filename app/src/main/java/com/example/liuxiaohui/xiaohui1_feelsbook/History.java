// This is the history page, allows user to view and delete histories.

package com.example.liuxiaohui.xiaohui1_feelsbook;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class History extends AppCompatActivity {

    private static final String FILENAME = "history.sav";
    private String[] records;
    private Button backBut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        backBut = findViewById(R.id.backBut);

        backBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                finish();  // if backBut pressed, take user to Home page.
            }
        });

        final ListView lv = (ListView)findViewById(R.id.historyView);
        records = loadRecords();
        final ArrayAdapter<String> adap = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, records);
        lv.setAdapter(adap);

        // from Juned Mughal
        // detailed info in README
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // the string record to be deleted
                String recordToDel = (String) lv.getItemAtPosition(position);
                // turn records to list for easy removing
                List<String> recordList = new ArrayList<String>(Arrays.asList(records));
                recordList.remove(recordToDel);  // deleting the record
                records = recordList.toArray(new String[0]);
                // rewrite deleted records into file
                saveUpdatedRecord(records);

                adap.notifyDataSetChanged();

                // prompt to user
                Toast.makeText(History.this, "Record Deleted", Toast.LENGTH_LONG).show();

                finish();  // take user to home page once a record is deleted

            }
        });
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

    // rewrite the history file with deleted records list
    protected void saveUpdatedRecord(String[] records) {
        try {
            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            for (String record : records) {
                fos.write((record+"\n").getBytes());
            }

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
