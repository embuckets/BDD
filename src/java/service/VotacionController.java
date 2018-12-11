/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dominio.Opcion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author emilio
 */
public class VotacionController {

    public Opcion getOpcionVotada(int idEncuesta, String matricula, int idUnidad) {
        PartitionRules partitionRules = new PartitionRules();
        List<String> urls = partitionRules.getUrls("opcion", String.valueOf(idUnidad));
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Opcion result = null;
        Connection conn = null;
        for (Iterator<String> iterator = urls.iterator(); iterator.hasNext();) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                String url = iterator.next();
                conn = DriverManager.getConnection(url + "&useLegacyDatetimeCode=false&serverTimezone=America/Mexico_City");
                preparedStatement = conn.prepareStatement("select * from opcion where id_opcion = (select id_opcion from votacion where id_encuesta=? and matricula=?);");
                preparedStatement.setInt(1, idEncuesta);
                preparedStatement.setString(2, matricula);
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    result = new Opcion();
                    result.setIdOpcion(resultSet.getInt("id_opcion"));
                    result.setIdEncuesta(resultSet.getInt("id_encuesta"));
                    result.setOpcion(resultSet.getString("opcion"));
                    break;
                }

            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();

            } catch (SQLException ex) {
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

    //TODO
    public boolean saveVotacion(int idEncuesta, int idOpcion, String matricula, int idUnidad) {
        PartitionRules partitionRules = new PartitionRules();
        List<String> urls = partitionRules.getUrls("votacion", String.valueOf(idUnidad));
        PreparedStatement preparedStatement = null;
        int result;
        boolean success = false;
//        Connection conn = null;
        List<Connection> conns = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            for (Iterator<String> iterator = urls.iterator(); iterator.hasNext();) {
                String url = iterator.next();
                Connection conn = DriverManager.getConnection(url + "&useLegacyDatetimeCode=false&serverTimezone=America/Mexico_City");
                conns.add(conn);
            }
            for (Connection conn : conns) {
                conn.setAutoCommit(false);
                preparedStatement = conn.prepareStatement("insert into votacion values(?,?,?,default)");//and cierra between now() - interval 7 day and now()
                preparedStatement.setInt(1, idEncuesta);
                preparedStatement.setString(2, matricula);
                preparedStatement.setInt(3, idOpcion);
                result = preparedStatement.executeUpdate();
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

    public boolean updateVotacion(int idEncuesta, int idOpcion, String matricula, int idUnidad/* idUnidad */) {
        PartitionRules partitionRules = new PartitionRules();
        List<String> urls = partitionRules.getUrls("votacion", String.valueOf(idUnidad));
        PreparedStatement preparedStatement = null;
        int result;
        boolean success = false;
//        Connection conn = null;
        List<Connection> conns = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            for (Iterator<String> iterator = urls.iterator(); iterator.hasNext();) {
                String url = iterator.next();
                Connection conn = DriverManager.getConnection(url + "&useLegacyDatetimeCode=false&serverTimezone=America/Mexico_City");
                conns.add(conn);
            }
            for (Connection conn : conns) {
                conn.setAutoCommit(false);
                preparedStatement = conn.prepareStatement("update votacion set id_opcion=?, emision = current_timestamp() where id_encuesta=? and matricula=?");
                preparedStatement.setInt(1, idOpcion);
                preparedStatement.setInt(2, idEncuesta);
                preparedStatement.setString(3, matricula);
                result = preparedStatement.executeUpdate();
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
            for (Connection conn : conns) {
                if (conn != null) {
                    try {
                        conn.rollback();
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

}
