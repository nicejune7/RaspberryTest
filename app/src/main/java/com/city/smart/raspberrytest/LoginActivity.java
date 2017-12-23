package com.city.smart.raspberrytest;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    EditText ipEdittext;
    EditText passwordEdittext;

    Button loginButton;
    Button sub1Button;
    Button sub2Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ipEdittext = (EditText) findViewById(R.id.et_login_ip);
        passwordEdittext = (EditText)findViewById(R.id.et_login_password);
        loginButton = (Button) findViewById(R.id.btn_login_login);
        sub1Button = (Button) findViewById(R.id.btn_login_sub1);
        sub2Button = (Button) findViewById(R.id.btn_login_sub2);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ip = ipEdittext.getText().toString();
                String password = passwordEdittext.getText().toString();

                Intent intent = new Intent(getApplicationContext(), NavigationActivity.class);
                intent.putExtra("ip", ip);
                intent.putExtra("password", password);

                startActivityForResult(intent, Const.REQUEST_CODE_ALERT);
            }
        });

        sub1Button = (Button) findViewById(R.id.btn_login_sub1);
        sub1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Sub1 버튼 클릭.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        sub2Button = (Button) findViewById(R.id.btn_login_sub2);
        sub2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Sub2 버튼 클릭", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
