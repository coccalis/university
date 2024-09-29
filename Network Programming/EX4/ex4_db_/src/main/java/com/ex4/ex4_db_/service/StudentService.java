package com.ex4.ex4_db_.service;

import com.ex4.ex4_db_.model.Student;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface StudentService {

    static final String RESULT_SUCCESS = "<h3>success</h3>";
    static final String RESULT_FAILURE = "<h3>failure</h3>";

    List<Student> getStudents();
    List<Student> getStudentsJson();

    Student getStudentXml(String id);
    Student getStudentJson(String id);

    String createStudent(String id, String firstName, String lastName,
                         String university,String semester, String modules);
    String updateStudent(String id, String firstName, String lastName,
                          String university,String semester, String modules);
    String deleteStudent(String id);

}
