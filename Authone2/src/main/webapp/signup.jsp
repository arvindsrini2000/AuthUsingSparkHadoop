<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Signup</title>
</head>
<body>
	<%
	response.setHeader("cache-control", "no-cache, no-store, must-revalidate");
	Cookie c[] = request.getCookies();
	String user = "";
	if(c!=null)
	{
		int i=-1;
		for (Cookie cookie:c) 
		{
			i++;
			if(cookie.getName().equals("username"))
			{
				user = cookie.getValue();
				break;
			}
		}
		if(i!=-1 && !c[i].getValue().equals("") && c[i].getName().equals("username"))
		{
			response.sendRedirect("Welcome.jsp");
		}
		else{
			%>
		 <div>
		  <h3>Welcome to Signup Page!</h3>
		  <form action="Signup" method="post">
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
		   <input type="submit" value="Signup" />
		  </form>
		  <p>Already have an account? <a href="login.jsp">Login!</a></p> 
		 </div>
		<%
		}}
	else{%>
		 <div>
		  <h3>Welcome to Signup Page!</h3>
		  <form action="Signup" method="post">
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
		   <input type="submit" value="Signup" />
		  </form>
		  <p>Already have an account? <a href="login.jsp">Login!</a></p> 
		 </div>
		<%} 
	

%>

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
