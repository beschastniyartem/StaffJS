package com.artem.trainee.DAO;

import com.artem.trainee.model.Department;

import java.util.List;

/**
 * Created by artemb on 07.07.14.
 */
public interface DepartmentDAO {
    public void addDepartment(Department department);
    public void updateDepartment(Department department);
    public Department getDepartmentById(int id);
    public void deleteDepartment(int id);
    public void deleteDepartment(Department department);
    public List<Department> getDepartments() ;
    public boolean checkDepartmentNameIsUnique(String name);
    public boolean checkDepartmentNameIsUniqueEdit(String name,int id);
}
