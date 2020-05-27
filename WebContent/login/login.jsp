<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
</head>
<body>

<h2>Login Page</h2>

<form action="<%=request.getContextPath()%>/login" method="post">

		<div class="form-group">
		
		<label for="uname">UserName</label><input type="text" name="username" placeholder="User Name" required/>
		
		</div>
		
		
		<div class="form-group">
		
		<label for="uname">Password</label><input type="text" name="password" placeholder="Password"required/>
		
		
		<button type="submit" class="btn btn-primary">Submit</button>
		
		</div>


</form>





</body>
</html>