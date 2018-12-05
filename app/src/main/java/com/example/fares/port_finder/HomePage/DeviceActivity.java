package com.example.fares.port_finder.HomePage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.example.fares.port_finder.R;

public class DeviceActivity extends AppCompatActivity {



    Switch activationAlarm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device);
        activationAlarm = findViewById(R.id.activerAlarm);
        activationAlarm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                        Toast.makeText(getApplicationContext(),"True",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),"False",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
