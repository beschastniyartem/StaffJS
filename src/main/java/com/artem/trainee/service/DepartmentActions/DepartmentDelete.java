package com.artem.trainee.service.DepartmentActions;

import com.artem.trainee.DAO.DepartmentDAO;
import com.artem.trainee.DAO.DepartmentDAOHibernateImpl;
import com.artem.trainee.DAO.Factory;
import com.artem.trainee.model.Department;
import com.artem.trainee.service.ResponseActions.Redirect;
import com.artem.trainee.service.ResponseActions.ResponseAction;
import com.artem.trainee.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by artemb on 04.07.14.
 */
@Service("/delete")
public class DepartmentDelete implements StaffService {
    @Autowired
    private DepartmentDAO departmentDAOHibernate;
    @Override
    public ResponseAction handle(HttpServletRequest req, HttpServletResponse resp) {
        Integer dep_id = Integer.valueOf(req.getParameter("dep_id"));
        //Department department = Factory.getInstance().getDepartmentDAO().getDepartmentById(dep_id);
        Department department = departmentDAOHibernate.getDepartmentById(dep_id);
        //Factory.getInstance().getDepartmentDAO().deleteDepartment(department);
        departmentDAOHibernate.deleteDepartment(department);
        //departmentDAO.deleteDepartment(dep_id);

        return new Redirect("/department");

    }
}
