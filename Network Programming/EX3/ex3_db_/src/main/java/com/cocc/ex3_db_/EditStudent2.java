package com.cocc.ex3_db_;
//IDE USED: IntelliJ IDEA Ultimate 2021.2.3


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;




@WebServlet(name="editStudent2", value = "/editStudent2")
public class EditStudent2 extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();

        //get data from editStudent servlet
        String sid=request.getParameter("ID");
        String id=sid;
        String firstName = request.getParameter("Firstname");
        String lastName = request.getParameter("Lastname");
        String university = request.getParameter("University");
        String semester = request.getParameter("Semester");
        String modules = request.getParameter("Modules");

        //create the student
        Student student = new Student();
        student.setId(id);
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setUniversity(university);
        student.setSemester(semester);
        student.setModules(modules);

        //update the data for student
        int status=StudentDAO.update(student);
        if(status>0){
            response.sendRedirect("viewStudent"); //return to viewstudent page

        }else{
            out.println("Error! Unable to update record!"); //error
        }

        out.close();
    }

}
