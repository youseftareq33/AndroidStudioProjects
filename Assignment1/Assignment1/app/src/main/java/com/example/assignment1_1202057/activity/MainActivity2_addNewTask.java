package com.example.assignment1_1202057.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment1_1202057.R;
import com.example.assignment1_1202057.classes.Task;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity2_addNewTask extends AppCompatActivity {
    private TextView textViewErrorMessage;

    private EditText editTextTaskTitle;

    private EditText editTextTaskDescription;

    private Spinner spinnerYear;
    private String selectedYear;
    private Spinner spinnerMonth;
    private String selectedMonth;
    private Spinner spinnerDay;
    private String selectedDay;
    private Spinner spinnerHours;
    private String selectedHours;
    private Spinner spinnerMinutes;
    private String selectedMinutes;
    private Spinner spinnerDayNight;
    private String selectedDayNight;

    private Button buttonAddTask;

    private ArrayList<Task> al_task;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    public static final String DATA = "DATA";

    private int id=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2_addnewtask);
        setupSharedPrefs();

        // Initialize views
        textViewErrorMessage=findViewById(R.id.textViewErrorMessage);
        textViewErrorMessage.setVisibility(View.INVISIBLE);

        editTextTaskTitle=findViewById(R.id.editTextTaskTitle);

        editTextTaskDescription=findViewById(R.id.editTextTaskDescription);

        spinnerYear=findViewById(R.id.spinnerYear);
        String years[]={"yyyy","2023","2024","2025","2026","2027","2028","2029","2030"};
        ArrayAdapter<String> spinner_year_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, years);
        spinner_year_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerYear.setAdapter(spinner_year_adapter);
        spinnerYear.setSelection(0);
        selectedYear="";
        spinnerYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedYear = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerMonth=findViewById(R.id.spinnerMonth);
        String months[]={"m","Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
        ArrayAdapter<String> spinner_month_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, months);
        spinner_month_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMonth.setAdapter(spinner_month_adapter);
        spinnerMonth.setSelection(0);
        selectedMonth="";
        spinnerMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedMonth = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerDay=findViewById(R.id.spinnerDay);
        String days[]={"dd","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
        ArrayAdapter<String> spinner_day_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, days);
        spinner_day_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDay.setAdapter(spinner_day_adapter);
        spinnerDay.setSelection(0);
        selectedDay="";
        spinnerDay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedDay = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerHours=findViewById(R.id.spinnerHours);
        String hours[]={"hh","01","02","03","04","05","06","07","08","09","10","11","12"};
        ArrayAdapter<String> spinner_hour_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, hours);
        spinner_hour_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerHours.setAdapter(spinner_hour_adapter);
        spinnerHours.setSelection(0);
        selectedHours="";
        spinnerHours.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedHours = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerMinutes=findViewById(R.id.spinnerMinutes);
        String minutes[]=new String[62];
        minutes[0]="mm";
        minutes[1]="00";
        minutes[2]="01";
        minutes[3]="02";
        minutes[4]="03";
        minutes[5]="04";
        minutes[6]="05";
        minutes[7]="06";
        minutes[8]="07";
        minutes[9]="08";
        minutes[10]="09";
        for(int i=11;i<=61;i++){
            minutes[i]=(i-1)+"";
        }
        ArrayAdapter<String> spinner_minute_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, minutes);
        spinner_minute_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMinutes.setAdapter(spinner_minute_adapter);
        spinnerMinutes.setSelection(0);
        selectedMinutes="";
        spinnerMinutes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedMinutes = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerDayNight=findViewById(R.id.spinnerDayNight);
        String dayNight[]={"AM","PM"};
        ArrayAdapter<String> spinner_dayNight_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, dayNight);
        spinner_dayNight_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDayNight.setAdapter(spinner_dayNight_adapter);
        spinnerDayNight.setSelection(0);
        selectedDayNight="";
        spinnerDayNight.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedDayNight = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        buttonAddTask=findViewById(R.id.buttonAddTask);

        al_task=new ArrayList<>();

        //************* action

        // AddTask button
        buttonAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewErrorMessage.setVisibility(View.GONE);
                if(editTextTaskTitle.getText().toString().equals(null) || editTextTaskDescription.getText().toString().equals(null) ||
                        (selectedYear.equals("") || selectedYear.equals("yyyy")) || (selectedMonth.equals("") || selectedMonth.equals("m")) ||
                        (selectedDay.equals("") || selectedDay.equals("dd")) || (selectedHours.equals("") || selectedHours.equals("hh")) ||
                        (selectedMinutes.equals("") || selectedMinutes.equals("mm"))){
                    textViewErrorMessage.setVisibility(View.VISIBLE);
                }
                else{
                    // load tasks from shared preference
                    String prevs_TasksString = prefs.getString(DATA, "");
                    if (!prevs_TasksString.isEmpty()) {
                        Gson gson = new Gson();
                        Type type = new TypeToken<ArrayList<Task>>() {}.getType();
                        al_task = gson.fromJson(prevs_TasksString, type);
                        id=al_task.size();
                    }


                    // add new task to the prevs tasks
                    String date=selectedYear+" "+"/"+" "+selectedMonth+" "+"/"+" "+selectedDay;
                    String time=selectedHours+":"+selectedMinutes+"  "+selectedDayNight;


                    Task task=null;
                    task=new Task(id,editTextTaskTitle.getText().toString(),editTextTaskDescription.getText().toString(),date,time,"Due");
                    al_task.add(task);

                    Gson gson = new Gson();
                    String tasksString = gson.toJson(al_task);

                    editor.putString(DATA, tasksString);
                    editor.apply();

                    Intent intent = new Intent(MainActivity2_addNewTask.this, MainActivity3_addTaskSuccessful.class);
                    startActivity(intent);
                }

            }
        });


    }

    private void setupSharedPrefs() {
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
    }

}
