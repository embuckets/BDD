/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dominio.Encuesta;
import dominio.Opcion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author emilio
 */
public class OpcionController {

    public List<Opcion> getOpcionesByIdEncuesta(int idEncuesta) {
        String url = new LocalURL().getURL();
        List<Opcion> result = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url);
            preparedStatement = conn.prepareStatement("select * from opcion where id_encuesta=?");//and cierra between now() - interval 7 day and now()
            preparedStatement.setInt(1, idEncuesta);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Opcion opcion = new Opcion();
                opcion.setIdOpcion(resultSet.getInt("id_opcion"));
                opcion.setIdEncuesta(resultSet.getInt("id_encuesta"));
                opcion.setOpcion(resultSet.getString("opcion"));
                result.add(opcion);
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
