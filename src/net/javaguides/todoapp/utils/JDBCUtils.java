package net.javaguides.todoapp.utils;


import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.bson.Document;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class JDBCUtils {

	private static String jdbcURL = "jdbc:mysql://localhost:3306/todo_app";
	private static String jdbcUsername = "root";
	private static String jdbcPassword = "peesu";

	public static Connection getConnection() {
		
		
		MongoClient mongo = new MongoClient("localhost");
		
		
		MongoDatabase db = mongo.getDatabase("search-bar-blog");
		
		MongoCollection<Document> col = db.getCollection("posts");
		
		
		FindIterable<Document> fi = col.find();
        MongoCursor<Document> cursor = fi.iterator();
        try {
            while(cursor.hasNext()) {
                System.out.println(cursor.next().toJson());
            }
        } finally {
            cursor.close();
        }
		
		
		
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	public static void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

	public static Date getSQLDate(LocalDate date) {
		return java.sql.Date.valueOf(date);
	}

	public static LocalDate getUtilDate(Date sqlDate) {
		return sqlDate.toLocalDate();
	}
	
	public static MongoClient mongodbms()
	{
		MongoClient mongo = new MongoClient();
		return mongo;
	}
	
}
