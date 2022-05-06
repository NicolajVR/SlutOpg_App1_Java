package com.example.slutopgaveapp1;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class setColorAndSend extends AppCompatActivity implements View.OnClickListener {
    private static final int[] BUTTON_IDS = {
            R.id.sendColorBtn,
    };
    //Store all buttons in an array for code optimization
    private List<Button> buttons;
    private Intent intent;
    private TextView sendColor;
    private Spinner color;
    private TextView ShowColorBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_color_and_send);

        buttons = new ArrayList<>(BUTTON_IDS.length);

        ShowColorBox = findViewById(R.id.ShowColorBox);

        // Here is a dynamic way to findviewbyid and set onclicklisterner on all buttons
        for (int id : BUTTON_IDS) {
            Button button = findViewById(id);
            button.setOnClickListener(this);
            buttons.add(button);

            //Dropdown section
            color = findViewById(R.id.dropDown);
            // DropDown (Spinner)
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Colors, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
            color.setAdapter(adapter);


        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sendColorBtn:
                //Changes color to textview.
                Toast.makeText(this, "Color was sent!", Toast.LENGTH_SHORT).show();

                String str = color.getSelectedItem().toString();
                ShowColorBox.setBackgroundColor(Color.parseColor(str));

                break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
    }

}