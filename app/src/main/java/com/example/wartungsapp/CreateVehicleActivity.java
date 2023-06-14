package com.example.wartungsapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class CreateVehicleActivity extends AppCompatActivity {

    private MainActivity mainActivity;
    private EditText editTextVehicleName;
    private ActivityResultLauncher<String> takeImage;
    private Uri imageURI;
    private EditText editTextMileage;
    private EditText editTextMonthlyMileage;
    private Button saveButton;
    private Vehicle newVehicle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehicle);

        this.mainActivity = new MainActivity();

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
                        imageURI = result;
                    }
                }
        );

        ImageViewVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeImage.launch("image/*");
            }
        });

        editTextVehicleName = findViewById(R.id.editTextVehicleName);

        editTextMileage = findViewById(R.id.editTextMileage);
        editTextMileage.addTextChangedListener(onTextChangedListener(editTextMileage));

        editTextMonthlyMileage = findViewById(R.id.editTextMonthlyMileage);
        editTextMonthlyMileage.addTextChangedListener(onTextChangedListener(editTextMonthlyMileage));

        saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = editTextVehicleName.getText().toString();
                        int mileage = Integer.parseInt(editTextMileage.getText().toString().replaceAll("\\.", ""));
                        int monthlyMileage = Integer.parseInt(editTextMonthlyMileage.getText().toString().replaceAll("\\.", ""));
                        newVehicle = new Vehicle(name, mileage, monthlyMileage, imageURI);

                        //newVehicle.save();
                        Intent intent = new Intent(CreateVehicleActivity.this, MainActivity.class);
                        intent.putExtra("newVehicle", newVehicle);

                        startActivity(intent);
                    }
                }
        );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

    private TextWatcher onTextChangedListener(EditText editText) {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                editText.removeTextChangedListener(this);

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
                    editText.setText(formattedString);
                    editText.setSelection(editText.getText().length());
                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                }

                editText.addTextChangedListener(this);
            }
        };
    }
}
