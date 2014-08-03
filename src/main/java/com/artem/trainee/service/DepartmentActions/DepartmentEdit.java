package com.artem.trainee.service.DepartmentActions;

import com.artem.trainee.DAO.DepartmentDAO;
import com.artem.trainee.DAO.DepartmentDAOHibernateImpl;
import com.artem.trainee.DAO.Factory;
import com.artem.trainee.model.Department;
import com.artem.trainee.service.ResponseActions.Forward;
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
@Service("/edit")
public class DepartmentEdit implements StaffService {
    @Autowired
    private DepartmentDAO departmentDAOHibernate;
    @Override
    public ResponseAction handle(HttpServletRequest req, HttpServletResponse resp) {
        //DepartmentDAO departmentDAO = new DepartmentDAOImpl();
        Integer dep_id = Integer.parseInt(req.getParameter("dep_id"));
        //Factory.getInstance().getDepartmentDAO().getDepartmentById(dep_id);
       // Department department= departmentDAO.getDepartmentById(dep_id);
        //Department department = Factory.getInstance().getDepartmentDAO().getDepartmentById(dep_id);
        Department department = departmentDAOHibernate.getDepartmentById(dep_id);
        req.setAttribute("department",department);
        return new Forward("/department_edit.jsp");
    }
}
