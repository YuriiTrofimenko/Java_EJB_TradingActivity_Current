/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tyaa.java.ejb.tradingactivity.session;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import org.tyaa.java.ejb.tradingactivity.lib.interfaces.RMIServiceRemote;
import javax.ejb.Stateless;
import org.tyaa.java.ejb.tradingactivity.lib.model.Sale;

/**
 *
 * @author student
 */
@Stateless
public class RMIService implements RMIServiceRemote {

    @EJB
    private SaleFacade mSaleFacade;
    
    @Override
    public List<Sale> getAllSales() {
        List<Sale> sales = new ArrayList<>();
        List<org.tyaa.java.ejb.tradingactivity.entity.Sale> saleEntities =
            mSaleFacade.findAll();
        if (saleEntities != null) {
            sales = saleEntities.stream().map((saleEntity) -> {
                
                return new Sale(
                        saleEntity.getId()
                        , saleEntity.getSecurityName()
                        , saleEntity.getQuantity()
                        , saleEntity.getPrice()
                );
            }).collect(Collectors.toList());
        }
        return sales;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
