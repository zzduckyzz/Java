package com.dao;

import com.model.Employee;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDBUntil {
    private DataSource dataSource;
    public EmployeeDBUntil(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    public List<Employee> getEmployees() throws SQLException{
        List<Employee> employees = new ArrayList();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = this.dataSource.getConnection();
            String sql = "select * from Employee";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while(rs.next()) {
                int id = rs.getInt("id");
                String fullname = rs.getString("full_Name");
                String birthday = rs.getString("birthday");
                String address = rs.getString("address");
                String position = rs.getString("position");
                String department = rs.getString("department");
                Employee tempEmployee = new Employee(id, fullname, birthday, address, position, department);
                employees.add(tempEmployee);
            }

            return employees;
        } finally {
            this.close(conn, stmt, rs);
        }
    }

    private void close(Connection conn, Statement stmt, ResultSet rs) {
        try {
            if (conn != null) {
                conn.close();
            }

            if (stmt != null) {
                stmt.close();
            }

            if (rs != null) {
                rs.close();
            }
        } catch (Exception var5) {
            var5.printStackTrace();
        }

    }

    public void addEmployee(Employee theEmployee) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = this.dataSource.getConnection();
            String sql = "insert into employee (full_name, birthday, address, position, department)values(?,?,?,?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, theEmployee.getFull_Name());
            stmt.setString(2, theEmployee.getBirthday());
            stmt.setString(3, theEmployee.getAddress());
            stmt.setString(4, theEmployee.getPosition());
            stmt.setString(5, theEmployee.getDepartment());
            stmt.execute();
        } finally {
            this.close(conn, stmt, (ResultSet)null);
        }

    }
}
