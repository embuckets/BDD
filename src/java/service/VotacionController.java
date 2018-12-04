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

/**
 *
 * @author emilio
 */
public class VotacionController {

    public Opcion getOpcion(int idEncuesta, String matricula, int idUnidad) {
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
                conn = DriverManager.getConnection(url);
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
    public boolean saveVotacion(int idEncuesta, int idOpcion, String matricula/*int iduNidad*/) {
        PartitionRules partitionRules = new PartitionRules();
        List<String> urls = partitionRules.getUrls("votacion", PartitionRules.DEFAULT_ID);
        PreparedStatement preparedStatement = null;
        int result;
        boolean success = false;
        Connection conn = null;
        List<Connection> conns = new ArrayList<>();
        for (Iterator<String> iterator = urls.iterator(); iterator.hasNext();) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                String url = iterator.next();
                conn = DriverManager.getConnection(url);
                conn.setAutoCommit(false);
                preparedStatement = conn.prepareStatement("insert into votacion values(?,?,?,default)");//and cierra between now() - interval 7 day and now()
                preparedStatement.setInt(1, idEncuesta);
                preparedStatement.setString(2, matricula);
                preparedStatement.setInt(3, idOpcion);
                result = preparedStatement.executeUpdate();
                if (result == 1) {
                    success = true;
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
        return success;
    }

    public boolean updateVotacion(int idEncuesta, int idOpcion, String matricula/* idUnidad */) {
        PartitionRules partitionRules = new PartitionRules();
        List<String> urls = partitionRules.getUrls("votacion", /* idUniad */ PartitionRules.DEFAULT_ID);
        PreparedStatement preparedStatement = null;
        int result;
        boolean success = false;
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url);
            preparedStatement = conn.prepareStatement("update votacion set id_opcion=?, emision = current_timestamp() where id_encuesta=? and matricula=?");
            preparedStatement.setInt(1, idOpcion);
            preparedStatement.setInt(2, idEncuesta);
            preparedStatement.setString(3, matricula);
            result = preparedStatement.executeUpdate();
            if (result == 1) {
                success = true;
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
        return success;
    }

}
