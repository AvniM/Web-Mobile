package com.android.umkc.datasciencecheatsheets;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    //Delete Account
    public void deleteAccount(View view){
        shortToast("Account deleted successfully");
    }

    //Logout
    public void logout(View view){
        shortToast("Logout successful");
    }

    //Wrapper for short Toast
    public void shortToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

}
