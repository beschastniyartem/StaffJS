package com.artem.trainee.service.SpringService;

import com.artem.trainee.DAO.DepartmentDAO;
import com.artem.trainee.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created on 15.07.14.
 */
@Service
public class DepartmentServiceImpl implements DepartmentService{
    @Autowired
    private DepartmentDAO departmentDAO;
    @Override
    public void addDepartment(Department department) {
        departmentDAO.addDepartment(department);
    }

    @Override
    public void updateDepartment(Department department) {
        departmentDAO.updateDepartment(department);
    }

    @Override
    public Department getDepartmentById(int id) {
        return departmentDAO.getDepartmentById(id);
    }

    @Override
    public void deleteDepartment(Department department) {
        departmentDAO.deleteDepartment(department);
    }

    @Override
    public List<Department> getDepartments() {
        return departmentDAO.getDepartments();
    }
    //not impl
    @Override
    public boolean checkDepartmentNameIsUniqueEdit(String name, int id) {
        return false;
    }
}
