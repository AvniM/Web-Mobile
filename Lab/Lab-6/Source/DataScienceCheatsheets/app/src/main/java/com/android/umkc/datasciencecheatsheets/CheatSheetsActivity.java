package com.android.umkc.datasciencecheatsheets;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class CheatSheetsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat_sheets);

        ListView myListView = (ListView) findViewById(R.id.myListview);

        ArrayList<String> topics = new ArrayList<String>();

        topics.add("Python");
        topics.add("R");
        topics.add("Scala");
        topics.add("Hadoop");
        topics.add("Analytics");
        topics.add("Machine Learning");
        topics.add("Deep Learning");
        topics.add("Java");
        topics.add("SQL");
        topics.add("Analytics");
        topics.add("TensorFlow");
        topics.add("Spark");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, topics);

        myListView.setAdapter(arrayAdapter);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "Downloading " + parent.getItemAtPosition(position).toString() + " cheatsheet", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
