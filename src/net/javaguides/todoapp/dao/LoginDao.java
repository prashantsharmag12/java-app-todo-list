package net.javaguides.todoapp.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.javaguides.todoapp.model.LoginBean;
import net.javaguides.todoapp.utils.JDBCUtils;

public class LoginDao {
	
	public boolean validate(LoginBean loginBean) throws SQLException, ClassNotFoundException
	{
		
		
		boolean status=false;
		
		String query;
		query="select * from users where username = ? and password = ?;";
		Class.forName("com.mysql.jdbc.Driver");
		try(Connection conn = JDBCUtils.getConnection();PreparedStatement preparedStatement = conn.prepareStatement(query))
		{
			
			preparedStatement.setString(1, loginBean.getUsername());
			preparedStatement.setString(2, loginBean.getPassword());
			
			System.out.println(preparedStatement);
			
			ResultSet rs  = preparedStatement.executeQuery();
			status=rs.next();
			
			
		}
		catch (SQLException e)
		{
			JDBCUtils.printSQLException(e);
		}
		return status;
	}
	
	
	/*public static void main(String[] args)
	{
		LoginDao loginDao = new LoginDao();
		
		LoginBean loginBean = new LoginBean();
		
		loginBean.setUsername("Kana");
		loginBean.setPassword("murlli");
		
		try {
			System.out.println(loginDao.validate(loginBean));
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
}
