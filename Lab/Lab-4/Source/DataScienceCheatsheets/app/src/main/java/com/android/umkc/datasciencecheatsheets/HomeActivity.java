package com.android.umkc.datasciencecheatsheets;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    //redirect
    public void topics(View view){
        Intent newpage = new Intent(HomeActivity.this, TopicsActivity.class);
        startActivity(newpage);
    }

    //redirect
    public void cheatsheets(View view){
        Intent newpage = new Intent(HomeActivity.this, CheatSheetsActivity.class);
        startActivity(newpage);
    }

    //redirect
    public void settings(View view){
        Intent newpage = new Intent(HomeActivity.this, SettingsActivity.class);
        startActivity(newpage);
    }

    //redirect
    public void help(View view){
        Intent newpage = new Intent(HomeActivity.this, HelpActivity.class);
        startActivity(newpage);
    }
}
