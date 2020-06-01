

package net.javaguides.todoapp.utils;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class MongoUtils{
	
	
	public static void main(String[] args)
	{
		MongoClient mongo = new MongoClient("localhost");
		MongoDatabase db = mongo.getDatabase("posts");
		
		
	}
	
}