/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tyaa.java.ejb.tradingactivity.client.console;

import org.tyaa.java.ejb.tradingactivity.lib.model.Sale;

/**
 *
 * @author student
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        for (Sale sale : RMIConnector.getRemoteService().getAllSales()) {
            System.out.println(sale);
        }
        RMIConnector.getRemoteService().addSale(new Sale(0, "MSFT", 1000, 12660, 1, 1));
    }
    
}
