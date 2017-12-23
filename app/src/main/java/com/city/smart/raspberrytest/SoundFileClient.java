package com.city.smart.raspberrytest;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SoundFileClient {
    private Socket socket;
    private OutputStream os;
    private OutputStreamWriter osw;
    private BufferedWriter bw;

    public void playMusic(String data, String host, int port) {
        try {
            socket = new Socket(host, port);
            os = socket.getOutputStream();
            osw = new OutputStreamWriter(os);
            bw = new BufferedWriter(osw);

            bw.write(data);
            bw.newLine();
            bw.flush();
            bw.close();
            osw.close();
            os.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
