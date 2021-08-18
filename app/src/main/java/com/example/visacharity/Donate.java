package com.example.visacharity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Donate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final User currUser = (User) getApplication();

        setContentView(R.layout.activity_donate);
        Integer charityNum = getIntent().getIntExtra("Charity Num", 0);
        TextView charityText = (TextView) findViewById(R.id.charityName);
        charityText.setText("Charity " + charityNum);

        final EditText ptsAmount = (EditText) findViewById(R.id.donationAmount);
        final TextView conversionAmount = (TextView) findViewById(R.id.conversionAmount);
        Button donateBtn = (Button) findViewById(R.id.donateBtn);
        ImageButton backBtn = (ImageButton) findViewById(R.id.backBtn);



        ptsAmount.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if(s.length() != 0){
                    Double pts = Double.parseDouble(s.toString())/10;
                    Double converted = (double) Math.round(pts * 100) / 100;
                    conversionAmount.setText("$" + String.format("%.2f", converted));
                }
            }
        });

        donateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Integer donatedPts = Integer.parseInt(ptsAmount.getText().toString());
                if (donatedPts > currUser.getTotalPoints()){
                    ptsAmount.setError("Not Enough Points");
                    return;
                }

                AlertDialog alert = new android.app.AlertDialog.Builder(Donate.this).create();
                alert.setMessage("Are you sure you want to donate to this charity?");
                alert.setButton(AlertDialog.BUTTON_POSITIVE, "YES",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                currUser.setTotalPoints(currUser.getTotalPoints() - donatedPts);
                                Toast.makeText(getApplicationContext(), //Context
                                        "Donation Successful", // Message to display
                                        Toast.LENGTH_SHORT // Duration of the message, another possible value is Toast.LENGTH_LONG
                                ).show();
                                launchMainActivity();
                                dialog.dismiss();

                            }
                        });

                alert.setButton(AlertDialog.BUTTON_NEGATIVE, "NO",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alert.show();


                 //Finally Show the toast
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchMainActivity();
            }
        });
    }

    private void launchMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

