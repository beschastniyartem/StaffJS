package com.artem.trainee.service.ResponseActions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by artemb on 07.07.14.
 */
public interface ResponseAction {
    void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}
