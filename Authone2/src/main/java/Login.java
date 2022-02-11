import java.io.IOException;		
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static javax.swing.JOptionPane.showMessageDialog;


@WebServlet("/Login")
public class Login extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		String username = req.getParameter("username");
		//System.out.println("In Login.java : "+username);
		String password = req.getParameter("password");
		try {
			String check = Readfromjar.readfrom(username, password);
			if(check.equals("done"))
			{
				//req.getSession().setAttribute("username", username);
				Cookie usercookie = new Cookie("username", username);
				//System.out.println(username);
				//usercookie.setMaxAge(30*60);
				res.addCookie(usercookie);
				showMessageDialog(null, "You have been successfully logged in!");
				res.sendRedirect("Welcome.jsp");
				
			}
//			else if(check.equals("pwd"))
//			{
//				showMessageDialog(null, "The password is incorrect!");
//				res.sendRedirect("login.jsp");
//			}
			else
			{
				//System.out.println(check);
				showMessageDialog(null, "The username or password is incorrect!");
				res.sendRedirect("login.jsp");
			}
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
	
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	/*protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}*/

}
