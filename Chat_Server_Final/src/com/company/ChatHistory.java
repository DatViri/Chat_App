package com.company;

import java.util.ArrayList;
import java.util.Observable;

//Object: Singleton template, has instance variables to support features visible, need suitable
//        collection for storing message
public class ChatHistory implements ChatObservable {
    //storing message collection
    private static ArrayList<ChatMessage>  chatMessagesList = new ArrayList<ChatMessage>();
    private ArrayList<ChatObserver> observerList = new ArrayList<>();

    private static ChatHistory ourInstance = new ChatHistory();

    public static ChatHistory getInstance() {
        return ourInstance;
    }

    private ChatHistory() {
    }

    //insert message method
    public void insert(ChatMessage message){
        chatMessagesList.add(message);
        ourInstance.notify(observerList);
    }

    public String getLatestMessage(){
        return chatMessagesList.get(chatMessagesList.size() -1).toString();
    }

    @Override
    public void register(ChatObserver chatObserver) {
        observerList.add(chatObserver);
    }

    @Override
    public void remove(ChatObserver chatObserver) {
        observerList.remove(chatObserver);
    }

    @Override
    public void notify(ArrayList<ChatObserver> observerList) {
        for (ChatObserver chatList: observerList
             ) {
            chatList.update();
        }
    }

    @Override
    public String toString() {
        StringBuilder sBuiler = new StringBuilder();
        for(ChatMessage message : chatMessagesList){
            sBuiler.append(message.toString());
        }
        return sBuiler.toString();
    }
}
