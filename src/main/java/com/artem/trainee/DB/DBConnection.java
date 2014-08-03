package com.artem.trainee.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by artemb on 07.07.14.
 */
public class DBConnection {
    private Connection connection;
    public DBConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/stuff", "root", "1");
        }catch (ClassNotFoundException cnfe){
            cnfe.printStackTrace()  ;
        }catch (SQLException sqle){
            sqle.printStackTrace();
        }
    }
    public  Connection getConnection(){
        return  this.connection;
    }
}
