package net.javaguides.todoapp.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.javaguides.todoapp.dao.TodoDao;
import net.javaguides.todoapp.dao.TodoDaoImpl;
import net.javaguides.todoapp.model.Todo;

@WebServlet("/")
public class TodoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   private TodoDao todoDAO;

	
   public void init()
   {
	   todoDAO = new TodoDaoImpl();
   }
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}

	
  protected void doGet(HttpServletRequest request, HttpServletResponse response) 
		  throws ServletException, IOException {
		
	
	  String action = request.getServletPath();
	  switch(action) {
	  
	  case "/new":
		  showNewForm(request,response);
		  break;
	  case "/insert":
		  insertTodo(request,response);
		  break;
	  case "/delete":
		  deleteTodo(request,response);
		  break;
	  case "/edit":
		  showEditTodo(request,response);
		  break;
	  case "/edit?:id":
		  showEditTodo(request,response);
		  break;
	  case "/list":
		  listTodo(request,response);
		  break;
	  case "/update":
		  updateTodo(request,response);
		  break;
	 
	 default:
		 RequestDispatcher dispatcher = request.getRequestDispatcher("login/login.jsp");
		 dispatcher.forward(request, response);
		 break;
	  
	  }
	  
	  
	}


private void updateTodo(HttpServletRequest request, HttpServletResponse response) 
		throws IOException {
	
	int id = Integer.parseInt(request.getParameter("id"));
	String title = request.getParameter("title");
	String description = request.getParameter("description");
	String username = request.getParameter("username");
	
	LocalDate targetDate = LocalDate.parse(request.getParameter("targetDate"));
	boolean isDone = Boolean.valueOf(request.getParameter("isDone"));
	
	Todo updateTodo = new Todo(id,title,username,description,targetDate,isDone);
	
	todoDAO.updateTodo(updateTodo);
	
	response.sendRedirect("list");
	
	
}


private void listTodo(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
	
	
	System.out.println(request.getParameter("username"));
	List<Todo> listTodo = todoDAO.selectAllTodos();
	System.out.println("in the list");
	request.setAttribute("listTodo", listTodo);
	RequestDispatcher dispatcher = request.getRequestDispatcher("todo/todo-list.jsp");
	
	dispatcher.forward(request, response);
	
	
}


private void showEditTodo(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
	
	int id = Integer.parseInt(request.getParameter("id"));
	
	Todo existingTodo = todoDAO.selectTodo(id);
	
	System.out.println("in the edit ");
	System.out.println(id);
	RequestDispatcher dispatcher = request.getRequestDispatcher("todo/todo-form.jsp");
	request.setAttribute("todo", existingTodo);
	dispatcher.forward(request, response);
	
}


private void deleteTodo(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
	
   int id = Integer.parseInt(request.getParameter("id"));
	
	todoDAO.deleteTodo(id);
	
	response.sendRedirect("list");
	
	
}


private void insertTodo(HttpServletRequest request, HttpServletResponse response) 
		throws IOException {
	
	String title=request.getParameter("title");
	String description = request.getParameter("description");
	String username = request.getParameter("userName");
	Boolean isDone = Boolean.valueOf(request.getParameter("idDone"));
	
	
	System.out.println("value of user is");
	System.out.println(request.getParameter("myhiddenvalue"));
	LocalDate targetDate = LocalDate.parse(request.getParameter("targetDate"));
	
	
	
	Todo newTodo = new Todo(title,username,description,targetDate,isDone);
	
	todoDAO.insertTodo(newTodo);
	response.sendRedirect("list");
	
	
	
}


private void showNewForm(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
	
	RequestDispatcher dispatcher = request.getRequestDispatcher("todo/todo-form.jsp");
	dispatcher.forward(request, response);
	
}

}
