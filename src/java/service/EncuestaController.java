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
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author emilio
 */
public class EncuestaController {

    public List<Encuesta> getEncuestasByIdUnidad(int idUnidad, String abre, String cierra) {
        PartitionRules partitionRules = new PartitionRules();
        List<String> urls = partitionRules.getUrls("encuesta", String.valueOf(idUnidad));
        List<Encuesta> result = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection conn = null;
        for (Iterator<String> iterator = urls.iterator(); iterator.hasNext();) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                String url = iterator.next();
                conn = DriverManager.getConnection(url  + "&useLegacyDatetimeCode=false&serverTimezone=America/Mexico_City");
                preparedStatement = conn.prepareStatement("select * from encuesta where id_unidad=? and abre > '" + abre + "' and cierra < '" + cierra + " 23:59:59'");
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
                break;

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
        }
        return result;
    }

    public Encuesta getEncuestaById(int idEncuesta) {
        PartitionRules partitionRules = new PartitionRules();
        List<String> urls = partitionRules.getUrls("encuesta", PartitionRules.DEFAULT_ID);
        Encuesta result = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection conn = null;
        for (Iterator<String> iterator = urls.iterator(); iterator.hasNext();) {
            String url = iterator.next();
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(url + "&useLegacyDatetimeCode=false&serverTimezone=America/Mexico_City");
                preparedStatement = conn.prepareStatement("select * from encuesta where id_encuesta=?");//and cierra between now() - interval 7 day and now()
                preparedStatement.setInt(1, idEncuesta);
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    result = new Encuesta();
                    result.setIdEncuesta(resultSet.getInt("id_encuesta"));
                    result.setTitulo(resultSet.getString("titulo"));
                    result.setDescripcion(resultSet.getString("descripcion"));
                    result.setAbre(resultSet.getTimestamp("abre").toLocalDateTime());
                    result.setCierra(resultSet.getTimestamp("cierra").toLocalDateTime());
                    result.setIdUnidad(resultSet.getInt("id_unidad"));
                    break;
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
        }
        return result;
    }
}
