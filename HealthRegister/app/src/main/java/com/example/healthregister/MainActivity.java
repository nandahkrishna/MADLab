package com.example.healthregister;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Details details = new Details();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.marital_status, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);

        Button button = (Button) findViewById(R.id.submitButton);
        button.setOnClickListener(new OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {

                EditText e1 = (EditText) findViewById(R.id.editText2);
                details.name = e1.getText().toString();

                EditText e2 = (EditText) findViewById((R.id.editText3));
                details.address = e2.getText().toString();

                DatePicker d = (DatePicker) findViewById((R.id.simpleDatePicker));
                Integer day = (Integer) d.getDayOfMonth();
                Integer month = (Integer) (d.getMonth() + 1);
                Integer year = (Integer) d.getYear();
                details.dob = day.toString() + "/" + month.toString() + "/" + year.toString();

                RadioGroup r = (RadioGroup) findViewById(R.id.RadioGroup);
                Integer rid = (Integer)r.getCheckedRadioButtonId();

                RadioButton r1 = (RadioButton) findViewById(rid);
                details.gender = r1.getText().toString();

                final Spinner s = (Spinner) findViewById(R.id.spinner);
                String text = s.getSelectedItem().toString();
                details.marital = text;

                EditText e4 = (EditText) findViewById((R.id.editText6));
                details.phone = e4.getText().toString();

                TimePicker t = (TimePicker) findViewById(R.id.timePicker);
                Integer hour = (Integer) t.getHour();
                Integer min = (Integer) t.getMinute();
                details.time = hour.toString() + ":" + min.toString();

                CheckBox c1 = (CheckBox) findViewById(R.id.simpleCheckBox1);
                int flag = 0;
                String t1 = "";
                if(c1.isChecked())
                    t1 = c1.getText().toString();
                else
                    flag++;

                CheckBox c2 = (CheckBox) findViewById(R.id.simpleCheckBox2);
                String t2 = "";
                if(c2.isChecked())
                    t2 = c2.getText().toString();
                else
                    flag++;

                if(flag != 2)
                    details.addiction = t1 + " " + t2;
                else
                    details.addiction = "None";

                NotificationCompat.Builder builder =
                        new NotificationCompat.Builder(getApplicationContext(), "notify_channel")
                                .setSmallIcon(R.mipmap.ic_launcher_round)
                                .setContentTitle("Health Register Submission")
                                .setContentText("You've successfully registered. Tap for more.")
                                .setAutoCancel(true)
                                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
                Intent notificationIntent = new Intent(getApplicationContext(), Details.class);
                notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                notificationIntent.putExtra("Details", details);
                PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, notificationIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(pendingIntent);
                NotificationManager manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                {
                    String channelId = "notify_channel";
                    NotificationChannel channel = new NotificationChannel(
                            channelId,
                            "Notification channel",
                            NotificationManager.IMPORTANCE_HIGH);
                    manager.createNotificationChannel(channel);
                    builder.setChannelId(channelId);
                }
                manager.notify(0, builder.build());

                Context context = getApplicationContext();
                Intent intent = new Intent(context, Details.class);
                intent.putExtra("Details", details);
                startActivity(intent);
            }
        });

        Button rbutton = (Button) findViewById(R.id.resetButton);
        rbutton.setOnClickListener(new OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                EditText e1 = (EditText) findViewById(R.id.editText2);
                EditText e2 = (EditText) findViewById(R.id.editText3);
                DatePicker d = (DatePicker) findViewById((R.id.simpleDatePicker));
                RadioGroup r = (RadioGroup) findViewById(R.id.RadioGroup);
                final Spinner s = (Spinner) findViewById(R.id.spinner);
                EditText e4 = (EditText) findViewById((R.id.editText6));
                TimePicker t = (TimePicker) findViewById(R.id.timePicker);
                CheckBox c1 = (CheckBox) findViewById(R.id.simpleCheckBox1);
                CheckBox c2 = (CheckBox) findViewById(R.id.simpleCheckBox2);
                e1.setText("");
                e2.setText("");
                Calendar calendar = Calendar.getInstance();
                d.updateDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
                r.clearCheck();
                e4.setText("");
                t.setHour(calendar.get(Calendar.HOUR_OF_DAY));
                t.setMinute(calendar.get(Calendar.MINUTE));
                if(c1.isChecked())
                    c1.toggle();
                if(c2.isChecked())
                    c2.toggle();
            }
        });

    }
}
