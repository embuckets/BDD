/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author emilio
 */
public class LocalURL {
    private static String protocol;
    private static String host;
    private static String port;
    private static String database;
    private static String user;
    private static String password;

    static {
        init();
    }
    
    public static String getURL(){
        return protocol + host + ":" +  port + "/" + database + "?user=" + user + "&password=" + password;
    }
    
    
    public static void init() {
        Properties props = new Properties();
        try {
            props.loadFromXML(new FileInputStream(new File("database-local.xml")));
            protocol = props.getProperty("protocol");
            host = props.getProperty("host");
            port = props.getProperty("port");
            database = props.getProperty("database");
            user = props.getProperty("user");
            password = props.getProperty("password");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
