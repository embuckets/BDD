/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

/**
 *
 * @author emilio
 */
public class PersistenciaFacade {

    public static String logIn(String user, String password) {
        return new LogInController().logIn(user, password);
    }
}
