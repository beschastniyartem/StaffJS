package com.artem.trainee.filter;

import com.artem.trainee.utils.HibernateUtil;
import org.hibernate.SessionFactory;
import org.hibernate.StaleObjectStateException;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created on 11.07.14.
 */

public class HibernateSessionRequestFilter implements  Filter{
    private SessionFactory sf;
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {
        try {
            sf.getCurrentSession().beginTransaction();
            // Call the next com.artem.trainee.filter (continue request processing)
            chain.doFilter(request, response);
            // Commit and cleanup
            sf.getCurrentSession().getTransaction().commit();
        } catch (StaleObjectStateException staleEx) {
            throw staleEx;
        } catch (Throwable ex) {
            ex.printStackTrace();
            try {
                if (sf.getCurrentSession().getTransaction().isActive()) {
                    sf.getCurrentSession().getTransaction().rollback();
                }
            } catch (Throwable rbEx) {
                rbEx.printStackTrace();
            }
            throw new ServletException(ex);
        }
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        sf = HibernateUtil.getSessionFactory();
    }
    public void destroy() {}
}