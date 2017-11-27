package com.company;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

//Object has server() method that listen incoming connection and starts  new CI thread for each connection
public class Server {
    public void serve() {
        try {
            ServerSocket ss = new ServerSocket(12345, 2);
            System.out.println("I have socket " + ss.getLocalPort());
            while(true) {
                Socket s = ss.accept();
                System.out.println("Accepted new connection");
                CI ci = new CI(s.getInputStream(), new PrintStream(s.getOutputStream(), true));
                Thread t = new Thread(ci);
                t.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
