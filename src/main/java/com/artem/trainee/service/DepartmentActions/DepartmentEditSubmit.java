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
@Service("/editSubmit")
public class DepartmentEditSubmit implements StaffService {
    @Autowired
    private DepartmentDAO departmentDAOHibernate;
    @Override
    public ResponseAction handle(HttpServletRequest req, HttpServletResponse resp) {
        //DepartmentDAO departmentDAO= new DepartmentDAOImpl();
        Integer dep_id = Integer.parseInt(req.getParameter("dep_id"));
        String name = req.getParameter("name");
        Department department = new Department(dep_id,name);
        //Department departmentFromId= departmentDAO.getDepartmentById(dep_id);
        //Department departmentFromId = Factory.getInstance().getDepartmentDAO().getDepartmentById(dep_id);
        Department departmentFromId = departmentDAOHibernate.getDepartmentById(dep_id);
        req.setAttribute("department",departmentFromId);
        Validator validator = new Validator();
        List<ConstraintViolation> violations = validator.validate(department);
        if(violations.size()>0){

            Map<String, String> errors = new HashMap<String, String>();

            for (ConstraintViolation violation : violations) {
                if (violation.getContext() instanceof FieldContext) {
                    FieldContext    fieldContext = (FieldContext) violation.getContext();
                    String text = violation.getMessage();
                    String key = fieldContext.getField().getName();
                    errors.put(key,text);
                }
            }
            req.setAttribute("errors",errors);
            return new Forward("department_edit.jsp");
        }
        else{

            //departmentDAO.updateDepartment(department);
            //Factory.getInstance().getDepartmentDAO().updateDepartment(department);
            departmentDAOHibernate.updateDepartment(department);
            return new Redirect("/department");
        }
    }
}