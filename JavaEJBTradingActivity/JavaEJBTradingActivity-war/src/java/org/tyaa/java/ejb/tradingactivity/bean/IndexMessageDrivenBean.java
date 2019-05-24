/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tyaa.java.ejb.tradingactivity.bean;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 *
 * @author student
 */
@MessageDriven(
        mappedName="jms/TradingActivityWebTopic",
        name = "IndexMDB")
public class IndexMessageDrivenBean implements MessageListener{

    @Override
    public void onMessage(Message msg) {
        
        try {
            TextMessage message = (TextMessage)msg;
            //считываем свойство из соответствующего поля, заданное вручную в consumer
            System.out.println("FROM MDB - message type IS " + message.getStringProperty("message_type"));
            //считываем  само сообщение
            System.out.println("FROM MDB - payload  IS " + message.getText());
        } catch (JMSException ex) {
            Logger.getLogger(IndexMessageDrivenBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
