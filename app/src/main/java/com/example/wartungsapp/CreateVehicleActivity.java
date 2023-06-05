package com.example.wartungsapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class CreateVehicleActivity extends AppCompatActivity {

    ActivityResultLauncher<String> takeImage;
    public EditText editTextMileage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehicle);

        Intent i = getIntent();

        //setup toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.createVehicleToolbarTitle);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        //setup imageview
        ImageView ImageViewVehicle = findViewById(R.id.imageViewVehicle);

        takeImage = registerForActivityResult(
                new ActivityResultContracts.GetContent(),
                new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {
                        ImageViewVehicle.setImageURI(result);
                        Log.d("debug house cheese", result.toString());
                    }
                }
        );

        ImageViewVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeImage.launch("image/*");
            }
        });

        editTextMileage = findViewById(R.id.editTextMileage);
        editTextMileage.addTextChangedListener(onTextChangedListener());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

    private TextWatcher onTextChangedListener() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                editTextMileage.removeTextChangedListener(this);

                try {
                    String originalString = s.toString();

                    Long longval;
                    if (originalString.contains(".")) {
                        originalString = originalString.replaceAll("\\.", "");
                    }
                    longval = Long.parseLong(originalString);

                    DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
                    formatter.applyPattern("#,###,###,###");
                    String formattedString = formatter.format(longval);
                    formattedString = formattedString.replaceAll(",", ".");


                    //setting text after format to EditText
                    editTextMileage.setText(formattedString);
                    editTextMileage.setSelection(editTextMileage.getText().length());
                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                }

                editTextMileage.addTextChangedListener(this);
            }
        };
    }
}
