/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ukk_wijoyo_2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author gaming
 */
public class koneksi {
    public static Connection wijoyokonek() throws SQLException{
        Connection conn = null;
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/ukk_wijoyo_2";
        String user = "root";
        String pass = "";
        
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, pass);
            System.out.println("Koneksi Berhasil!");
        } catch (ClassNotFoundException ex) {
            System.out.println("Koneksi Error!");
        }
        
        return conn;
    }
}
