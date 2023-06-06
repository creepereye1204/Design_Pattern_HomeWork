package com.Design_Pattern.Board.User;

import com.Design_Pattern.Board.BoardApplication;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class ClientData implements Observer
{
    public abstract String selectData();

    public void addData(State state){
        BoardApplication.userRecord.addUser(state);
    }
    public void preprocessData(Set<State> set){
        String name=selectData();
        for(State state:BoardApplication.userRecord.currentUser){
            if(name.equals(state.getClass().getSimpleName())){
                set.add(state);
            }
        }
    }
    public void showTable(Set<State> set){
        for(State state:set){
            System.out.println(state.getIp());
            System.out.println(state.getClass().getName());
        }
    }
}
