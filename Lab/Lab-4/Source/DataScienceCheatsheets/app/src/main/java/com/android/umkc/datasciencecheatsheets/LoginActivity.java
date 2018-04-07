package com.android.umkc.datasciencecheatsheets;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginActivity extends AppCompatActivity {

    private LoginButton mFacebookSignInButton;
    private CallbackManager facebookCallbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Facebook Login
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        facebookCallbackManager = CallbackManager.Factory.create();

        setContentView(R.layout.activity_login);

        // Facebook Login handle callbacks
        mFacebookSignInButton = (LoginButton)findViewById(R.id.facebook_sign_in_button);
        mFacebookSignInButton.registerCallback(facebookCallbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(final LoginResult loginResult) {
                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                    }
                    @Override
                    public void onCancel() {
                        shortToast("Error during login");
                    }
                    @Override
                    public void onError(FacebookException error) {
                        Log.d(LoginActivity.class.getCanonicalName(), error.getMessage());
                        shortToast("Error during login");
                    }
                }
        );

        // Code to get keyhash after first facebook login
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.android.umkc.datasciencecheatsheets",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.d("NameNotFound:", e.toString());
        } catch (NoSuchAlgorithmException e) {
            Log.d("NoSuchAlgorithm:", e.toString());
        }

    }

    //Authenticate Login
    public void verifyLogin(View view){
        EditText usernameVal = (EditText) findViewById(R.id.username);
        EditText passwordVal = (EditText) findViewById(R.id.pwd);

        String username = usernameVal.getText().toString();
        String password = passwordVal.getText().toString();

        // Check credentials
        if(!username.isEmpty() && !password.isEmpty()) {
            if(username.equals("avni") && password.equals("admin")){
                //redirect to home page
                Intent homePage = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(homePage);
            }
            else {
                shortToast("Invalid username or password.");
            }
        }
        else {
            shortToast("Enter username and password.");
        }
    }

    //Forgot password Feature
    public void forgotPassword(View view){
        shortToast("Forgot Password!");
    }

    //redirect to register page
    public void redirectRegister(View view){
        Intent loginPage = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(loginPage);
    }

    //Wrapper for short Toast
    public void shortToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        facebookCallbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}
