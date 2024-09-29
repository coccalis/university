package com.cocc.ex3_db_;

//IDE USED: IntelliJ IDEA Ultimate 2021.2.3

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "editStudent", value = "/editStudent")
public class EditStudent extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        response.setContentType("text/html");
        PrintWriter out=response.getWriter();

        out.println("<html><head><title>Edit Student</title>");
        out.println("<link rel=\"stylesheet\" href=\"css/cssAddStudent.css\" </head><body>");

        String sid=request.getParameter("id");
        String id = sid;
        Student student = StudentDAO.getStudentById(id); //get student id from the sql database

        //prints the data from the selected student
        out.println("<div class=\"center\"> <h1> Edit Student </h1> ");
        out.println("<form action='editStudent2' method='post' style='position: relative; top: -100px; '>"); //call editStudent 2 to confirm the changes made
        out.println("<input class=\"inputs\" type=\"text\"  name=\"ID\" id=\"id\" placeholder=\"AM\" value='" + student.getId() + " '/><br>");
        out.println("<input class=\"inputs\" type=\"text\"  name=\"Firstname\" id=\"firstName\" placeholder=\"FirstName\" value='" + student.getFirstName() + "'/><br>");
        out.println("<input class=\"inputs\" type=\"text\"  name=\"Lastname\" id=\"lastName\" placeholder=\"LastName\" value='" + student.getLastName() + " '/><br>");
        out.println("<input class=\"inputs\" type=\"text\"  name=\"University\" id=\"university\" placeholder=\"University\" value='" +student.getUniversity() +"'/><br>");
        out.println("<input class=\"inputs\" type=\"text\"  name=\"Semester\" id=\"semester\" placeholder=\"Semester\" value='" +student.getSemester() +"'/><br>\n");
        out.println("<input class=\"inputs\" type=\"text\"  name=\"Modules\" id=\"modules\" placeholder=\"Modules\" value='" +student.getModules()+ "'/><br>");
        out.println("<input class=\"button\" type=\"submit\" style=\"margin-top:30px\" value=\"Edit & Save\" /> ");
        out.println("</form></div></body></html>");
        out.close();
    }


}
