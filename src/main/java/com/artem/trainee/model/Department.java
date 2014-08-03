package com.artem.trainee.model;

import com.artem.trainee.DAO.DepartmentDAO;
import com.artem.trainee.DAO.DepartmentDAOImpl;
import net.sf.oval.constraint.CheckWith;
import net.sf.oval.constraint.CheckWithCheck;
import net.sf.oval.constraint.NotEqual;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;


@Entity
@Proxy(lazy = false)
@Table(name="Department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDepartment",nullable =false,unique = true)
    private int dep_id;
    @NotEqual(value = "", message = "Name can`t be empty")
    @CheckWith(value=NameCheck.class, message = "This name already exists")
    @Column(name="name", nullable=false)
    private String name;
    public Department() {
    }
    String name2;
    public Department(String name) {
        this.name = name;
    }

    public Department(int dep_id, String name) {
        this.dep_id = dep_id;
        this.name = name;
    }


    public int getDep_id() {
        return dep_id;
    }


    public void setDep_id(int dep_id) {
        this.dep_id = dep_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Department{" +
                "dep_id=" + dep_id +
                ", name='" + name + '\'' +
                '}';
    }
    private static class NameCheck implements  CheckWithCheck.SimpleCheck
    {
        //false if already exists
        public boolean isSatisfied(Object validatedObject, Object value)
        {
            try {
                DepartmentDAO departmentDAO = new DepartmentDAOImpl();
                return departmentDAO.checkDepartmentNameIsUniqueEdit(((Department) validatedObject).name, ((Department) validatedObject).dep_id);
            } catch (Exception ignored) {}
            return false;
        }
    }
}
