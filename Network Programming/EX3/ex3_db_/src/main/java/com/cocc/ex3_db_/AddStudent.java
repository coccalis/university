package com.cocc.ex3_db_;
//IDE USED: IntelliJ IDEA Ultimate 2021.2.3

import java.io.*;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


@WebServlet(name = "addStudentServlet", value = "/addStudent-servlet")
public class AddStudent extends HttpServlet {


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        //get the data from html page
        String id = request.getParameter("ID");
        String firstName = request.getParameter("Firstname");
        String lastName = request.getParameter("Lastname");
        String university = request.getParameter("University");
        String semester = request.getParameter("Semester");
        String modules = request.getParameter("Modules");

        //create student
        Student student = new Student();
        student.setId(id);
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setUniversity(university);
        student.setSemester(semester);
        student.setModules(modules);


        //call save  from StudentDao to save the student to sql database
        int status = StudentDAO.save(student);
        if(status>0){ //if it succeeds load addStudentSuccess html page else load addStudentError html page.
            request.getRequestDispatcher("addStudentSuccess.html").include(request,response);
        }else {
            request.getRequestDispatcher("addStudentError.html").include(request,response);

        }
        out.close();
    }

    public void destroy() {
    }
}