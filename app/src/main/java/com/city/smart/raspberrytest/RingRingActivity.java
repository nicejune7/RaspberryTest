package com.city.smart.raspberrytest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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

public class RingRingActivity extends AppCompatActivity {
    ImageView iv_result;
    TextView tv_response;
    Button btn_1;
    Button btn_2;
    Button btn_3;
    Button btn_4;
    Button btn_setiv;

    int port = 8080;
    Socket socket;
    OutputStream outputStream;
    OutputStreamWriter outputStreamWriter;
    BufferedWriter bufferedWriter;

    private MyAsyncTask myAsyncTask;
    int yesorno = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ring_ring);

        iv_result = (ImageView)findViewById(R.id.iv_ring_result);
        tv_response = (TextView)findViewById(R.id.tv_ring_response);
        btn_1 = (Button)findViewById(R.id.btn_ring_1);
        btn_2 = (Button)findViewById(R.id.btn_ring_2);
        btn_3 = (Button)findViewById(R.id.btn_ring_3);
        btn_4 = (Button)findViewById(R.id.btn_ring_4);
        btn_setiv = (Button)findViewById(R.id.btn_ring_setiv);
        tv_response.setText("\"응답을 기다리는 중...\"");

        Intent intent2 = getIntent();
        final String messageType = intent2.getExtras().getString("type");

        SharedPreferences pref = getSharedPreferences("ip", MODE_PRIVATE);
        final String host = pref.getString("ip", "");
        if (host == "") {
            Snackbar.make(getWindow().getDecorView().getRootView(), "호스트가 비어있습니다!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        } else {
            //myAsyncTask = new MyAsyncTask();
            //myAsyncTask.execute(host);


            new Thread() {
                public void run() {
                    //NewFileClient newFileClient = new NewFileClient();
                    //newFileClient.sendData("image", host);
                    sendData("image", host);
                    iv_result.setImageURI(Uri.parse("file://" + Environment.getExternalStorageDirectory() + File.separator + "Raspberry Test" + File.separator + "testfile.png"));
                }

            }.start();

        }

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (messageType.equals("error2")) {
                    Snackbar.make(getWindow().getDecorView().getRootView(), "type 불러오기 실패!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else if (messageType.equals("error1")) {
                    Snackbar.make(getWindow().getDecorView().getRootView(), "type 저장하기 실패!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else if (messageType.equals("type1")) {
                    tv_response.setText("\"택배입니다.\"");
                } else if (messageType.equals("type2")) {
                    tv_response.setText("\"잠시만 기다려 주세요.\"");
                } else {
                    Snackbar.make(getWindow().getDecorView().getRootView(), "messageType : " + messageType, Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }

                iv_result.setImageURI(Uri.parse("file://" + Environment.getExternalStorageDirectory() + File.separator + "Raspberry Test" + File.separator + "testfile.png"));
            }
        }, 5000);

        btn_setiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iv_result.setImageURI(Uri.parse("file://" + Environment.getExternalStorageDirectory() + File.separator + "Raspberry Test" + File.separator + "testfile.png"));
            }
        });

        btn_1.setOnClickListener(new View.OnClickListener() {
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

                Toast.makeText(RingRingActivity.this, "전송완료.", Toast.LENGTH_LONG).show();
                finish();
            }
        });

        btn_2.setOnClickListener(new View.OnClickListener() {
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

                Toast.makeText(RingRingActivity.this, "전송완료.", Toast.LENGTH_LONG).show();
                finish();
            }
        });

        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(RingRingActivity.this, "전송완료.", Toast.LENGTH_LONG).show();
                finish();
            }
        });

        btn_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(RingRingActivity.this, "전송완료.", Toast.LENGTH_LONG).show();
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

        FileOutputStream fileOutputStream = new FileOutputStream(newFile);

        int ch;
        while ((ch = bufferedInputStream.read()) != -1) {
            fileOutputStream.write(ch);
        }

        fileOutputStream.close();
        inputStream.close();
    }

    public class MyAsyncTask extends AsyncTask<String, Integer, Integer> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Integer doInBackground(String... params) {
            sendData("image", params[0]);
            return 0;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            //iv_result.setImageURI(Uri.parse("file://" + Environment.getExternalStorageDirectory() + File.separator + "Raspberry Test" + File.separator + "testfile.png"));
        }
    }
}
