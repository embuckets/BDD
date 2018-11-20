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
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author emilio
 */
public class LocalURL {

    private String protocol;
    private String host;
    private String port;
    private String database;
    private String user;
    private String password;

    public LocalURL() {
        init();
    }

    public String getURL() {
        return protocol + host + ":" + port + "/" + database + "?user=" + user + "&password=" + password;
    }

    public void init() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream input = classLoader.getResourceAsStream("resources/database-local.xml");
// ...
        Properties props = new Properties();
        try {
            props.loadFromXML(input);
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
