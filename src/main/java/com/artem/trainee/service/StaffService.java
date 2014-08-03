package com.artem.trainee.service;

import com.artem.trainee.service.ResponseActions.ResponseAction;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by artemb on 07.07.14.
 */
//Better if @Transactional will be declared in Service layer(currently in interface)
@Transactional
public interface StaffService {
    ResponseAction handle(HttpServletRequest req, HttpServletResponse resp);
}
