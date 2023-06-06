package com.Design_Pattern.Board;


import com.Design_Pattern.Board.User.*;
import com.Design_Pattern.Board.utill.Sha256;
import com.Design_Pattern.Board.utill.UserFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.WebApplicationType;

import java.util.HashMap;




@SpringBootApplication
public class BoardApplication {
	public enum user{
		USER,ADMIN,ROOT
	}
	public static Sha256 sha256= new Sha256();
	public static UserFactory userFactory=UserFactory.getFactory();
	public static UserRecord userRecord=new UserRecord();
	public static RootData rootData=new RootData();
	public static AdminData adminData=new AdminData();
	public static UserData userData=new UserData();

	public static void main(String[] args) {
		userRecord.addObserver(rootData);
		userRecord.addObserver(adminData);
		userRecord.addObserver(userData);


		SpringApplication application = new SpringApplication(BoardApplication.class);
		application.setWebApplicationType(WebApplicationType.SERVLET);
		application.run(args);
	}
}




