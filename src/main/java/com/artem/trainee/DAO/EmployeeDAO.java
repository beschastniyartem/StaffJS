package com.artem.trainee.DAO;

import com.artem.trainee.model.Employee;

import java.util.List;

/**
 * Created by artemb on 07.07.14.
 */
public interface EmployeeDAO {

    public void addEmployee(Employee emp);
    public void updateEmployee(Employee emp);
    public Employee getEmployeeById(int id);
    public void deleteEmployee(int id);
    public void deleteEmployee(Employee employee);
    public List<Employee> getEmployees();
    public List<Employee> getEmployeesByDepartment(int dep_id) ;
    public boolean checkEmployeeEmailIsUnique(String email);
    public boolean checkEmployeeEmailIsUniqueEdit(String email,int empl_id);

}
