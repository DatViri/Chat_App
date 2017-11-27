package com.company;

import java.util.ArrayList;

public interface ChatObservable {
    public void register(ChatObserver chatObserver);
    public void remove(ChatObserver chatObserver);
    public void notify(ArrayList<ChatObserver> observerList);

}
