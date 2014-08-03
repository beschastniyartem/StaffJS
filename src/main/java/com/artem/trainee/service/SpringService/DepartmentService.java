package com.artem.trainee.service.SpringService;

import com.artem.trainee.model.Department;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created on 15.07.14.
 */
@Transactional
public interface DepartmentService {
    public void addDepartment(Department department);
    public void updateDepartment(Department department);
    public Department getDepartmentById(int id);
    public void deleteDepartment(Department department);
    public List<Department> getDepartments() ;
    public boolean checkDepartmentNameIsUniqueEdit(String name,int id);
}
