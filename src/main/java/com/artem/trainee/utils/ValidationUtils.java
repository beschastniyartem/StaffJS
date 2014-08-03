package com.artem.trainee.utils;

import com.artem.trainee.DAO.DepartmentDAOHibernateImpl;
import com.artem.trainee.model.Department;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import net.sf.oval.context.FieldContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created on 16.07.14.
 */
@Component
public class ValidationUtils {

    public Map<String, String> validation(Object obj) {
        Map<String, String> errors = new HashMap<String, String>();
        if (obj != null) {
            Validator validator = new Validator();
            List<ConstraintViolation> violations = validator.validate(obj);
            if (violations.size() > 0) {
                for (ConstraintViolation violation : violations) {
                    if (violation.getContext() instanceof FieldContext) {
                        FieldContext fieldContext = (FieldContext) violation.getContext();
                        String text = violation.getMessage();
                        String key = fieldContext.getField().getName();
                        errors.put(key, text);
                    }
                }
            }
        }
        return errors;
    }
}
