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
import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

/**
 *
 * @author emilio
 */
public class ResultadoController {

    public List<Opcion> getResultadosDeEncuesta(int idEncuesta, int idUnidad) {
        PartitionRules partitionRules = new PartitionRules();
        List<String> urls = partitionRules.getUrls("opcion", String.valueOf(idUnidad));
        List<Opcion> result = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection conn = null;
        for (Iterator<String> iterator = urls.iterator(); iterator.hasNext();) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                String url = iterator.next();
                conn = DriverManager.getConnection(url);
                preparedStatement = conn.prepareStatement("select opcion.id_opcion, opcion.id_encuesta, opcion.opcion, count(votacion.id_opcion) as votos from votacion right join opcion using (id_encuesta,id_opcion) where id_encuesta=? group by opcion.opcion");//and cierra between now() - interval 7 day and now()
                preparedStatement.setInt(1, idEncuesta);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Opcion opcion = new Opcion();
                    opcion.setIdEncuesta(resultSet.getInt("id_encuesta"));
                    opcion.setIdOpcion(resultSet.getInt("id_opcion"));
                    opcion.setOpcion(resultSet.getString("opcion"));
                    opcion.setVotos(resultSet.getInt("votos"));
                    result.add(opcion);
                }
                break;

            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
                //throw error
                break;

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
