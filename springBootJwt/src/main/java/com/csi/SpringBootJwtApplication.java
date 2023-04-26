package com.csi;

import com.csi.model.User;
import com.csi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class SpringBootJwtApplication {

	@Autowired
	UserService userServiceImpl;
	public static void main(String[] args) {
		SpringApplication.run(SpringBootJwtApplication.class, args);
	}

	@PostConstruct
	public void saveData(){
		User user=new User(121,"gokul","Gokul@gmail.com","gokul@123");

		userServiceImpl.saveData(user);

	}
}
