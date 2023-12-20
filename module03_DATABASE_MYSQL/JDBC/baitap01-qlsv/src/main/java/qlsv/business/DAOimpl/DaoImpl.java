package qlsv.business.DAOimpl;

import qlsv.business.DAO.IgenericDao;
import qlsv.business.entity.Student;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static java.sql.DriverManager.getConnection;

public class DaoImpl implements IgenericDao <Student, Integer> {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/qlsv";
    private static final String USER = "your_username";
    private static final String PASSWORD = "your_password";


    @Override
    public void create(Student student) {

    }

    private Connection getConnection() {
        return null;
    }


    @Override
    public Student findById(Integer id) {
        return null;
    }

    @Override
    public List<Student> findAll() {
        return null;
    }

    @Override
    public void update(Student entity) {

    }

    @Override
    public void delete(Integer id) {

    }
}
