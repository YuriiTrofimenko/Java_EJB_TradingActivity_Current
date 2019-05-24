/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tyaa.java.ejb.tradingactivity.session;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import javax.ejb.EJB;
import org.tyaa.java.ejb.tradingactivity.lib.interfaces.RMIServiceRemote;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.tyaa.java.ejb.tradingactivity.entity.Broker;
import org.tyaa.java.ejb.tradingactivity.entity.Category;
import org.tyaa.java.ejb.tradingactivity.lib.model.Sale;

/**
 *
 * @author student
 */
@Stateless
public class RMIService implements RMIServiceRemote {

    @EJB
    private SaleFacade mSaleFacade;
    
    @EJB
    private BrokerFacade mBrokerFacade;
    
    @EJB
    private CategoryFacade mCategoryFacade;
    
    @Resource(name="jms/TradingActivityPool")
    private ConnectionFactory connectionFactory;
    
    @Resource(name="jms/TradingActivityWebTopic")
    private Destination destination;

    
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
                        , saleEntity.getBrokerId().getId()
                        , saleEntity.getCategoryId().getId()
                );
            }).collect(Collectors.toList());
        }
        return sales;
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public int addSale(Sale sale) {
        int result = 0;
        try {
            org.tyaa.java.ejb.tradingactivity.entity.Sale saleEntity
                = new org.tyaa.java.ejb.tradingactivity.entity.Sale();
            saleEntity.setSecurityName(sale.getSecurityName());
            saleEntity.setPrice(sale.getPrice());
            saleEntity.setQuantity(sale.getQuantity());

            Broker broker = mBrokerFacade.find(sale.getBrokerId());
            Category category = mCategoryFacade.find(sale.getCategoryId());
            saleEntity.setBrokerId(broker);
            saleEntity.setCategoryId(category);
            mSaleFacade.create(saleEntity);
            sendActionString("+1");
            
        } catch (Exception e) {
            result = -1;
        }
        return result;
    }
    
    public void sendActionString(String _actionString) {
        try {
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(destination);
            TextMessage message = session.createTextMessage();
            message.setStringProperty("message_type", "action");
            message.setText(_actionString);
            producer.send(message);
            System.out.println("message sent");
            session.close();
            connection.close();
        } catch (JMSException ex) {
            System.err.println("Sending message error");
            ex.printStackTrace();
        } 
    }
}
