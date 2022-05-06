package com.example.slutopgaveapp1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class typeNameWithRadioBtn extends AppCompatActivity implements View.OnClickListener{

    private static final int[] BTN_IDS = {
            R.id.sendNameBtn,
            R.id.motherBtn,
            R.id.fatherBtn,
            R.id.catBtn,
            R.id.dogBtn
    };
    private List<Button> buttons;
    //Variables
    private Intent intent;
    private EditText typeName;
    private RadioButton radioButton;
    private RadioGroup radioGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_name_with_radio_btn);

        typeName = findViewById(R.id.typeName);

        buttons = new ArrayList<>(BTN_IDS.length);

        for (int number : BTN_IDS) {
            Button button = findViewById(number);
            button.setOnClickListener(this);
            buttons.add(button);
        }

        intent = getIntent();


        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
    }

    public void onClickButtonMethod(View v) {
        int selectedId = radioGroup.getCheckedRadioButtonId();
        radioButton = (RadioButton) findViewById(selectedId);
        if (selectedId == -1) {
            Toast.makeText(typeNameWithRadioBtn.this, "Nothing selected", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(typeNameWithRadioBtn.this, radioButton.getText(), Toast.LENGTH_SHORT).show();
            String str = typeName.getText().toString();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.putExtra("message_key", str);
            startActivity(intent);
        }
    }


    @Override
    public void onClick(View v) {
       //Knap har virket til at få navn ud, nogle minutter før efter nogle ændringer, virker det ikke længere dsv.
    }
}