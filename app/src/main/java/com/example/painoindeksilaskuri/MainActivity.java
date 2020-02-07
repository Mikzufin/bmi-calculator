package com.example.painoindeksilaskuri;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private double BMI = 0.0;
    private String kayttajanNimi = "Matti";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public void onSaveInstanceState( Bundle saveInstanceState) {
        super.onSaveInstanceState(saveInstanceState);
        // Talletetaan aktiviteeetin sisäinen tila
        saveInstanceState.putDouble("BMI_VALUE", BMI);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState != null) {
            // Luetaan talletettu BMI tietojäseneen
            BMI = savedInstanceState.getDouble("BMI_VALUE", 0.0);
        }
        // Kirjoitetan BMI käyttöliittymäkomponenttiin
        TextView resultTextView = (TextView) findViewById(R.id.resultTextView);
        resultTextView.setText(String.format("%.1f", BMI));

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout);

        if (BMI < 20) {
            // underweight
            linearLayout.setBackgroundColor(getColor(R.color.underWeightColor));
        } else if (BMI < 26) {
            // normal
            linearLayout.setBackgroundColor(getColor(R.color.normalWeightColor));
        } else {
            // overweight
            linearLayout.setBackgroundColor(getColor(R.color.overWeightColor));

        }
    }

    public void openProfile (View view) {
        // Määritellään intent ProfileActivityn avaamiseksi
        // Explicit intent (devaajan "käsin" valitsema aktiviteetti
        Intent openProfileIntent = new Intent(this, ProfileActivity.class);
        openProfileIntent.putExtra("USER_NAME", kayttajanNimi);
        startActivity(openProfileIntent);
    }

    public void dialCustomerSupport (View view) {
        // Implicit intent (Android valitsee spivimman default - aktiviteetin toteuttamaan actionin)
        // testi
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + "0403452345"));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void calculateBMI(View view) {

        EditText weightEditText = (EditText) findViewById(R.id.weightEditText);
        EditText heightEditText = (EditText) findViewById(R.id.heightEditText);
        TextView resultTextView = (TextView) findViewById(R.id.resultTextView);

        double weight = Double.parseDouble( weightEditText.getText ().toString());
        double height = Double.parseDouble( heightEditText.getText ().toString());
        BMI = weight / (height/100*height/100);

        resultTextView.setText(String.format ( "%.1f", BMI));
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout);

        if ( BMI < 20 ) {
            // underweight
            linearLayout.setBackgroundColor(getColor(R.color.underWeightColor));
        }
        else if (BMI < 26 ){
            // normal
            linearLayout.setBackgroundColor(getColor(R.color.normalWeightColor));
        }
        else {
            // overweight
            linearLayout.setBackgroundColor(getColor(R.color.overWeightColor));


            //Button myButton = new Button (this);
            //myButton.setText("Hello");
            //linearLayout.addView ( myButton );
        }


    }
}
