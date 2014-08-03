package com.artem.trainee.service.DepartmentActions;

import com.artem.trainee.DAO.DepartmentDAO;
import com.artem.trainee.DAO.DepartmentDAOHibernateImpl;
import com.artem.trainee.DAO.Factory;
import com.artem.trainee.model.Department;
import com.artem.trainee.service.ResponseActions.Forward;
import com.artem.trainee.service.ResponseActions.Redirect;
import com.artem.trainee.service.ResponseActions.ResponseAction;
import com.artem.trainee.service.StaffService;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import net.sf.oval.context.FieldContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by artemb on 04.07.14.
 */
@Service("/addSubmit")
public class DepartmentAddSubmit implements StaffService {
    @Autowired
    private DepartmentDAO departmentDAOHibernate;

    @Override
    public ResponseAction handle(HttpServletRequest req, HttpServletResponse resp) {
        // DepartmentDAO departmentDAO= new DepartmentDAOImpl();
        String name = req.getParameter("name");
        Department department = new Department(name);
        Validator validator = new Validator();
        List<ConstraintViolation> violations = validator.validate(department);
        if (violations.size() > 0) {

            Map<String, String> errors = new HashMap<String, String>();

            for (ConstraintViolation violation : violations) {
                if (violation.getContext() instanceof FieldContext) {
                    FieldContext fieldContext = (FieldContext) violation.getContext();
                    String text = violation.getMessage();

                    String key = fieldContext.getField().getName();
                    errors.put(key, text);
                }
            }
            req.setAttribute("errors", errors);
            req.setAttribute("department", name);

            return new Forward("department_add.jsp");
        } else {
            //Factory.getInstance().getDepartmentDAO().addDepartment(department);
            departmentDAOHibernate.addDepartment(department);
            //departmentDAO.addDepartment(department);
            return new Redirect("/department");
        }
    }
}
