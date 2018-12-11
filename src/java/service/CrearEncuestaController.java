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
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yaqic
 */
public class CrearEncuestaController {

    public boolean crearEncuesta(Encuesta encuesta, int idUnidad) {
        PartitionRules partitionRules = new PartitionRules();
        List<String> urls = partitionRules.getUrls("encuesta", String.valueOf(idUnidad));
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean success = false;
        List<Connection> conns = new ArrayList<>();
        int idEncuestaValido = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            for (Iterator<String> iterator = urls.iterator(); iterator.hasNext();) {
                String url = iterator.next();
                Connection conn = DriverManager.getConnection(url + "&useLegacyDatetimeCode=false&serverTimezone=America/Mexico_City");
                conns.add(conn);
            }
            boolean isValid = false;
            while (!isValid) {
                idEncuestaValido = encuesta.generateID();
                isValid = isIDValid(idEncuestaValido, idUnidad);
            }

            for (Connection conn : conns) {
                conn.setAutoCommit(false);
                preparedStatement = conn.prepareStatement("insert into encuesta values(?,?,?,?,?,default,?)", Statement.RETURN_GENERATED_KEYS);//and cierra between now() - interval 7 day and now()
                preparedStatement.setInt(1, idEncuestaValido);
                preparedStatement.setString(2, encuesta.getTitulo());
                preparedStatement.setString(3, encuesta.getDescripcion());
                preparedStatement.setTimestamp(4, Timestamp.valueOf(encuesta.getAbre()));
                preparedStatement.setTimestamp(5, Timestamp.valueOf(encuesta.getCierra()));
                preparedStatement.setInt(6, idUnidad);
                preparedStatement.executeUpdate();
                resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
//                    int idEncuesta = resultSet.getInt(1);
                    for (Opcion opcion : encuesta.getOpciones()) {
                        preparedStatement = conn.prepareStatement("insert into opcion values(default,?,?)");
                        preparedStatement.setInt(1, idEncuestaValido);
                        preparedStatement.setString(2, opcion.getOpcion());
                        preparedStatement.executeUpdate();
                    }
                }

                //puede result ser otra cosa sin ser exception??
//                if (result == 1) {
//                    success = true;
//                }
            }
            //si llega aqui significa que no hubo exepciones
            for (Connection conn : conns) {
                conn.commit();
                conn.setAutoCommit(true);
            }
            success = true;

        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();

        } catch (SQLException ex) {
            //sqlstate = 23000 -> duplicate entry
            for (Connection conn : conns) {
                if (conn != null) {
                    try {
                        if (!conn.getAutoCommit()) {
                            conn.rollback();
                            conn.setAutoCommit(true);
                        }
                    } catch (SQLException ex1) {
                        ex1.printStackTrace();
                    }
                }
            }
            //TODO: checar si la base de datos esta desconectada para intentar las otras
            ex.printStackTrace();
        } finally {

            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            for (Connection conn : conns) {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
        return success;
    }

    public boolean isIDValid(int idEncuesta, int idUnidad) {
        PartitionRules partitionRules = new PartitionRules();
        List<String> urls = partitionRules.getUrls("encuesta", String.valueOf(idUnidad));
        List<Connection> conns = new ArrayList<>();
        boolean success = true;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            for (Iterator<String> iterator = urls.iterator(); iterator.hasNext();) {
                String url = iterator.next();
                Connection conn = DriverManager.getConnection(url + "&useLegacyDatetimeCode=false&serverTimezone=America/Mexico_City");
                conns.add(conn);
            }
            for (Connection conn : conns) {
                preparedStatement = conn.prepareStatement("select id_encuesta from encuesta where id_encuesta=?");
                preparedStatement.setInt(1, idEncuesta);
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    success = false;
                    break;
                }
            }

        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();

        } catch (SQLException ex) {
            //sqlstate = 23000 -> duplicate entry
            //TODO: checar si la base de datos esta desconectada para intentar las otras
            ex.printStackTrace();
        } finally {

            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            for (Connection conn : conns) {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return success;
    }

}
