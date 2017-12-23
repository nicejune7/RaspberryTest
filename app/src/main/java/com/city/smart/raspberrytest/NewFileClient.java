package com.city.smart.raspberrytest;

import android.content.SharedPreferences;
import android.os.Environment;

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

import static android.content.Context.MODE_PRIVATE;

public class NewFileClient {
    //String host = "192.168.43.9";
    int port = 8080;
    Socket socket;
    OutputStream outputStream;
    OutputStreamWriter outputStreamWriter;
    BufferedWriter bufferedWriter;

    public void sendData(String data, String host) {
        try {
            socket = new Socket(host, port);
            outputStream = socket.getOutputStream();
            outputStreamWriter = new OutputStreamWriter(outputStream);
            bufferedWriter = new BufferedWriter(outputStreamWriter);

            bufferedWriter.write(data);
            bufferedWriter.newLine();
            bufferedWriter.flush();

            //saveFile(socket);
            if (data.equals("image")) {
                saveFile(socket);
            } else {
                // DO NOTHING
            }

            bufferedWriter.close();
            outputStreamWriter.close();
            outputStream.close();

            /*
            if (data.equals("image")) {
                saveFile(socket);
            } else {
                // DO NOTHING
            }
            */
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
