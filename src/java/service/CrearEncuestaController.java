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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
        int idOpcionValido = 0;
        int encuestaInsertada = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            for (Iterator<String> iterator = urls.iterator(); iterator.hasNext();) {
                String url = iterator.next();
                Connection conn = DriverManager.getConnection(url + "&useLegacyDatetimeCode=false&serverTimezone=America/Mexico_City");
                conns.add(conn);
            }
            //genera id de encuesta valido
            boolean isValid = false;
            while (!isValid) {
                idEncuestaValido = encuesta.generateID();
                isValid = isIDEncuestaValid(idEncuestaValido, idUnidad);
            }
            encuesta.setIdEncuesta(idEncuestaValido);
            //genera id de opcion valido
            for (Opcion opcion : encuesta.getOpciones()) {
                isValid = false;
                while (!isValid) {
                    idOpcionValido = opcion.generateID();
                    isValid = isIDOpcionValid(idOpcionValido, idUnidad);
                }
                opcion.setIdOpcion(idOpcionValido);
            }
            for (Connection conn : conns) {
                conn.setAutoCommit(false);
//                preparedStatement = conn.prepareStatement("insert into encuesta values(?,?,?,?,?,default,?)", Statement.RETURN_GENERATED_KEYS);
                preparedStatement = conn.prepareStatement("insert into encuesta values(?,?,?,?,?,default,?)");
                preparedStatement.setInt(1, idEncuestaValido);
                preparedStatement.setString(2, encuesta.getTitulo());
                preparedStatement.setString(3, encuesta.getDescripcion());
                preparedStatement.setTimestamp(4, Timestamp.valueOf(encuesta.getAbre()));
                preparedStatement.setTimestamp(5, Timestamp.valueOf(encuesta.getCierra()));
                preparedStatement.setInt(6, idUnidad);
                encuestaInsertada = preparedStatement.executeUpdate();
//                resultSet = preparedStatement.getGeneratedKeys();
                if (encuestaInsertada == 1) {
//                    int idEncuesta = resultSet.getInt(1);
                    for (Opcion opcion : encuesta.getOpciones()) {
                        preparedStatement = conn.prepareStatement("insert into opcion values(?,?,?)");
                        preparedStatement.setInt(1, opcion.getIdOpcion());
                        preparedStatement.setInt(2, idEncuestaValido);
                        preparedStatement.setString(3, opcion.getOpcion());
                        preparedStatement.executeUpdate();
                    }
                }
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

    public boolean isIDEncuestaValid(int idEncuesta, int idUnidad) {
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

    public boolean isIDOpcionValid(int idOpcion, int idUnidad) {
        PartitionRules partitionRules = new PartitionRules();
        List<String> urls = partitionRules.getUrls("opcion", String.valueOf(idUnidad));
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
                preparedStatement = conn.prepareStatement("select id_opcion from opcion where id_opcion=?");
                preparedStatement.setInt(1, idOpcion);
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
