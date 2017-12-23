package com.city.smart.raspberrytest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class GiveFeedbackActivity extends AppCompatActivity {
    TextView tv_result;
    Button btn_order1;
    Button btn_order2;
    Button btn_order3;
    Button btn_order4;

    int port = 8080;
    Socket socket;
    OutputStream outputStream;
    OutputStreamWriter outputStreamWriter;
    BufferedWriter bufferedWriter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_give_feedback);

        tv_result = (TextView)findViewById(R.id.tv_feedback_result);
        btn_order1 = (Button)findViewById(R.id.btn_feedback_order1);
        btn_order2 = (Button)findViewById(R.id.btn_feedback_order2);
        btn_order3 = (Button)findViewById(R.id.btn_feedback_order3);
        btn_order4 = (Button)findViewById(R.id.btn_feedback_order4);

        Intent intent = getIntent();
        String messageType = intent.getExtras().getString("type");

        //SharedPreferences pref2 = getSharedPreferences("type", MODE_PRIVATE);
        //String messageType = pref2.getString("type", "error2");
        if (messageType.equals("error2")) {
            Snackbar.make(getWindow().getDecorView().getRootView(), "type 불러오기 실패!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        } else if (messageType.equals("error1")) {
            Snackbar.make(getWindow().getDecorView().getRootView(), "type 저장하기 실패!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        } else if (messageType.equals("type1")) {
            tv_result.setText("택배입니다.");
        } else if (messageType.equals("type2")) {
            tv_result.setText("경비아저씨 입니다.");
        } else {
            Snackbar.make(getWindow().getDecorView().getRootView(), "messageType : " + messageType, Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }

        btn_order1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref = getSharedPreferences("ip", MODE_PRIVATE);
                final String host = pref.getString("ip", "");
                if (host == "") {
                    Snackbar.make(getWindow().getDecorView().getRootView(), "호스트가 비어있습니다!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else {
                    new Thread() {
                        public void run() {
                            //NewFileClient newFileClient = new NewFileClient();
                            //newFileClient.sendData("image", host);
                            sendData("sound1", host);
                        }
                    }.start();
                }

                Toast.makeText(GiveFeedbackActivity.this, "전송완료.", Toast.LENGTH_LONG).show();
                finish();
            }
        });

        btn_order2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref = getSharedPreferences("ip", MODE_PRIVATE);
                final String host = pref.getString("ip", "");
                if (host == "") {
                    Snackbar.make(getWindow().getDecorView().getRootView(), "호스트가 비어있습니다!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else {
                    new Thread() {
                        public void run() {
                            //NewFileClient newFileClient = new NewFileClient();
                            //newFileClient.sendData("image", host);
                            sendData("sound2", host);
                        }
                    }.start();
                }

                Toast.makeText(GiveFeedbackActivity.this, "전송완료.", Toast.LENGTH_LONG).show();
                finish();
            }
        });

        btn_order3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(GiveFeedbackActivity.this, "전송완료.", Toast.LENGTH_LONG).show();
                finish();
            }
        });

        btn_order4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(GiveFeedbackActivity.this, "전송완료.", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }

    public void sendData(String data, String host) {
        try {
            socket = new Socket(host, port);
            outputStream = socket.getOutputStream();
            outputStreamWriter = new OutputStreamWriter(outputStream);
            bufferedWriter = new BufferedWriter(outputStreamWriter);

            bufferedWriter.write(data);
            bufferedWriter.newLine();
            bufferedWriter.flush();

            if (data.equals("image")) {
                saveFile(socket);
            } else {
                // DO NOTHING
            }

            bufferedWriter.close();
            outputStreamWriter.close();
            outputStream.close();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveFile(Socket clientSock) throws IOException {
        File newFolder = new File(Environment.getExternalStorageDirectory() + File.separator + "Raspberry Test");
        newFolder.mkdir();

        String imageName = "testfile.png";
        File newFile = new File(Environment.getExternalStorageDirectory() + File.separator + "Raspberry Test", imageName);

        InputStream inputStream = clientSock.getInputStream();
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);

        FileOutputStream fileOutputStream = new FileOutputStream(newFile, true);

        int ch;
        while ((ch = bufferedInputStream.read()) != -1) {
            fileOutputStream.write(ch);
        }

        fileOutputStream.close();
        inputStream.close();
    }
}
