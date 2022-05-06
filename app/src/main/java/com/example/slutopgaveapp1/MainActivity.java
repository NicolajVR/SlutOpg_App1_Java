package com.example.slutopgaveapp1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int[] BUTTON_IDS = {
            R.id.getOnesNameBtn,
            R.id.getOnesColorBtn
    };
    //Store all buttons in an array for code optimization
    private List<Button> buttons;
    //variables
    private ActivityResultLauncher<Intent> launcher;
    private TextView getName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getName = findViewById(R.id.gettingName);

        buttons = new ArrayList<Button>(BUTTON_IDS.length);

        // setting ID's dynamically.
        for (int id : BUTTON_IDS) {
            Button button = findViewById(id);
            button.setOnClickListener(this);
            buttons.add(button);

            Intent intent = getIntent();
            String str = intent.getStringExtra("message_key");
            getName.setText(str);

            launcher = registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult result) {
                            if (result.getResultCode() == AppConstants.RESULT_CODE_SECOND) {
                                Intent intent = result.getData();
                                Toast.makeText(MainActivity.this, intent.getStringExtra("msg"), Toast.LENGTH_SHORT).show();
                                return;
                            }
                            if (result.getResultCode() == AppConstants.RESULT_CODE_THIRD) {
                                Toast.makeText(MainActivity.this, "*******", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                    });
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.getOnesNameBtn:
                Intent intent = new Intent(MainActivity.this, typeNameWithRadioBtn.class);
                intent.putExtra("fromMain", "Besked fra main");
                launcher.launch(intent);
                Toast.makeText(this, "getting ones name was pressed", Toast.LENGTH_SHORT).show();
                break;

            case R.id.getOnesColorBtn:
                Intent intent2 = new Intent(MainActivity.this, setColorAndSend.class);
                intent2.putExtra("fromMain", "Besked fra main");
                startActivity(intent2);

                Toast.makeText(this, "get ones color was pressed", Toast.LENGTH_SHORT).show();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
    }
}
