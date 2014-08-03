package com.artem.trainee.main;

import com.artem.trainee.configurationSpring.ConfigWithAnnotations;
import com.artem.trainee.model.Department;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Properties;

/**
 * Created on 14.07.14.
 */
@Component
public class SpringMain {

  //  @Qualifier("sessionFactory")
    @Autowired
    SessionFactory sessionFactory;
    @Autowired
    Properties hibProperties;
    private Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }
    public List<Department> getDepartments() {
        return getCurrentSession().createQuery("from Department").list();
    }
    public static void main(String[] args){
        ApplicationContext ctx =new AnnotationConfigApplicationContext(ConfigWithAnnotations.class);

   // SessionFactory sf = ctx.getBean(SessionFactory.class);
        SessionFactory sf = (SessionFactory) ctx.getBean("sessionFactory");
        System.out.println("SF = " +sf);
        SpringMain sm = new SpringMain();
        System.out.println("HibProp = "+sm.hibProperties);
        System.out.println("GetDep = "+sm.getDepartments());

    }
}