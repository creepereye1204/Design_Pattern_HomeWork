package com.Design_Pattern.Board.User;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class RootData extends ClientData{
    Set<State> rootSet= new HashSet<>();
    @Override
    public String selectData() {
        return Root.class.getSimpleName();
    }

    @Override
    public void update() {
        super.preprocessData(this.rootSet);
        if(this.rootSet.size()!=0){
            System.out.println("현재 Root 관리자가 사용하는 IP"+Root.getRoot().getIp());
            System.out.println("로그인 시간:"+new Date().getTime());
            showTable(this.rootSet);
        }
        else {
            System.out.println("현재 Root 관리자 Log off");
        }

    }
}
