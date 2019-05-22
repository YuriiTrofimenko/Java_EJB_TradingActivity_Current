/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tyaa.java.ejb.tradingactivity.session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.tyaa.java.ejb.tradingactivity.entity.Broker;

/**
 *
 * @author student
 */
@Stateless
public class BrokerFacade extends AbstractFacade<Broker> {

    @PersistenceContext(unitName = "JavaEJBTradingActivity-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BrokerFacade() {
        super(Broker.class);
    }
    
}
