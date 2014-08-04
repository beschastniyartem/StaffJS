package com.artem.trainee.controller;

import com.artem.trainee.model.Department;
import com.artem.trainee.model.Employee;
import com.artem.trainee.service.SpringService.DepartmentService;
import com.artem.trainee.service.SpringService.EmployeeService;
import com.artem.trainee.utils.ValidationUtils;
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
    @Autowired
    private ValidationUtils validationUtils;

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
    public Map<String, String> addSubmitEmployee(Employee employee) {
        Map<String, String> errors = validationUtils.validation(employee);
        if(errors.isEmpty()) {
            employeeService.addEmployee(employee);
        }
        return errors;
    }

    @RequestMapping(value = "/editEmployee")
    @ResponseBody
    public Map<String, String> editSubmitEmployee(Employee employee) {
        Map<String, String> errors = validationUtils.validation(employee);
        if(errors.isEmpty()) {
            employeeService.updateEmployee(employee);
        }
        return errors;

    }
}
