package com.example.dattruong.chat_client_final;

import android.util.Log;

import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

//Object: send information from chat client to chat server
public class ThreadSender implements Runnable {
    private Socket socket;
    private String msgOut;
    private PrintWriter out;

    public ThreadSender(Socket socket, String msgOut) {
        this.socket = socket;
        this.msgOut = msgOut;
    }

    @Override
    public void run() {
        try {
            Log.i("abc", "run: ");
            out = new PrintWriter(socket.getOutputStream());
            out.println(msgOut);
            out.flush();
        } catch (Exception e) {
            Log.e("TCP", "C:	Error", e);
        }
    }
}
