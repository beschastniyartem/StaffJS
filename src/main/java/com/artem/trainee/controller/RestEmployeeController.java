package com.artem.trainee.controller;

import com.artem.trainee.model.Department;
import com.artem.trainee.model.Employee;
import com.artem.trainee.service.SpringService.DepartmentService;
import com.artem.trainee.service.SpringService.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * Created on 24.07.14.
 */
@RestController
@RequestMapping("/rest/")
public class RestEmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @RequestMapping(value = "/employee/{dep_id}")
    public @ResponseBody
    List<Employee> employeeList(@PathVariable Integer dep_id) {
        List<Employee> employeeList = employeeService.getEmployeesByDepartment(dep_id);
        return employeeList;
    }

    @RequestMapping(value="/deleteEmployee")
    @ResponseBody
    public void deleteEmployee(Employee employee) {
        employeeService.deleteEmployee(employee);
    }

    @RequestMapping(value = "/addEmployee")
    @ResponseBody
    public void addSubmitEmployee(Employee employee) {
        employeeService.addEmployee(employee);
    }

    @RequestMapping(value = "/editEmployee")
    @ResponseBody
    public void editSubmitEmployee(Employee employee) {
        employeeService.updateEmployee(employee);
    }
}
