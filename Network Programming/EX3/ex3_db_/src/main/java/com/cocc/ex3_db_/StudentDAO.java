package com.cocc.ex3_db_;
//IDE USED: IntelliJ IDEA Ultimate 2021.2.3


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    public StudentDAO(){
    }

    //connect with sql db
    public static Connection getConnection(){
         Connection con = null;

        try{
            Class.forName("org.mariadb.jdbc.Driver");//MariaDB Driver
            con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/askisi1_db", "kokkalis", "1234"); //MariaDB
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        return con;
    }

    // update the data of a student
    public static int update(Student student){
        int status = 0;
        Connection con = StudentDAO.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("update students set firstname=?,lastname=?,university=?,semester=?,modules=? where ID=?");
            ps.setString(1,student.getFirstName());
            ps.setString(2,student.getLastName());
            ps.setString(3,student.getUniversity());
            ps.setString(4,student.getSemester());
            ps.setString(5,student.getModules());
            ps.setString(6, student.getId());


            status=ps.executeUpdate();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return status;
    }

    //delete a student from database
    public static int delete(String id){
        int status=0;
        Connection con = StudentDAO.getConnection();
        try{
            PreparedStatement ps = con.prepareStatement("delete from students where id=?");
            ps.setString(1, String.valueOf(id));
            status=ps.executeUpdate();
            con.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return status;

    }


    // add new student to database
    public static int save(Student student){
        int status =0;
        Connection conn = StudentDAO.getConnection();
        String sqlCommand = "INSERT INTO students (ID, firstname, lastname, university, semester, Modules) VALUES (?, ?, ?, ?,?,?);" ;
        //System.out.println(student);

        try{
            PreparedStatement ps = conn.prepareStatement(sqlCommand);
            ps.setString(1, student.getId());
            ps.setString(2, student.getFirstName());
            ps.setString(3, student.getLastName());
            ps.setString(4, student.getUniversity());
            ps.setString(5, student.getSemester());
            ps.setString(6, student.getModules());
            status= ps.executeUpdate();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return status;
    }


    //get student id from database
    public static Student getStudentById(String id){
        Student student = new Student();
        Connection con = StudentDAO.getConnection();
        try{
            PreparedStatement ps = con.prepareStatement("select * from students where id=?");
            ps.setString(1,id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                student.setId(rs.getString(1));
                student.setFirstName(rs.getString(2));
                student.setLastName(rs.getString(3));
                student.setUniversity(rs.getString(4));
                student.setSemester(rs.getString(5));
                student.setModules(rs.getString(6));
            }
            con.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return student;
    }

    // show all students
    public static List<Student> getAllStudents(){
        List<Student> studentList = new ArrayList<>();
        Connection con = StudentDAO.getConnection();

        try{
            PreparedStatement ps = con.prepareStatement("select * from students");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Student student = new Student();
                student.setId(rs.getString(1));
                student.setFirstName(rs.getString(2));
                student.setLastName(rs.getString(3));
                student.setUniversity(rs.getString(4));
                student.setSemester(rs.getString(5));
                student.setModules(rs.getString(6));
                studentList.add(student);
            }
            con.close();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return studentList;
    }



}
