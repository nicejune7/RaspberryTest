package com.city.smart.raspberrytest;

import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DemoActivity extends AppCompatActivity {
    TextView tv_ip;
    EditText et_newIp;
    Button btn_set;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        tv_ip = (TextView)findViewById(R.id.tv_demo_ip);
        et_newIp = (EditText)findViewById(R.id.et_demo_newip);
        btn_set = (Button) findViewById(R.id.btn_demo_set);

        final SharedPreferences prefs = getSharedPreferences("ip", MODE_PRIVATE);
        String ip = prefs.getString("ip", "NO IP VALUE");
        tv_ip.setText(ip);

        btn_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newIp = et_newIp.getText().toString();
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("ip", newIp);
                editor.commit();

                String checkIp = prefs.getString("ip", "FAIL TO SAVE");
                tv_ip.setText(checkIp);

                Snackbar.make(getWindow().getDecorView().getRootView(), "저장", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
