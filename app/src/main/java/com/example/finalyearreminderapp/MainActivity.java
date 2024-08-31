package com.example.finalyearreminderapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private int notificationId=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.setBtn).setOnClickListener(this);
        findViewById(R.id.cancelBtn).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        EditText editText=findViewById(R.id.editText);
        TimePicker timePicker=findViewById(R.id.timePicker);

        Intent intent= new Intent(MainActivity.this,AlarmReceiver.class);
        intent.putExtra("notificationId",notificationId);
        intent.putExtra("message",editText.getText().toString());

        PendingIntent alarmIntent=PendingIntent.getBroadcast(
                MainActivity.this,0,intent,PendingIntent.FLAG_CANCEL_CURRENT
        );

        AlarmManager alarmManager=(AlarmManager) getSystemService(ALARM_SERVICE);
        switch (view.getId()){
            case R.id.setBtn:
                int hour=timePicker.getCurrentHour();
                int minutes=timePicker.getCurrentMinute();

                Calendar startTime=Calendar.getInstance();
                startTime.set(Calendar.HOUR_OF_DAY,hour);
                startTime.set(Calendar.MINUTE,minutes);
                startTime.set(Calendar.SECOND,0);
                long alarmStartTime= startTime.getTimeInMillis();

                alarmManager.set(AlarmManager.RTC_WAKEUP,alarmStartTime,alarmIntent);
                Toast.makeText(this, "Done!", Toast.LENGTH_SHORT).show();
                break;

            case R.id.cancelBtn:
                alarmManager.cancel(alarmIntent);
                Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return  true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if(item.getItemId()==R.id.GotoGroupChat){
            Intent intent=new Intent(MainActivity.this, Login.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);

    }
}