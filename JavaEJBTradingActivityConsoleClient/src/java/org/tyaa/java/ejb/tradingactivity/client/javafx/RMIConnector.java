/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tyaa.java.ejb.tradingactivity.client.javafx;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.tyaa.java.ejb.tradingactivity.lib.interfaces.RMIServiceRemote;

/**
 *
 * @author student
 */
public class RMIConnector {
    
    private static InitialContext mInitialContext;
    private static RMIServiceRemote mServiceRemote;
    
    static{
        try {
            mInitialContext = new InitialContext();
        } catch (NamingException ex) {
            Logger.getLogger(RMIConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {            
            mServiceRemote =
                    (RMIServiceRemote) mInitialContext.lookup("rmi/RMIService");
        } catch (NamingException ex) {
            Logger.getLogger(RMIConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static RMIServiceRemote getRemoteService(){
        return mServiceRemote;
    }
}
