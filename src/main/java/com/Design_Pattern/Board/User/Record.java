package com.Design_Pattern.Board.User;

import java.util.HashSet;
import java.util.Set;

public abstract class Record {
    static Set<Observer> observers= new HashSet<>();
    public void addObserver(Observer observer){
        this.observers.add(observer);
    }
    public void notifyData(){
        for(Observer observer:observers){
            observer.update();
        }
    }
}
