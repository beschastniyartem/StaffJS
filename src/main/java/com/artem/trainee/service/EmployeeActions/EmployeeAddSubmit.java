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
@Service("/addEmployeeSubmit")
public class EmployeeAddSubmit implements StaffService {
    @Autowired
    private EmployeeDAO employeeDAOHibernate;
    @Override
    public ResponseAction handle(HttpServletRequest req, HttpServletResponse resp) {

        Integer dep_id = Integer.parseInt(req.getParameter("dep_id"));
        String email = req.getParameter("email");
        Double  salary = ParamUtils.getDouble(req,"salary");
        Date birthday = ParamUtils.getDate(req, "birthday");
        Employee employee = new Employee(email, salary, birthday, dep_id);

        //   employee.setSalary(ParamUtils.getDouble(req, "salary"));

        //to add empl,after first adding
        req.setAttribute("dep_id",dep_id);
        Validator validator = new Validator();
        List<ConstraintViolation> violations = validator.validate(employee);
        //if(employeeDAO.checkEmployeeEmailIsUnique(email))
        if(violations.size()>0){
            for(ConstraintViolation constraintViolation:violations) {
                req.setAttribute("email",email);
                req.setAttribute("salary",salary);
                req.setAttribute("birthday",birthday);

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
                /*if(ParamUtils.IsValid(constraintViolation.getContext(),"email")){
                    req.setAttribute("emailError","email Is Not Valid");
                }
                try {
                    ParamUtils.IsValid((String) constraintViolation.getMessageVariables().get("simpleCheck"), "EmailCheck");
                    req.setAttribute("emailError","email already exists");
                }catch (Exception e){
                }
                if(ParamUtils.IsValid(constraintViolation.getContext(),"salary")){
                    req.setAttribute("salaryError","salary Is Not Valid");
                }
                if(ParamUtils.IsValid(constraintViolation.getContext(),"birthday")){
                    req.setAttribute("birthdayError","birthday Is Not Valid");
                }*/
            }
            return new Forward("employee_add.jsp");
        }
        else{
            //Factory.getInstance().getEmployeeDAO().addEmployee(employee);
            employeeDAOHibernate.addEmployee(employee);
            /*EmployeeDAO employeeDAO = new EmployeeDAOImpl();
              employeeDAO.addEmployee(employee);*/
              return new Redirect("employee?dep_id="+dep_id);
        }
    }
}
