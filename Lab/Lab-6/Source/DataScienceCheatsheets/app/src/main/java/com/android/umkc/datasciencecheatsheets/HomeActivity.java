package com.android.umkc.datasciencecheatsheets;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.umkc.datasciencecheatsheets.db.SQLiteDB;
import com.android.umkc.datasciencecheatsheets.model.Topic;

import java.util.ArrayList;
import java.util.Locale;

public class HomeActivity extends AppCompatActivity {

    private static final int REQ_CODE_SPEECH_INPUT = 100;
    private TextView mVoiceInputTv;
    private ImageButton mSpeakBtn;
    TextToSpeech toSpeech;
    String topics, topicToAdd, topicToDelete;

    String msg = "Hello! Welcome to Siri mode." +
                 "\nSay 1 to hear list of topics." +
                 "\nSay 2 to add a topic." +
                 "\nSay 3 to delete a topic.";

    private SQLiteDB sqLiteDB;

    @Override
    protected void onStart() {
        super.onStart();
        sqLiteDB = new SQLiteDB(this);

        if (sqLiteDB.getCount() == 0) {

            Topic topic1 = new Topic();
            topic1.setName("Python");
            Topic topic2 = new Topic();
            topic2.setName("Java");

            sqLiteDB.insert(topic1);
            sqLiteDB.insert(topic2);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mVoiceInputTv = (TextView) findViewById(R.id.voiceInput);
        mSpeakBtn = (ImageButton) findViewById(R.id.btnSpeak);

        mVoiceInputTv.setText(msg);

        toSpeech = new TextToSpeech(HomeActivity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                toSpeech.speak(msg,TextToSpeech.QUEUE_FLUSH,null);
            }
        });

        mSpeakBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startVoiceInput("Your choice?");
            }
        });
    }

    private void startVoiceInput(String msg) {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, msg);

        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Log.e("Errrr", "ActivityNotFoundException");
        }
    }

    public String getTopicList(){

        String topics = "";
        Cursor cursor = sqLiteDB.retrieve();

        if (cursor.moveToFirst()) {
            do {
                if (!topics.isEmpty()){
                    topics = topics + ", ";
                }

                topics = topics + cursor.getString(0);
            }while (cursor.moveToNext());
        }
        return topics;
    }

    public void addTopic(String t){
        Topic topic = new Topic();
        topic.setName(t);
        sqLiteDB.insert(topic);
    }

    public void removeTopic(String t){
        sqLiteDB.delete(t);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    final String res = result.get(0);
                    mVoiceInputTv.setText(res);

                    if (res.contains("1")){
                        //Get topics from database
                        topics = getTopicList();

                        msg = "Ok. Here is the list of available topics.";
                        msg = msg + "\n" + topics;

                        toSpeech = new TextToSpeech(HomeActivity.this, new TextToSpeech.OnInitListener() {
                            @Override
                            public void onInit(int i) {
                                toSpeech.speak(msg,TextToSpeech.QUEUE_FLUSH,null);
                            }
                        });
                        mVoiceInputTv.setText(res + "\n" + msg);

                    }
                    else if (res.contains("2")){
                        msg = "Ok. Say 'add' and then say the topic you want to add.";
                        toSpeech = new TextToSpeech(HomeActivity.this, new TextToSpeech.OnInitListener() {
                            @Override
                            public void onInit(int i) {
                                toSpeech.speak(msg,TextToSpeech.QUEUE_FLUSH,null);
                            }
                        });
                        mVoiceInputTv.setText(res + "\n" + msg);
                    }
                    else if (res.contains("3")){
                        msg = "Ok. Say 'delete' and then say the topic you want to delete.";
                        toSpeech = new TextToSpeech(HomeActivity.this, new TextToSpeech.OnInitListener() {
                            @Override
                            public void onInit(int i) {
                                toSpeech.speak(msg,TextToSpeech.QUEUE_FLUSH,null);
                            }
                        });
                        mVoiceInputTv.setText(msg);mVoiceInputTv.setText(res + "\n" + msg);
                    }
                    else if (res.startsWith("add")){
                        topicToAdd = res.replace("add ", "");
                        addTopic(topicToAdd);

                        msg = "Ok. Topic added.";
                        toSpeech = new TextToSpeech(HomeActivity.this, new TextToSpeech.OnInitListener() {
                            @Override
                            public void onInit(int i) {
                                toSpeech.speak(msg,TextToSpeech.QUEUE_FLUSH,null);
                            }
                        });
                        mVoiceInputTv.setText(msg);mVoiceInputTv.setText(res + "\n" + msg);
                    }
                    else if (res.startsWith("delete")){
                        topicToDelete = res.replace("delete ", "");
                        removeTopic(topicToDelete);

                        msg = "Ok. Topic deleted.";
                        toSpeech = new TextToSpeech(HomeActivity.this, new TextToSpeech.OnInitListener() {
                            @Override
                            public void onInit(int i) {
                                toSpeech.speak(msg,TextToSpeech.QUEUE_FLUSH,null);
                            }
                        });
                        mVoiceInputTv.setText(msg);mVoiceInputTv.setText(res + "\n" + msg);
                    }
                    else if (res.startsWith("exit")){
                        msg = "Ok. Exiting now.";
                        toSpeech = new TextToSpeech(HomeActivity.this, new TextToSpeech.OnInitListener() {
                            @Override
                            public void onInit(int i) {
                                toSpeech.speak(msg,TextToSpeech.QUEUE_FLUSH,null);
                            }
                        });
                        mVoiceInputTv.setText(msg);
                    }
                    else if (res.contains("thank")){
                        msg = "Thank you too.";
                        toSpeech = new TextToSpeech(HomeActivity.this, new TextToSpeech.OnInitListener() {
                            @Override
                            public void onInit(int i) {
                                toSpeech.speak(msg,TextToSpeech.QUEUE_FLUSH,null);
                            }
                        });
                        mVoiceInputTv.setText(msg);
                    }
                    else {
                        msg = "Please select a valid option.";
                        toSpeech = new TextToSpeech(HomeActivity.this, new TextToSpeech.OnInitListener() {
                            @Override
                            public void onInit(int i) {
                                toSpeech.speak(msg,TextToSpeech.QUEUE_FLUSH,null);
                            }
                        });
                        mVoiceInputTv.setText(res + "\n" + msg);
                    }
                }
                break;
            }
        }
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
