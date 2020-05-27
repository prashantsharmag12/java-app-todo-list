<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
        
        <h1>Register Form</h1>
        
       

              
                    
                    
                    <form action="<%=request.getContextPath()%>/register" method="post">
                    
                    
                    <div class="form-group">
                    
                    <label for="uname">FirstName</label><input type="text" class="form-control" id="uname" placeholder="firstName" name="firstName" required>
                    
                    </div>
                    
                    
                    <div class="form-group">
                    
                    <label for="uname">LastName</label><input type="text" class="form-control" id="uname" placeholder="lastName" name="lastName" required>
                    
                    </div>
                    
                    
                    <div class="form-group">
                    
                    <label for="uname">UserName</label><input type="text" class="form-control" id="uname" placeholder="userName" name="userName" required>
                    
                    </div>
                    
                    <div class="form-group">
                    
                    <label for="uname">Password</label><input type="password" class="form-control" id="uname" placeholder="password" name="password" required>
                    
                    </div>
                    
                    
                    <button type="submit" class="btn btn-primary">Register</button>
                    
                    
                    
                    
                    </form>

        
        
        
</body>
</html>