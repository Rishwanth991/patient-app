package p1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
@WebServlet("/viewpatients")
public class Viewpatients extends GenericServlet{
	public void service(ServletRequest req,ServletResponse res)
	throws ServletException,IOException
	
	{
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			PreparedStatement ps=con.prepareStatement("select* from patient");
			ResultSet rs=ps.executeQuery();
			out.println("<html><body>");
            out.println("<h2>Patient Details</h2>");

            out.println("<table border='1'>");

            out.println("<tr>");
            out.println("<th>ID</th>");
            out.println("<th>Name</th>");
            out.println("<th>Age</th>");
            out.println("<th>Gender</th>");
            out.println("<th>Disease</th>");
            out.println("<th>Mobile</th>");
            out.println("</tr>");

            while(rs.next()) {

                out.println("<tr>");
                out.println("<td>"+rs.getInt(1)+"</td>");
                out.println("<td>"+rs.getString(2)+"</td>");
                out.println("<td>"+rs.getInt(3)+"</td>");
                out.println("<td>"+rs.getString(4)+"</td>");
                out.println("<td>"+rs.getString(5)+"</td>");
                out.println("<td>"+rs.getString(6)+"</td>");
                out.println("</tr>");
            }

            out.println("</table>");
            out.println("</body></html>");

            rs.close();
            ps.close();
            con.close();

        } catch(Exception e) {
            out.println(e);
        }
		}
		
	}


