package com.artem.trainee.service.DepartmentActions;

import com.artem.trainee.DAO.DepartmentDAOHibernateImpl;
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
@Service("/add")
public class DepartmentAdd implements StaffService {
    @Override
    public ResponseAction handle(HttpServletRequest req, HttpServletResponse resp) {
        return new Forward("/department_add.jsp");
    }
}
