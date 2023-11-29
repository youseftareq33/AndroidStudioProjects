package com.example.assignment1_1202057.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import com.example.assignment1_1202057.R;

public class MainActivity3_addTaskSuccessful extends AppCompatActivity{

    private Button buttonDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity3_addtasksuccessful);

        // Initialize views
        buttonDone=findViewById(R.id.buttonDone);

        //************* action

        // AddNewTaskPage button
        buttonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity3_addTaskSuccessful.this, MainActivity1_home.class);
                startActivity(intent);
            }
        });

    }
}
