package com.android.umkc.datasciencecheatsheets;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    //redirect to login page
    public void redirectLogin(View view){
        Intent loginPage = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(loginPage);
    }

    //Authenticate Register
    public void verifyRegister(View view){
        EditText usernameVal = (EditText) findViewById(R.id.username);
        EditText emailVal = (EditText) findViewById(R.id.email);
        EditText mobileVal = (EditText) findViewById(R.id.mobile);
        EditText passwordVal = (EditText) findViewById(R.id.password);
        EditText password2Val = (EditText) findViewById(R.id.reenterPassword);

        String username = usernameVal.getText().toString();
        String email = emailVal.getText().toString();
        String mobile = mobileVal.getText().toString();
        String password = passwordVal.getText().toString();
        String password2 = password2Val.getText().toString();

        // Check credentials
        if(!username.isEmpty() && !email.isEmpty() && !mobile.isEmpty() && !password.isEmpty() && !password2.isEmpty()) {
            if(password.equals(password2)){
                //redirect to home page
                Intent homePage = new Intent(RegisterActivity.this, HomeActivity.class);
                startActivity(homePage);
            }
            else {
                shortToast("Passwords doesn't match.");
            }
        }
        else {
            shortToast("Enter all fields.");
        }
    }

    //Wrapper for short Toast
    public void shortToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
