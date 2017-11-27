package com.company;

//Object: registers as observer of ChatHistory and prints chat messages
public class ChatConsole implements ChatObserver {
    private ChatHistory a;

    public ChatConsole() {
        a = ChatHistory.getInstance();
        a.register(this);
    }

    @Override
    public void update() {
        System.out.println(a.getLatestMessage());
    }
}
