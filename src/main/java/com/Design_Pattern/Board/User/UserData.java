package com.Design_Pattern.Board.User;

import java.util.HashSet;
import java.util.Set;

public class UserData extends ClientData{
    Set<State> UserSet= new HashSet<>();
    @Override
    public String selectData() {
        return User.class.getSimpleName();
    }

    @Override
    public void update() {
        super.preprocessData(this.UserSet);
        if(this.UserSet.size()!=0){

            System.out.println("유저 수:"+this.UserSet.size());
            showTable(this.UserSet);
        }else {
            System.out.println("현재 유저 없음");
        }


    }
}
