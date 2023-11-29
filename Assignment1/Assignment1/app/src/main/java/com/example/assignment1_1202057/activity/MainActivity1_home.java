package com.example.assignment1_1202057.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment1_1202057.R;
import com.example.assignment1_1202057.classes.Task;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity1_home extends AppCompatActivity {

    private Button buttonAddNewTaskPage;
    private Button buttonViewDoneTask;
    private ListView listViewDueTask;

    private ArrayList<Task> al_task;
    private ArrayList<Task> al_DueTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity1_home);


        // Initialize views
        buttonAddNewTaskPage=findViewById(R.id.buttonAddNewTaskPage);

        buttonViewDoneTask=findViewById(R.id.buttonViewDoneTask);

        listViewDueTask = findViewById(R.id.listViewDueTask);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String tasksString = prefs.getString("DATA", "");

        al_task=new ArrayList<>();
        al_DueTask=new ArrayList<>();

        if (!tasksString.isEmpty()) {
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<Task>>() {}.getType();
            al_task = gson.fromJson(tasksString, type);


            for(int i=0;i<al_task.size();i++){
                if(al_task.get(i).getStatus().equals("Due")){
                    al_DueTask.add(al_task.get(i));
                }
            }

            ArrayAdapter<Task> listAdapter = new ArrayAdapter<Task>(this,
                    android.R.layout.simple_list_item_1, al_DueTask);

            listViewDueTask.setAdapter(listAdapter);

            AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent,
                                        View view,
                                        int position,
                                        long id) {
                    Intent intent = new Intent(MainActivity1_home.this, MainActivity5_DueTaskDetails.class);

                    intent.putExtra("task_id", al_DueTask.get((int)id).getId());
                    startActivity(intent);

                }
            };
            listViewDueTask.setOnItemClickListener(itemClickListener);
        }




        //************* action

        // AddNewTaskPage button
        buttonAddNewTaskPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity1_home.this, MainActivity2_addNewTask.class);
                startActivity(intent);
            }
        });

        // ViewDoneTask button
        buttonViewDoneTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity1_home.this, MainActivity4_doneTask.class);
                startActivity(intent);
            }
        });


    }

}