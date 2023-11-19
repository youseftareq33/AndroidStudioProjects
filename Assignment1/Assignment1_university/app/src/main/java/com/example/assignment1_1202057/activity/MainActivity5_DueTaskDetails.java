package com.example.assignment1_1202057.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment1_1202057.R;
import com.example.assignment1_1202057.classes.Task;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity5_DueTaskDetails extends AppCompatActivity {

    private TextView v_TaskTitle;
    private TextView v_TaskDescription;
    private TextView v_TaskDate;
    private TextView v_TaskTime;
    private TextView v_TaskStatus;
    private Button buttonCompleteTask;

    private ArrayList<Task> al_task;
    private ArrayList<Task> al_DueTask;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    public static final String DATA = "DATA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity5_duetaskdetails);

        // Initialize views
        al_task=new ArrayList<>();
        al_DueTask=new ArrayList<>();

        Intent intent = getIntent();
        int id = (int)intent.getExtras().get("task_id");

        // load tasks from shared preference
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String prevs_TasksString = prefs.getString(DATA, "");
        editor = prefs.edit();

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Task>>() {}.getType();
        al_task = gson.fromJson(prevs_TasksString, type);

        for(int i=0;i<al_task.size();i++){
            if(al_task.get(i).getStatus().equals("Due")){
                al_DueTask.add(al_task.get(i));
            }
        }

        Task task= al_DueTask.get(id);

        v_TaskTitle=findViewById(R.id.v_TaskTitle);
        v_TaskTitle.setText(task.getTitle());

        v_TaskDescription=findViewById(R.id.v_TaskDescription);
        v_TaskDescription.setText(task.getDescription());

        v_TaskDate=findViewById(R.id.v_TaskDate);
        v_TaskDate.setText(task.getDate());

        v_TaskTime=findViewById(R.id.v_TaskTime);
        v_TaskTime.setText(task.getTime());

        v_TaskStatus=findViewById(R.id.v_TaskStatus);
        v_TaskStatus.setText(task.getStatus());

        buttonCompleteTask=findViewById(R.id.buttonCompleteTask);

        buttonCompleteTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Task> copyList = new ArrayList<>(al_task);

                copyList.get(id).setStatus("Done");

                String updateTasksString = gson.toJson(copyList);
                editor.putString(DATA, updateTasksString);
                editor.apply();

                // Redirect to MainActivity1_home
                Intent intent = new Intent(MainActivity5_DueTaskDetails.this, MainActivity1_home.class);
                startActivity(intent);
            }
        });
    }


}
