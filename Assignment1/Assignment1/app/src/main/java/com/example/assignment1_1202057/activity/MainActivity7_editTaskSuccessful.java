package com.example.assignment1_1202057.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment1_1202057.R;

public class MainActivity7_editTaskSuccessful extends AppCompatActivity {
    private Button buttonDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity7_edittasksuccesful);

        Intent intent = getIntent();
        int id = (int)intent.getExtras().get("task_id");

        // Initialize views
        buttonDone=findViewById(R.id.buttonDone);

        //************* action

        // AddNewTaskPage button
        buttonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity7_editTaskSuccessful.this, MainActivity5_DueTaskDetails.class);
                intent.putExtra("task_id", id);
                startActivity(intent);
            }
        });

    }
}
