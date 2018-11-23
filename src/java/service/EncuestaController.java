/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dominio.Encuesta;
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
public class EncuestaController {

    public List<Encuesta> getEncuestasByIdUnidad(int idUnidad) {
        String url = new LocalURL().getURL();
        List<Encuesta> result = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url);
            preparedStatement = conn.prepareStatement("select * from encuesta where id_unidad=?");//and cierra between now() - interval 7 day and now()
            preparedStatement.setInt(1, idUnidad);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Encuesta encuesta = new Encuesta();
                encuesta.setIdEncuesta(resultSet.getInt("id_encuesta"));
                encuesta.setTitulo(resultSet.getString("titulo"));
                encuesta.setDescripcion(resultSet.getString("descripcion"));
                encuesta.setAbre(resultSet.getTimestamp("abre").toLocalDateTime());
                encuesta.setCierra(resultSet.getTimestamp("cierra").toLocalDateTime());
                encuesta.setIdUnidad(resultSet.getInt("id_unidad"));
                result.add(encuesta);
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

    public Encuesta getEncuestaById(int idEncuesta) {
        String url = new LocalURL().getURL();
        Encuesta result = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url);
            preparedStatement = conn.prepareStatement("select * from encuesta where id_encuesta=?");//and cierra between now() - interval 7 day and now()
            preparedStatement.setInt(1, idEncuesta);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result = new Encuesta();
                result.setIdEncuesta(resultSet.getInt("id_encuesta"));
                result.setTitulo(resultSet.getString("titulo"));
                result.setDescripcion(resultSet.getString("descripcion"));
                result.setAbre(resultSet.getTimestamp("abre").toLocalDateTime());
                result.setCierra(resultSet.getTimestamp("cierra").toLocalDateTime());
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
