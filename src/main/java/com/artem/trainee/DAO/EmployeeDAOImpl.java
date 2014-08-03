package com.artem.trainee.DAO;

import com.artem.trainee.DB.DBConnection;
import com.artem.trainee.model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by artemb on 07.07.14.
 */
public class EmployeeDAOImpl implements EmployeeDAO {
    private Connection connection;
    @Override
    public boolean checkEmployeeEmailIsUniqueEdit(String email, int empl_id) {

            DBConnection dbConnection = new DBConnection();
            connection = dbConnection.getConnection();
            PreparedStatement ps = null;
            try {
                ps = connection.prepareStatement("select * from Employee where email=?");
                ps.setString(1, email);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    if(rs.getInt("idEmployee")!=empl_id){
                        return false;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return true;
    }

    @Override
    public boolean checkEmployeeEmailIsUnique(String email) {

        DBConnection dbConnection = new DBConnection();
        connection = dbConnection.getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("select * from Employee where email=?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }



    public EmployeeDAOImpl(){}
    @Override
    public void addEmployee(Employee emp) {
        DBConnection dbConnection = new DBConnection();
        connection = dbConnection.getConnection();
        PreparedStatement ps = null;
        try{
            ps = connection.prepareStatement("insert into Employee (email,salary,birthday,dep_id) values (?,?,?,?)");
            ps.setString(1, emp.getEmail());
            ps.setDouble(2, emp.getSalary());
            ps.setDate(3, emp.getBirthday());
            ps.setInt(4,emp.getDep_id());

            ps.execute();
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void updateEmployee(Employee emp) {
        DBConnection dbConnection = new DBConnection();
        connection = dbConnection.getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("update Employee set email=?, salary=?, birthday= ?"
                    +"where idEmployee=?");
            ps.setString(1, emp.getEmail());
            ps.setDouble(2, emp.getSalary());
            ps.setDate(3, emp.getBirthday());
            ps.setInt(4, emp.getEmpl_id());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Employee getEmployeeById(int id) {
        DBConnection dbConnection = new DBConnection();
        connection = dbConnection.getConnection();
        Employee employee = new Employee();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("select * from Employee where idEmployee=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                employee.setEmpl_id(id);
                employee.setEmail(rs.getString("email"));
                employee.setSalary(rs.getDouble("salary"));
                employee.setBirthday(rs.getDate("birthday"));
                employee.setDep_id(rs.getInt("dep_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return employee;
    }

    @Override
    public void deleteEmployee(int id) {
        DBConnection dbConnection = new DBConnection();
        connection = dbConnection.getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection .prepareStatement("delete from Employee where idEmployee=?");
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteEmployee(Employee employee) {
        DBConnection dbConnection = new DBConnection();
        connection = dbConnection.getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection .prepareStatement("delete from Employee where idEmployee=?");
            ps.setInt(1, employee.getEmpl_id());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Employee> getEmployees() {
        DBConnection dbConnection = new DBConnection();
        connection = dbConnection.getConnection();
        Statement statement = null;
        ResultSet rs = null;
        String query = "select * from Employee;";
        List<Employee> employeeList = new ArrayList<Employee>();

        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(query);

            while (rs.next()) {
                Employee employee = new Employee();
                employee.setEmpl_id(rs.getInt("idEmployee"));
                employee.setEmail(rs.getString("email"));
                employee.setSalary(rs.getDouble("salary"));
                employee.setBirthday(rs.getDate("birthday"));
                employee.setDep_id(rs.getInt("dep_id"));
                employeeList.add(employee);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return employeeList;
    }

    @Override
    public List<Employee> getEmployeesByDepartment(int dep_id) {
        DBConnection dbConnection = new DBConnection();
        connection = dbConnection.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "select * from Employee where dep_id=?;";
        List<Employee> employeeList = new ArrayList<Employee>();

        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1,dep_id);
            rs = ps.executeQuery();

            while (rs.next()) {
                Employee employee = new Employee();
                employee.setEmpl_id(rs.getInt("idEmployee"));
                employee.setEmail(rs.getString("email"));
                employee.setSalary(rs.getDouble("salary"));
                employee.setBirthday(rs.getDate("birthday"));
                employeeList.add(employee);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(connection==null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return employeeList;
    }
}
