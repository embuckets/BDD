/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author emilio
 */
public class LogInController {

    public String logIn(String user, String password) {
        PartitionRules partitionRules = new PartitionRules();
        List<String> urls = partitionRules.getUrls("login", PartitionRules.DEFAULT_ID);
        String result = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection conn = null;
//            Class.forName("com.mysql.jdbc.Driver");
        for (Iterator<String> iterator = urls.iterator(); iterator.hasNext();) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                String url = iterator.next();
                conn = DriverManager.getConnection(url + "&useLegacyDatetimeCode=false&serverTimezone=America/Mexico_City");
                preparedStatement = conn.prepareStatement("select rol from rol where id_rol = ( select id_rol from login where matricula=? and password=? )");
                preparedStatement.setString(1, user);
                preparedStatement.setString(2, password);
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    result = resultSet.getString("rol");
                    break;
                }

            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
                break;

            } catch (SQLException ex) {
                //TODO: checar si la base de datos esta desconectada para intentar las otras
                ex.printStackTrace();
                if(!iterator.hasNext()){
                    //throw exception
                    break;
                }
            } finally {
                if (resultSet != null) {
                    try {
                        resultSet.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
                if (preparedStatement != null) {
                    try {
                        preparedStatement.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
        return result;
    }
//    
//    public String logIn(String user, String password) {
//        PartitionRules partitionRules = new PartitionRules();
//        List<String> urls = partitionRules.getUrls("login", "1");
//        String result = null;
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//        Connection conn = null;
//        try {
////            Class.forName("com.mysql.jdbc.Driver");
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            for(Iterator<String> iterator = urls.iterator(); iterator.hasNext();){
//                String url = iterator.next();
//                
//            }
//            conn = DriverManager.getConnection(url + "&useLegacyDatetimeCode=false&serverTimezone=America/Mexico_City");
//            preparedStatement = conn.prepareStatement("select rol from rol where id_rol = ( select id_rol from login where matricula=? and password=? )");
//            preparedStatement.setString(1, user);
//            preparedStatement.setString(2, password);
//            resultSet = preparedStatement.executeQuery();
//            if (resultSet.next()) {
//                result = resultSet.getString("rol");
//            }
//
//        } catch (ClassNotFoundException ex) {
//            ex.printStackTrace();
//
//        } catch (SQLException ex) {
//            //TODO: checar si la base de datos esta desconectada para intentar las otras
//            ex.printStackTrace();
//        } finally {
//            if (resultSet != null) {
//                try {
//                    resultSet.close();
//                } catch (SQLException ex) {
//                    ex.printStackTrace();
//                }
//            }
//            if (preparedStatement != null) {
//                try {
//                    preparedStatement.close();
//                } catch (SQLException ex) {
//                    ex.printStackTrace();
//                }
//            }
//            if (conn != null) {
//                try {
//                    conn.close();
//                } catch (SQLException ex) {
//                    ex.printStackTrace();
//                }
//            }
//        }
//        return result;
//    }

}
