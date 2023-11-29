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
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment1_1202057.R;
import com.example.assignment1_1202057.classes.Task;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MainActivity5_DueTaskDetails extends AppCompatActivity {

    private TextView v_TaskTitle;
    private EditText v_EditTaskTitle;

    private TextView v_TaskDescription;
    private EditText v_EditTaskDescription;

    private TextView v_TaskDate;
    private TextView EditDate;
    private LinearLayout LLEditDate;
    private Spinner EditSpinnerYear;
    private String selectedYear;
    private Spinner EditSpinnerMonth;
    private String selectedMonth;
    private Spinner EditSpinnerDay;
    private String selectedDay;

    private TextView textAt;

    private TextView v_TaskTime;
    private TextView EditTime;
    private LinearLayout LLEditTime;
    private Spinner EditSpinnerHours;
    private String selectedHours;
    private Spinner EditSpinnerMinutes;
    private String selectedMinutes;
    private Spinner EditSpinnerDayNight;
    private String selectedDayNight;

    private TextView v_TaskStatus;
    private Button buttonEdit;
    private Button buttonConfirmEdits;
    private Button buttonCancelEdits;
    private Button buttonCompleteTask;

    private Task task;
    private ArrayList<Task> al_task;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    public static final String DATA = "DATA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity5_duetaskdetails);

        // Initialize views
        al_task=new ArrayList<>();

        Intent intent = getIntent();
        int id = (int)intent.getExtras().get("task_id");

        // load tasks from shared preference
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String prevs_TasksString = prefs.getString(DATA, "");
        editor = prefs.edit();

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Task>>() {}.getType();
        al_task = gson.fromJson(prevs_TasksString, type);

        task=null;

        for(int i=0;i<al_task.size();i++){
            if(al_task.get(i).getId()==id){
                task=al_task.get(i);
            }
        }



        v_TaskTitle=findViewById(R.id.v_TaskTitle);
        v_TaskTitle.setText(task.getTitle());
        v_EditTaskTitle=findViewById(R.id.v_EditTaskTitle);
        v_EditTaskTitle.setVisibility(View.INVISIBLE);

        v_TaskDescription=findViewById(R.id.v_TaskDescription);
        v_TaskDescription.setText(task.getDescription());
        v_EditTaskDescription=findViewById(R.id.v_EditTaskDescription);
        v_EditTaskDescription.setVisibility(View.INVISIBLE);

        v_TaskDate=findViewById(R.id.v_TaskDate);
        v_TaskDate.setText(task.getDate());
        EditDate=findViewById(R.id.EditDate);
        EditDate.setVisibility(View.INVISIBLE);
        LLEditDate=findViewById(R.id.LLEditDate);
        LLEditDate.setVisibility(View.INVISIBLE);
        EditSpinnerYear=findViewById(R.id.EditSpinnerYear);
        String years[]={"yyyy","2023","2024","2025","2026","2027","2028","2029","2030"};
        ArrayAdapter<String> spinner_year_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, years);
        spinner_year_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        EditSpinnerYear.setAdapter(spinner_year_adapter);
        selectedYear="";
        EditSpinnerYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedYear = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        EditSpinnerMonth=findViewById(R.id.EditSpinnerMonth);
        String months[]={"m","Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
        ArrayAdapter<String> spinner_month_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, months);
        spinner_month_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        EditSpinnerMonth.setAdapter(spinner_month_adapter);
        selectedMonth="";
        EditSpinnerMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedMonth = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        EditSpinnerDay=findViewById(R.id.EditSpinnerDay);
        String days[]={"dd","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
        ArrayAdapter<String> spinner_day_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, days);
        spinner_day_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        EditSpinnerDay.setAdapter(spinner_day_adapter);
        selectedDay="";
        EditSpinnerDay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedDay = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        textAt=findViewById(R.id.textAt);

        v_TaskTime=findViewById(R.id.v_TaskTime);
        v_TaskTime.setText(task.getTime());
        EditTime=findViewById(R.id.EditTime);
        EditTime.setVisibility(View.INVISIBLE);
        LLEditTime=findViewById(R.id.LLEditTime);
        LLEditTime.setVisibility(View.INVISIBLE);
        EditSpinnerHours=findViewById(R.id.EditSpinnerHours);
        String hours[]={"hh","01","02","03","04","05","06","07","08","09","10","11","12"};
        ArrayAdapter<String> spinner_hour_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, hours);
        spinner_hour_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        EditSpinnerHours.setAdapter(spinner_hour_adapter);
        selectedHours="";
        EditSpinnerHours.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedHours = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        EditSpinnerMinutes=findViewById(R.id.EditSpinnerMinutes);
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
        EditSpinnerMinutes.setAdapter(spinner_minute_adapter);
        selectedMinutes="";
        EditSpinnerMinutes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedMinutes = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        EditSpinnerDayNight=findViewById(R.id.EditSpinnerDayNight);
        String dayNight[]={"AM","PM"};
        ArrayAdapter<String> spinner_dayNight_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, dayNight);
        spinner_dayNight_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        EditSpinnerDayNight.setAdapter(spinner_dayNight_adapter);
        selectedDayNight="";
        EditSpinnerDayNight.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedDayNight = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        v_TaskStatus=findViewById(R.id.v_TaskStatus);
        v_TaskStatus.setText(task.getStatus());

        buttonEdit=findViewById(R.id.buttonEdit);

        buttonConfirmEdits=findViewById(R.id.buttonConfirmEdits);
        buttonConfirmEdits.setVisibility(View.GONE);

        buttonCancelEdits=findViewById(R.id.buttonCancelEdits);
        buttonCancelEdits.setVisibility(View.GONE);

        buttonCompleteTask=findViewById(R.id.buttonCompleteTask);


        //************* action

        // Edit button
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v_TaskTitle.setVisibility(View.GONE);
                v_EditTaskTitle.setVisibility(View.VISIBLE);
                v_EditTaskTitle.setText(task.getTitle());

                v_TaskDescription.setVisibility(View.GONE);
                v_EditTaskDescription.setVisibility(View.VISIBLE);
                v_EditTaskDescription.setText(task.getDescription());

                v_TaskDate.setVisibility(View.INVISIBLE);
                textAt.setVisibility(View.INVISIBLE);
                v_TaskTime.setVisibility(View.GONE);
                EditDate.setVisibility(View.VISIBLE);
                LLEditDate.setVisibility(View.VISIBLE);
                SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy / MMM / d", Locale.ENGLISH);
                try {
                    Date date=dateFormat.parse(task.getDate());
                    System.out.println(task.getDate());
                    SimpleDateFormat yearFormat=new SimpleDateFormat("yyyy");
                    selectedYear=yearFormat.format(date);
                    SimpleDateFormat monthFormat=new SimpleDateFormat("MMM");
                    selectedMonth=monthFormat.format(date);
                    SimpleDateFormat dayFormat=new SimpleDateFormat("d");
                    selectedDay=dayFormat.format(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }


                for(int i=0;i<years.length;i++){
                    if(years[i].equals(selectedYear)){
                        EditSpinnerYear.setSelection(i);
                        break;
                    }
                }

                for(int i=0;i<months.length;i++){
                    if(months[i].equals(selectedMonth)){
                        EditSpinnerMonth.setSelection(i);
                        break;
                    }
                }

                for(int i=0;i< days.length;i++){
                    if(days[i].equals(selectedDay)){
                        EditSpinnerDay.setSelection(i);
                        break;
                    }
                }

                EditTime.setVisibility(View.VISIBLE);
                LLEditTime.setVisibility(View.VISIBLE);
                SimpleDateFormat timeFormat=new SimpleDateFormat("hh:mm  a", Locale.ENGLISH);
                try {
                    Date time=timeFormat.parse(task.getTime());
                    SimpleDateFormat hourFormat=new SimpleDateFormat("hh");
                    selectedHours=hourFormat.format(time);
                    SimpleDateFormat minuteFormat=new SimpleDateFormat("mm");
                    selectedMinutes=minuteFormat.format(time);
                    SimpleDateFormat dayNightFormat=new SimpleDateFormat("a");
                    selectedDayNight=dayNightFormat.format(time);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                for(int i=0;i<hours.length;i++){
                    if(hours[i].equals(selectedHours)){
                        EditSpinnerHours.setSelection(i);
                        break;
                    }
                }

                for(int i=0;i<minutes.length;i++){
                    if(minutes[i].equals(selectedMinutes)){
                        EditSpinnerMinutes.setSelection(i);
                        break;
                    }
                }

                for(int i=0;i<dayNight.length;i++){
                    if(dayNight[i].equals(selectedDayNight)){
                        EditSpinnerDayNight.setSelection(i);
                        break;
                    }
                }

                buttonCompleteTask.setVisibility(View.GONE);
                buttonEdit.setVisibility(View.GONE);
                buttonConfirmEdits.setVisibility(View.VISIBLE);
                buttonCancelEdits.setVisibility(View.VISIBLE);

            }
        });

        // ConfirmEdits button
        buttonConfirmEdits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean dataChanged=false;

                for(int i=0;i<al_task.size();i++){
                    if(al_task.get(i).getId()==id){

                        if(!al_task.get(i).getTitle().equals(v_EditTaskTitle.getText().toString())){
                            al_task.get(i).setTitle(v_EditTaskTitle.getText().toString());
                            dataChanged=true;
                        }
                        if(!al_task.get(i).getDescription().equals(v_EditTaskDescription.getText().toString())){
                            al_task.get(i).setDescription(v_EditTaskDescription.getText().toString());
                            dataChanged=true;
                        }

                        String date=selectedYear+" "+"/"+" "+selectedMonth+" "+"/"+" "+selectedDay;
                        String time=selectedHours+":"+selectedMinutes+"  "+selectedDayNight;
                        if(!al_task.get(i).getDate().equals(date)){
                            al_task.get(i).setDate(date);
                            dataChanged=true;
                        }
                        if(!al_task.get(i).getTime().equals(time)){
                            al_task.get(i).setTime(time);
                            dataChanged=true;
                        }

                        break;
                    }
                }

                if(dataChanged==true){
                    String updateTasksString = gson.toJson(al_task);
                    editor.putString(DATA, updateTasksString);
                    editor.apply();

                    // Redirect to MainActivity7_editTaskSuccessful
                    Intent intent = new Intent(MainActivity5_DueTaskDetails.this, MainActivity7_editTaskSuccessful.class);
                    intent.putExtra("task_id", task.getId());
                    startActivity(intent);
                }


                v_TaskTitle.setVisibility(View.VISIBLE);
                v_TaskTitle.setText(task.getTitle());
                v_EditTaskTitle.setVisibility(View.GONE);

                v_TaskDescription.setVisibility(View.VISIBLE);
                v_TaskDescription.setText(task.getDescription());
                v_EditTaskDescription.setVisibility(View.GONE);

                v_TaskDate.setVisibility(View.VISIBLE);
                textAt.setVisibility(View.VISIBLE);
                v_TaskTime.setVisibility(View.VISIBLE);
                EditDate.setVisibility(View.INVISIBLE);
                LLEditDate.setVisibility(View.INVISIBLE);
                v_TaskDate.setText(task.getDate());
                EditTime.setVisibility(View.INVISIBLE);
                LLEditTime.setVisibility(View.INVISIBLE);

                buttonConfirmEdits.setVisibility(View.GONE);
                buttonCancelEdits.setVisibility(View.GONE);
                buttonEdit.setVisibility(View.VISIBLE);
                buttonCompleteTask.setVisibility(View.VISIBLE);





            }
        });



        // CancelEdits button
        buttonCancelEdits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v_TaskTitle.setVisibility(View.VISIBLE);
                v_EditTaskTitle.setVisibility(View.GONE);

                v_TaskDescription.setVisibility(View.VISIBLE);
                v_EditTaskDescription.setVisibility(View.GONE);

                v_TaskDate.setVisibility(View.VISIBLE);
                textAt.setVisibility(View.VISIBLE);
                v_TaskTime.setVisibility(View.VISIBLE);
                EditDate.setVisibility(View.INVISIBLE);
                LLEditDate.setVisibility(View.INVISIBLE);
                EditTime.setVisibility(View.INVISIBLE);
                LLEditTime.setVisibility(View.INVISIBLE);

                buttonConfirmEdits.setVisibility(View.GONE);
                buttonCancelEdits.setVisibility(View.GONE);
                buttonEdit.setVisibility(View.VISIBLE);
                buttonCompleteTask.setVisibility(View.VISIBLE);
            }
        });

        // CompleteTask button
        buttonCompleteTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for(int i=0;i<al_task.size();i++){
                    if(al_task.get(i).getId()==id){
                        al_task.get(i).setStatus("Done");
                    }
                }

                String updateTasksString = gson.toJson(al_task);
                editor.putString(DATA, updateTasksString);
                editor.apply();

                // Redirect to MainActivity1_home
                Intent intent = new Intent(MainActivity5_DueTaskDetails.this, MainActivity1_home.class);
                startActivity(intent);
            }
        });



    }

    @Override
    public void onBackPressed() {
        // Redirect to MainActivity1_home
        Intent intent = new Intent(MainActivity5_DueTaskDetails.this, MainActivity1_home.class);
        startActivity(intent);

        super.onBackPressed();
    }

}
