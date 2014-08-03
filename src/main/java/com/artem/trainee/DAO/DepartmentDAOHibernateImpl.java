package com.artem.trainee.DAO;

import com.artem.trainee.model.Department;
import com.artem.trainee.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created on 09.07.14.
 */
@Repository
public class DepartmentDAOHibernateImpl implements DepartmentDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void addDepartment(Department department) {
        getCurrentSession().save(department);
       /*Session  session  = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(department);
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
    public void updateDepartment(Department department) {
        Department departmentToUpdate = getDepartmentById(department.getDep_id());
        departmentToUpdate.setName(department.getName());
        getCurrentSession().save(departmentToUpdate);
        /*Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(department);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Всё очень плохо 2");
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }*/
    }

    @Override
    public Department getDepartmentById(int id) {
        Department department = (Department) getCurrentSession().get(Department.class, id);
      /*  Session  session  = null;
        Department department = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            department = (Department) session.get(Department.class,id);
        }catch (Exception e){
            System.out.println("Всё очень плохо 3");
        }finally {
            if(session!=null&&session.isOpen()){
                session.close();
            }
        }*/
        return department;
    }

    @Override
    public void deleteDepartment(int id) {
        //Лейзи инициализатион чёто-там
        Department department = getDepartmentById(id);
        if (department != null) {
            System.out.println("Department delete = " + department);
        }
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(department);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Всё очень плохо 4");
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void deleteDepartment(Department department) {
        getCurrentSession().delete(department);
        /*Session  session  = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(department);
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
    public List<Department> getDepartments() {
        return getCurrentSession().createQuery("from Department").list();
      /*  Session session = null;
        List departments = new ArrayList<Department>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            departments = session.createCriteria(Department.class).list();
        } catch (Exception e) {
            System.out.println("Всё очень плохо 5");
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return departments;*/
    }

    @Override
    public boolean checkDepartmentNameIsUnique(String name) {
        return false;
    }

    //Dont correct validation
    @Override
    public boolean checkDepartmentNameIsUniqueEdit(String name, int id) {
        Department department = getDepartmentById(id);
        try {
            department.getDep_id();
        } catch (Exception e) {
            return true;
        }
        if (department.getDep_id() == id) {
            return false;
        } else {
            return true;
        }
    }

}