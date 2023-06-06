package com.Design_Pattern.Board.utill;

import com.Design_Pattern.Board.BoardApplication;
import com.Design_Pattern.Board.User.Admin;
import com.Design_Pattern.Board.User.Root;
import com.Design_Pattern.Board.User.State;
import com.Design_Pattern.Board.User.User;

public class UserFactory {
    private UserFactory(){};
    public static UserFactory userFactory=null;
    public static UserFactory getFactory(){
        return userFactory==null?new UserFactory():userFactory;
    }
    public State spawnUser(String ip,BoardApplication.user user){
        State setState;
        switch (user){
            case ROOT: {
                setState = Root.getRoot();
                setState.setIp(ip);
                BoardApplication.rootData.addData(setState);
                break;
            }
            case ADMIN:{
                setState=new Admin();
                setState.setIp(ip);
                BoardApplication.adminData.addData(setState);
                break;
            }
            default: {
                setState = new User();
                setState.setIp(ip);
                BoardApplication.userData.addData(setState);
                break;
            }
        }
        return setState;
    }
}
