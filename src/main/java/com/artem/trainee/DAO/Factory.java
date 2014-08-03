package com.artem.trainee.DAO;

/**
 * Created on 09.07.14.
 */
public class Factory {
    private static DepartmentDAO departmentDAO = null;
    private static EmployeeDAO employeeDAO = null;
    private static Factory instance = null;

    public static Factory getInstance(){
        if (instance == null){
            instance = new Factory();
        }
        return instance;
    }

    public DepartmentDAO getDepartmentDAO(){
        if (departmentDAO == null){
           // departmentDAO = new DepartmentDAOImpl();
            departmentDAO = new DepartmentDAOHibernateImpl();
        }
        return departmentDAO;
    }

    public EmployeeDAO getEmployeeDAO(){
        if (employeeDAO == null){
            //employeeDAO = new EmployeeDAOImpl();
            employeeDAO = new EmployeeDAOHibernateImpl();
        }
        return employeeDAO;
    }


}