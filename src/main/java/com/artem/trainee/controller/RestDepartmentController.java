package com.artem.trainee.controller;

import com.artem.trainee.model.Department;
import com.artem.trainee.service.SpringService.DepartmentService;
import com.artem.trainee.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;


/**
 * Created on 21.07.14.
 */
@RestController
@RequestMapping("/rest/")
public class RestDepartmentController {
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private ValidationUtils validationUtils;

    @RequestMapping(value = "/department")
    public @ResponseBody List<Department> getAllDepartments() {
        List<Department> departments = departmentService.getDepartments();
        return departments;
    }
    @RequestMapping(value="/add")
    @ResponseBody
    public String addDepartment(Department department) {
        Map<String, String> errors = validationUtils.validation(department);
        if(errors.isEmpty()) {
            departmentService.addDepartment(department);
        }
        return  errors.get("name");
    }
    @RequestMapping(value="/delete")
    @ResponseBody
    public void deleteDepartment(Department department) {
        departmentService.deleteDepartment(department);
    }
    @RequestMapping(value="/edit")
    @ResponseBody
    public String updateDepartment(Department department) {
        Map<String, String> errors = validationUtils.validation(department);
        if(errors.isEmpty()) {
            departmentService.updateDepartment(department);
        }
        return  errors.get("name");
    }

}
