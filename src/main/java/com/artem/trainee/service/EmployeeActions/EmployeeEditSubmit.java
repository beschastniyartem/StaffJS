package com.artem.trainee.service.EmployeeActions;

import com.artem.trainee.DAO.EmployeeDAO;
import com.artem.trainee.DAO.EmployeeDAOHibernateImpl;
import com.artem.trainee.DAO.Factory;
import com.artem.trainee.model.Employee;
import com.artem.trainee.service.ResponseActions.Forward;
import com.artem.trainee.service.ResponseActions.Redirect;
import com.artem.trainee.service.ResponseActions.ResponseAction;
import com.artem.trainee.service.StaffService;
import com.artem.trainee.utils.ParamUtils;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import net.sf.oval.context.FieldContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by artemb on 07.07.14.
 */
@Service("/editEmployeeSubmit")
public class EmployeeEditSubmit implements StaffService {
    @Autowired
    private EmployeeDAO employeeDAOHibernate;
    @Override
    public ResponseAction handle(HttpServletRequest req, HttpServletResponse resp) {

       // EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        int empl_id = Integer.parseInt(req.getParameter("empl_id"));
        String email = req.getParameter("email");
        Double salary = ParamUtils.getDouble(req,"salary");
        Date birthday = ParamUtils.getDate(req,"birthday");

        //Integer dep_id = employeeDAO.getEmployeeById(empl_id).getDep_id();
        //Integer dep_id = Factory.getInstance().getEmployeeDAO().getEmployeeById(empl_id).getDep_id();
        Integer dep_id = employeeDAOHibernate.getEmployeeById(empl_id).getDep_id();
        req.setAttribute("dep_id",dep_id);
        Employee employee = new Employee(empl_id, email, salary, birthday,dep_id);
        //To add new employee after edit
         Validator validator = new Validator();
         List<ConstraintViolation> violations = validator.validate(employee);

      if(violations.size()>0){
            for(ConstraintViolation constraintViolation:violations) {
                req.setAttribute("employee",employee);

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
            }
            return new Forward("employee_edit.jsp");
        }
        else{
            //Factory.getInstance().getEmployeeDAO().updateEmployee(employee);
            employeeDAOHibernate.updateEmployee(employee);
            //employeeDAO.updateEmployee(employee);
            return new Redirect("employee?dep_id="+dep_id);
        }
    }
}
