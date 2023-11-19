package com.example.assignment1_1202057.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.assignment1_1202057.R;
import com.example.assignment1_1202057.activity_tools.TaskAdapter;
import com.example.assignment1_1202057.classes.Task;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity4_doneTask extends AppCompatActivity{

    private ListView listViewDoneTask;

    private ArrayList<Task> al_task;
    private ArrayList<Task> al_DoneTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity4_donetask);

        // Initialize views
        listViewDoneTask = findViewById(R.id.listViewDoneTask);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String tasksString = prefs.getString("DATA", "");

        al_task=new ArrayList<>();
        al_DoneTask=new ArrayList<>();

        if (!tasksString.isEmpty()) {
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<Task>>() {
            }.getType();
            al_task = gson.fromJson(tasksString, type);

            for (int i = 0; i < al_task.size(); i++) {
                if (al_task.get(i).getStatus().equals("Done")) {
                    al_DoneTask.add(al_task.get(i));
                }
            }

            TaskAdapter listAdapter = new TaskAdapter(this, al_DoneTask);


            listViewDoneTask.setAdapter(listAdapter);

            AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent,
                                        View view,
                                        int position,
                                        long id) {
                    Intent intent = new Intent(MainActivity4_doneTask.this, MainActivity6_DoneTaskDetails.class);

                    intent.putExtra("task_id", al_DoneTask.get((int)id).getId());
                    startActivity(intent);

                }
            };

            listViewDoneTask.setOnItemClickListener(itemClickListener);
        }
    }
}
