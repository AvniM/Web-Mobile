package com.example.karthik.myorder;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class SummaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        TextView summaryName = (TextView)findViewById(R.id.summaryName);
        TextView summaryOlive = (TextView)findViewById(R.id.summaryOlive);
        TextView summaryPepper = (TextView)findViewById(R.id.summaryPepper);
        TextView summaryOnion = (TextView)findViewById(R.id.summaryOnion);
        TextView summaryTomato = (TextView)findViewById(R.id.summaryTomato);
        TextView summaryQuantity = (TextView)findViewById(R.id.summaryQuantity);
        TextView summaryPrice = (TextView)findViewById(R.id.summaryPrice);

        ImageView image = (ImageView)findViewById(R.id.summaryImage);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras != null) {
                String name = extras.getString("name");
                String olive = extras.getString("olive");
                String pepper = extras.getString("pepper");
                String onion = extras.getString("onion");
                String tomato = extras.getString("tomato");
                String quantity = extras.getString("quantity");
                String price = extras.getString("price");

                summaryName.setText("Name: " + name);
                summaryOlive.setText("Add Olives: " + olive);
                summaryPepper.setText("Add Peppers: " + pepper);
                summaryOnion.setText("Add Onions: " + onion);
                summaryTomato.setText("Add Tomtoes: " + tomato);
                summaryQuantity.setText("Quantity: " + quantity);
                summaryPrice.setText("Price: " + price);

                if (olive.equals("Yes") && pepper.equals("Yes")){
                    image.setImageResource(R.drawable.lesstopping);
                }
                else {
                    image.setImageResource(R.drawable.moretopping);
                }
            }
        }
    }
}
