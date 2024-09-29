package com.cocc.ex3_db_;
//IDE USED: IntelliJ IDEA Ultimate 2021.2.3

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "deleteStudent", value = "/deleteStudent")
public class DeleteStudent extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //get id of the student and delete student. Then return back to viewStudent page
        String sid=request.getParameter("id");
        String id = sid;
        StudentDAO.delete(id);
        response.sendRedirect("viewStudent");


    }


}
