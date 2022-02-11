<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
<%
	response.setHeader("cache-control", "no-cache, no-store, must-revalidate");
	Cookie ck[] = request.getCookies();
	if(ck!=null){
		int i=-1;
		for (Cookie c:ck) {
			i++;
			if(c.getName().equals("username"))
				break;
		}
		if(i!=-1 && !ck[i].getValue().equals("") && ck[i].getName().equals("username"))
		{
			response.sendRedirect("Welcome.jsp");
		}else{
%>
 <div>
  <h3>Welcome to Login Page!</h3>
  <form action="Login" method="post">
   <table>
    <tr>
     <td>Username</td>
     <td><input type="text" name="username" required/></td>
    </tr>
    <tr>
     <td>Password</td>
     <td><input type="password" name="password" id="pd" required/></td>
    </tr>
    <tr>
     <td></td>
     <td><input type="checkbox" onclick="showPassword()">Show Password</td>
    </tr>

   </table>
   <br>
   <input type="submit" value="Login" />
  </form>
  <p>Don't have an account? <a href="signup.jsp">Create one!</a></p> 
 </div>
<%}
}%>

</body>

<script>
	function showPassword() 
	{
	  var pd = document.getElementById("pd");
	  if(pd.type === "password") 
	  {
	    pd.type = "text";
	  } 
	  else 
	  {
	    pd.type = "password";
	  }
	}
</script>
</html>
