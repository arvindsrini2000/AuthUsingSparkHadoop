import static javax.swing.JOptionPane.showMessageDialog;
import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructType;
import java.io.IOException;		
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/Signup")
public class Signup extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static String username = "";
	static String password = "";
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		//Write writer = new Write();
		username = req.getParameter("username");
		password = req.getParameter("password");
		try {
			boolean haswritten = Write.writeinto(username, password);
			if(haswritten)
			{
				showMessageDialog(null, "Successfully Signed Up!");
				Cookie c1 = new Cookie("username",username);
				c1.setMaxAge(100000);
				res.addCookie(c1);
				//req.getSession().setAttribute("username", username);
				res.sendRedirect("Welcome.jsp");
			}
			else
			{
				showMessageDialog(null, "Username already exists!");
				res.sendRedirect("signup.jsp");
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
