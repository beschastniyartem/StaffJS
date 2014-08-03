package com.artem.trainee.service.EmployeeActions;

import com.artem.trainee.DAO.EmployeeDAO;
import com.artem.trainee.DAO.EmployeeDAOHibernateImpl;
import com.artem.trainee.DAO.EmployeeDAOImpl;
import com.artem.trainee.DAO.Factory;
import com.artem.trainee.service.ResponseActions.Forward;
import com.artem.trainee.service.ResponseActions.ResponseAction;
import com.artem.trainee.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by artemb on 04.07.14.
 */
@Service("/employee")
public class DepartmentListEmployee implements StaffService {
    @Autowired
    private EmployeeDAO employeeDAOHibernate;
    //EmployeeDAO employeeDAO= new EmployeeDAOImpl();
    @Override
    public ResponseAction handle(HttpServletRequest req, HttpServletResponse resp) {
        Integer dep_id = Integer.parseInt(req.getParameter("dep_id"));
        //req.setAttribute("employeeList", Factory.getInstance().getEmployeeDAO().getEmployeesByDepartment(dep_id));
        req.setAttribute("employeeList", employeeDAOHibernate.getEmployeesByDepartment(dep_id));
        //req.setAttribute("employeeList",employeeDAO.getEmployeesByDepartment(dep_id));
        req.setAttribute("dep_id",dep_id);
            return new Forward("/employee_list_by_department.jsp");
    }
}
