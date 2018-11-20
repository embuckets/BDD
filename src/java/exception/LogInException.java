/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

/**
 *
 * @author emilio
 */
public class LogInException extends Exception{

    public LogInException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }
    
}
