package com.android.umkc.datasciencecheatsheets;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //redirect to login page
    public void redirectLogin(View view){
        Intent loginPage = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(loginPage);
    }

    //redirect to register page
    public void redirectRegister(View view){
        Intent loginPage = new Intent(MainActivity.this, RegisterActivity.class);
        startActivity(loginPage);
    }
}
