package com.artem.trainee.DAO;

import com.artem.trainee.DB.DBConnection;
import com.artem.trainee.model.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by artemb on 07.07.14.
 */
public class DepartmentDAOImpl implements DepartmentDAO  {
    private Connection connection = null;
    @Override
    public boolean checkDepartmentNameIsUnique(String name) {
        DBConnection dbConnection = new DBConnection();
        connection = dbConnection.getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("select * from Department where name=?");
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return false;
            }
        } catch (SQLException e) {
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
        return true;
    }

    @Override
    public boolean checkDepartmentNameIsUniqueEdit(String name, int id)  {
        DBConnection dbConnection = new DBConnection();
        connection = dbConnection.getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("select * from Department where name=?");
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                if(rs.getInt("idDepartment")!=id){
                    return false;
                }
            }
        } catch (SQLException e) {
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
        return true;
    }


    @Override
    public void addDepartment(Department department) {
        DBConnection dbConnection = new DBConnection();
        connection = dbConnection.getConnection();
        PreparedStatement ps = null;
        try{
            ps = connection.prepareStatement("insert into Department (name) values (?)");
            ps.setString(1, department.getName());
            ps.execute();
        }
        catch (SQLException e) {
            // System.out.println("UNIQUE EXCEPTION");
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
    }

    @Override
    public void updateDepartment(Department department) {
        DBConnection dbConnection = new DBConnection();
        connection = dbConnection.getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("update Department set name=?"
                    +"where idDepartment=?");
            ps.setString(1, department.getName());
            ps.setDouble(2, department.getDep_id());
            ps.executeUpdate();
        } catch (SQLException e) {
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
    }

    @Override
    public Department getDepartmentById(int id) {
        DBConnection dbConnection = new DBConnection();
        connection = dbConnection.getConnection();
        Department department= new Department();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("select * from Department where idDepartment=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                department.setDep_id(id);
                department.setName(rs.getString("name"));
            }
        } catch (SQLException e) {
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

        return department;
    }

    @Override
    public void deleteDepartment(int id) {
        DBConnection dbConnection = new DBConnection();
        connection = dbConnection.getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection .prepareStatement("delete from Department where idDepartment=?");
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
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
    }

    @Override
    public void deleteDepartment(Department department) {

        DBConnection dbConnection = new DBConnection();
        connection = dbConnection.getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection .prepareStatement("delete from Department where idDepartment=?");
            ps.setInt(1, department.getDep_id());
            ps.executeUpdate();

        } catch (SQLException e) {
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
    }

    @Override
    public List<Department> getDepartments(){
        DBConnection dbConnection = new DBConnection();
        connection = dbConnection.getConnection();
        Statement statement = null;
        ResultSet rs = null;
        String query = "select * from Department;";
        List<Department> departmentList = new ArrayList<Department>();

        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(query);

            while (rs.next()) {
                Department department = new Department();
                department.setDep_id(rs.getInt("idDepartment"));
                department.setName(rs.getString("name"));
                departmentList.add(department);
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
        return departmentList;
    }
}
