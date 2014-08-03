package com.artem.trainee.model;
import com.artem.trainee.DAO.EmployeeDAO;
import com.artem.trainee.DAO.EmployeeDAOImpl;
import net.sf.oval.constraint.*;

import javax.persistence.*;
import java.sql.Date;


@Entity
@Table(name="Employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEmployee",nullable =false,unique = true)
    private int empl_id;
    @Email(message = "Is not valid email address")
    @CheckWith(value=EmailCheck.class,message    = "This email already exists")
    @Column(name="email")
    private String email;
    @NotNull(message = "Salary can`t be empty")
    @NotNegative(message = "Salary can`t be negative")
    @Column(name="salary")
    private Double salary;
    @NotNull(message = "Birthday is not valid")
    @Column(name="birthday")
    private Date birthday;
    @Column(name = "dep_id")
    private Integer dep_id;

/*    @JoinColumn(name = "dep_id")
    @ManyToOne(optional = false)
    private Department department;*/

    public Employee() {
    }

    public Employee(String email, Double salary, Date birthday) {
        this.email = email;
        this.salary = salary;
        this.birthday = birthday;
    }

    public Employee(int empl_id, String email, Double salary, Date birthday) {
        this.empl_id = empl_id;
        this.email = email;
        this.salary = salary;
        this.birthday = birthday;
    }

    public Employee(int empl_id, String email, Double salary, Date birthday, Integer dep_id) {
        this.empl_id = empl_id;
        this.email = email;
        this.salary = salary;
        this.birthday = birthday;
        this.dep_id = dep_id;
    }

    public Employee(String email, Double salary, Date birthday, Integer dep_id) {
        this.email = email;
        this.salary = salary;
        this.birthday = birthday;
        this.dep_id = dep_id;
    }



    public int getEmpl_id() {
        return empl_id;
    }

    public void setEmpl_id(int empl_id) {
        this.empl_id = empl_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getDep_id() {
        return dep_id;
    }

    public void setDep_id(Integer dep_id) {
        this.dep_id = dep_id;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empl_id=" + empl_id +
                ", email='" + email + '\'' +
                ", salary=" + salary +
                ", birthday=" + birthday +
                ", dep_id=" + dep_id +
                '}';
    }
    private static class EmailCheck implements  CheckWithCheck.SimpleCheck
    {
        public boolean isSatisfied(Object validatedObject, Object value)
        {
            try {
                EmployeeDAO employeeDAO = new EmployeeDAOImpl();
                return employeeDAO.checkEmployeeEmailIsUniqueEdit(((Employee) validatedObject).email, ((Employee) validatedObject).empl_id);
            } catch (Exception ignored) {}
            return false;
        }
    }
}
