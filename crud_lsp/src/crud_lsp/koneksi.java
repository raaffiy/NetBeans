/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crud_lsp;

/**
 *
 * @author Acer
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class koneksi {
    private static Connection mysqlconfig;
    public static Connection configDbConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/db_lsp";
            String user="root";
            String pass="";
//            DriverManager.getDriver("com.mysql.jdbc.Driver");
            mysqlconfig = DriverManager.getConnection(url, user, pass);
        }catch(SQLException sqlexc){
            System.err.println("Koneksi gagal " + sqlexc.getMessage());
        }catch(ClassNotFoundException e){
            System.out.println("error" + e);
        }
        return mysqlconfig;
    }
}
