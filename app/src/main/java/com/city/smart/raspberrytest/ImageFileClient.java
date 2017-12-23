package com.city.smart.raspberrytest;

import android.os.AsyncTask;
import android.os.Environment;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ImageFileClient {
    private Socket socket;

    public ImageFileClient(String host, int port) {
        try {
            socket = new Socket(host, port);
            saveFile(socket);
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

