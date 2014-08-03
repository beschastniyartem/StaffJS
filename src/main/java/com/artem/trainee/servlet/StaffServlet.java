package com.artem.trainee.servlet;

import com.artem.trainee.configurationSpring.ConfigWithAnnotations;
import com.artem.trainee.service.DepartmentActions.*;
import com.artem.trainee.service.EmployeeActions.*;
import com.artem.trainee.service.ResponseActions.ResponseAction;
import com.artem.trainee.service.StaffService;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.HttpRequestHandlerServlet;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by artemb on 07.07.14.
 */
//@WebServlet(name = "annotatedServletHandler", urlPatterns = "/")
public class StaffServlet extends HttpRequestHandlerServlet{
   /* //Strategy pattern for simply Java EE app without Spring
    private static Map<String, StaffService> services = new HashMap<String, StaffService>() {{
        put("/department", new DepartmentList());
        put("/add", new DepartmentAdd());
        put("/addSubmit", new DepartmentAddSubmit());
        put("/delete", new DepartmentDelete());
        put("/edit", new DepartmentEdit());
        put("/editSubmit", new DepartmentEditSubmit());

        put("/employee", new DepartmentListEmployee());
        put("/addEmployee", new EmployeeAdd());
        put("/addEmployeeSubmit", new EmployeeAddSubmit());
        put("/deleteEmployee", new EmployeeDelete());
        put("/editEmployee", new EmployeeEdit());
        put("/editEmployeeSubmit", new EmployeeEditSubmit());
    }};

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        String uri = req.getRequestURI();
        //further implementation of Strategy Pattern
        //StaffService service = services.get(uri);

        //каждый раз инициализирует ConfigWithAnnotations,не смотря на то что и так есть listener в web.xml
        //Getting Bean from @Component("/name"),looks like @RequestMapping in SpringMVC
        //ApplicationContext ctx =new AnnotationConfigApplicationContext(ConfigWithAnnotations.class);
        StaffService ss = (StaffService) wac.getBean(uri);
        ResponseAction ra = null;
        try {
            ra = ss.handle(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            ra.execute(req, resp);
        } catch (Exception er) {
            er.printStackTrace();
        }
    }*/
}