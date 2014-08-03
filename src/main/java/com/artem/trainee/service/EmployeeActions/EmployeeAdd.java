package com.artem.trainee.service.EmployeeActions;

import com.artem.trainee.DAO.EmployeeDAOHibernateImpl;
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
@Service("/addEmployee")
public class EmployeeAdd implements StaffService {
    @Override
    public ResponseAction handle(HttpServletRequest req, HttpServletResponse resp) {
        Integer dep_id = Integer.parseInt(req.getParameter("dep_id"));
        req.setAttribute("dep_id",dep_id);
        return new Forward("/employee_add.jsp");

    }
}
