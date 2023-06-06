package com.Design_Pattern.Board.User;

import java.util.HashSet;
import java.util.Set;

public class UserRecord extends Record {
    static public Set<State> currentUser= new HashSet<>();
    public void addUser(State state){
        this.currentUser.add(state);
        notifyData();
    }

}
