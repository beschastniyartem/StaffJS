package com.artem.trainee.service.EmployeeActions;


import com.artem.trainee.DAO.EmployeeDAO;
import com.artem.trainee.DAO.EmployeeDAOHibernateImpl;
import com.artem.trainee.DAO.Factory;
import com.artem.trainee.model.Employee;
import com.artem.trainee.service.ResponseActions.Redirect;
import com.artem.trainee.service.ResponseActions.ResponseAction;
import com.artem.trainee.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by artemb on 07.07.14.
 */
@Service("/deleteEmployee")
public class EmployeeDelete implements StaffService {
    @Autowired
    private EmployeeDAO employeeDAOHibernate;
    @Override
    public ResponseAction handle(HttpServletRequest req, HttpServletResponse resp) {

        int empl_id = Integer.parseInt(req.getParameter("empl_id"));
        System.out.println("EMPLID = "+empl_id);
        //Employee employee = Factory.getInstance().getEmployeeDAO().getEmployeeById(empl_id);
        Employee employee = employeeDAOHibernate.getEmployeeById(empl_id);
        //Factory.getInstance().getEmployeeDAO().deleteEmployee(employee);
        employeeDAOHibernate.deleteEmployee(employee);
        return new Redirect("employee?dep_id="+employee.getDep_id());

    }
}
