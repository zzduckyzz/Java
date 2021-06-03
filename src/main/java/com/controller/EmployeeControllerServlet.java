package com.controller;

import com.dao.EmployeeDBUntil;
import com.model.Employee;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

@WebServlet(
        name = "EmployeeControllerServlet",
        value = {"/EmployeeControllerServlet"}
)
public class EmployeeControllerServlet extends HttpServlet {
    private DataSource dataSource;
    private EmployeeDBUntil employeeDBUntil;

    public EmployeeControllerServlet() {
    }

    public void init() throws ServletException {
        super.init();
        InitialContext initContext = null;

        try {
            initContext = new InitialContext();
            Context envContext = (Context)initContext.lookup("java:comp/env");
            this.dataSource = (DataSource)envContext.lookup("jdbc/codeleanvn");
        } catch (NamingException var4) {
            var4.printStackTrace();
        }

        try {
            this.employeeDBUntil = new EmployeeDBUntil(this.dataSource);
        } catch (Exception var3) {
            throw new ServletException(var3);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String theCommand = request.getParameter("command");
            if (theCommand == null) {
                theCommand = "LIST";
            }

            byte var5 = -1;
            switch(theCommand.hashCode()) {
                case 64641:
                    if (theCommand.equals("ADD")) {
                        var5 = 1;
                    }
                    break;
                case 2336926:
                    if (theCommand.equals("LIST")) {
                        var5 = 0;
                    }
            }

            switch(var5) {
                case 0:
                    this.listEmployee(request, response);
                    break;
                case 1:
                    this.addEmployee(request, response);
            }

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    protected void listEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        List<Employee> employeeList = this.employeeDBUntil.getEmployees();
        request.setAttribute("Employee_list", employeeList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("list.jsp");
        dispatcher.forward(request, response);
    }

    protected void addEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, SQLException, IOException {
        String fullname = request.getParameter("fullname");
        String birthday = request.getParameter("birthday");
        String address = request.getParameter("address");
        String position = request.getParameter("position");
        String department = request.getParameter("department");
        Employee theEmployee = new Employee(fullname, birthday, address, position, department);
        this.employeeDBUntil.addEmployee(theEmployee);
        this.listEmployee(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
