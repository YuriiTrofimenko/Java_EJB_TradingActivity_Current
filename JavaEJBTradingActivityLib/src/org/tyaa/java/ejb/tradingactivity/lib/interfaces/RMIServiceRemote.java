/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tyaa.java.ejb.tradingactivity.lib.interfaces;

import java.util.List;
import javax.ejb.Remote;
import org.tyaa.java.ejb.tradingactivity.lib.model.Sale;

/**
 *
 * @author student
 */
@Remote
public interface RMIServiceRemote {

    List<Sale> getAllSales();
    
}
