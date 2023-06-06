package com.Design_Pattern.Board.User;

import java.util.HashSet;
import java.util.Set;

public class AdminData extends ClientData {
    Set<State> AdminSet= new HashSet<>();
    @Override
    public String selectData() {
        return Admin.class.getSimpleName();
    }

    @Override
    public void update() {
        super.preprocessData(this.AdminSet);
        if(this.AdminSet.size()!=0){

            System.out.println("어드민의 수:"+this.AdminSet.size());
            showTable(this.AdminSet);
        }else{
            System.out.println("어드민 없음");
        }


    }
}
