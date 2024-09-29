package com.ex4.ex4_db_.service;


import com.ex4.ex4_db_.database.StudentDAO;
import com.ex4.ex4_db_.model.Student;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/studentService")
public class StudentServiceImpl implements StudentService {

    StudentDAO studentDAO = new StudentDAO();

    @GET
    @Path("/studentsXml")
    @Produces(MediaType.APPLICATION_XML)
    @Override
    public List<Student> getStudents(){
        return studentDAO.getAllStudents();
    }

    @GET
    @Path("/studentsJson")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public List<Student> getStudentsJson(){
        return studentDAO.getAllStudents();
    }



    @GET
    @Path("/searchStudentXml")
    @Produces(MediaType.APPLICATION_XML)
    @Override
    public Student getStudentXml(@QueryParam("studentID")String id){
        return studentDAO.getStudent(id);
    }

    @GET
    @Path("/searchStudentJson")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Student getStudentJson(@QueryParam("studentID")String id){
        return studentDAO.getStudent(id);
    }

    @POST
    @Path("/addStudents")
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Override
    public String createStudent(@FormParam("ID") String id,
                                @FormParam("Firstname") String firstName,
                                @FormParam("Lastname") String lastName,
                                @FormParam("University") String university,
                                @FormParam("Semester") String semester,
                                @FormParam("Modules") String modules){

        Student student = new Student();
        student.setId(id);
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setUniversity(university);
        student.setSemester(semester);
        student.setModules(modules);
        int result= studentDAO.addStudent(student);
        if(result == 1) {
            return RESULT_SUCCESS;

        }
        return RESULT_FAILURE;
    }


    @POST
    @Path("/updateStudents")
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Override
    public String updateStudent(@FormParam("ID") String id,
                                @FormParam("Firstname") String firstName,
                                @FormParam("Lastname") String lastName,
                                @FormParam("University") String university,
                                @FormParam("Semester") String semester,
                                @FormParam("Modules") String modules){
        Student student = new Student();
        student.setId(id);
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setUniversity(university);
        student.setSemester(semester);
        student.setModules(modules);
        int result= studentDAO.updateStudent(student);
        if(result == 1) {
            return RESULT_SUCCESS;

        }
        return RESULT_FAILURE;


    }

    @GET
    @Path("/deleteStudent")
    @Produces(MediaType.TEXT_HTML)
    @Override
    public String deleteStudent(@QueryParam("studentID") String id){
        int result = studentDAO.deleteStudent(id);
        if (result == 1) {
            return RESULT_SUCCESS;
        }
        return RESULT_FAILURE;
    }


}
