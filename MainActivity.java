package com.example.d064989.surprise;


import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private ConstraintLayout screen;
    SensorManager m;
    TextView welcome;
    Button bScharf;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        m = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        m.registerListener(this, m.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION),
                SensorManager.SENSOR_DELAY_NORMAL);

        screen = findViewById(R.id.Layout);
        welcome = findViewById(R.id.welcome);
        bScharf = findViewById(R.id.bScharf);

        screen.setBackgroundColor(Color.BLUE);
        welcome.setText("READY to catch a thief!");
    }

    public void onScharf(View button) {
        screen.setBackgroundColor(Color.YELLOW);
        welcome.setText("Die App ist jetzt im Scharf-Modus");
        bScharf.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = Math.abs(event.values[0]);
        float y = Math.abs(event.values[1]);
        float z = Math.abs(event.values[2]);

        if (bScharf.getVisibility() == View.INVISIBLE) {

            if (x - 0 > 3 || y - 0 > 3 || z - 0 > 3) {
                welcome.setText("Das Handy wurde geklaut, HELP!");
                screen.setBackgroundColor(Color.RED);
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (bScharf.getVisibility() == View.INVISIBLE) {

            screen.setBackgroundColor(Color.BLUE);
            welcome.setText("READY to catch a thief!");
            bScharf.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

}
