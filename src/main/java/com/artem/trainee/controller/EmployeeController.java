package com.artem.trainee.controller;

import com.artem.trainee.model.Department;
import com.artem.trainee.model.Employee;
import com.artem.trainee.service.SpringService.EmployeeService;
import com.artem.trainee.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.util.List;
import java.util.Map;

/**
 * Created on 16.07.14.
 */
@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ValidationUtils validationUtils;
    @RequestMapping(value = "/employee/{dep_id}")
    public ModelAndView employeeList(@ModelAttribute @PathVariable Integer dep_id,Employee employee) {
        ModelAndView modelAndView = new ModelAndView("employee_list_by_department");
        List<Employee> employeeList = employeeService.getEmployeesByDepartment(dep_id);
        /*Employee employee = new Employee();*/
        employee.setDep_id(dep_id);
        modelAndView.addObject("employeeList", employeeList);
        return modelAndView;
    }
    @RequestMapping(value = "/addEmployee/{dep_id}")
    public ModelAndView addEmployee(@PathVariable Integer dep_id) {
        ModelAndView modelAndView = new ModelAndView("employee_add");
        Employee employee = new Employee();
        employee.setDep_id(dep_id);
        employee.setBirthday(Date.valueOf("2014-01-01"));
        modelAndView.addObject("employee", employee);
        return modelAndView;
    }
    @RequestMapping(value = "/addEmployeeSubmit")
    public ModelAndView addSubmitEmployee(@ModelAttribute Employee employee) {
        ModelAndView modelAndView = null;
        Map<String, String> errors = validationUtils.validation(employee);
        if (errors.isEmpty()) {
            modelAndView = new ModelAndView("redirect:employee/"+employee.getDep_id()+".html");
            employeeService.addEmployee(employee);
        } else {
            modelAndView = new ModelAndView("employee_add");
            modelAndView.addObject("employee", employee);
            modelAndView.addObject("errors", errors);
        }
        return modelAndView;
    }
    @RequestMapping(value = "/deleteEmployee")
    public ModelAndView deleteEmployee(@ModelAttribute Employee employee) {
        Employee employeeToDelete = employeeService.getEmployeeById(employee.getEmpl_id());
        //Department departmentLocal = departmentService.getDepartmentById(department.getDep_id());
        ModelAndView modelAndView = new ModelAndView("redirect:employee/"+employee.getDep_id()+".html");
        employeeService.deleteEmployee(employeeToDelete);
        return modelAndView;
    }

    @RequestMapping(value="/editEmployee/{empl_id}")
    public ModelAndView editEmployee(@PathVariable Integer empl_id) {
        ModelAndView modelAndView = new ModelAndView("employee_edit");
        Employee employee = employeeService.getEmployeeById(empl_id);
        modelAndView.addObject("employee",employee);
        return modelAndView;
    }

    @RequestMapping(value="/editEmployeeSubmit")
    public ModelAndView edditSubmitEmployee(@ModelAttribute Employee employee) {
        ModelAndView modelAndView = null;
        Map<String, String> errors = validationUtils.validation(employee);
        if (errors.isEmpty()) {
            modelAndView = new ModelAndView("redirect:employee/"+employee.getDep_id()+".html");
            employeeService.updateEmployee(employee);
        }else{
            modelAndView = new ModelAndView("employee_edit");
            modelAndView.addObject("employee", employee);
            modelAndView.addObject("errors", errors);
        }
        return modelAndView;
    }
}
