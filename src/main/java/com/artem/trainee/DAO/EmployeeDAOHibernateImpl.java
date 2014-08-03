package com.artem.trainee.DAO;

import com.artem.trainee.model.Department;
import com.artem.trainee.model.Employee;
import com.artem.trainee.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 09.07.14.
 */
@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {
    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void addEmployee(Employee emp) {
        getCurrentSession().save(emp);
        /*Session  session  = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(emp);
            session.getTransaction().commit();
        }catch (Exception e){
            System.out.println("Всё очень плохо");
        }finally {
            if(session!=null&&session.isOpen()){
                session.close();
            }
        }*/
    }

    @Override
    public void updateEmployee(Employee emp) {
        Employee employeeToUpdate = getEmployeeById(emp.getEmpl_id());
        employeeToUpdate.setEmail(emp.getEmail());
        employeeToUpdate.setSalary(emp.getSalary());
        employeeToUpdate.setBirthday(emp.getBirthday());
        employeeToUpdate.setDep_id(emp.getDep_id());
        getCurrentSession().save(employeeToUpdate);/*Session  session  = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(emp);
            session.getTransaction().commit();
        }catch (Exception e){
            System.out.println("Всё очень плохо 2");
        }finally {
            if(session!=null&&session.isOpen()){
                session.close();
            }
        }*/

    }

    @Override
    public Employee getEmployeeById(int id) {
        Employee employee = (Employee) getCurrentSession().get(Employee.class, id);
       /* Session  session  = null;
        Employee employee = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            employee = (Employee) session.get(Employee.class,id);
        }catch (Exception e){
            System.out.println("Всё очень плохо 3");
        }finally {
            if(session!=null&&session.isOpen()){
                session.close();
            }
        }*/
        return employee;
    }

    @Override
    public void deleteEmployee(int id) {
        Employee employee  = getEmployeeById(id);
        if(employee!=null){
            System.out.println("Department delete = "+employee);
        }
        Session  session  = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(employee);
            session.getTransaction().commit();
        }catch (Exception e){
            System.out.println("Всё очень плохо 4");
        }finally {
            if(session!=null&&session.isOpen()){
                session.close();
            }
        }
    }

    @Override
    public void deleteEmployee(Employee employee) {
        getCurrentSession().delete(employee);
        /*Session  session  = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(employee);
            session.getTransaction().commit();
        }catch (Exception e){
            System.out.println("Всё очень плохо 4");
        }finally {
            if(session!=null&&session.isOpen()){
                session.close();
            }
        }*/
    }
    @SuppressWarnings("unchecked")
    @Override
    public List<Employee> getEmployees() {

        Session session = null;
        List employees =     new ArrayList<Employee>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            employees = session.createCriteria(Employee.class).list();
        } catch (Exception e) {
            System.out.println("Всё очень плохо 5");
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return employees;
    }

    @Override
    public List<Employee> getEmployeesByDepartment(int dep_id) {
        return getCurrentSession().createQuery("from Employee WHERE dep_id="+dep_id).list();
       /* Session session = null;
        List employees = new ArrayList<Employee>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            employees = session.createQuery("from Employee WHERE dep_id="+dep_id).list();
        } catch (Exception e) {
            System.out.println("Всё очень плохо 5");
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return employees;*/
    }

    @Override
    public boolean checkEmployeeEmailIsUnique(String email) {
        return false;
    }

    @Override
    public boolean checkEmployeeEmailIsUniqueEdit(String email, int empl_id) {

        Employee employee = getEmployeeById(empl_id);
        try{
            employee.getEmpl_id();
        }catch(Exception e){
            return true;
        }
        if(employee.getEmpl_id()==empl_id) {
            return false;
        }else{
            return true;
        }
    }
}
