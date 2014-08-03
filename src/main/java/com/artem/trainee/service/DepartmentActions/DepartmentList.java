package com.artem.trainee.service.DepartmentActions;

import com.artem.trainee.DAO.DepartmentDAO;
import com.artem.trainee.DAO.DepartmentDAOHibernateImpl;
import com.artem.trainee.DAO.Factory;
import com.artem.trainee.service.ResponseActions.Forward;
import com.artem.trainee.service.ResponseActions.ResponseAction;
import com.artem.trainee.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by artemb on 03.07.14.
 */
@Service("/department")
public class DepartmentList implements StaffService {
    //DepartmentDAO departmentDAO = new DepartmentDAOImpl();
    @Autowired
    private DepartmentDAO departmentDAOHibernate;

    @Override
    public ResponseAction handle(HttpServletRequest req, HttpServletResponse resp) {
        //req.setAttribute("departmentList",departmentDAO.getDepartments());
        //req.setAttribute("departmentList", Factory.getInstance().getDepartmentDAO().getDepartments());
        req.setAttribute("departmentList", departmentDAOHibernate.getDepartments());
        return new Forward("/department_list.jsp");
    }
}
