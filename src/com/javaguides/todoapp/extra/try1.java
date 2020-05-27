package com.javaguides.todoapp.extra;

import net.javaguides.todoapp.dao.UserDao;
import net.javaguides.todoapp.model.User;

public class try1 {

	public static void main(String[] args) {
		
		
		
		UserDao userdao = new UserDao();
		
		User user = new User();
		
		
		user.setUsername("23");
		user.setFirstName("fdf");
		user.setLastName("dfdf");
		user.setPassword("fdfd");
		
		try {
			userdao.registerEmployee(user);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
				

	}

}
