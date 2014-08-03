package com.artem.trainee.service.SpringService;

import com.artem.trainee.DAO.EmployeeDAO;
import com.artem.trainee.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created on 15.07.14.
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeDAO employeeDAO;

    @Override
    public void addEmployee(Employee emp) {
        employeeDAO.addEmployee(emp);
    }

    @Override
    public void updateEmployee(Employee emp) {
        employeeDAO.updateEmployee(emp);
    }

    @Override
    public Employee getEmployeeById(int id) {
        return employeeDAO.getEmployeeById(id);
    }

    @Override
    public void deleteEmployee(Employee employee) {
        employeeDAO.deleteEmployee(employee);
    }

    @Override
    public List<Employee> getEmployeesByDepartment(int dep_id) {
        return employeeDAO.getEmployeesByDepartment(dep_id);
    }
    //lalala
    @Override
    public boolean checkEmployeeEmailIsUniqueEdit(String email, int empl_id) {
        return false;
    }
}
