package com.artem.trainee.servlet;

import com.artem.trainee.service.ResponseActions.ResponseAction;
import com.artem.trainee.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created on 15.07.14.
 */
//@Component("annotatedServletHandler")
public class AnnotatedHttpServletRequestHandler implements HttpRequestHandler {
    @Autowired
    private Map<String, StaffService> stringStaffServiceMap;

    @Override
    public void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        StaffService staffService = stringStaffServiceMap.get(uri);
        ResponseAction ra = null;
        try {
            ra = staffService.handle(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            ra.execute(req, resp);
        } catch (Exception er) {
            er.printStackTrace();
        }
    }
}
