package net.javaguides.todoapp.dao;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import net.javaguides.todoapp.model.Todo;
import net.javaguides.todoapp.utils.JDBCUtils;

public class TodoDaoImpl implements TodoDao{

	
	
	private String INSERT_TODO_SQL = "insert into todos"+" (title, username , description , target_date,is_done ) values "+ 
	" ( ?, ?, ?, ?,?);";
	
	private String SELECT_TODO_BY_ID = "select id,title,username,description,target_date,is_done from todos where id = ?;";
	
	private String SELECT_ALL_TODOS = "select * from todos;";
	
	private String DELETE_TODO_BY_ID = "delete from todos where id = ?;";
	
	private String UPDATE_TODO = "update todos set title=?,username=?,description=?,target_date=?,is_done=? where id=?;";
	
	public TodoDaoImpl() {
		
	}
	@Override
	public void insertTodo(Todo todo) {
	
		System.out.println(INSERT_TODO_SQL);
		
		
		try (Connection connection = JDBCUtils.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TODO_SQL);) {
            preparedStatement.setString(1, todo.getTitle());
            preparedStatement.setString(2, todo.getUsername());
            preparedStatement.setString(3, todo.getDescription());
            preparedStatement.setDate(4, JDBCUtils.getSQLDate(todo.getTargetDate()));
            preparedStatement.setBoolean(5, todo.getStatus());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
		
		
		
	}

	@Override
	public Todo selectTodo(long todoId) {
		
		Todo todo = null;
		
		try(Connection conn = JDBCUtils.getConnection();PreparedStatement preparedStatement = conn.prepareStatement(SELECT_TODO_BY_ID);)
		{
			preparedStatement.setLong(1, todoId);
			System.out.println(preparedStatement);
			
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next())
			{
				long id = rs.getLong("id");
                String title = rs.getString("title");
                String username = rs.getString("username");
                String description = rs.getString("description");
                LocalDate targetDate = rs.getDate("target_date").toLocalDate();
                boolean isDone = rs.getBoolean("is_done");
                todo = new Todo(id, title, username, description, targetDate, isDone);
				
			}
			
		
			
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return todo;
	}

	@Override
	public List<Todo> selectAllTodos() {
		
		
		
		List < Todo > todos = new ArrayList < > ();
		
		try(Connection conn = JDBCUtils.getConnection();PreparedStatement preparedStatement = conn.prepareStatement(SELECT_ALL_TODOS);){
			
			System.out.println(SELECT_ALL_TODOS);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
                long id = rs.getLong("id");
                String title = rs.getString("title");
                String username = rs.getString("username");
                String description = rs.getString("description");
                LocalDate targetDate = rs.getDate("target_date").toLocalDate();
                boolean isDone = rs.getBoolean("is_done");
                todos.add(new Todo(id, title, username, description, targetDate, isDone));
            }
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return todos;
	}

	@Override
	public boolean deleteTodo(long id) {
		
		
		
		boolean rowDeleted=false;
        try (Connection connection = JDBCUtils.getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_TODO_BY_ID);) {
            statement.setLong(1, id);
            try {
				rowDeleted = statement.executeUpdate() > 0;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        return rowDeleted;
		
		
		
	}
	@Override
	public boolean updateTodo(Todo todo) {
		boolean rowUpdated=false;
        try (Connection connection = JDBCUtils.getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_TODO);) {
            statement.setString(1, todo.getTitle());
            statement.setString(2, todo.getUsername());
            statement.setString(3, todo.getDescription());
            statement.setDate(4, JDBCUtils.getSQLDate(todo.getTargetDate()));
            statement.setBoolean(5, todo.getStatus());
            statement.setLong(6, todo.getId());
            rowUpdated = statement.executeUpdate() > 0;
        } catch (SQLException e) {
			
			e.printStackTrace();
		}
        return rowUpdated;
	}

	
}
