<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome</title>
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
			out.print("<div><p>Welcome to the profile page!</p></div>" + user.split("@")[0]);
			out.println("<div><hr></div>");
			out.println("<form action='Logout' method='post'><input type='submit' value='Logout'></form>");
		}
		else 
		{
			response.sendRedirect("login.jsp");
		}
	}
	else
	{
		response.sendRedirect("login.jsp");
	}

%>

	


	
</body>
</html>
