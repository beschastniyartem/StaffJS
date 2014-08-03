package com.artem.trainee.controller;

import com.artem.trainee.model.Department;
import com.artem.trainee.service.SpringService.DepartmentService;
import com.artem.trainee.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created on 15.07.14.
 */
@Controller
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private ValidationUtils validationUtils;

    @RequestMapping(value = "/department")
    @ResponseBody
    public ModelAndView departmentList() {
        ModelAndView modelAndView = new ModelAndView("department_list");
        List<Department> departmentList = departmentService.getDepartments();
        modelAndView.addObject("departmentList", departmentList);
        modelAndView.addObject("department",new Department());
        return modelAndView;
    }
    /*@RequestMapping(value = "/restDepartment", method = RequestMethod.GET)
    public @ResponseBody List<Department> getAllDepartments() {
        List<Department> departments = departmentService.getDepartments();
        return departments;
    }*/

    @RequestMapping(value = "/add")
    public ModelAndView addDepartment() {
        ModelAndView modelAndView = new ModelAndView("department_add");
        modelAndView.addObject("department",new Department());
        return modelAndView;
    }

    @RequestMapping(value = "/addSubmit")
    public ModelAndView addSubmitDepartment(@ModelAttribute Department department) {
        ModelAndView modelAndView = null;
        Map<String, String> errors = validationUtils.validation(department);
        if (errors.isEmpty()) {
            modelAndView = new ModelAndView("redirect:department.html");
            departmentService.addDepartment(department);
        } else {
            modelAndView = new ModelAndView("department_add");
            modelAndView.addObject("department", department);
            modelAndView.addObject("errors", errors);
        }
        return modelAndView;
    }

    @RequestMapping(value = "/delete")
    public ModelAndView deleteDepartment(@ModelAttribute Department department) {
        //Department departmentLocal = departmentService.getDepartmentById(department.getDep_id());
        ModelAndView modelAndView = new ModelAndView("redirect:department.html");
        departmentService.deleteDepartment(department);
        return modelAndView;
    }
    @RequestMapping(value="/edit/{dep_id}")
    public ModelAndView editDepartment(@PathVariable Integer dep_id) {
        ModelAndView modelAndView = new ModelAndView("department_edit");
        Department department = departmentService.getDepartmentById(dep_id);
        modelAndView.addObject("department",department);
        return modelAndView;
    }

    @RequestMapping(value="/editSubmit")
    public ModelAndView editSubmitDeparment(@ModelAttribute Department department) {
        ModelAndView modelAndView = null;
        Map<String, String> errors = validationUtils.validation(department);
        if (errors.isEmpty()) {
            modelAndView = new ModelAndView("redirect:department.html");
            departmentService.updateDepartment(department);

        }else{
            modelAndView = new ModelAndView("department_edit");
            modelAndView.addObject("department", department);
            modelAndView.addObject("errors", errors);
        }
        return modelAndView;
    }

}


