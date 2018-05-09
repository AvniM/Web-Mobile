package com.example.karthik.myorder;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String MAIN_ACTIVITY_TAG = "MainActivity";
    final int PIZZA_PRICE = 7;
    final int OLIVES_PRICE = 1;
    final int PEPPERS_PRICE = 1;
    final int ONIONS_PRICE = 1;
    final int TOMATOES_PRICE = 1;
    int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    /**
     * This method is called when the order button is clicked.
     */
    public String submitOrder() {
       /* Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("geo:47.6,-122.3"));
        if (intent.resolveActivity(getPackageManager()) !=null){
            startActivity(intent);
        }*/

        // get user input
        EditText userInputNameView = (EditText) findViewById(R.id.user_input);
        String userInputName = userInputNameView.getText().toString();

        CheckBox olive_checked = (CheckBox) findViewById(R.id.olive_checked);
        boolean hasOlives = olive_checked.isChecked();
        CheckBox pepper_checked = (CheckBox) findViewById(R.id.pepper_checked);
        boolean hasPeppers = pepper_checked.isChecked();
        CheckBox onion_checked = (CheckBox) findViewById(R.id.onion_checked);
        boolean hasOnions = onion_checked.isChecked();
        CheckBox tomato_checked = (CheckBox) findViewById(R.id.tomato_checked);
        boolean hasTomatoes = tomato_checked.isChecked();

        // calculate and store the total price
        float totalPrice = calculatePrice(hasOlives, hasPeppers, hasOnions, hasTomatoes);

        // create and store the order summary
        String orderSummaryMessage = createOrderSummary(userInputName, hasOlives, hasPeppers, hasOnions, hasTomatoes, totalPrice);
        return orderSummaryMessage;

    }

    public void placeOrder(View view){

        String summary = submitOrder();

        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.setType("plain/text");
        sendIntent.putExtra(Intent.EXTRA_EMAIL, new String[] { "avni@gmail.com" });
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Your Pizza Place Order");
        sendIntent.putExtra(Intent.EXTRA_TEXT, summary);

        if (sendIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(sendIntent);
        }
    }

    public void viewSummary(View view){

        EditText userInputNameView = (EditText) findViewById(R.id.user_input);
        String userInputName = userInputNameView.getText().toString();

        CheckBox olive_checked = (CheckBox) findViewById(R.id.olive_checked);
        boolean hasOlives = olive_checked.isChecked();
        CheckBox pepper_checked = (CheckBox) findViewById(R.id.pepper_checked);
        boolean hasPeppers = pepper_checked.isChecked();
        CheckBox onion_checked = (CheckBox) findViewById(R.id.onion_checked);
        boolean hasOnions = onion_checked.isChecked();
        CheckBox tomato_checked = (CheckBox) findViewById(R.id.tomato_checked);
        boolean hasTomatoes = tomato_checked.isChecked();

        // calculate and store the total price
        float totalPrice = calculatePrice(hasOlives, hasPeppers, hasOnions, hasTomatoes);

        Intent summaryPage = new Intent(MainActivity.this, SummaryActivity.class);
        summaryPage.putExtra("name", userInputName);
        summaryPage.putExtra("olive", boolToString(hasOlives));
        summaryPage.putExtra("pepper", boolToString(hasPeppers));
        summaryPage.putExtra("onion", boolToString(hasOnions));
        summaryPage.putExtra("tomato", boolToString(hasTomatoes));
        summaryPage.putExtra("quantity", String.valueOf(quantity));
        summaryPage.putExtra("price", String.valueOf(totalPrice));

        if (summaryPage.resolveActivity(getPackageManager()) != null) {
            startActivity(summaryPage);
        }
    }

    public void selectDate(View view){
        Intent i = new Intent(MainActivity.this, CalendarActivity.class);
        startActivity(i);
    }

    private String boolToString(boolean bool){
        return bool?(getString(R.string.yes)):(getString(R.string.no));
    }

    private String createOrderSummary(String userInputName, boolean hasOlives, boolean hasPeppers, boolean hasOnions, boolean hasTomatoes, float price) {
        String orderSummaryMessage = getString(R.string.order_summary_name,userInputName) +"\n\n"+
                getString(R.string.order_summary_olive,boolToString(hasOlives))+"\n"+
                getString(R.string.order_summary_pepper,boolToString(hasPeppers))+"\n"+
                getString(R.string.order_summary_onion,boolToString(hasOnions))+"\n"+
                getString(R.string.order_summary_tomato,boolToString(hasTomatoes)) +"\n\n"+
                getString(R.string.order_summary_quantity,quantity)+"\n\n"+
                getString(R.string.order_summary_total_price,price) +"\n\n"+
                getString(R.string.thank_you);
        return orderSummaryMessage;

    }


    /**
     * Method to calculate the total price
     *
     * @return total Price
     */
    private float calculatePrice(boolean hasOlives, boolean hasPeppers, boolean hasOnions, boolean hasTomatoes) {
        int basePrice = PIZZA_PRICE;

        if (hasOlives) {
            basePrice += OLIVES_PRICE;
        }
        if (hasPeppers) {
            basePrice += PEPPERS_PRICE;
        }
        if (hasOnions) {
            basePrice += ONIONS_PRICE;
        }
        if (hasTomatoes) {
            basePrice += TOMATOES_PRICE;
        }

        return quantity * basePrice;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }


    /**
     * This method increments the quantity of coffee cups by one
     *
     * @param view on passes the view that we are working with to the method
     */

    public void increment(View view) {
        if (quantity < 50) {
            quantity = quantity + 1;
            display(quantity);
        } else {
            Log.i("MainActivity", "Please select less than 50 pizzas.");
            Context context = getApplicationContext();
            String lowerLimitToast = getString(R.string.too_many_pizzas);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, lowerLimitToast, duration);
            toast.show();
            return;
        }
    }

    /**
     * This method decrements the quantity of coffee cups by one
     *
     * @param view passes on the view that we are working with to the method
     */
    public void decrement(View view) {
        if (quantity > 0) {
            quantity = quantity - 1;
            display(quantity);
        } else {
            Log.i("MainActivity", "Please select atleast one pizza.");
            Context context = getApplicationContext();
            String upperLimitToast = getString(R.string.too_little_pizza);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, upperLimitToast, duration);
            toast.show();
            return;
        }
    }
}
