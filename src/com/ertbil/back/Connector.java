package com.ertbil.back;
import com.ertbil.front.AdminUpdaterScr;
import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class Connector {
    private static Connection con = null;
    private static  Statement statement = null;
    private static  PreparedStatement preparedStatement = null;

    public Connector(){
        String url ="jdbc:mysql://"+ Database.host +":"+Database.port+"/"+Database.dbName+"?useUnicode=true&characterEncoding=utf8";
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {

            e.printStackTrace();
        }
        try {
            con = DriverManager.getConnection(url,Database.username,Database.password);
            System.out.println("Bağlantı kuruldu");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static boolean logControl(String username, String password) {
        String sorgu = "Select * From `adminler` where username = ? and password = ?";
        try {
            String url ="jdbc:mysql://"+ Database.host +":"+Database.port+"/"+Database.dbName+"?useUnicode=true&characterEncoding=utf8";
            con = DriverManager.getConnection(url,Database.username,Database.password);
            preparedStatement = con.prepareStatement(sorgu);

            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);

            ResultSet rs = preparedStatement.executeQuery();

                return rs.next();

        } catch (SQLException | NullPointerException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
    public static void addEmployee(String ad, String soyad, String departman, int maas) {
        String url ="jdbc:mysql://"+ Database.host +":"+Database.port+"/"+Database.dbName+"?useUnicode=true&characterEncoding=utf8";
        try {
            con = DriverManager.getConnection(url,Database.username,Database.password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //INSERT INTO `calisanlar`(`id`, `ad`, `soyad`, `departman`, `maas`) VALUES ('[value-1]','[value-2]','[value-3]','[value-4]','[value-5]')

        String sorgu ="INSERT INTO `calisanlar`( `ad`, `soyad`, `departman`, `maas`) VALUES ('"+ad+"','"+soyad+"','"+departman+"','"+maas+"')";
        try {
            preparedStatement = con.prepareStatement(sorgu);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void updateEmployee(String ad, String soyad, String departman, int maas,int id) {
        String url ="jdbc:mysql://"+ Database.host +":"+Database.port+"/"+Database.dbName+"?useUnicode=true&characterEncoding=utf8";
        try {
            con = DriverManager.getConnection(url,Database.username,Database.password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //UPDATE `calisanlar` SET `id`='[value-1]',`ad`='[value-2]',`soyad`='[value-3]',`departman`='[value-4]',`maas`='[value-5]' WHERE 1
        String sorgu ="UPDATE  `calisanlar` SET  `ad` = '"+ad+"',`soyad` = '"+soyad+"',`departman`= '"+departman+"', `maas`='"+maas+"' WHERE id = "+id;
        try {
            preparedStatement = con.prepareStatement(sorgu);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    public static void deleteEmployee(int id) {
        try {
            statement = con.createStatement();
            String sorgu = "Delete From calisanlar where id ="+id;
            statement.executeUpdate(sorgu);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void deleteAdmin(String username) {
            try {
                statement = con.createStatement();
                String sorgu = "Delete From `adminler` where username ='"+username+"'";
                statement.executeUpdate(sorgu);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
    }

    public static void adminUpdater(String username, String password1) {
        String url ="jdbc:mysql://"+ Database.host +":"+Database.port+"/"+Database.dbName+"?useUnicode=true&characterEncoding=utf8";
        try {
            con = DriverManager.getConnection(url,Database.username,Database.password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //UPDATE `calisanlar` SET `id`='[value-1]',`ad`='[value-2]',`soyad`='[value-3]',`departman`='[value-4]',`maas`='[value-5]' WHERE 1
        String sorgu ="UPDATE  `adminler` SET  `username` = '"+username+"',`password` = '"+password1+"' WHERE username ='"+ AdminUpdaterScr.getFirstUsername()+"'";
        try {
            preparedStatement = con.prepareStatement(sorgu);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public static void addAdmin(String username, String password) {
        String url ="jdbc:mysql://"+ Database.host +":"+Database.port+"/"+Database.dbName+"?useUnicode=true&characterEncoding=utf8";
        try {
            con = DriverManager.getConnection(url,Database.username,Database.password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //INSERT INTO `calisanlar`(`id`, `ad`, `soyad`, `departman`, `maas`) VALUES ('[value-1]','[value-2]','[value-3]','[value-4]','[value-5]')

        String sorgu ="INSERT INTO `adminler`( `username`, `password`) VALUES ('"+username+"','"+password+"')";
        try {
            preparedStatement = con.prepareStatement(sorgu);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public ArrayList<Employee> calisanlariGetir() {
        String url ="jdbc:mysql://"+ Database.host +":"+Database.port+"/"+Database.dbName+"?useUnicode=true&characterEncoding=utf8";
        try {
            con = DriverManager.getConnection(url,Database.username,Database.password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        ArrayList<Employee> output = new ArrayList<Employee>();
        try {
            Statement statement = con.createStatement();
            String sorgu= "SELECT * FROM `calisanlar`";
            ResultSet rs = statement.executeQuery(sorgu);
            while (rs.next()){
               int id = rs.getInt("id");
               String ad = rs.getString("ad");
               String soyad = rs.getString("soyad");
               String depart = rs.getString("departman");
               int maas = rs.getInt("maas");
               output.add(new Employee(id,ad,soyad,depart,maas));
            }
            return output;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public ArrayList<Admin> adminleriGetir() {

        String url ="jdbc:mysql://"+ Database.host +":"+Database.port+"/"+Database.dbName+"?useUnicode=true&characterEncoding=utf8";
        try {
            con = DriverManager.getConnection(url,Database.username,Database.password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        ArrayList<Admin> output = new ArrayList<Admin>();
        try {
            Statement statement = con.createStatement();
            String sorgu= "SELECT * FROM `adminler`";
            ResultSet rs = statement.executeQuery(sorgu);
            while (rs.next()){
                String username =rs.getString("username");
                String password = rs.getString("password");
                output.add(new Admin(username,password));
            }
            return output;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
