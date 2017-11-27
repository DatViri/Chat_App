package com.example.dattruong.chat_client_final;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;

//Object recieve information from chat server
public class ThreadReciever implements Runnable {
    private Handler uiHandler;
    private Socket socket;
    private String ServerIP = "192.168.0.103"; //set static HOST, change everytime when using new HOST
    private int ServerPort = 12345; //set static PORT, change everytime when using new port
    private BufferedReader br;


    public ThreadReciever(Handler uiHandler, Socket socket) {
        this.socket = socket;
        this.uiHandler = uiHandler;
    }


    @Override
    public void run() {
        try {
            //Parse IP and Port number to socket and create connection between server and client
            socket.connect(new InetSocketAddress(ServerIP, ServerPort));
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (true) {
                //try readline from input port
                String chatMessage = br.readLine();
                Message msg = uiHandler.obtainMessage();
                msg.what = 0;
                msg.obj = chatMessage;
                uiHandler.sendMessage(msg);
            }
        } catch (Exception e) {
            Log.e("TCP", "C:	Error", e);
        } finally {
            try {
                socket.close();
            } catch (Exception e) {
                Log.e("TCP", "C:	Error", e);
            }
        }
    }
}
