package com.cocc.ex3_db_;
//IDE USED: IntelliJ IDEA Ultimate 2021.2.3

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "viewServlet", value = "/viewStudent")
public class ViewStudents extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<html><head><title>Students Table</title>");
        out.println("<link rel=\"stylesheet\" href=\"css/cssViewStudents.css\" </head><body>");
        // create the list to get all students from sql database
        List<Student> studentList=StudentDAO.getAllStudents();

        out.println("<div class=\"table100\"><h1 style= \" color: #3d3a54; text-align: center;\">Students Table</h1>");
        out.println("<table style=\"position: relative; top: -5px;\"><thead>");
        out.println("<tr class=\"table100-head\">");
        out.println("<th class=\"column1\">ID</th><th class=\"column2\">Name</th><th class=\"column3\">Lastname</th>");
        out.println("<th class=\"column4\">University</th><th class=\"column5\">Semester</th><th class=\"column6\">Modules</th>");
        out.println("<th class=\"column7\">Edit</th><th class=\"column8\">Delete</th> </tr> </thead>");
        out.println("<tbody>");
        //print all students to table
        for(Student student: studentList){
            out.println("<tr>"
                    + "<td class=\"column1\">" + student.getId() + "</td>"
                    + "<td class=\"column2\">" + student.getFirstName() + "</td>"
                    + "<td class=\"column3\">" + student.getLastName() + "</td>"
                    + "<td class=\"column4\">" + student.getUniversity() + "</td>"
                    + "<td class=\"column5\">" + student.getSemester() + "</td>"
                    + "<td class=\"column6\">" + student.getModules() + "</td>"
                    + "<td class=\"column7\"><a class=\"a\" href='editStudent?id= "+ student.getId() + "'>Edit</a></td>" //if the link is pressed direct it to edit page
                    + "<td class=\"column8\"><a class=\"a\" href='deleteStudent?id= "+ student.getId() + "'>Delete</a></td>" //if the link is pressed it calls the "delete.class" to delete the student
                    +"</tr>");
        }
        out.println("</tbody></table></div>");
        out.println("<div style=\"position: absolute; right:    0; bottom:   0;\">");
        out.println("<br><a class=\"button-17\" href=\"index.jsp\" > Return Home </a> </div>");



        out.println("</body></html>");
        out.close();

    }


}
