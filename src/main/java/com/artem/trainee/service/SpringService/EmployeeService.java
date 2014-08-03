package com.artem.trainee.service.SpringService;

import com.artem.trainee.model.Employee;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created on 15.07.14.
 */
@Transactional
public interface EmployeeService  {
    public void addEmployee(Employee emp);
    public void updateEmployee(Employee emp);
    public Employee getEmployeeById(int id);
    public void deleteEmployee(Employee employee);
    public List<Employee> getEmployeesByDepartment(int dep_id) ;
    public boolean checkEmployeeEmailIsUniqueEdit(String email,int empl_id);
}
