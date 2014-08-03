package com.artem.trainee.service.EmployeeActions;

import com.artem.trainee.DAO.EmployeeDAO;
import com.artem.trainee.DAO.EmployeeDAOHibernateImpl;
import com.artem.trainee.DAO.Factory;
import com.artem.trainee.model.Employee;
import com.artem.trainee.service.ResponseActions.Forward;
import com.artem.trainee.service.ResponseActions.ResponseAction;
import com.artem.trainee.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by artemb on 07.07.14.
 */
@Service("/editEmployee")
public class EmployeeEdit implements StaffService {
    @Autowired
    private EmployeeDAO employeeDAOHibernate;
    @Override
    public ResponseAction handle(HttpServletRequest req, HttpServletResponse resp) {

        //EmployeeDAO employeeDAO= new EmployeeDAOImpl();
        int empl_id = Integer.parseInt(req.getParameter("empl_id"));
        //Employee employee= employeeDAO.getEmployeeById(empl_id);
        //Employee employee = Factory.getInstance().getEmployeeDAO().getEmployeeById(empl_id);
        Employee employee = employeeDAOHibernate.getEmployeeById(empl_id);
        req.setAttribute("employee",employee);
        return new Forward("/employee_edit.jsp");
    }
}
