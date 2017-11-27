package com.company;

import java.sql.Timestamp;

public class ChatMessage {
    private String message;
    private Users user;
    private Timestamp timestamp;

    public ChatMessage(String message, Users user) {
        this.message = message;
        this.user = user;
        timestamp = new Timestamp(System.currentTimeMillis());
    }

    @Override
    public String toString() {
        return user.getName()+ " : " + message + " " + timestamp.getTime() + "\n";
    }
}
