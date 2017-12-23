package com.city.smart.raspberrytest;

import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.messaging.FirebaseMessaging;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    Button btn_soundserver;
    Button btn_imageserver;
    Button btn_setimage;
    ImageView iv_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_soundserver = (Button)findViewById(R.id.btn_soundserver);
        btn_imageserver = (Button)findViewById(R.id.btn_imageserver);
        btn_setimage = (Button)findViewById(R.id.btn_setimage);
        iv_1 = (ImageView)findViewById(R.id.iv_1);

        btn_soundserver.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread() {
                    public void run() {
                        //SoundFileClient soundFileClient = new SoundFileClient();
                        //soundFileClient.playMusic("sound", "192.168.43.126", 8080);
                        NewFileClient newFileClient = new NewFileClient();
                        newFileClient.sendData("sound", "192.168.43.9");
                    }
                }.start();
            }
        });

        btn_imageserver.setOnClickListener(new Button.OnClickListener() {
                        @Override
            public void onClick(View view) {
                new Thread() {
                    public void run() {
                        //ImageFileClient imageFileClient = new ImageFileClient("172.30.1.10", 8080);
                        NewFileClient newFileClient = new NewFileClient();
                        newFileClient.sendData("image", "192.168.43.9");
                    }
                }.start();
            }
        });

        btn_setimage.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                iv_1.setImageURI(Uri.parse("file://" + Environment.getExternalStorageDirectory() + File.separator + "Raspberry Test" + File.separator + "testfile.png"));
            }
        });
    }


}