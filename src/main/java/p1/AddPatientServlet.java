package p1;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/add")
public class AddPatientServlet extends GenericServlet {
    public void service(ServletRequest req,
                        ServletResponse res)
            throws ServletException, IOException {
    	HttpServletResponse response=(HttpServletResponse) res;
    	response.setHeader("Access-Control-Allow-Origin", "http://localhost:3001");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "*");

        patient p = new patient();
        System.out.println("pid="+req.getParameter("pid"));
        System.out.println("age="+req.getParameter("age"));

        p.setPid(Integer.parseInt(req.getParameter("pid")));
        p.setPname(req.getParameter("pname"));
        p.setAge(Integer.parseInt(req.getParameter("age")));
        p.setGender(req.getParameter("gender"));
        p.setDisease(req.getParameter("disease"));
        p.setMobile(req.getParameter("mobile"));

        patientDAO dao = new patientDAO();

           int x=dao.addPatient(p);
        PrintWriter out = res.getWriter();

        if(x > 0)
            out.println("Patient Added Successfully");
        else
            out.println("Insertion Failed");
    }
}