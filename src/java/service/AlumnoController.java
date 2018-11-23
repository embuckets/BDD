/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dominio.Alumno;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author emilio
 */
public class AlumnoController {

    public Alumno getAlumnoByMatricula(String matricula) {
        String url = new LocalURL().getURL();
        Alumno result = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url);
            preparedStatement = conn.prepareStatement("select * from alumno where matricula =?");
            preparedStatement.setString(1, matricula);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = new Alumno();
                result.setMatricula(matricula);
                result.setNombre(resultSet.getString("nombre"));
                result.setPaterno(resultSet.getString("paterno"));
                result.setMaterno(resultSet.getString("materno"));
                result.setIdDivision(resultSet.getInt("id_division"));
                result.setIdUnidad(resultSet.getInt("id_unidad"));
            }

        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();

        } catch (SQLException ex) {
            //TODO: checar si la base de datos esta desconectada para intentar las otras
            ex.printStackTrace();
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
        return result;
    }

}
